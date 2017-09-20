package ua.com.gup.rental.dao;

import ua.com.gup.rental.algorithm.type.OfferType;
import ua.com.gup.rental.algorithm.staff.Customer;
import ua.com.gup.rental.domain.RentalTransaction;

import java.util.List;

public interface TransactionDAO {

    void rentOffer(Customer customer, OfferType existedOfferType, int days, boolean useBonus);

    List<RentalTransaction> getRentalTransactions();

    List<RentalTransaction> getRentalTransactionsForCustomer(String name);
}
