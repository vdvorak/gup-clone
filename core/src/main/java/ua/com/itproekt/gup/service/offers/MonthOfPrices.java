package ua.com.itproekt.gup.service.offers;

import ua.com.itproekt.gup.service.offers.price.MonthOfPrice;

public class MonthOfPrices {

    private Long weekdayPrice;
    private Long weekendPrice;
    private MonthOfPrice specialPrice;

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

    public MonthOfPrice getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(MonthOfPrice specialPrice) {
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
