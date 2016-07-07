package ua.com.itproekt.gup.api.rest.offers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.OfferUserContactInfo;
import ua.com.itproekt.gup.model.offer.Reservation;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.reservationSchedule.ReservationScheduleService;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/offersService")
public class OfferReservationRestController {

    @Autowired
    OffersService offersService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    BankSession bankSession;

    @Autowired
    ReservationScheduleService reservationScheduleService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/{period}/reserve", method = RequestMethod.POST)
    public ResponseEntity<Void> reserveOffer(@PathVariable String offerId, Integer period) {
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer offer = offersService.findById(offerId);
        if (!offer.getCanBeReserved() || (offer.getReservation() != null)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (period < 1 || period > offer.getMaximumReservedPeriod()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        String userId = SecurityOperations.getLoggedUserId();

        Profile profile = profilesService.findById(userId);

        Reservation reservation = new Reservation()
                .setProfileId(userId)
                .setPeriod(period)
                .setUserContactInfo(new OfferUserContactInfo()
                        .setContactName(profile.getUsername())
                        .setEmail(profile.getEmail())
                        .setPhoneNumbers(profile.getContact().getContactPhones())
                        .setSkypeLogin(profile.getContact().getSkypeUserName()));


        if (bankSession.getUserBalance(userId) > 5*period) {
            offersService.reserveOffer(offerId, reservation);
            bankSession.investInOrganization(5555, userId, (long) (5 * period), 30, "success");
            reservationScheduleService.add(offerId);
        }


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
