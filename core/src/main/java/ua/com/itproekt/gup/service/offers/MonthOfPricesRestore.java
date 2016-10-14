package ua.com.itproekt.gup.service.offers;

import ua.com.itproekt.gup.service.offers.price.MonthOfPrice;

import java.util.Arrays;

public class MonthOfPricesRestore {

    private MonthOfPrice weekday;
    private MonthOfPrice weekend;
    private MonthOfPrice[] specialdays;

    public MonthOfPrice getWeekday() {
        return weekday;
    }

    public void setWeekday(MonthOfPrice weekday) {
        this.weekday = weekday;
    }

    public MonthOfPrice getWeekend() {
        return weekend;
    }

    public void setWeekend(MonthOfPrice weekend) {
        this.weekend = weekend;
    }

    public MonthOfPrice[] getSpecialdays() {
        return specialdays;
    }

    public void setSpecialdays(MonthOfPrice[] specialdays) {
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
