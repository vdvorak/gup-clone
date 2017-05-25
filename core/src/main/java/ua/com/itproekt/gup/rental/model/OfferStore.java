package ua.com.itproekt.gup.rental.model;


import java.util.Arrays;

public class OfferStore {

    private String[] offerTitles;
    private String[] customerNames;

    public OfferStore(){
    }

    public OfferStore(String[] offerTitles, String[] customerNames){
        this.offerTitles = offerTitles;
        this.customerNames = customerNames;
    }

    public String[] getOfferTitles() {
        return offerTitles;
    }

    public void setOfferTitles(String[] offerTitles) {
        this.offerTitles = offerTitles;
    }

    public String[] getCustomerNames() {
        return customerNames;
    }

    public void setCustomerNames(String[] customerNames) {
        this.customerNames = customerNames;
    }

    @Override
    public String toString() {
        return "OfferStore{" +
                "offerTitles=" + Arrays.toString(offerTitles) +
                ", customerNames=" + Arrays.toString(customerNames) +
                '}';
    }
}
