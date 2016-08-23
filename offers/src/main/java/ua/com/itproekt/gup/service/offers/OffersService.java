package ua.com.itproekt.gup.service.offers;

import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.itproekt.gup.model.offer.Reservation;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.server.api.rest.dto.OfferInfo;
import ua.com.itproekt.gup.server.api.rest.dto.OfferRegistration;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;
import java.util.Set;

public interface OffersService {
    void create(Offer offer);

    void createWithRegistration(OfferRegistration offerRegistration);

    Offer findById(String offerId);

    Offer findBySeoKey(String seoKey);

    Offer findOfferAndIncViews(String offerId);

    void delete(String id);

    boolean offerExists(String id);

    EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions);

    Offer edit(Offer oldOffer);

    void reserveOffer(String offerId, Reservation reservation);

    void deleteReservation(String offerId);

    void rentOffer(String offerId, RentedOfferPeriodInfo rentedOfferPeriodInfo);

    void deleteRent(String offerId, String rentId);

    void setActive(String offerId, boolean isActive);

    Set<String> getMatchedNames(String name);

    /**
     * Return one offer for public view
     *
     * @param offerId - offer id
     * @return object of OfferInfo class
     */
    OfferInfo getPublicOfferInfoById(String offerId);

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
     *
     * @param offerFilterOptions
     * @param excludeOfferId
     * @return
     */
    List<OfferInfo> getListOfMiniPublicOffersWithOptionsAndExclude(OfferFilterOptions offerFilterOptions, String excludeOfferId);


//    ModerationMessage moderateOffer(ModerationMessage moderationMessage);
}
