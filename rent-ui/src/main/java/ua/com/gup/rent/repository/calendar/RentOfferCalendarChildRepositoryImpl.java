package ua.com.gup.rent.repository.calendar;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendarChild;
import ua.com.gup.rent.repository.abstracted.RentOfferGenericRepositoryImpl;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class RentOfferCalendarChildRepositoryImpl extends RentOfferGenericRepositoryImpl<RentOfferCalendarChild, String> implements RentOfferCalendarChildRepository {

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOfferCalendarChild.class)) {
            mongoTemplate.createCollection(RentOfferCalendarChild.class);
        }
    }

    public RentOfferCalendarChildRepositoryImpl() {
        super(RentOfferCalendarChild.class);
    }

    @Override
    public List<RentOfferCalendarChild> findAllByOffer(RentOffer rentOffer) {
        Query query = new Query(Criteria.where("rentOfferId").is(rentOffer.getId()));
        return mongoTemplate.find(query, RentOfferCalendarChild.class);
    }

    @Override
    public void removeCalendars(RentOffer rentOffer) {
        Query query = new Query(Criteria.where("rentOfferId").is(rentOffer.getId()));
        mongoTemplate.remove(query, RentOfferCalendarChild.class);
    }
}
