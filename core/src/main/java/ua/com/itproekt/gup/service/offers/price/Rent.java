package ua.com.itproekt.gup.service.offers.price;

/**
 * ...
 */
public class Rent {

    /**
     * Хранит массив дат (на которые установливается аренда услуги)
     */
    private Long[] rents;

    public Rent(){}

    public Rent(Long[] rents){
        this.rents = rents;
    }

    public Long[] get() {
        return rents;
    }

    public void set(Long[] rents) {
        this.rents = rents;
    }

}
