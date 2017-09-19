package ua.com.gup.rental.algorithm;

import ua.com.gup.rental.algorithm.type.Types;
import ua.com.gup.rental.algorithm.type.OfferType;
import ua.com.gup.rental.resource.Resource;
import ua.com.gup.rental.resource.ResourceBundleType;
import ua.com.gup.rental.dao.OfferStoreDAO;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AddOffer implements Command {

    private OfferStoreDAO store;

    public AddOffer(OfferStoreDAO store) {
        this.store = store;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        String title = null;
        Types type = null;

        while (title == null || type == null) {

            if (title == null) {
                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.offer.title"));
                title = scanner.nextLine();

                for (OfferType existedOfferType : store.getOffers())
                    if (existedOfferType.getTitle().equals(title) || title.isEmpty()) {
                        title = null;
                        break;
                    }
            }

            if (title == null)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("offer.title.exists"));

            if (title != null) {

                List<Types> typeses = Arrays.asList(Types.values());

                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.new.offer.type", typeses));
                String tempType = scanner.nextLine().toUpperCase();

                for (Types value : typeses) {
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
