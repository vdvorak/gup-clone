package ua.com.itproekt.gup.rental.store;

import ua.com.itproekt.gup.rental.item.ItemType;
import ua.com.itproekt.gup.rental.item.Offer;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.staff.Customer;
import ua.com.itproekt.gup.rental.transaction.RentalTransaction;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class OfferStore implements OfferDAO, TransactionDAO {

    private static String[] fakeVideoTitles = {"Shrek", "Spider Man III", "Game of Thrones", "Mortal Combat"};
    private static String[] fakeCustomerNames = {"Kauri", "Lauri"};

    private List<Offer> offers;
    private List<RentalTransaction> transactions;

    public OfferStore() {
        offers = new LinkedList<>();
        transactions = new LinkedList<>();

        generateFakeOffers();
        generateFakeTransactions();
    }

    private void generateFakeOffers() {
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("fake.offers.generation"));

        for (String videoTitle : fakeVideoTitles)
            addOffer(videoTitle, ItemType.getRandom());

        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("end.of.fake.offers.generation"));
    }

    private void generateFakeTransactions() {
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("fake.transactions.generation"));

        for (String customerName : fakeCustomerNames) {

            // Range: 1 - 31 days
            int randomDays = new Random().nextInt(30) + 1;
            boolean useBonusPoints = new Random().nextBoolean();
            Offer randomOffer = getAvailableOffers().get(new Random().nextInt(offers.size() - 1));
            Customer customer = new Customer(customerName, 1000);

            rentOffer(customer, randomOffer, randomDays, useBonusPoints);
        }
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("end.of.fake.transactions.generation"));
    }

    @Override
    public void addOffer(String title, ItemType type) {
        Offer newOffer = new Offer(title, type);
        offers.add(newOffer);
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("added.offer", newOffer.toString()));
    }

    @Override
    public void removeOffer(Offer existedOffer) {
        offers.remove(existedOffer);
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("removed.offer", existedOffer.toString()));
    }

    @Override
    public void changeOfferType(Offer offer, ItemType type) {
        offer.setType(type);
        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("offer.change.type", offer.getTitle(), offer.getType()));
    }

    @Override
    public List<Offer> getOffers() {
        return offers;
    }

    @Override
    public List<Offer> getAvailableOffers() {
        List<Offer> availableOffers = new LinkedList<>();

        for (Offer offer : offers) {
            if (offer.isAvailable())
                availableOffers.add(offer);
        }

        return availableOffers;
    }

    @Override
    public void rentOffer(Customer customer, Offer existedOffer, int days, boolean useBonus) {

        // Change already rented offer availability to False
        for (Offer offer : offers)
            if (offer.equals(existedOffer)) {
                offer.setAvailability(false);
                existedOffer = offer;
            }

        RentalTransaction rentalTransaction;
        if (useBonus) {
            rentalTransaction = new RentalTransaction(customer, existedOffer, days, true);
            transactions.add(rentalTransaction);
        } else {
            rentalTransaction = new RentalTransaction(customer, existedOffer, days, false);
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
