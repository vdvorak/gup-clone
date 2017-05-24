package ua.com.itproekt.gup.rental.command;

import ua.com.itproekt.gup.rental.item.Offer;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.store.OfferStore;

public class GetOffers implements Command {
    private OfferStore store;

    public GetOffers(OfferStore store) {
        this.store = store;
    }

    @Override
    public void execute() {
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("offers"));
        for (Offer offer : store.getOffers())
            System.out.println(offer.toString());
    }
}
