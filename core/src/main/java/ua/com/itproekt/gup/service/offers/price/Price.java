package ua.com.itproekt.gup.service.offers.price;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Price extends ConcurrentLinkedQueue<Long> {

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

}
