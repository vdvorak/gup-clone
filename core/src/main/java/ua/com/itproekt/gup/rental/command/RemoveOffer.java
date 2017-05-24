package ua.com.itproekt.gup.rental.command;


import ua.com.itproekt.gup.rental.item.Offer;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.store.OfferStore;

import java.util.List;
import java.util.Scanner;

public class RemoveOffer implements Command {
    private OfferStore store;

    public RemoveOffer(OfferStore store) {
        this.store = store;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Offer existedOffer = null;

        List<Offer> offers = store.getOffers();

        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("select.offer.title"));
        for (Offer offer : offers)
            System.out.println(offer.toString());

        while (existedOffer == null) {

            System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.offer.title"));
            String tempTitle = scanner.nextLine();

            for (Offer offer : offers) {
                if (offer.getTitle().equals(tempTitle)) {
                    existedOffer = offer;
                    break;
                }
            }

            if (existedOffer == null)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("no.such.offer"));
        }

        store.removeOffer(existedOffer);
    }
}
