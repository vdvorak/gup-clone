package ua.com.itproekt.gup.rental.command;

import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.store.OfferStore;
import ua.com.itproekt.gup.rental.transaction.RentalTransaction;

public class GetRentals implements Command {
    private OfferStore store;

    public GetRentals(OfferStore store) {
        this.store = store;
    }

    @Override
    public void execute() {
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("rental.transactions"));
        for (RentalTransaction rentalTransaction : store.getRentalTransactions())
            System.out.println(rentalTransaction.toString());
    }
}
