package ua.com.itproekt.gup.service.emailnotification;


import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.profiles.Profile;

import java.util.Map;

public interface MailSenderService {

    EmailServiceTokenModel sendVerificationEmail(EmailServiceTokenModel emailServiceTokenModel);

    EmailServiceTokenModel sendRegistrationEmail(EmailServiceTokenModel emailServiceTokenModel);

    EmailServiceTokenModel sendLostPasswordEmail(EmailServiceTokenModel emailServiceTokenModel);

    void sendSubscriptionOfferEmail(Profile profile, Offer offer, Map<String, String> resources);
}
