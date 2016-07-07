package ua.com.itproekt.gup.service.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.subscription.SubscriptionRepository;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.subscription.Subscription;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;


    @Override
    public void create(String userId, OfferFilterOptions offerFilterOptions) {
        Subscription newSubscription = new Subscription(userId, offerFilterOptions).setSinceDateEqualsToCurrentDate();
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
}
