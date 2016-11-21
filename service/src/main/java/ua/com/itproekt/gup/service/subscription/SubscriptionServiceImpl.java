package ua.com.itproekt.gup.service.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.subscription.SubscriptionRepository;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.OfferModerationReports;
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
    public boolean create(String email, OfferFilterOptions offerFilterOptions) {

        // check if user has the same subscription
       if (ifUserAlreadyHasTheSameSubscription(email, offerFilterOptions)){
            return false;
        }

        Subscription subscription = new Subscription();
        subscription.setEmail(email);
        subscription.setOfferFilterOptions(offerFilterOptions);
        subscription.setOfferFilterOptionsCheckSum(Integer.toString(offerFilterOptions.hashCode()));
        subscription.setLastCheckDateAndCreateDateEqualsToCurrentDate();

        String userId = SecurityOperations.getLoggedUserId();

        if (userId!= null){
            subscription.setId(userId);
        }

        subscriptionRepository.create(subscription);
        return true;
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

        OfferModerationReports offerModerationReports = new OfferModerationReports();
        offerModerationReports.setModerationStatus(ModerationStatus.COMPLETE);

        for (Subscription subscription : subscriptionList) {

            subscription.getOfferFilterOptions().setOfferModerationReports(offerModerationReports);

            subscription.getOfferFilterOptions().setActive(true);

            subscription.getOfferFilterOptions().setId(newOffer.getId());// Добовляем ID текущего ОБ


            // make search among offers with our filterOptions
            // для одной конкретной подписки ищем по фильтру Объявления, и там должно быть лишь одно - наше.
            List<Offer> offerList = offersService.findOffersWihOptions(subscription.getOfferFilterOptions()).getEntities();

            // here we can receive only one offer - our offer< if it is match to the filter
            if (offerList.size() > 0) {
                Map<String, String> resources = new HashMap<>();
                mailSenderService.sendSubscriptionOfferEmail(subscription.getEmail(), offerList.get(0), resources);
            }

            // change sinceDate of the current subscription to time.Now() and update it
            subscription.setLastCheckDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
            subscriptionRepository.findAndUpdate(subscription);


        }
    }


    /**
     * Check if person already has the same subscription
     *
     * @param email                 - the person email.
     * @param offerFilterOptions    - the OfferFilterOption object.
     * @return                      - the true or false.
     */
    private boolean ifUserAlreadyHasTheSameSubscription(String email, OfferFilterOptions offerFilterOptions){

        SubscriptionFilterOptions subscriptionFilterOptions = new SubscriptionFilterOptions();
        subscriptionFilterOptions.setOfferFilterOptionsCheckSum(Integer.toString(offerFilterOptions.hashCode()));

        List<Subscription> subscriptionList = findWithFilterOption(subscriptionFilterOptions);

        for (Subscription subscription : subscriptionList) {
            if (subscription.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }


}
