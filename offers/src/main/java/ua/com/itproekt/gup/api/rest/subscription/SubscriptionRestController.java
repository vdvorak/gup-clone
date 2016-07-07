package ua.com.itproekt.gup.api.rest.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.SecurityOperations;

@Controller
@RequestMapping("/api/rest/subscriptionRest")
public class SubscriptionRestController {

    @Autowired
    SubscriptionService subscriptionService;


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/subscription/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> createOffer(@RequestBody OfferFilterOptions offerFilterOptions) {

        if (!SecurityOperations.isUserLoggedIn()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String userId = SecurityOperations.getLoggedUserId();
        subscriptionService.create(userId, offerFilterOptions);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
