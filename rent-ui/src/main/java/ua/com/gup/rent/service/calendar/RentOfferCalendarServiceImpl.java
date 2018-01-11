package ua.com.gup.rent.service.calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.repository.calendar.RentCalendarOfferRepository;

import java.util.List;


@Service
public class RentOfferCalendarServiceImpl implements RentOfferCalendarService {
    private final Logger log = LoggerFactory.getLogger(RentOfferCalendarServiceImpl.class);

    @Autowired
    private RentCalendarOfferRepository rentCalendarOfferRepository;

    @Override
    public void save(RentOfferCalendar rentOfferCalendar) {
        rentCalendarOfferRepository.save(rentOfferCalendar);
    }

    @Override
    public boolean exists(String id) {
        return rentCalendarOfferRepository.exists(id);
    }

    @Override
    public List<RentOfferCalendar> findAll() {
        return rentCalendarOfferRepository.findAll();
    }

    @Override
    public List<RentOfferCalendar> findAllByOfferId(String offerId) {
        return rentCalendarOfferRepository.findAllByOfferId(offerId);
    }
}
