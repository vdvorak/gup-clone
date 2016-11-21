package ua.com.itproekt.gup.service.emailnotification;


import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.profiles.Profile;

import java.util.Map;

/**
 * Service for sending email.
 *
 * @author Kobylyatskyy Alexander
 */
public interface MailSenderService {

    /**
     * Send email for the verification.
     *
     * @param emailServiceTokenModel    - the EmailServiceTokenModel object.
     * @return                          - the EmailServiceTokenModel object.
     */
    EmailServiceTokenModel sendVerificationEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     * Send registration email
     *
     * @param emailServiceTokenModel    - the EmailServiceTokenModel object.
     * @return                          - the EmailServiceTokenModel object.
     */
    EmailServiceTokenModel sendRegistrationEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     *Sen email with lost password.
     *
     * @param emailServiceTokenModel    - the EmailServiceTokenModel object.
     * @return                          - the EmailServiceTokenModel object.
     */
    EmailServiceTokenModel sendLostPasswordEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     * Send email to the subscription.
     *
     * @param email                     - the email of the addressee.
     * @param offer                     - the Offer object.
     * @param resources                 - the resource.
     */
    void sendSubscriptionOfferEmail(String email, Offer offer, Map<String, String> resources);
}
