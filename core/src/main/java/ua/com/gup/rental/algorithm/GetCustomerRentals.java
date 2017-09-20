package ua.com.gup.rental.algorithm;

import ua.com.gup.rental.resource.Resource;
import ua.com.gup.rental.resource.ResourceBundleType;
import ua.com.gup.rental.dao.OfferStoreDAO;
import ua.com.gup.rental.domain.RentalTransaction;

import java.util.List;
import java.util.Scanner;

public class GetCustomerRentals implements Command {
    private OfferStoreDAO store;

    public GetCustomerRentals(OfferStoreDAO store) {
        this.store = store;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);

        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.customer.name"));
        String name = scanner.nextLine();

        List<RentalTransaction> customerTransactions = store.getRentalTransactionsForCustomer(name);
        if (customerTransactions.size() > 0)
            for (RentalTransaction rentalTransaction : store.getRentalTransactionsForCustomer(name))
                System.out.println(rentalTransaction.toString());
        else
            System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("no.customer.transactions", name));
    }
}
