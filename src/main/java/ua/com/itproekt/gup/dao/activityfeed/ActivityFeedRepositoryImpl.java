package ua.com.itproekt.gup.dao.activityfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

import javax.annotation.PostConstruct;

@Repository
public class ActivityFeedRepositoryImpl implements ActivityFeedRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @PostConstruct
    void init() {
        if (!mongoTemplate.collectionExists(Event.class)) {
            mongoTemplate.createCollection(Event.class);
        }
    }

    @Override
    public void createEvent(Event event) {
        mongoTemplate.insert(event);
    }

    @Override
    public void setFeedsViewed(EventFilterOptions eventFO) {
        Query query = new Query()
                .addCriteria(Criteria.where("uId").is(eventFO.getTargetUId()))
                .addCriteria(Criteria.where("viewed").ne(Boolean.TRUE));

        Update update = new Update()
                .set("viewed", Boolean.TRUE);

        mongoTemplate.updateMulti(query, update, Event.class);
    }

    @Override
    public EntityPage<Event> findEventsWihOptions(EventFilterOptions eventFO) {
        Query query = new Query();

        //should not be == null
        query.addCriteria(Criteria.where("uId").is(eventFO.getTargetUId()));

        if (eventFO.getAfterDate() != null) {
            query.addCriteria(Criteria.where("createdDate").gte(eventFO.getAfterDate()));
        }

//        if (eventFO.getType() != null) {
//            query.addCriteria(Criteria.where("type").is(eventFO.getType()));
//        }

        if (eventFO.isHideViewed()) {
            query.addCriteria(Criteria.where("viewed").ne(Boolean.TRUE));
        }


        query.skip(eventFO.getSkip());
        query.limit(eventFO.getLimit());

        return new EntityPage<>(mongoTemplate.count(query, Event.class),
                                mongoTemplate.find(query, Event.class));
    }

    @Override
    public void deleteEvent(String eventId) {
        Query query = new Query(Criteria.where("id").is(eventId));
        mongoTemplate.remove(query, Event.class);
    }

    @Override
    public void deleteEventsWihOptions(EventFilterOptions eventFO) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean eventExists(String eventId) {
        Query query = new Query(Criteria.where("id").is(eventId));
        return mongoTemplate.exists(query, Event.class);
    }
}
