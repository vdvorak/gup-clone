package ua.com.itproekt.gup.dao.offers;


import org.springframework.data.mongodb.core.MongoTemplate;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.Set;

/**
 * Interface for working with offers
 * @author Kobylyatskyy Alexander
 */
public interface OfferRepository {
    void create(Offer offer);

    /**
     * Find and return one offer by it's ID.
     *
     * @param offerId   - the offer ID.
     * @return          - one offer.
     */
    Offer findById(String offerId);

    /**
     * Find and return one offer by it's SEO key.
     *
     * @param seoKey    - the SEO key string.
     * @return          - one offer.
     */
    Offer findBySeoKey(String seoKey);

    /**
     * Update offer which is already exist.
     *
     * @param offer     - the offer object which must be updated.
     * @return          - updated offer.
     */
    Offer findAndUpdate(Offer offer);

    /**
     * Delete one document by it's ID
     *
     * @param offerId   - the offer ID which must be deleted.
     * @return          - the number of deleted document.
     */
    int delete(String offerId);

    /**
     * Check if offer with specific ID is exist.
     *
     * @param offerId   - the offer id that must be checked.
     * @return          - true or false.
     */
    boolean offerExists(String offerId);

    /**
     *
     * @param offerFilterOptions    - the OfferFilterOptions object contain options from filter.
     * @return                      - the EntityPage object which contain
     *                              list of found offers which relevant to filterOption object.
     */
    EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions);

    /**
     * @param offerFilterOptions
     * @param excludeOfferId
     * @return
     */
    EntityPage<Offer> findOffersWithOptionsAndExcludes(OfferFilterOptions offerFilterOptions, String excludeOfferId);

    /**
     *
     * @param offerId
     */
    void deleteReservation(String offerId);

    /**
     *
     * @param offerId
     * @param rentedOfferPeriodInfo
     */
    void rentOffer(String offerId, RentedOfferPeriodInfo rentedOfferPeriodInfo);

    /**
     * Increase offer's filed "views" by 1.
     *
     * @param offerId   - the offer ID.
     */
    void incViewsAtOne(String offerId);

    /**
     *
     * @param offerId   - the offer ID
     * @param rentId    - the ID of rent object.
     */
    void deleteRent(String offerId, String rentId);

    /**
     *
     * @param name
     * @return
     */
    Set<String> getMatchedNames(String name);

    /**
     * Method for testing offerRepository.
     *
     * @param mongoTemplateInstanceForTests - the Mongotemplate object.
     */
    void setMongoTemplateInstanceForTests(MongoTemplate mongoTemplateInstanceForTests);
}