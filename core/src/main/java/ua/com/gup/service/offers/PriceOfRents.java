package ua.com.gup.service.offers;

import ua.com.gup.service.offers.price.PriceOfRent;

public class PriceOfRents {

    private Long weekdayPrice;
    private Long weekendPrice;
    private PriceOfRent specialPrice;

    public Long getWeekdayPrice() {
        return weekdayPrice;
    }

    public void setWeekdayPrice(Long weekdayPrice) {
        this.weekdayPrice = weekdayPrice;
    }

    public Long getWeekendPrice() {
        return weekendPrice;
    }

    public void setWeekendPrice(Long weekendPrice) {
        this.weekendPrice = weekendPrice;
    }

    public PriceOfRent getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(PriceOfRent specialPrice) {
        this.specialPrice = specialPrice;
    }

    @Override
    public String toString() {
        return "MonthOfPrices{" +
                "weekdayPrice=" + weekdayPrice +
                ", weekendPrice=" + weekendPrice +
                ", specialPrice=" + specialPrice +
                '}';
    }

}
