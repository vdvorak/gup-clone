package ua.com.itproekt.gup.service.offers;

import ua.com.itproekt.gup.service.offers.price.MonthOfPrice;

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

}
