package ua.com.gup.rental.algorithm;

import ua.com.gup.rental.algorithm.type.OfferType;
import ua.com.gup.rental.resource.Resource;
import ua.com.gup.rental.resource.ResourceBundleType;
import ua.com.gup.rental.dao.OfferStoreDAO;

public class GetOffers implements Command {
    private OfferStoreDAO store;

    public GetOffers(OfferStoreDAO store) {
        this.store = store;
    }

    @Override
    public void execute() {
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("offers"));
        for (OfferType offerType : store.getOffers())
            System.out.println(offerType.toString());
    }
}
