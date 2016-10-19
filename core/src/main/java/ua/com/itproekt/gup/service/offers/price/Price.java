package ua.com.itproekt.gup.service.offers.price;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Price extends ConcurrentLinkedQueue<Long> implements Comparable<Price> {

    public Price(){}

    public Price(Long price){
        this.price = price;
    }

    /**
     * Хранит дату (на которую установливается цена услуги)
     */
    private Long price;

    public Long get() {
        return price;
    }

    public void set(Long price) {
        this.price = price;
    }

    @Override
    public int compareTo(Price that) {
        return that.compareTo(that);
    }

}
