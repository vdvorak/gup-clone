package ua.com.gup.rental.algorithm;


import ua.com.gup.rental.algorithm.type.OfferType;
import ua.com.gup.rental.resource.Resource;
import ua.com.gup.rental.resource.ResourceBundleType;
import ua.com.gup.rental.dao.OfferStoreDAO;

import java.util.List;
import java.util.Scanner;

public class RemoveOffer implements Command {
    private OfferStoreDAO store;

    public RemoveOffer(OfferStoreDAO store) {
        this.store = store;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        OfferType existedOfferType = null;

        List<OfferType> offerTypes = store.getOffers();

        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("select.offer.title"));
        for (OfferType offerType : offerTypes)
            System.out.println(offerType.toString());

        while (existedOfferType == null) {

            System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.offer.title"));
            String tempTitle = scanner.nextLine();

            for (OfferType offerType : offerTypes) {
                if (offerType.getTitle().equals(tempTitle)) {
                    existedOfferType = offerType;
                    break;
                }
            }

            if (existedOfferType == null)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("no.such.offer"));
        }

        store.removeOffer(existedOfferType);
    }
}
