package ua.com.itproekt.gup.rental.command;

import ua.com.itproekt.gup.rental.item.ItemType;
import ua.com.itproekt.gup.rental.item.Offer;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.store.OfferStore;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ChangeOfferType implements Command {
    private OfferStore store;

    public ChangeOfferType(OfferStore store) {
        this.store = store;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        Offer existedOffer = null;
        ItemType newType = null;

        List<Offer> offers = store.getOffers();

        while (existedOffer == null || newType == null) {

            if (existedOffer == null) {
                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.offer.title"));
                String tempTitle = scanner.nextLine();

                for (Offer offer : offers) {
                    if (offer.getTitle().equals(tempTitle) && offer.isAvailable()) {
                        existedOffer = offer;
                        break;
                    }
                }
            }

            if (existedOffer == null)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("cannot.change.offer.properties"));

            if (existedOffer != null) {
                List<ItemType> itemTypes = Arrays.asList(ItemType.values());

                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.new.offer.type", itemTypes));
                String tempType = scanner.nextLine().toUpperCase();

                for (ItemType value : itemTypes) {
                    if (value.toString().toUpperCase().equals(tempType)) {
                        newType = value;
                        break;
                    }
                }
            }

            if (existedOffer != null && newType == null)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("wrong.offer.type"));

        }

        if (existedOffer != null && newType != null && existedOffer.getType().equals(newType))
            System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("offer.types.identical"));
        else
            store.changeOfferType(existedOffer, newType);
    }
}
