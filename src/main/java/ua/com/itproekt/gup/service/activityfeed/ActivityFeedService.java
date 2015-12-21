package ua.com.itproekt.gup.service.activityfeed;

import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventFilterOptions;
import ua.com.itproekt.gup.util.EntityPage;

public interface ActivityFeedService {
    void createEvent(Event event);

    void deleteEvent(String eventId);

    void deleteEventsWihOptions(EventFilterOptions eventFO);

    boolean eventExists(String eventId);

    EntityPage<Event> findEventsWithOptions(EventFilterOptions eventFO);

    EntityPage<Event> findEventsWithOptionsAndSetViewed(EventFilterOptions eventFO);
}