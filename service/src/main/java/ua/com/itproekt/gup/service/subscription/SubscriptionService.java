package ua.com.itproekt.gup.service.subscription;


import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.subscription.Subscription;
import ua.com.itproekt.gup.model.subscription.filter.SubscriptionFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

public interface SubscriptionService {
    /**
     * @param userId             userId
     * @param offerFilterOptions the offerFilterOptions
     */
    void create(String userId, OfferFilterOptions offerFilterOptions);

    void create(Subscription subscription);

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

    /**
     * @param subscriptionFilterOptions subscription filter options
     * @return List of subscriptions satisfying the filter
     */
    EntityPage<Subscription> findWithFilterOption(SubscriptionFilterOptions subscriptionFilterOptions);

    /**
     *
     */
    void checkIfOfferSuiteForSubscriptionAndSendEmail(Offer offer);


}