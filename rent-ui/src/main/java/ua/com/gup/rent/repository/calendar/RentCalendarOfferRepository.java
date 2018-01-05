package ua.com.gup.rent.repository.calendar;


import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;

import java.util.List;


public interface RentCalendarOfferRepository {

    void save(RentOfferCalendar complaintOffer);

    void update(RentOfferCalendar complaintOffer);

    RentOfferCalendar findById(String id);

    boolean exists(String id);

    List<RentOfferCalendar> findAll();

    List<RentOfferCalendar> findAllByOfferId(String offerId);

}
