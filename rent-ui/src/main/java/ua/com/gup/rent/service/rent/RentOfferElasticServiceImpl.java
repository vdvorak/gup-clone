package ua.com.gup.rent.service.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.rent.ElasticRentOffer;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendarInterval;
import ua.com.gup.rent.repository.RentOfferElasticRepository;
import ua.com.gup.rent.util.RentCalendarUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class RentOfferElasticServiceImpl implements RentOfferElasticService {

    @Autowired
    private RentOfferElasticRepository rentOfferElasticRepository;

    @Async
    public void createElasticRentOffer(RentOffer rentOffer) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        RentOfferCalendar rentOfferCalendar = rentOffer.getRentOfferCalendar();
        for (int i = 0; i < rentOffer.getRentObjectsCount(); i++) {
            ElasticRentOffer elasticRentOffer = new ElasticRentOffer(rentOffer);

            RentOfferCalendarInterval interval = new RentOfferCalendarInterval();
            interval.setRentStartDate(rentOfferCalendar.getRentStartDate());
            interval.setRentEndDate(rentOfferCalendar.getRentEndDate());

            LocalDate rentStartDate = LocalDate.parse(rentOfferCalendar.getRentStartDate(), formatter);
            LocalDate rentEndDate = LocalDate.parse(rentOfferCalendar.getRentEndDate(), formatter);
            interval.setDaysMap(RentCalendarUtil.getDaysMapForDates(rentStartDate,
                    rentEndDate, Arrays.asList(rentOfferCalendar.getDays())));
            elasticRentOffer.setRentOfferCalendarInterval(interval);
            rentOfferElasticRepository.create(elasticRentOffer);
        }
    }

    @Override
    public void updateElasticRentOffer(RentOffer rentOffer) {
        rentOfferElasticRepository.updateByRentOffer(rentOffer);
    }
}
