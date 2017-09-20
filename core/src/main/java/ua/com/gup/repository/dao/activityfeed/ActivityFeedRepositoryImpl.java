package ua.com.gup.repository.dao.activityfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import ua.com.gup.model.activityfeed.Event;
import ua.com.gup.model.activityfeed.EventFilterOptions;
import ua.com.gup.util.EntityPage;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

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

    @Deprecated
    @Override
    public void setFeedsViewed(EventFilterOptions eventFO) {
        Query query = new Query()
                .addCriteria(Criteria.where("targetUId").is(eventFO.getTargetUId()))
                .addCriteria(Criteria.where("isViewed").ne(Boolean.TRUE));

        Update update = new Update()
                .set("isViewed", Boolean.TRUE);

        mongoTemplate.updateMulti(query, update, Event.class);
    }

    @Override
    public void setViewed(String eventId) {
        Query query = new Query()
                .addCriteria(Criteria.where("id").is(eventId));

        Update update = new Update()
                .set("isViewed", Boolean.TRUE);

        mongoTemplate.updateFirst(query, update, Event.class);
    }

    @Override
    public void setAllViewed(String userId) {
        Query query = new Query()
                .addCriteria(Criteria.where("targetUId").is(userId))
                .addCriteria(Criteria.where("isViewed").ne(Boolean.TRUE));

        Update update = new Update()
                .set("isViewed", Boolean.TRUE);

        mongoTemplate.updateMulti(query, update, Event.class);
    }

    @Override
    public EntityPage<Event> findEventsWihOptions(EventFilterOptions eventFO) {
        Query query = new Query();

        //should not be == null
        query.addCriteria(Criteria.where("targetUId").is(eventFO.getTargetUId()));

        if (eventFO.getAfterDate() != null) {
            query.addCriteria(Criteria.where("createdDate").gte(eventFO.getAfterDate()));
        }

//        if (eventFO.getType() != null) {
//            query.addCriteria(Criteria.where("type").is(eventFO.getType()));
//        }

//      * if (eventFO.isHideViewed()) {
//      *     query.addCriteria(Criteria.where("isViewed").ne(Boolean.TRUE));
//      * }

        if (!eventFO.isShowViewed()) {
            query.addCriteria(Criteria.where("isViewed").ne(Boolean.TRUE));
        }

        query.skip(eventFO.getSkip());
        query.limit(eventFO.getLimit());

        long totalEntities = mongoTemplate.count(query, Event.class);
        List<Event> entities = mongoTemplate.find(query, Event.class);

        // reverse result to show newest events first
        Collections.reverse(entities);

        return new EntityPage<>(totalEntities, entities);
    }

    @Override
    public void deleteEvent(String eventId) {
        Query query = new Query(Criteria.where("id").is(eventId));
        mongoTemplate.remove(query, Event.class);
    }

    @Override
    public void deleteAllEvents(String userId) {
        Query query = new Query(Criteria.where("targetUId").is(userId));
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
