package ua.com.itproekt.gup.rental.dao;

import ua.com.itproekt.gup.rental.algorithm.type.Types;
import ua.com.itproekt.gup.rental.algorithm.type.OfferType;

import java.util.List;

public interface OfferDAO {

    void addOffer(String title, Types type);

    void removeOffer(OfferType existedOfferType);

    void changeOfferType(OfferType offerType, Types type);

    List<OfferType> getOffers();

    List<OfferType> getAvailableOffers();
}
