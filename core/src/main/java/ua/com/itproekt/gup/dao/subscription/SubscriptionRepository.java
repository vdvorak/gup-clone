package ua.com.itproekt.gup.dao.subscription;


import ua.com.itproekt.gup.model.subscription.Subscription;
import ua.com.itproekt.gup.model.subscription.filter.SubscriptionFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;

/**
 * @author Kobylatskyy Alexander
 */
public interface SubscriptionRepository {
    /**
     * Create subscription.
     *
     * @param subscription - the subscription.
     */
    void create(Subscription subscription);

    /**
     * Find and return specific subscription by it's ID.
     *
     * @param subscriptionId            - the subscription ID.
     * @return                          - the one subscription.
     */
    Subscription find(String subscriptionId);

    /**
     * Update one specific subscription.
     *
     * @param subscription              - the subscription.
     * @return                          - the updated subscription.
     */
    Subscription findAndUpdate(Subscription subscription);

    /**
     * Delete one specific subscription by it ID.
     *
     * @param subscriptionId            - the subscription's ID.
     * @return                          - the int.
     */
    int delete(String subscriptionId);

    /**
     * Return list of subscriptions.
     *
     * @param subscriptionFilterOptions - the subscription filter options.
     * @return                          - the list of subscriptions satisfying the filter.
     */
   List<Subscription> findWithFilterOption(SubscriptionFilterOptions subscriptionFilterOptions);

    /**
     * Return all subscriptions.
     *
     * @return                          - the list of all subscriptions.
     */
    List<Subscription> findAll();
}
