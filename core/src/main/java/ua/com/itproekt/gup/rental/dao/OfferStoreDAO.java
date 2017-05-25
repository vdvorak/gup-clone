package ua.com.itproekt.gup.rental.dao;

import ua.com.itproekt.gup.rental.algorithm.type.Types;
import ua.com.itproekt.gup.rental.algorithm.type.OfferType;
import ua.com.itproekt.gup.rental.model.OfferStore;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.algorithm.staff.Customer;
import ua.com.itproekt.gup.rental.domain.RentalTransaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class OfferStoreDAO implements OfferDAO, TransactionDAO {

    private OfferStore offerStore;

    private List<OfferType> offerTypes;
    private List<RentalTransaction> transactions;

    public OfferStoreDAO() {
        offerTypes = new LinkedList<>();
        transactions = new LinkedList<>();
    }

    public OfferStoreDAO(OfferStore offerStore){
        this();
        this.offerStore = offerStore;

        generateOffers();
        generateTransactions();
    }

    private void generateOffers() {
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("fake.offers.generation"));

        for (String offerTitle : offerStore.getOfferTitles())
            addOffer(offerTitle, Types.getRandom());

        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("end.of.fake.offers.generation"));
    }

    private void generateTransactions() {
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("fake.transactions.generation"));

        for (String customerName : offerStore.getCustomerNames()) {
            // Range: 1 - 31 days
            int randomDays = new Random().nextInt(30) + 1;
            boolean useBonusPoints = new Random().nextBoolean();
            OfferType randomOfferType = getAvailableOffers().get(new Random().nextInt(offerTypes.size() - 1));
            Customer customer = new Customer(customerName, 1000);

            rentOffer(customer, randomOfferType, randomDays, useBonusPoints);
        }
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("end.of.fake.transactions.generation"));
    }

    @Override
    public void addOffer(String title, Types type) {
        OfferType newOfferType = new OfferType(title, type);
        offerTypes.add(newOfferType);
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("added.offer", newOfferType.toString()));
    }

    @Override
    public void removeOffer(OfferType existedOfferType) {
        offerTypes.remove(existedOfferType);
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("removed.offer", existedOfferType.toString()));
    }

    @Override
    public void changeOfferType(OfferType offerType, Types type) {
        offerType.setType(type);
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("offer.change.type", offerType.getTitle(), offerType.getType()));
    }

    @Override
    public List<OfferType> getOffers() {
        return offerTypes;
    }

    @Override
    public List<OfferType> getAvailableOffers() {
        List<OfferType> availableOfferTypes = new LinkedList<>();

        for (OfferType offerType : offerTypes) {
            if (offerType.isAvailable())
                availableOfferTypes.add(offerType);
        }

        return availableOfferTypes;
    }

    @Override
    public void rentOffer(Customer customer, OfferType existedOfferType, int days, boolean useBonus) {

        // Change already rented offer availability to False
        for (OfferType offerType : offerTypes)
            if (offerType.equals(existedOfferType)) {
                offerType.setAvailability(false);
                existedOfferType = offerType;
            }

        RentalTransaction rentalTransaction;
        if (useBonus) {
            rentalTransaction = new RentalTransaction(customer, existedOfferType, days, true);
            transactions.add(rentalTransaction);
        } else {
            rentalTransaction = new RentalTransaction(customer, existedOfferType, days, false);
            transactions.add(rentalTransaction);
        }

        System.out.println(rentalTransaction.toString());
    }

    @Override
    public List<RentalTransaction> getRentalTransactions() {
        return transactions;
    }

    @Override
    public List<RentalTransaction> getRentalTransactionsForCustomer(String name) {
        List<RentalTransaction> customerTransactions = new LinkedList<>();

        for (RentalTransaction transaction : transactions)
            if (transaction.getCustomer().getName().equals(name))
                customerTransactions.add(transaction);

        return customerTransactions;
    }

    public Customer getCustomer(String name) {
        for (RentalTransaction transaction : transactions)
            if (transaction.getCustomer().getName().equals(name))
                return transaction.getCustomer();

        return null;
    }
}
