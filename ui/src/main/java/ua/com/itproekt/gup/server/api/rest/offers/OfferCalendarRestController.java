package ua.com.itproekt.gup.server.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/offersService")
public class OfferCalendarRestController {

    @Autowired
    OffersService offersService;

    @Autowired
    ActivityFeedService activityFeedService;

    @RequestMapping(value = "/offer/{offerId}/calendar", method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCalendar(@PathVariable String offerId){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer offer = offersService.findById(offerId);
        if (!offer.getCanBeRented() || (offer.getRent() != null)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //TODO:
        }

        return new ResponseEntity<>("{\"" +
                "  ,\"saint lazy day\": {\"" +
                "    \"price\": 15000\"" +
                "    ,\"days\": [\"22.06.2015\",\"30.06.2016\"]\"" +
                "  }\"" +
                "}", HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/calendar", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createCalendar(@PathVariable String offerId,
                                                 @RequestBody String price, @RequestBody String firstDate, @RequestBody String lastDate){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer offer = offersService.findById(offerId);
        if (!offer.getCanBeRented() || (offer.getRent() != null)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //TODO:
        }

        return new ResponseEntity<>("{\"" +
            "  ,\"weekdays\": {\"" +
            "    \"price\": 10000\"" +
            "    ,\"days\": [\"01.06.2015\",\"21.06.2016\"]\"" +
            "  }\"" +
            "  ,\"weekends\": {\"" +
            "    \"price\": 12000\"" +
            "    ,\"days\": [\"22.06.2015\",\"31.06.2016\"]\"" +
            "  }\"" +
            "}", HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/calendar", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateCalendar(@PathVariable String offerId,
                                                 @RequestBody String firstDate, @RequestBody String lastDate){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer offer = offersService.findById(offerId);
        if (!offer.getCanBeRented() || (offer.getRent() != null)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //TODO:
        }

        return new ResponseEntity<>("{\"" +
                "  ,\"weekdays\": {\"" +
                "    \"price\": 10000\"" +
                "    ,\"days\": [\"01.06.2015\",\"21.06.2016\"]\"" +
                "  }\"" +
                "  ,\"weekends\": {\"" +
                "    \"price\": 12000\"" +
                "    ,\"days\": [\"22.06.2015\",\"31.06.2016\"]\"" +
                "  }\"" +
                "}", HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/calendar", method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCalendar(@PathVariable String offerId,
                                                 @RequestBody String firstDate, @RequestBody String lastDate){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer offer = offersService.findById(offerId);
        if (!offer.getCanBeRented() || (offer.getRent() != null)) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //TODO:
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
