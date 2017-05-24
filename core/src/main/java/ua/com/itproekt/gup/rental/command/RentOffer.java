package ua.com.itproekt.gup.rental.command;

import ua.com.itproekt.gup.rental.calculator.Calculator;
import ua.com.itproekt.gup.rental.item.ItemType;
import ua.com.itproekt.gup.rental.item.Offer;
import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;
import ua.com.itproekt.gup.rental.staff.Customer;
import ua.com.itproekt.gup.rental.store.OfferStore;
import ua.com.itproekt.gup.rental.transaction.RentalTransaction;
import ua.com.itproekt.gup.rental.util.Converter;
import ua.com.itproekt.gup.rental.util.Parser;

import java.util.Scanner;

public class RentOffer implements Command {

    private int selectedDays;
    private OfferStore store;
    private String customerName;
    private String offerTitle;
    private Boolean termsAcceptance;
    private Boolean useBonusPoints;
    private Customer customer;
    private Offer selectedOffer;

    public RentOffer(OfferStore store) {
        this.store = store;
        this.customerName = null;
        this.offerTitle = null;
        this.selectedDays = 0;
        this.termsAcceptance = false;
        this.useBonusPoints = false;
        this.customer = null;
        this.selectedOffer = null;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        while (selectedOffer == null || selectedDays < 1) {

            if (customer == null) {
                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.your.name"));
                customerName = scanner.nextLine();

                customer = findOrCreateNewCustomer(customerName);
            }

            if (customer != null && selectedOffer == null) {

                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.offer.title"));
                offerTitle = scanner.nextLine();

                selectedOffer = findOffer(offerTitle);
            }

            if (customer != null && selectedOffer != null && selectedDays < 1) {

                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("enter.rent.days"));
                selectedDays = Parser.tryParseToInteger(scanner.nextLine());
            }

            if (selectedOffer == null)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("offer.not.existed.or.rented"));

            if (selectedOffer != null && selectedDays < 1)
                System.out.println(Resource.getInstance(ResourceBundleType.ERRORS.getBundle()).getString("days.less.one"));
        }


        System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("you.choose.offer", selectedOffer.getTitle(), selectedOffer.getType(),
                Converter.ToDays(selectedDays), Converter.ToCurrency(Calculator.calculatePrice(selectedOffer.getType(), selectedDays))));


        if (selectedOffer.getType() == ItemType.NEW_RELEASES && customer.getBonus() > RentalTransaction.BONUS_CHARGE_PER_DIEM * selectedDays) {

            System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("pay.by.bonus", customer.getBonus(),
                    RentalTransaction.BONUS_CHARGE_PER_DIEM * selectedDays));

            useBonusPoints = Boolean.parseBoolean(scanner.nextLine());

            if (useBonusPoints) {
                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("accept.terms"));
                termsAcceptance = Boolean.parseBoolean(scanner.nextLine());

                if (termsAcceptance)
                    store.rentOffer(customer, selectedOffer, selectedDays, true);
            }
        } else {

            System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("accept.terms"));
            termsAcceptance = Boolean.parseBoolean(scanner.nextLine());

            if (termsAcceptance)
                store.rentOffer(customer, selectedOffer, selectedDays, false);
        }

        if (!termsAcceptance)
            System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("rejected.terms"));
    }

    private Offer findOffer(String title) {
        Offer existedOffer = null;
        if (!title.isEmpty())
            for (Offer offer : store.getAvailableOffers())
                if (offer.getTitle().equals(title)) {
                    existedOffer = offer;
                    break;
                }
        return existedOffer;
    }

    private Customer findOrCreateNewCustomer(String customerName) {
        if (!customerName.isEmpty()) {
            Customer customer = store.getCustomer(customerName);
            if (customer == null) {
                customer = new Customer(customerName);
                System.out.println(Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("new.customer.created", customer.getName()));
                return customer;
            } else
                return customer;
        }
        return null;
    }
}

