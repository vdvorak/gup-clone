package ua.com.itproekt.gup.service.emailnotification;

import org.apache.velocity.app.VelocityEngine;
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
import org.springframework.ui.velocity.VelocityEngineUtils;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.profiles.Profile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;


@Service
public class MailSenderServiceImpl implements MailSenderService {

    private static Logger LOG = LoggerFactory.getLogger(MailSenderServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

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

    public EmailServiceTokenModel sendVerificationEmail(final EmailServiceTokenModel emailVerificationModel) {
        Map<String, String> resources = new HashMap<>();
          return sendVerificationEmail(emailVerificationModel, emailVerificationSubjectText,
                  "velocity/VerifyEmail.vm", resources);
    }

    public EmailServiceTokenModel sendRegistrationEmail(final EmailServiceTokenModel emailVerificationModel) {
        Map<String, String> resources = new HashMap<>();
          return sendVerificationEmail(emailVerificationModel, emailRegistrationSubjectText,
                  "velocity/RegistrationEmail.vm", resources);
    }

    public EmailServiceTokenModel sendLostPasswordEmail(final EmailServiceTokenModel emailServiceTokenModel) {
        Map<String, String> resources = new HashMap<>();
         return sendVerificationEmail(emailServiceTokenModel, lostPasswordSubjectText,
                 "velocity/LostPasswordEmail.vm", resources);
    }

    @Override
    public void sendSubscriptionOfferEmail(Profile profile, Offer offer, final Map<String, String> resources) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_RELATED, "UTF-8");
                messageHelper.setTo(profile.getEmail());
                messageHelper.setFrom(emailFromAddress);
                messageHelper.setReplyTo(emailReplyToAddress);
                messageHelper.setSubject("Результаты поиска по подписке с сайта GUP");
                Map<String, Object> model = new HashMap<>();
                model.put("model", offer);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/OfferSubscriptionEmail.vm", "UTF-8", model);
                messageHelper.setText(text, true);

                for(String resourceIdentifier: resources.keySet()) {
                    addInlineResource(messageHelper, resources.get(resourceIdentifier), resourceIdentifier);
                }
            }

        };

     mailSender.send(preparator);


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
                messageHelper.setSubject("Добро пожаловать на GUP");
                Map<String, Object> model = new HashMap<>();
                model.put("model", emailVerificationModel);
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, velocityModel, "UTF-8", model);
                messageHelper.setText(text, true);

                for(String resourceIdentifier: resources.keySet()) {
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
