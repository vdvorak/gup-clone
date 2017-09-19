package ua.com.itproekt.gup.rental.algorithm;

import ua.com.itproekt.gup.rental.algorithm.calculator.Calculator;
import ua.com.itproekt.gup.rental.algorithm.type.Types;
import ua.com.itproekt.gup.rental.algorithm.type.OfferType;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.algorithm.staff.Customer;
import ua.com.itproekt.gup.rental.dao.OfferStoreDAO;
import ua.com.itproekt.gup.rental.domain.RentalTransaction;
import ua.com.itproekt.gup.rental.util.Converter;
import ua.com.itproekt.gup.rental.util.Parser;

import java.util.Scanner;

public class RentOffer implements Command {

    private int selectedDays;
    private OfferStoreDAO store;
    private String customerName;
    private String offerTitle;
    private Boolean termsAcceptance;
    private Boolean useBonusPoints;
    private Customer customer;
    private OfferType selectedOfferType;

    public RentOffer(OfferStoreDAO store) {
        this.store = store;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        while (selectedOfferType == null || selectedDays < 1) {
            if (customer == null) {
                System.err.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.your.name"));
                customerName = scanner.nextLine();
                customer = findOrCreateNewCustomer(customerName);
            }

            if (customer != null && selectedOfferType == null) {
                System.err.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.offer.title"));
                offerTitle = scanner.nextLine();
                selectedOfferType = findOffer(offerTitle);
            }

            if (customer != null && selectedOfferType != null && selectedDays < 1) {
                System.err.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.rent.days"));
                selectedDays = Parser.toInteger(scanner.nextLine());
            }

            if (selectedOfferType == null)
                System.err.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("offer.not.existed.or.rented"));

            if (selectedOfferType != null && selectedDays < 1)
                System.err.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("days.less.one"));
        }

        System.err.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("you.choose.offer", selectedOfferType.getTitle(), selectedOfferType.getType(), Converter.toDays(selectedDays), Converter.toCurrency(Calculator.calculatePrice(selectedOfferType.getType(), selectedDays))));

        if (selectedOfferType.getType() == Types.NEW_RELEASES && customer.getBonus() > RentalTransaction.BONUS_CHARGE_PER_DIEM * selectedDays) {
            System.err.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("pay.by.bonus", customer.getBonus(), RentalTransaction.BONUS_CHARGE_PER_DIEM * selectedDays));
            useBonusPoints = Boolean.parseBoolean(scanner.nextLine());
            if (useBonusPoints) {
                System.err.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("accept.terms"));
                termsAcceptance = Boolean.parseBoolean(scanner.nextLine());
                if (termsAcceptance)
                    store.rentOffer(customer, selectedOfferType, selectedDays, true);
            }
        } else {
            System.err.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("accept.terms"));
            termsAcceptance = Boolean.parseBoolean(scanner.nextLine());
            if (termsAcceptance)
                store.rentOffer(customer, selectedOfferType, selectedDays, false);
        }

        if (!termsAcceptance)
            System.err.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("rejected.terms"));
    }

    private OfferType findOffer(String title) {
        OfferType existedOfferType = null;
        if (!title.isEmpty())
            for (OfferType offerType : store.getAvailableOffers())
                if (offerType.getTitle().equals(title)) {
                    existedOfferType = offerType;
                    break;
                }
        return existedOfferType;
    }

    private Customer findOrCreateNewCustomer(String customerName) {
        if (!customerName.isEmpty()) {
            Customer customer = store.getCustomer(customerName);
            if (customer == null) {
                customer = new Customer(customerName);
                System.err.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("new.customer.created", customer.getName()));
                return customer;
            } else
                return customer;
        }
        return null;
    }
}

