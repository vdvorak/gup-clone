package ua.com.itproekt.gup.server.api.rest.activityfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventFilterOptions;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/activityFeed")
public class ActivityFeedRestController {

    @Autowired
    private ActivityFeedService activityFeedService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/event/read/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Event>> getUserEvents(@RequestBody EventFilterOptions eventFO) {

        String userId = SecurityOperations.getLoggedUserId();
        eventFO.setTargetUId(userId);

        EntityPage<Event> events = activityFeedService.findEventsWithOptions(eventFO);
        if (events.getEntities().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/event/id/{eventId}/setViewed", method = RequestMethod.GET)
    public ResponseEntity<Void> setViewed(@PathVariable String eventId) {

        activityFeedService.setViewed(eventId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/event/id/{eventId}/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteEvent(@PathVariable String eventId) {

        if (!activityFeedService.eventExists(eventId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        activityFeedService.deleteEvent(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/event/id/delete/all", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteEvent() {

        try {
            String userId = SecurityOperations.getLoggedUserId();
            activityFeedService.deleteAllEvents(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/event/set/viewed/all", method = RequestMethod.POST)
    public ResponseEntity<Void> setViewed() {

        try {
            String userId = SecurityOperations.getLoggedUserId();
            activityFeedService.setAllViewed(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
