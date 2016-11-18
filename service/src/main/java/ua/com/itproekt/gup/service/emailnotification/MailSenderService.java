package ua.com.itproekt.gup.service.emailnotification;


import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.profiles.Profile;

import java.util.Map;

public interface MailSenderService {

    /**
     *
     * @param emailServiceTokenModel
     * @return
     */
    EmailServiceTokenModel sendVerificationEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     *
     * @param emailServiceTokenModel
     * @return
     */
    EmailServiceTokenModel sendRegistrationEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     *
     * @param emailServiceTokenModel
     * @return
     */
    EmailServiceTokenModel sendLostPasswordEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     *
     * @param email
     * @param offer
     * @param resources
     */
    void sendSubscriptionOfferEmail(String email, Offer offer, Map<String, String> resources);
}
