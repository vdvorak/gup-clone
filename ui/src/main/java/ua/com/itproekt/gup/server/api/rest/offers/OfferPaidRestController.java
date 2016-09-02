package ua.com.itproekt.gup.server.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.dto.OfferInfo;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/offersPaidService")
public class OfferPaidRestController {

    @Autowired
    OffersService offersService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    SeoSequenceService seoSequenceService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    private StorageService storageService;

    @Autowired
    BankSession bankSession;

    //------------------------------------------ Paid -----------------------------------------------------------------

    @CrossOrigin
    @RequestMapping(value = "/offer/check/{id}", method = RequestMethod.POST,
                  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferInfo> checkOfferById(@PathVariable String id) {
        Offer offer = offersService.findOfferAndIncViews(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        //if user is author - he will receive additional fields
        if (offer.getAuthorId().equals(userId)) {
            return new ResponseEntity<>(offersService.getPrivateOfferInfoByOffer(offer), HttpStatus.OK);
        }

        return new ResponseEntity<>(offersService.getPublicOfferInfoByOffer(offer), HttpStatus.OK);
    }

    @CrossOrigin
//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/paid/marked/offers", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> paidMarkedOffers(@RequestBody String offerId) {
        String userId = SecurityOperations.getLoggedUserId();
        String amount = bankSession.getBonusByUserId(userId);
//        return new ResponseEntity<>("userId="+userId+"; amount="+amount+";", HttpStatus.OK);
        return new ResponseEntity<>("offerId="+offerId+"; userId="+userId+";", HttpStatus.OK);
    }

}
