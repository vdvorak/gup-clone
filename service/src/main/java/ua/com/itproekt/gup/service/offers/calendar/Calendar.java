package ua.com.itproekt.gup.service.offers.calendar;

import java.util.Arrays;

public class Calendar {

    private Integer price;
    private Integer[] days;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer[] getDays() {
        return days;
    }

    public void setDays(Integer[] days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "price=" + price +
                ", days=" + Arrays.toString(days) +
                '}';
    }
}
