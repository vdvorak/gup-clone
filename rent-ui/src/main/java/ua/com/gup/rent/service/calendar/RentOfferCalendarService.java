package ua.com.gup.rent.service.calendar;

import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendarChild;
import ua.com.gup.rent.service.abstracted.generic.RentOfferGenericService;

import java.time.LocalDate;

public interface RentOfferCalendarService extends RentOfferGenericService<RentOfferCalendarChild, String> {

    void createRentOfferCalendars(RentOffer rentOffer);

    void updateRentOfferCalendars(RentOffer rentOffer);

    void refreshRentOfferCalendars(RentOffer rentOffer);

    void removeRentOfferCalendars(RentOffer rentOffer);


    void indexRentOffer(RentOffer rentOffer);

    void indexRentOfferCalendars(RentOffer rentOffer);
}
