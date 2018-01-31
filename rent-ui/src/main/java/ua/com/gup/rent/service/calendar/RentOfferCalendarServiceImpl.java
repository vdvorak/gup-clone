package ua.com.gup.rent.service.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendarChild;
import ua.com.gup.rent.repository.calendar.RentOfferCalendarChildRepository;
import ua.com.gup.rent.service.ElasticSearchService;
import ua.com.gup.rent.service.abstracted.RentOfferGenericServiceImpl;
import ua.com.gup.rent.util.RentCalendarUtil;

import java.util.Arrays;

@Service
public class RentOfferCalendarServiceImpl extends RentOfferGenericServiceImpl<RentOfferCalendarChild, String> implements RentOfferCalendarService {

    @Autowired
    private ElasticSearchService searchService;


    @Autowired
    public RentOfferCalendarServiceImpl(RentOfferCalendarChildRepository repository) {
        super(repository);
    }

    @Override
    public void createRentOfferCalendars(RentOffer rentOffer) {
        RentOfferCalendar rentOfferCalendar = rentOffer.getRentOfferCalendar();
        for (int i = 0; i < rentOffer.getRentObjectsCount(); i++) {
            RentOfferCalendarChild rentOfferCalendarChild = new RentOfferCalendarChild();
            rentOfferCalendarChild.setRentStartDate(rentOfferCalendar.getRentStartDate());
            rentOfferCalendarChild.setRentEndDate(rentOfferCalendar.getRentEndDate());
            rentOfferCalendarChild.setRentOfferId(rentOffer.getId());
            rentOfferCalendarChild.setDaysMap(RentCalendarUtil.getDaysMapForDates(rentOfferCalendar.getRentStartDate(),
                    rentOfferCalendar.getRentEndDate(), Arrays.asList(rentOfferCalendar.getDays())));
            getRepository().create(rentOfferCalendarChild);
        }

    }

    @Override
    public void updateRentOfferCalendars(RentOffer rentOffer) {
//        for (RentOfferCalendar rentOfferCalendar : rentOffer.getRentOfferCalendars()) {
//            RentOfferCalendarChild rentOfferCalendarChild = new RentOfferCalendarChild();
//            rentOfferCalendarChild.setRentStartDate(rentOfferCalendar.getRentStartDate());
//            rentOfferCalendarChild.setRentEndDate(rentOfferCalendar.getRentEndDate());
//            rentOfferCalendarChild.setRentOfferId(rentOffer.getId());
//            rentOfferCalendarChild.setDaysMap(RentCalendarUtil.getDaysMapForDates(rentOfferCalendar.getRentStartDate(),
//                    rentOfferCalendar.getRentEndDate(), Arrays.asList(rentOfferCalendar.getDays())));
//            getRepository().create(rentOfferCalendarChild);
//        }
    }

    @Override
    public void refreshRentOfferCalendars(RentOffer rentOffer) {
        removeRentOfferCalendars(rentOffer);
        createRentOfferCalendars(rentOffer);
    }

    @Override
    public void removeRentOfferCalendars(RentOffer rentOffer) {
        getRepository().removeCalendars(rentOffer);
    }

    @Override
    public void indexRentOffer(RentOffer rentOffer) {
        searchService.indexRentOffer(rentOffer);
    }

    @Override
    public void indexRentOfferCalendars(RentOffer rentOffer) {
        searchService.indexRentOfferCalendars(rentOffer);
    }

    @Override
    protected RentOfferCalendarChildRepository getRepository() {
        return (RentOfferCalendarChildRepository) super.getRepository();
    }


}
