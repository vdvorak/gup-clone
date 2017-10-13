package ua.com.gup.mongo.model.offer;

import java.util.Set;

public class Rent {
    /**
     * 1st parameter - rentPeriod
     * 2nd parameter - price
     */
    private Set<RentPeriodInfo> rentPeriodsAndPrices;

    /**
     * 1st parameter - rentPeriod
     * 2nd parameter - contact info of the user who rented offer
     */
    private Set<RentedOfferPeriodInfo> rentedOfferPeriodInfo;


    public Set<RentPeriodInfo> getRentPeriodsAndPrices() {
        return rentPeriodsAndPrices;
    }

    public void setRentPeriodsAndPrices(Set<RentPeriodInfo> rentPeriodsAndPrices) {
        this.rentPeriodsAndPrices = rentPeriodsAndPrices;
    }

    public Set<RentedOfferPeriodInfo> getRentedOfferPeriodInfo() {
        return rentedOfferPeriodInfo;
    }

    public void setRentedOfferPeriodInfo(Set<RentedOfferPeriodInfo> rentedOfferPeriodInfo) {
        this.rentedOfferPeriodInfo = rentedOfferPeriodInfo;
    }
}
