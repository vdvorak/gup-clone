package ua.com.gup.service.emailnotification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ua.com.gup.mongo.composition.domain.email.EmailMessage;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.composition.domain.verification.VerificationToken;
import ua.com.gup.mongo.model.enumeration.EmailType;
import ua.com.gup.repository.email.EmailRepository;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.service.profile.VerificationTokenService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the mail service.
 *
 * @author Kobylyatskyy Alexander
 */
@Service
public class EmailServiceImpl implements EmailService {

    private static Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private ProfilesService profilesService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Value("${email.services.emailVerificationSubjectText}")
    private String emailVerificationSubjectText;

    @Value("${email.services.emailRegistrationSubjectText}")
    private String emailRegistrationSubjectText;

    @Value("${email.services.lostPasswordSubjectText}")
    private String lostPasswordSubjectText;

    @Value("${email.services.fromAddress}")
    private String emailFromAddress;

    @Value("${email.services.replyToAddress}")
    private String emailReplyToAddress;


    @Value("${email.services.register.confirm.url}")
    private String registerConfirmUrl;

    @Value("${email.services.reset.password.url}")
    private String resetPasswordUrl;


    @Override
    public EmailMessage findOneMessage() {
        return emailRepository.findOneMessage();
    }

    @Override
    public void removeMessage(EmailMessage message) {
        emailRepository.removeMessage(message);
    }

    @Override
    public EmailMessage updateLastAttemptTimestamp(EmailMessage message) {
        return emailRepository.updateLastAttemptTimestamp(message);
    }

    @Override
    public EmailMessage prepareMessageAndAddToQueue(final Profile profile, EmailType emailType) {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setLastAttemptTimestamp(new Date().getTime());
        emailMessage.setReplyTo(emailReplyToAddress);
        emailMessage.setFrom(emailFromAddress);
        emailMessage.setRecipients(new String[]{profile.getEmail()});
        emailMessage.setUserId(profile.getId());
        emailMessage.setEmailType(emailType);
        emailRepository.createMessage(emailMessage);
        return emailMessage;
    }

    /**
     * TODO: After getting all templates and requirements, rewrite this piece of sh... (sendMail method)
     *
     * @param message
     * @throws Exception
     */
    @Override
    public void sendEmail(EmailMessage message) throws Exception {
        // Prepare the evaluation context for Thymeleaf
        final Context ctx = new Context();
        Profile profile = profilesService.findById(message.getUserId());
        String templateContent = null;
        VerificationToken verificationToken = null;
        String mailSubject = "No theme";

        switch (message.getEmailType()) {
            case EMAIL_REGISTRATION:
                verificationToken = verificationTokenService.generateEmailRegistrationToken(message.getUserId());
                ctx.setVariable("confirmRegisterUrl", registerConfirmUrl);
                ctx.setVariable("confirmRegisterToken", verificationToken.getToken());
                templateContent = templateEngine.process("mail/registered.html", ctx);
                mailSubject = emailRegistrationSubjectText;
                break;
            case EMAIL_FORGET_PASSWORD:
                verificationToken = verificationTokenService.generateForgetPasswordToken(message.getUserId());
                ctx.setVariable("userName", profile.getUsername());
                ctx.setVariable("resetPasswordUrl", resetPasswordUrl);
                ctx.setVariable("resetPasswordToken", verificationToken.getToken());
                templateContent = templateEngine.process("mail/password-reset.html", ctx);
                mailSubject = emailRegistrationSubjectText;
                break;
        }


        String finalTemplateContent = templateContent;
        String finalMailSubject = mailSubject;
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_RELATED, "UTF-8");
                messageHelper.setTo(message.getRecipients());
                messageHelper.setFrom(message.getFrom());
                messageHelper.setReplyTo(message.getReplyTo());
                messageHelper.setSubject(finalMailSubject);
                messageHelper.setText(finalTemplateContent, true);
            }
        };
        this.mailSender.send(preparator);
        this.verificationTokenService.saveToken(verificationToken);
    }

    @Override
    public EmailServiceTokenModel sendVerificationEmail(final EmailServiceTokenModel emailVerificationModel) {
        Map<String, String> resources = new HashMap<>();
        return sendVerificationEmail(emailVerificationModel, emailVerificationSubjectText,
                "velocity/VerifyEmail.vm", resources);
    }

    @Override
    public EmailServiceTokenModel sendRegistrationEmail(final EmailServiceTokenModel emailVerificationModel) {
        Map<String, String> resources = new HashMap<>();
        return sendVerificationEmail(emailVerificationModel, emailRegistrationSubjectText,
                "velocity/RegistrationEmail.vm", resources);
    }

    @Override
    public EmailServiceTokenModel sendLostPasswordEmail(final EmailServiceTokenModel emailServiceTokenModel) {
        Map<String, String> resources = new HashMap<>();
        return sendVerificationEmail(emailServiceTokenModel, lostPasswordSubjectText,
                "velocity/NewLostPasswordEmail.vm", resources);
    }

    @Override
    public void sendSubscriptionOfferEmail(String subscriptionId, String email, Offer offer, final Map<String, String> resources) {
        throw new RuntimeException("Not implemented yet");
    }


    private void addInlineResource(MimeMessageHelper messageHelper, String resourcePath,
                                   String resourceIdentifier) throws MessagingException {
        Resource resource = new ClassPathResource(resourcePath);
        messageHelper.addInline(resourceIdentifier, resource);
    }


    //TODO: fix subject encoding (cp1251 -> UTF-8)
    private EmailServiceTokenModel sendVerificationEmail(final EmailServiceTokenModel emailVerificationModel,
                                                         final String emailSubject,
                                                         final String velocityModel,
                                                         final Map<String, String> resources) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_RELATED, "UTF-8");
                messageHelper.setTo(emailVerificationModel.getEmail());
                messageHelper.setFrom(emailFromAddress);
                messageHelper.setReplyTo(emailReplyToAddress);
                messageHelper.setSubject(emailSubject);
//                Map<String, Object> model = new HashMap<>();
//                model.put("model", emailVerificationModel);
//                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, velocityModel, "UTF-8", model);
                StringBuilder text = new StringBuilder(emailVerificationModel.getHostNameUrl());
                text.append(String.format("/swagger/oauth/registerConfirm?token=%s", emailVerificationModel.getEncodedToken()));
                messageHelper.setText(text.toString(), true);
                for (String resourceIdentifier : resources.keySet()) {
                    addInlineResource(messageHelper, resources.get(resourceIdentifier), resourceIdentifier);
                }
            }
        };
        LOG.debug("Sending {} token to : {}", emailVerificationModel.getTokenType().toString(), emailVerificationModel.getEmail());
        this.mailSender.send(preparator);
        return emailVerificationModel;
    }

    public void setEmailVerificationSubjectText(String emailVerificationSubjectText) {
        this.emailVerificationSubjectText = emailVerificationSubjectText;
    }

    public void setEmailRegistrationSubjectText(String emailRegistrationSubjectText) {
        this.emailRegistrationSubjectText = emailRegistrationSubjectText;
    }

    public void setLostPasswordSubjectText(String lostPasswordSubjectText) {
        this.lostPasswordSubjectText = lostPasswordSubjectText;
    }

    public void setEmailFromAddress(String emailFromAddress) {
        this.emailFromAddress = emailFromAddress;
    }

    public void setEmailReplyToAddress(String emailReplyToAddress) {
        this.emailReplyToAddress = emailReplyToAddress;
    }


}
