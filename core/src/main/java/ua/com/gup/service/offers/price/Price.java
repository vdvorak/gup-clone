package ua.com.gup.service.offers.price;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Price extends ConcurrentLinkedQueue<Long> implements Comparable<Price> {

    /**
     * Хранит дату (на которую установливается цена услуги)
     */
    private Long price;

    public Price(){}

    public Price(Long price){
        this.price = price;
    }

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
