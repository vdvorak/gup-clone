package ua.com.gup.rent.service.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.rent.ElasticRentOffer;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.repository.RentOfferElasticRepository;

@Service
public class RentOfferElasticServiceImpl implements RentOfferElasticService {

    @Autowired
    private RentOfferElasticRepository rentOfferElasticRepository;

    @Async
    public void asyncSaveRentOfferElastic(RentOffer rentOffer) {
        for (RentOfferCalendar rentOfferCalendar : rentOffer.getRentOfferCalendars()) {
            ElasticRentOffer elasticRentOffer = new ElasticRentOffer(rentOffer);
            elasticRentOffer.setRentOfferCalendar(rentOfferCalendar);
            rentOfferElasticRepository.create(elasticRentOffer);
        }
    }
}
