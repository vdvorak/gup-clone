package ua.com.itproekt.gup.service.subscription;


import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.subscription.Subscription;

public interface SubscriptionService {
    /**
     * @param userId       userId
     * @param offerFilterOptions the offerFilterOptions
     */
    void create(String userId, OfferFilterOptions offerFilterOptions);

    /**
     * @param subscriptionId the subscription ID
     * @return one subscription
     */
    Subscription find(String subscriptionId);

    /**
     * @param subscription the subscription
     * @return the updated subscription
     */
    Subscription findAndUpdate(Subscription subscription);

    /**
     * @param subscriptionId the subscription
     * @return the int
     */
    int delete(String subscriptionId);
}
