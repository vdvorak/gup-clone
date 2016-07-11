package ua.com.itproekt.gup.service.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.subscription.SubscriptionRepository;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.subscription.Subscription;
import ua.com.itproekt.gup.model.subscription.filter.SubscriptionFilterOptions;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.util.EntityPage;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    OffersService offersService;


    @Override
    public void create(String userId, OfferFilterOptions offerFilterOptions) {
        Subscription newSubscription = new Subscription(userId, offerFilterOptions).setSinceDateAndCreateDateEqualsToCurrentDate();
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


        EntityPage<Subscription> subscriptionEntityPage = subscriptionRepository.findAll();
        List<Subscription> subscriptionList = subscriptionEntityPage.getEntities();

        EntityPage<Offer> offerEntityPage;

        for (Subscription subscription : subscriptionList) {
            subscription.getOfferFilterOptions().setLastModerationDate(newOfferLastModerationDate);

            // make search among offers with our filterOptions
            List<Offer> offerList = offersService.findOffersWihOptions(subscription.getOfferFilterOptions()).getEntities();
            if (offerList.size() > 0) {
                // go through results and send them for user email

                for (Offer offer : offerList) {
                    System.err.println("Result for subscription finding: " + offer.toString());
                }
            }

            // change sinceDate of the current subscription to time.Now() and update it
            subscription.setSinceDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
            subscriptionRepository.findAndUpdate(subscription);


        }

    }
}
