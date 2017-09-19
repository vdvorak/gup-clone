package ua.com.itproekt.gup.rental.algorithm;

import ua.com.itproekt.gup.rental.algorithm.type.Types;
import ua.com.itproekt.gup.rental.algorithm.type.OfferType;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.dao.OfferStoreDAO;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ChangeOfferType implements Command {

    private OfferStoreDAO store;

    public ChangeOfferType(OfferStoreDAO store) {
        this.store = store;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        OfferType existedOfferType = null;
        Types newType = null;

        List<OfferType> offerTypes = store.getOffers();

        while (existedOfferType == null || newType == null) {
            if (existedOfferType == null) {
                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.offer.title"));
                String tempTitle = scanner.nextLine();

                for (OfferType offerType : offerTypes) {
                    if (offerType.getTitle().equals(tempTitle) && offerType.isAvailable()) {
                        existedOfferType = offerType;
                        break;
                    }
                }
            }

            if (existedOfferType == null)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("cannot.change.offer.properties"));

            if (existedOfferType != null) {
                List<Types> typeses = Arrays.asList(Types.values());

                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.new.offer.type", typeses));
                String tempType = scanner.nextLine().toUpperCase();

                for (Types value : typeses) {
                    if (value.toString().toUpperCase().equals(tempType)) {
                        newType = value;
                        break;
                    }
                }
            }

            if (existedOfferType != null && newType == null)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("wrong.offer.type"));
        }

        if (existedOfferType != null && newType != null && existedOfferType.getType().equals(newType)){
            System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("offer.types.identical"));
        } else {
            store.changeOfferType(existedOfferType, newType);
        }
    }
}
