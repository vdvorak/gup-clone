package ua.com.itproekt.gup.service.offers.price;

import java.util.Arrays;

public class PriceOfRent {

    private Long price;
    private String[] days;

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
        return "MonthOfPrice{" +
                "price=" + price +
                ", days=" + Arrays.toString(days) +
                '}';
    }

}
