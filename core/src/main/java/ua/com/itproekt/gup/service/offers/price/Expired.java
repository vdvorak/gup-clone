package ua.com.itproekt.gup.service.offers.price;

public class Expired implements Rent {

    /**
     * Хранит дату (на которую установливается аренда услуги)
     */
    private Long rent;

    private Long id;

    public Expired(){}

    public Expired(Long rent){
        this.rent = rent;
    }

    public Long get() {
        return rent;
    }

    public void set(Long rents) {
        this.rent = rent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
