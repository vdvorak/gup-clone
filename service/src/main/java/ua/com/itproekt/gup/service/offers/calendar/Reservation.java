package ua.com.itproekt.gup.service.offers.calendar;

public class Reservation {

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

}
