package ua.com.itproekt.gup.dao.offers;


import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.Set;

public interface OfferRepository {
    void create(Offer offer);

    Offer findById(String offerId);

    Offer findBySeoKey(String seoKey);

    Offer findAndUpdate(Offer offer);

    int delete(String offerId);

    boolean offerExists(String offerId);

    EntityPage<Offer> findOffersWihOptions(OfferFilterOptions offerFilterOptions);

    void deleteReservation(String offerId);

    void rentOffer(String offerId, RentedOfferPeriodInfo rentedOfferPeriodInfo);

    void incViewsAtOne(String offerId);

    void deleteRent(String offerId, String rentId);

    Set<String> getMatchedNames(String name);
}