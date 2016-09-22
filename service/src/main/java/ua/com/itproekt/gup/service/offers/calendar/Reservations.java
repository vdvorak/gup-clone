package ua.com.itproekt.gup.service.offers.calendar;

import java.util.Map;

public class Reservations {

    private Map<String, Reservation> reservations;

    public Map<String, Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Map<String, Reservation> reservations) {
        this.reservations = reservations;
    }
}
