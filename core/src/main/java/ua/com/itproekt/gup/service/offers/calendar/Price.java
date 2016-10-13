package ua.com.itproekt.gup.service.offers.calendar;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Price extends ConcurrentLinkedQueue<Long> {

    public Price(){}
    public Price(Long price){
        this.price = price;
    }

    private Long price;

    public Long get() {
        return price;
    }

    public void set(Long price) {
        this.price = price;
    }

}
