package ua.com.itproekt.gup.service.offers.price;

import java.util.Arrays;

public class Rent {

    private Boolean rent;
    private String[] days;

    public Boolean getRent() {
        return rent;
    }

    public void setRent(Boolean rent) {
        this.rent = rent;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "MonthOfRent{" +
                "rent=" + rent +
                ", days=" + Arrays.toString(days) +
                '}';
    }

}
