package ua.com.gup.rent.repository.calendar;

import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendarChild;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

import java.util.List;

public interface RentOfferCalendarChildRepository extends RentOfferGenericRepository<RentOfferCalendarChild, String> {

    List<RentOfferCalendarChild> findAllByOffer(RentOffer rentOffer);

    void removeCalendars(RentOffer rentOffer);
}
