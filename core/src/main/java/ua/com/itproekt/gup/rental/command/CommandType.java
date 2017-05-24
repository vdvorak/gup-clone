package ua.com.itproekt.gup.rental.command;

import ua.com.itproekt.gup.rental.resource.Resource;
import ua.com.itproekt.gup.rental.resource.ResourceBundleType;

public enum CommandType {
    ADD_OFFER(1, AddOffer.class.getSimpleName()),
    REMOVE_OFFER(2, RemoveOffer.class.getSimpleName()),
    CHANGE_OFFER_TYPE(3, ChangeOfferType.class.getSimpleName()),
    GET_OFFERS(4, GetOffers.class.getSimpleName()),
    GET_AVAILABLE_OFFERS(5, GetAvailableOffers.class.getSimpleName()),
    RENT_OFFER(6, RentOffer.class.getSimpleName()),
    GET_RENTALS(7, GetRentals.class.getSimpleName()),
    GET_RENTALS_FOR_CUSTOMER(8, GetRentalsForCustomer.class.getSimpleName()),
    EXIT(9, Resource.getInstance(ResourceBundleType.LABELS.getBundle()).getString("exit"));

    private final Integer valueID;
    private final String value;

    private CommandType(Integer valueID, String value) {
        this.valueID = valueID;
        this.value = value;
    }

    @Override

    public String toString() {
        String presentableValue = value.replaceAll("([a-z]+)([A-Z])", "$1 $2");
        return valueID + " - " + (presentableValue.substring(0, 1).toUpperCase() + presentableValue.substring(1));
    }
}
