package ua.com.itproekt.gup.service.offers;

import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.itproekt.gup.model.offer.Reservation;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.Set;

public interface OffersService {
    void create(Offer offer);

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
}
