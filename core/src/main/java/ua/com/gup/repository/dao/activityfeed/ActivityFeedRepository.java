package ua.com.gup.repository.dao.activityfeed;

import ua.com.gup.model.activityfeed.Event;
import ua.com.gup.model.activityfeed.EventFilterOptions;
import ua.com.gup.util.EntityPage;

public interface ActivityFeedRepository {
    EntityPage<Event> findEventsWihOptions(EventFilterOptions eventFO);

    void deleteEvent(String eventId);

    void deleteEventsWihOptions(EventFilterOptions eventFO);

    boolean eventExists(String eventId);

    void createEvent(Event event);

    void setFeedsViewed(EventFilterOptions eventFO);

    void setViewed(String eventId);

    void setAllViewed(String userId);

    void deleteAllEvents(String userId);
}
