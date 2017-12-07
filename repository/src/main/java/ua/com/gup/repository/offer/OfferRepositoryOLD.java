package ua.com.gup.repository.offer;


import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.filter.OfferFilterOptions;
import ua.com.gup.mongo.model.other.EntityPage;

import java.util.Collection;
import java.util.Set;

/**
 * Interface for working with offers
 *
 * @author Kobylyatskyy Alexander
 */
public interface OfferRepositoryOLD {
    void create(Offer offer);

    /**
     * Find and return one offer by it's ID.
     *
     * @param offerId - the offer ID.
     * @return - one offer.
     */
    Offer findById(String offerId);

    /**
     * Find and return one offer by it's SEO key.
     *
     * @param seoKey - the SEO key string.
     * @return - one offer.
     */
    Offer findBySeoKey(String seoKey);


    /**
     * Find and return one offer by it's SEO url.
     *
     * @param seoUrl - the SEO url string.
     * @return - one offer.
     */
    Offer findBySeoUrl(String seoUrl);

    /**
     * Update offer which is already exist.
     *
     * @param offer - the offer object which must be updated.
     * @return - updated offer.
     */
    Offer findAndUpdate(Offer offer);

    /**
     * Delete one document by it's ID
     *
     * @param offerId - the offer ID which must be deleted.
     * @return - the number of deleted document.
     */
    int delete(String offerId);

    /**
     * Check if offer with specific ID is exist.
     *
     * @param offerId - the offer id that must be checked.
     * @return - true or false.
     */
    boolean offerExists(String offerId);

    /**
     * Find offers based on filter options.
     *
     * @param offerFilterOptions - the OfferFilterOptions object contain options from filter.
     * @return - the EntityPage object which contain
     * list of found offers which relevant to filterOption object.
     */
    EntityPage<Offer> findOffersWithOptions(OfferFilterOptions offerFilterOptions);

    /**
     * Find offers based on filter options without excluded one.
     *
     * @param offerFilterOptions - the OfferFilter object
     * @param excludeOfferId     - the offer ID which must be exclude from result set.
     * @return - the EntityPage object which contain
     * list of found offers which relevant to filterOption object.
     */
    EntityPage<Offer> findOffersWithOptionsAndExcludes(OfferFilterOptions offerFilterOptions, String excludeOfferId);


    /**
     * Find offers based on filter options with excluded some offers.
     *
     * @param offerFilterOptions - the Offer filter options.
     * @param excludeOffersId    - the id's of the offers which must be excluded from result list.
     * @return - the entityPage object with offers inside.
     */
    EntityPage<Offer> findOffersWithOptionsAndExcludes(OfferFilterOptions offerFilterOptions, Collection<String> excludeOffersId);


    /**
     * Delete reservation information
     *
     * @param offerId - the offer ID
     */
    void deleteReservation(String offerId);

}