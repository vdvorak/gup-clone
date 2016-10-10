package ua.com.itproekt.gup.service.offers.calendar;

import java.util.Arrays;

public class CalendarSpecialPriceClass {

    private String name;
    private Long price;
    private String[] days;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String[] getDays() {
        return days;
    }

    public void setDays(String[] days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "specialPrice{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", days=" + Arrays.toString(days) +
                '}';
    }
}
