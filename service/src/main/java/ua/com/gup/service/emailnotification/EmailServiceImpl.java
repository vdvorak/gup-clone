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
import ua.com.gup.domain.email.EmailMessage;
import ua.com.gup.domain.offer.Offer;
import ua.com.gup.dto.SubscribeOfferEmail;
import ua.com.gup.model.profiles.Profile;
import ua.com.gup.model.profiles.verification.VerificationToken;
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
    private VerificationTokenService verificationTokenService;
    private final String deleteThisSubscribeLInk = "http://gup.com.ua:8184/swagger/rest/subscription/delete/";

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


    @Value("${hostName.url}")
    private String hostNameUrl;

//    emailService.sendRegistrationEmail(new EmailServiceTokenModel(profile.getEmail(), token, hostNameUrl));


    @Override
    public EmailMessage findOneMessage() {
        return emailRepository.findOneMessage();
    }

    @Override
    public void removeMessage(EmailMessage message) {
        emailRepository.removeMessage(message);
    }

    @Override
    public EmailMessage prepareRegistrationMessageAndAddToQueue(final Profile profile) {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setLastAttemptTimestamp(new Date().getTime());
        emailMessage.setText(emailVerificationSubjectText);
        emailMessage.setSubject(emailRegistrationSubjectText);
        emailMessage.setReplyTo(emailReplyToAddress);
        emailMessage.setFrom(emailFromAddress);
        emailMessage.setRecipients(new String[]{profile.getEmail()});
        emailMessage.setUserId(profile.getId());
        emailRepository.createMessage(emailMessage);
        return emailMessage;
    }


    @Override
    public void sendEmail(EmailMessage message) {
        VerificationToken verificationToken = verificationTokenService.generateEmailRegistrationToken(message.getUserId());
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_RELATED, "UTF-8");
                messageHelper.setTo(message.getRecipients());
                messageHelper.setFrom(message.getFrom());
                messageHelper.setReplyTo(message.getReplyTo());
                messageHelper.setSubject(message.getSubject());

                StringBuilder text = new StringBuilder(hostNameUrl);
                text.append(String.format("swagger/oauth/registerConfirm?token=%s", verificationToken.getToken()));
                messageHelper.setText(text.toString(), true);
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
//        MimeMessagePreparator preparator = new MimeMessagePreparator() {
//            public void prepare(MimeMessage mimeMessage) throws Exception {
//                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
//                        MimeMessageHelper.MULTIPART_MODE_RELATED, "UTF-8");
//                messageHelper.setTo(email);
//                messageHelper.setFrom(emailFromAddress);
//                messageHelper.setReplyTo(emailReplyToAddress);
//                messageHelper.setSubject("Результаты поиска по подписке с сайта GUP");
//                Map<String, Object> model = new HashMap<>();
//                model.put("model", subscribeEmailBodyPreparator(subscriptionId, email, offer));
//                String text = "";//VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/OfferSubscriptionEmail.vm", "UTF-8", model);
//                messageHelper.setText(text, true);
//
//                for (String resourceIdentifier : resources.keySet()) {
//                    addInlineResource(messageHelper, resources.get(resourceIdentifier), resourceIdentifier);
//                }
//            }
//        };
//
//        mailSender.send(preparator);
    }


    /**
     * Prepare email for body.
     *
     * @param subscriptionId - the subscription ID.
     * @param email          - the email of the addressee.
     * @param offer          - the Offer object.
     * @return - the SubscribeOfferEmail object.
     */
    private SubscribeOfferEmail subscribeEmailBodyPreparator(String subscriptionId, String email, Offer offer) {
        SubscribeOfferEmail subscribeOfferEmail = new SubscribeOfferEmail();

        Profile profile = profilesService.findProfileByEmail(email);

        if (profile != null && profile.getUsername() != null) {
            subscribeOfferEmail.setUserName(profile.getUsername());
        } else {
            subscribeOfferEmail.setUserName("Уважаемый пользователь портала GUP");
        }

        subscribeOfferEmail.setOffer(offer);
        subscribeOfferEmail.setDeleteThisSubscribeLink(deleteThisSubscribeLInk + subscriptionId);


        return subscribeOfferEmail;
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
