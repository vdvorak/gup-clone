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
public class CalendarRentRestController {

    @Autowired
    OffersService offersService;

    @Autowired
    ActivityFeedService activityFeedService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/rent", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> rentOffer(@PathVariable String offerId,
                                                    @RequestBody RentedOfferPeriodInfo rentedOfferPeriodInfo) {
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer offer = offersService.findById(offerId);
        if (!offer.getCanBeRented() || (offer.getRent() != null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // HttpStatus.CONFLICT?
        }

        String authorId = offer.getAuthorId();
        String userId = SecurityOperations.getLoggedUserId();
        rentedOfferPeriodInfo.setProfileId(userId);
        offersService.rentOffer(offerId, rentedOfferPeriodInfo);

        activityFeedService.createEvent(new Event(authorId, EventType.OFFER_RENT, offerId, userId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/deleteRent/{rentId}", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteRent(@PathVariable String offerId,
                                           @PathVariable String rentId) {
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String authorId = offersService.findById(offerId).getAuthorId();
        String userId = SecurityOperations.getLoggedUserId();
        if (!userId.equals(authorId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        offersService.deleteRent(offerId, rentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
