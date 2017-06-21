package ua.com.gup.service;

import ua.com.gup.domain.Offer;
import ua.com.gup.domain.filter.OfferFilter;
import ua.com.gup.domain.subscription.Subscription;


public interface SubscriptionService {

    /**
     * Create subscription with specific filterOption for user.
     *
     * @param email                     - the email of the user.
     * @param offerFilter               - the OfferFilterOptions object.
     * @return                          - true if all is Ok, false - if user has the same subscription already
     *                                  for the current email.
     */
    boolean create(String email, OfferFilter offerFilter);

    /**
     * Find and return one subscriptions by it's ID.
     *
     * @param id                        - the subscription ID.
     * @return                          - the one subscription.
     */
    Subscription findById(String id);

    /**
     * Find and return one subscriptions by it's Email.
     *
     * @param email                     - the subscription Email.
     * @return                          - the one subscription.
     */
    Subscription findByEmail(String email);

    /**
     * Find and return one subscriptions by it's OfferFilter.
     *
     * @param offerFilter               - the subscription OfferFilter.
     * @return                          - the one subscription.
     */
    Subscription findByOfferFilter(OfferFilter offerFilter);

    /**
     * Delete one subscription.
     *
     * @param id                        - the ID of the subscriptions which must be delete.
     * @return                          - the int.
     */
    int deleteById(String id);

    /**
     * Delete one subscription.
     *
     * @param email                     - the Email of the subscriptions which must be delete.
     * @return                          - the int.
     */
    int deleteByEmail(String email);

    /**
     * Check if the offer suit to the any subscriptions and send email to the subscriptions owners.
     *
     * @param offer                     - the offer which must be checked for the subscriptions suitable.
     */
    void checkWithSendEmail(Offer offer);

}
