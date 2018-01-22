package ua.com.gup.service;


import ua.com.gup.mongo.composition.domain.email.EmailMessage;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.enumeration.EmailType;
import ua.com.gup.mongo.model.offer.EmailStatus;
import ua.com.gup.service.model.EmailServiceTokenModel;

import java.util.List;
import java.util.Map;

/**
 * Service for sending email.
 *
 * @author Kobylyatskyy Alexander
 */
public interface EmailService {


    List<EmailMessage> findAndModifyMessages(EmailStatus oldStatus, EmailStatus newStatus, int limit);

    void sendEmail(EmailMessage message) throws Exception;

    void removeMessage(EmailMessage message);

    EmailMessage updateStatusAndLastAttemptTimestamp(EmailMessage message);

    EmailMessage prepareMessageAndAddToQueue(Profile profile, EmailType emailType);

    /**
     * Send email for the verification.
     *
     * @param emailServiceTokenModel - the EmailServiceTokenModel object.
     * @return - the EmailServiceTokenModel object.
     */
    @Deprecated
    EmailServiceTokenModel sendVerificationEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     * Send registration email
     *
     * @param emailServiceTokenModel - the EmailServiceTokenModel object.
     * @return - the EmailServiceTokenModel object.
     */
    @Deprecated
    EmailServiceTokenModel sendRegistrationEmail(EmailServiceTokenModel emailServiceTokenModel);

    /**
     * Sen email with lost password.
     *
     * @param emailServiceTokenModel - the EmailServiceTokenModel object.
     * @return - the EmailServiceTokenModel object.
     */
    @Deprecated
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
