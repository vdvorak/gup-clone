package ua.com.gup.model.offers;

import ua.com.gup.model.offers.price.PriceOfRent;

import java.util.Arrays;

public class PriceOfRentsRestore {

    private PriceOfRent weekday;
    private PriceOfRent weekend;
    private PriceOfRent[] specialdays;

    public PriceOfRent getWeekday() {
        return weekday;
    }

    public void setWeekday(PriceOfRent weekday) {
        this.weekday = weekday;
    }

    public PriceOfRent getWeekend() {
        return weekend;
    }

    public void setWeekend(PriceOfRent weekend) {
        this.weekend = weekend;
    }

    public PriceOfRent[] getSpecialdays() {
        return specialdays;
    }

    public void setSpecialdays(PriceOfRent[] specialdays) {
        this.specialdays = specialdays;
    }

    @Override
    public String toString() {
        return "MonthOfPricesRestore{" +
                "weekday=" + weekday +
                ", weekend=" + weekend +
                ", specialdays=" + Arrays.toString(specialdays) +
                '}';
    }

}
