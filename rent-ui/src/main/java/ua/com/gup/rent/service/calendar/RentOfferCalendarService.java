package ua.com.gup.rent.service.calendar;

import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;

import java.util.List;

/**
 * @author $developer
 **/
public interface RentOfferCalendarService {

    void save(RentOfferCalendar rentOfferCalendar);
    boolean exists(String id);
    List<RentOfferCalendar> findAll();
    List<RentOfferCalendar> findAllByOfferId(String offerId);
}
