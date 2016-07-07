package ua.com.itproekt.gup.dao.subscription;


import ua.com.itproekt.gup.model.subscription.Subscription;

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
}
