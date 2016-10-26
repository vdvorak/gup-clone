package ua.com.itproekt.gup.service.offers.price;

/**
 * ...
 */
public class Available {

    /**
     * Хранит массив дат (на которые установливается аренда услуги)
     */
    private Long[] rents;

    public Available(){}

    public Available(Long[] rents){
        this.rents = rents;
    }

    public Long[] get() {
        return rents;
    }

    public void set(Long[] rents) {
        this.rents = rents;
    }

}
