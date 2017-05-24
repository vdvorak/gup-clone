package ua.com.itproekt.gup.rental.store;

import ua.com.itproekt.gup.rental.item.ItemType;
import ua.com.itproekt.gup.rental.item.Offer;

import java.util.List;

public interface OfferDAO {

    void addOffer(String title, ItemType type);

    void removeOffer(Offer existedOffer);

    void changeOfferType(Offer offer, ItemType type);

    List<Offer> getOffers();

    List<Offer> getAvailableOffers();
}
