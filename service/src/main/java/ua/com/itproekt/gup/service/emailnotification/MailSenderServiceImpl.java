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
import ua.com.itproekt.gup.dto.SubscribeOfferEmail;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the mail service.
 *
 * @author Kobylyatskyy Alexander
 */
@Service
public class MailSenderServiceImpl implements MailSenderService {

    private static Logger LOG = LoggerFactory.getLogger(MailSenderServiceImpl.class);

    @Autowired
    private ProfilesService profilesService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    private final String deleteThisSubscribeLInk = "http://gup.com.ua:8184/api/rest/subscription/delete/";

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
    public void sendSubscriptionOfferEmail(String subscriptionId,String email, Offer offer, final Map<String, String> resources) {


        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                        MimeMessageHelper.MULTIPART_MODE_RELATED, "UTF-8");
                messageHelper.setTo(email);
                messageHelper.setFrom(emailFromAddress);
                messageHelper.setReplyTo(emailReplyToAddress);
                messageHelper.setSubject("Результаты поиска по подписке с сайта GUP");
                Map<String, Object> model = new HashMap<>();
                model.put("model", subscribeEmailBodyPreparator(subscriptionId, email, offer));
                String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/OfferSubscriptionEmail.vm", "UTF-8", model);
                messageHelper.setText(text, true);

                for(String resourceIdentifier: resources.keySet()) {
                    addInlineResource(messageHelper, resources.get(resourceIdentifier), resourceIdentifier);
                }
            }
        };

     mailSender.send(preparator);
    }


    /**
     *
     * @param subscriptionId
     * @param email
     * @param offer
     * @return
     */
    private SubscribeOfferEmail subscribeEmailBodyPreparator(String subscriptionId, String email, Offer offer){
        SubscribeOfferEmail subscribeOfferEmail = new SubscribeOfferEmail();

        Profile profile = profilesService.findProfileByEmail(email);

        if (profile!=null && profile.getUsername()!=null){
            subscribeOfferEmail.setUserName(profile.getUsername());
        }else{
            subscribeOfferEmail.setUserName("Уважаемый пользователь портала GUP");
        }

        subscribeOfferEmail.setOffer(offer);
        subscribeOfferEmail.setDeleteThisSubscribeLink(deleteThisSubscribeLInk+subscriptionId);


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
