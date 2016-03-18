package ua.com.itproekt.gup.dao.activityfeed;

import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

public interface ActivityFeedRepository {
    EntityPage<Event> findEventsWihOptions(EventFilterOptions eventFO);

    void deleteEvent(String eventId);

    void deleteEventsWihOptions(EventFilterOptions eventFO);

    boolean eventExists(String eventId);

    void createEvent(Event event);

    void setFeedsViewed(EventFilterOptions eventFO);

    void setViewed(String eventId);
}
