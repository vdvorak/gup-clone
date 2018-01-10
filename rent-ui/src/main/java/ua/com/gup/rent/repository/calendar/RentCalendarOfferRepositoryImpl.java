package ua.com.gup.rent.repository.calendar;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RentCalendarOfferRepositoryImpl implements RentCalendarOfferRepository {

    private final Logger log = LoggerFactory.getLogger(RentCalendarOfferRepositoryImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(RentOfferCalendar.class)) {
            mongoTemplate.createCollection(RentOfferCalendar.class);
        }
    }

    @Override
    public void save(RentOfferCalendar rentOfferCalendar) {
        mongoTemplate.insert(rentOfferCalendar);
    }

    @Override
    public void update(RentOfferCalendar rentOfferCalendar) {
        mongoTemplate.save(rentOfferCalendar);
    }

    @Override
    public RentOfferCalendar findById(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query, RentOfferCalendar.class);
    }

    @Override
    public boolean exists(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, RentOfferCalendar.class);
    }

    @Override
    public List<RentOfferCalendar> findAll(){
        return mongoTemplate.findAll(RentOfferCalendar.class).stream().collect(Collectors.toList());
    }

    @Override
    public List<RentOfferCalendar> findAllByOfferId(String offerId) {
        Query query = new Query(Criteria.where("offerId").is(offerId));
        return mongoTemplate.find(query, RentOfferCalendar.class).stream().collect(Collectors.toList());
    }
}
