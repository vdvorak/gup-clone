package ua.com.itproekt.gup.api.rest.offers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.Reservation;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/offersService")
public class OfferReservationRestController {

    @Autowired
    OffersService offersService;

    @Autowired
    ActivityFeedService activityFeedService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/reserve", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> reserveOffer(@PathVariable String offerId,
                                             @RequestBody Reservation reservation) {
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer offer = offersService.findById(offerId);
        if (!offer.getCanBeReserved() || (offer.getReservation() != null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // HttpStatus.CONFLICT
        }

        String authorId = offer.getAuthorId();
        String userId = SecurityOperations.getLoggedUserId();
        reservation.setProfileId(userId);
        offersService.reserveOffer(offerId, reservation);

        activityFeedService.createEvent(new Event(authorId, EventType.OFFER_RESERVATION, offerId, userId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/deleteReservation", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteReservation(@PathVariable String offerId) {
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String authorId = offersService.findById(offerId).getAuthorId();
        String userId = SecurityOperations.getLoggedUserId();
        if (!userId.equals(authorId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        offersService.deleteReservation(offerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
