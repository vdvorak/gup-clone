package ua.com.gup.service.emailnotification;


import ua.com.gup.domain.Offer;
import ua.com.gup.model.email.EmailMessage;
import ua.com.gup.model.profiles.Profile;

import java.util.Map;

/**
 * Service for sending email.
 *
 * @author Kobylyatskyy Alexander
 */
public interface EmailService {

    EmailMessage findOneMessage();

    void sendEmail(EmailMessage message);


    void removeMessage(EmailMessage message);

    EmailMessage prepareRegistrationMessageAndAddToQueue(Profile profile);

    /**
     * Send email for the verification.
     *
     * @param emailServiceTokenModel - the EmailServiceTokenModel object.
     * @return - the EmailServiceTokenModel object.
     */
    EmailServiceTokenModel sendVerificationEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     * Send registration email
     *
     * @param emailServiceTokenModel - the EmailServiceTokenModel object.
     * @return - the EmailServiceTokenModel object.
     */
    EmailServiceTokenModel sendRegistrationEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     * Sen email with lost password.
     *
     * @param emailServiceTokenModel - the EmailServiceTokenModel object.
     * @return - the EmailServiceTokenModel object.
     */
    EmailServiceTokenModel sendLostPasswordEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     * Send email to the subscription.
     *
     * @param subscriptionId - the ID of the subscription.
     * @param email          - the email of the addressee.
     * @param offer          - the Offer object.
     * @param resources      - the resource.
     */
    void sendSubscriptionOfferEmail(String subscriptionId, String email, Offer offer, Map<String, String> resources);
}
