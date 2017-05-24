package ua.com.itproekt.gup.rental.command;

import ua.com.itproekt.gup.rental.item.Offer;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.store.OfferStore;

public class GetAvailableOffers implements Command {
    private OfferStore store;

    public GetAvailableOffers(OfferStore store) {
        this.store = store;
    }

    @Override
    public void execute() {
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("available.offers"));
        for (Offer offer : store.getAvailableOffers())
            System.out.println(offer.toString());
    }
}
