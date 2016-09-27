package ua.com.itproekt.gup.service.offers.calendar;

import java.util.Arrays;

public class Rent {

    private Integer[] days;

    public Integer[] getDays() {
        return days;
    }

    public void setDays(Integer[] days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                ", days=" + Arrays.toString(days) +
                '}';
    }
}
