package ua.com.itproekt.gup.service.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.subscription.SubscriptionRepository;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.subscription.Subscription;
import ua.com.itproekt.gup.model.subscription.filter.SubscriptionFilterOptions;
import ua.com.itproekt.gup.service.emailnotification.MailSenderService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.EntityPage;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    OffersService offersService;

    @Autowired
    MailSenderService mailSenderService;

    @Autowired
    ProfilesService profilesService;


    @Override
    public void create(String userId, OfferFilterOptions offerFilterOptions) {
        Subscription newSubscription = new Subscription(userId, offerFilterOptions).setLastCheckDateAndCreateDateEqualsToCurrentDate();
        subscriptionRepository.create(newSubscription);
    }

    @Override
    public Subscription find(String subscriptionId) {
        return subscriptionRepository.find(subscriptionId);
    }

    @Override
    public Subscription findAndUpdate(Subscription subscription) {
        return subscriptionRepository.findAndUpdate(subscription);
    }

    @Override
    public int delete(String subscriptionId) {
        return subscriptionRepository.delete(subscriptionId);
    }

    @Override
    public EntityPage<Subscription> findWithFilterOption(SubscriptionFilterOptions subscriptionFilterOptions) {
        return subscriptionRepository.findWithFilterOption(subscriptionFilterOptions);
    }

    @Override
    public void checkIfOfferSuiteForSubscriptionAndSendEmail(Offer newOffer) {
        Long newOfferLastModerationDate = newOffer.getLastModerationDate();

        List<Subscription> subscriptionList = subscriptionRepository.findAll().getEntities();


        Profile profile;

        for (Subscription subscription : subscriptionList) {
            subscription.getOfferFilterOptions().setLastModerationDate(newOfferLastModerationDate);


            subscription.getOfferFilterOptions()
                    .setModerationStatus(ModerationStatus.COMPLETE)
                    .setActive(true);


            // make search among offers with our filterOptions
            List<Offer> offerList = offersService.findOffersWihOptions(subscription.getOfferFilterOptions()).getEntities();


            profile = profilesService.findWholeProfileById(subscription.getUserId());

            if (offerList.size() > 0) {
                // go through results and send them for user email

                for (Offer offer : offerList) {
                    Map<String, String> resources = new HashMap<>();
                    mailSenderService.sendSubscriptionOfferEmail(profile, offer, resources);
                }
            }

            // change sinceDate of the current subscription to time.Now() and update it
            subscription.setLastCheckDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
            subscriptionRepository.findAndUpdate(subscription);


        }

    }
}
