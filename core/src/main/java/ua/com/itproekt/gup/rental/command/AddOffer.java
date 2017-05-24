package ua.com.itproekt.gup.rental.command;

import ua.com.itproekt.gup.rental.item.ItemType;
import ua.com.itproekt.gup.rental.item.Offer;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.store.OfferStore;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddOffer implements Command {

    private OfferStore store;

    public AddOffer(OfferStore store) {
        this.store = store;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        String title = null;
        ItemType type = null;

        while (title == null || type == null) {

            if (title == null) {
                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.offer.title"));
                title = scanner.nextLine();

                for (Offer existedOffer : store.getOffers())
                    if (existedOffer.getTitle().equals(title) || title.isEmpty()) {
                        title = null;
                        break;
                    }
            }

            if (title == null)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("offer.title.exists"));

            if (title != null) {

                List<ItemType> itemTypes = Arrays.asList(ItemType.values());

                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.new.offer.type", itemTypes));
                String tempType = scanner.nextLine().toUpperCase();

                for (ItemType value : itemTypes) {
                    if (value.toString().toUpperCase().equals(tempType)) {
                        type = value;
                        break;
                    }
                }
            }

            if (title != null && type == null)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("wrong.offer.type"));
        }

        store.addOffer(title, type);
    }
}
