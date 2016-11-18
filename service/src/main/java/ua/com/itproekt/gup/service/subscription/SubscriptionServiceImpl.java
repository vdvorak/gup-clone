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
import ua.com.itproekt.gup.util.SecurityOperations;

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
    public void create(String email, OfferFilterOptions offerFilterOptions) {
        Subscription subscription = new Subscription();
        subscription.setEmail(email);
        subscription.setOfferFilterOptions(offerFilterOptions);
        subscription.setLastCheckDateAndCreateDateEqualsToCurrentDate();

        String userId = SecurityOperations.getLoggedUserId();

        if (userId!= null){
            subscription.setId(userId);
        }

        subscriptionRepository.create(subscription);
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
    public List<Subscription> findWithFilterOption(SubscriptionFilterOptions subscriptionFilterOptions) {
        return subscriptionRepository.findWithFilterOption(subscriptionFilterOptions);
    }

    @Override
    public void checkIfOfferSuiteForSubscriptionAndSendEmail(Offer newOffer) {
//        Long newOfferLastModerationDate = newOffer.getLastModerationDate();

        List<Subscription> subscriptionList = subscriptionRepository.findAll();


        Profile profile;

        for (Subscription subscription : subscriptionList) {

            subscription.getOfferFilterOptions()
                    .getOfferModerationReports()
                    .setModerationStatus(ModerationStatus.COMPLETE);
            subscription.getOfferFilterOptions().setActive(true);


//                    .setLastModerationDate(newOfferLastModerationDate)



            // make search among offers with our filterOptions
            List<Offer> offerList = offersService.findOffersWihOptions(subscription.getOfferFilterOptions()).getEntities();


            if (offerList.size() > 0) {
                // go through results and send them for user email

                profile = profilesService.findWholeProfileById(subscription.getEmail());

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
