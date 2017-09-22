package ua.com.gup.model.offers;



import ua.com.gup.model.offers.price.Rent;

import java.util.List;

public class RentsRestore {

    private List<Rent> availables;
    private List<Rent> rented;
    private List<Rent> expired;

    public List<Rent> getAvailables() {
        return availables;
    }

    public void setAvailables(List<Rent> availables) {
        this.availables = availables;
    }

    public List<Rent> getRented() {
        return rented;
    }

    public void setRented(List<Rent> rented) {
        this.rented = rented;
    }

    public List<Rent> getExpired() {
        return expired;
    }

    public void setExpired(List<Rent> expired) {
        this.expired = expired;
    }

}
