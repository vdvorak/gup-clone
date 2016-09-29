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

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/calendar", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> calendarOffer(@PathVariable String offerId,
                                                        @RequestBody String firstDate, @RequestBody String lastDate){
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

}
