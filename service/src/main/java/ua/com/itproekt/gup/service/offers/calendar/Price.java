package ua.com.itproekt.gup.service.offers.calendar;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Price extends ConcurrentLinkedQueue<Integer> {

    public Price(){}
    public Price(Integer price){
        this.price = price;
    }

    private Integer price;

    public Integer get() {
        return price;
    }

    public void set(Integer price) {
        this.price = price;
    }

}
