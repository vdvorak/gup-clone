package ua.com.itproekt.gup.rental.store;

import ua.com.itproekt.gup.rental.item.Offer;
import ua.com.itproekt.gup.rental.staff.Customer;
import ua.com.itproekt.gup.rental.transaction.RentalTransaction;

import java.util.List;

public interface TransactionDAO {

    void rentOffer(Customer customer, Offer existedOffer, int days, boolean useBonus);

    List<RentalTransaction> getRentalTransactions();

    List<RentalTransaction> getRentalTransactionsForCustomer(String name);
}
