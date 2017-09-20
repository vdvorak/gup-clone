package ua.com.gup.service.activityfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.repository.dao.activityfeed.ActivityFeedRepository;
import ua.com.gup.model.activityfeed.Event;
import ua.com.gup.model.activityfeed.EventFilterOptions;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.EntityPage;

@Service
public class ActivityFeedServiceImpl implements ActivityFeedService {

    @Autowired
    ActivityFeedRepository activityFeedRepository;

    @Autowired
    ProfilesService profilesService;

    @Override
    public void createEvent(Event event) {
        activityFeedRepository.createEvent(event);

        //ToDo somebody was drink when put this
//        Profile profile = profilesService.findById(event.getTargetUId());
//        if (profile != null) {
//            event.setMakerName(profile.getUsername());
//            event.setMakerImgId(profile.getImgId());
//            activityFeedRepository.createEvent(event);
//        }
    }

    @Override
    public EntityPage<Event> findEventsWithOptions(EventFilterOptions eventFO) {
        return activityFeedRepository.findEventsWihOptions(eventFO);
    }

    @Override
    public void setViewed(String eventId) {
        activityFeedRepository.setViewed(eventId);
    }

    @Override
    public void setAllViewed(String userId) {
        activityFeedRepository.setAllViewed(userId);
    }

    @Override
    public void deleteAllEvents(String userId) {
        activityFeedRepository.deleteAllEvents(userId);
    }

    @Override
    public void deleteEvent(String eventId) {
        activityFeedRepository.deleteEvent(eventId);
    }

    @Override
    public void deleteEventsWihOptions(EventFilterOptions eventFO) {
        activityFeedRepository.deleteEventsWihOptions(eventFO);
    }

    @Override
    public boolean eventExists(String eventId) {
        return activityFeedRepository.eventExists(eventId);
    }
}
