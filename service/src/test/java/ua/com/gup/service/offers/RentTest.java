package ua.com.gup.service.offers;

import java.util.Arrays;

public class RentTest {

    private String[] days;

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Rent{" +
                ", days=" + Arrays.toString(days) +
                '}';
    }
}
