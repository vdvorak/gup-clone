package ua.com.itproekt.gup.dao.offers;


import org.springframework.data.mongodb.core.MongoTemplate;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;
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
     * Find offers based on filter options.
     *
     * @param offerFilterOptions    - the OfferFilterOptions object contain options from filter.
     * @return                      - the EntityPage object which contain
     *                              list of found offers which relevant to filterOption object.
     */
    EntityPage<Offer> findOffersWithOptions(OfferFilterOptions offerFilterOptions);

    /**
     * Find offers based on filter options without excluded one.
     *
     * @param offerFilterOptions    - the OfferFilter object
     * @param excludeOfferId        - the offer ID which must be exclude from result set.
     * @return                      - the EntityPage object which contain
     *                              list of found offers which relevant to filterOption object.
     */
    EntityPage<Offer> findOffersWithOptionsAndExcludes(OfferFilterOptions offerFilterOptions, String excludeOfferId);


    /**
     *  Find offers based on filter options with excluded some offers.
     *
     * @param offerFilterOptions    - the Offer filter options.
     * @param excludeOffersId       - the id's of the offers which must be excluded from result list.
     * @return                      - the entityPage object with offers inside.
     */
    EntityPage<Offer> findOffersWithOptionsAndExcludes(OfferFilterOptions offerFilterOptions, List<String> excludeOffersId);


    /**
     * Delete reservation information
     *
     * @param offerId   - the offer ID
     */
    void deleteReservation(String offerId);

    /**
     * Create Rent object for specific Offer.
     *
     * @param offerId               - the offer ID which must be rented.
     * @param rentedOfferPeriodInfo - the rented period.
     */
    void rentOffer(String offerId, RentedOfferPeriodInfo rentedOfferPeriodInfo);

    /**
     * Increase offer's filed "views" by 1.
     *
     * @param offerId   - the offer ID.
     */
    void incViewsAtOne(String offerId);

    void incPhoneViewsAtOne(String offerId);

    /**
     * Delete specific Rent object by it's ID.
     *
     * @param offerId   - the offer ID.
     * @param rentId    - the ID of rent object.
     */
    void deleteRent(String offerId, String rentId);

    /**
     * Return collection of matched names in String.
     * @param name  - the name which must.
     * @return      - collection of matched names in String.
     */
    Set<String> getMatchedNames(String name);


    //FixMe smth really strange
    //For tests
    /**
     * Method for testing offerRepository.
     *
     * @param mongoTemplateInstanceForTests - the Mongotemplate object.
     */
    void setMongoTemplateInstanceForTests(MongoTemplate mongoTemplateInstanceForTests);
}