package ua.com.itproekt.gup.rental.algorithm;

import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.dao.OfferStoreDAO;
import ua.com.itproekt.gup.rental.domain.RentalTransaction;

public class GetRentals implements Command {
    private OfferStoreDAO store;

    public GetRentals(OfferStoreDAO store) {
        this.store = store;
    }

    @Override
    public void execute() {
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("rental.transactions"));
        for (RentalTransaction rentalTransaction : store.getRentalTransactions())
            System.out.println(rentalTransaction.toString());
    }
}
