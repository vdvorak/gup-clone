package ua.com.itproekt.gup.service.offers;

import ua.com.itproekt.gup.dto.OfferInfo;
import ua.com.itproekt.gup.dto.OfferRegistration;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.itproekt.gup.model.offer.Reservation;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;
import java.util.Set;

public interface OffersService {
    /**
     * Create one offer and return it.
     *
     * @param offer offer object
     */
    void create(Offer offer);

    /**
     * Create offer with registration. For those case where use is not authorized.
     *
     * @param offerRegistration Offer Registration class object
     */
    void createWithRegistration(OfferRegistration offerRegistration);

    /**
     * Return one offer by it's id.
     *
     * @param offerId offer id
     * @return offer object
     */
    Offer findById(String offerId);

    Offer findBySeoKey(String seoKey);

    /**
     * Methods receive seoUrl as string. Return offer and increase it numbers of views.
     *
     * @param seoUrl Seo URL
     * @return Offer object
     */
    Offer findBySeoUrlAndIncViews(String seoUrl);

    /**
     * Return offer and increase it's views count.
     *
     * @param offerId offer id
     * @return Offer object
     */
    Offer findOfferAndIncViews(String offerId);

    /**
     * Delete offer by it's id.
     *
     * @param id offer id
     */
    void delete(String id);

    /**
     * Return true if offer exist.
     *
     * @param id offer id
     * @return true or false
     */
    boolean offerExists(String id);

    /**
     * Return EntityPage of Offers that received with offer filter options.
     *
     * @param offerFilterOptions OfferFilterOptions object
     * @return EntityPage of Offers
     */
    EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions);

    /**
     * Edit offer and return new updated one.
     *
     * @param oldOffer Offer which we need to update from
     * @return new Offer
     */
    Offer edit(Offer oldOffer);

    /**
     * Reservation of one offer
     *
     * @param offerId     Offer id
     * @param reservation Reservation object
     */
    void reserveOffer(String offerId, Reservation reservation);

    /**
     * Delete reservation by offer id
     *
     * @param offerId offer id
     */
    void deleteReservation(String offerId);

    /**
     * Rent offer for specific period.
     *
     * @param offerId               offer id
     * @param rentedOfferPeriodInfo period of offer rent
     */
    void rentOffer(String offerId, RentedOfferPeriodInfo rentedOfferPeriodInfo);

    /**
     * Delete specific rent from offer
     *
     * @param offerId offer
     * @param rentId  id of rent
     */
    void deleteRent(String offerId, String rentId);

    /**
     * @param offerId
     * @param isActive
     */
    void setActive(String offerId, boolean isActive);

    /**
     * Method for autocomplete input in frontend
     *
     * @param name String input part
     * @return Set of results string
     */
    Set<String> getMatchedNames(String name);

    /**
     * Return one offer for public view
     *
     * @param offer offer
     * @return offer wrapped in OfferInfo class
     */
    OfferInfo getPublicOfferInfoByOffer(Offer offer);


    /**
     * One offer that can contain privet information for it's author.
     *
     * @param offerId offer id
     * @return offer wrapped in OfferInfo class
     */
    OfferInfo getPrivateOfferInfoById(String offerId);

    /**
     * One offer that can contain privet information for it's author.
     *
     * @param offer offer
     * @return offer wrapped in OfferInfo class
     */
    OfferInfo getPrivateOfferInfoByOffer(Offer offer);

    /**
     * Return list of lightweight offers i.e. for page with search result
     *
     * @param offerFilterOptions - object with filter options
     * @return list of offers wrapped in OfferInfo class
     */
    List<OfferInfo> getListOfMiniPublicOffersWithOptions(OfferFilterOptions offerFilterOptions);

    /**
     * @param offerFilterOptions    - the offer filter object
     * @param excludeOfferId        - the ID of the offer which must be excluded from result set.
     * @return                      - the list of OfferInfo object.
     */
    List<OfferInfo> getListOfMiniPublicOffersWithOptionsAndExclude(OfferFilterOptions offerFilterOptions, String excludeOfferId);


    /**
     * @param offerFilterOptions
     * @param orderTotalList
     * @return
     */
    List<OfferInfo> getListOfPrivateOfferInfoWithOptions(OfferFilterOptions offerFilterOptions, List<Order> orderTotalList);

    /**
     * Return list of Offer Info objects with private information. Accept offerFilterOptions.
     *
     * @param offerFilterOptions
     * @return
     */
    List<OfferInfo> getListOfPrivateOfferInfoWithOptions(OfferFilterOptions offerFilterOptions);

    /**
     * Return id if main offer image
     *
     * @return
     */
    String getMainOfferImage(Offer offer);
}
