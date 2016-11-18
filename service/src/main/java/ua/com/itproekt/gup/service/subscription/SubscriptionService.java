package ua.com.itproekt.gup.service.subscription;


import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.subscription.Subscription;
import ua.com.itproekt.gup.model.subscription.filter.SubscriptionFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

/**
 * Service for work with subscriptions.
 *
 * @author Kobylyatskyy Alexander
 */
public interface SubscriptionService {

    /**
     * Create subscription with specific filterOption for user.
     *
     * @param email                 - the email of the user.
     * @param offerFilterOptions    - the OfferFilterOptions object.
     */
    void create(String email, OfferFilterOptions offerFilterOptions);

    /**
     * Find and return one subscriptions by it's ID.
     *
     * @param subscriptionId        - the subscription ID.
     * @return                      - the one subscription.
     */
    Subscription find(String subscriptionId);

    /**
     * Update subscription.
     *
     * @param subscription          - the subscription which must be update.
     * @return                      - the updated subscription.
     */
    Subscription findAndUpdate(Subscription subscription);

    /**
     * Delete one subscription.
     *
     * @param subscriptionId        - the ID of the subscriptions which must be delete.
     * @return                      - the int.
     */
    int delete(String subscriptionId);


    // ToDo переделать на List
    /**
     * Find and return list of Subscriptions relevant to SubscriptionFilterOptions.
     *
     * @param subscriptionFilterOptions - the subscription filter options.
     * @return                          - the List of subscriptions satisfying the filter.
     */
    EntityPage<Subscription> findWithFilterOption(SubscriptionFilterOptions subscriptionFilterOptions);

    /**
     * Check if the offer suit to the any subscriptions and send email to the subscriptions owners.
     *
     * @param offer                 - the offer which must be checked for the subscriptions suitable.
     */
    void checkIfOfferSuiteForSubscriptionAndSendEmail(Offer offer);


}