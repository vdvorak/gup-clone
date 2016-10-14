package ua.com.itproekt.gup.dao.subscription;


import ua.com.itproekt.gup.model.subscription.Subscription;
import ua.com.itproekt.gup.model.subscription.filter.SubscriptionFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

public interface SubscriptionRepository {
    /**
     * @param subscription the subscription
     */
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
     * @return
     */
    EntityPage<Subscription> findAll();
}
