package ua.com.itproekt.gup.api.rest.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.subscription.Subscription;
import ua.com.itproekt.gup.model.subscription.filter.SubscriptionFilterOptions;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

@Controller
@RequestMapping("/api/rest/")
public class SubscriptionRestController {

    @Autowired
    SubscriptionService subscriptionService;


    //------------------------------------------ Read -----------------------------------------------------------------
    @RequestMapping(value = "/subscription/read/{subscriptionId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subscription> getOfferById(@PathVariable String subscriptionId) {

        Subscription subscription = subscriptionService.find(subscriptionId);
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @RequestMapping(value = "/subscription/read/all", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Subscription>> getOfferById(@RequestBody SubscriptionFilterOptions subscriptionFilterOptions) {

        EntityPage<Subscription> subscriptions = subscriptionService.findWithFilterOption(subscriptionFilterOptions);

        if (subscriptions.getTotalEntities() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }


    //------------------------------------------ Create ----------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/subscription/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> createSubscription(@RequestBody OfferFilterOptions offerFilterOptions) {

        if (!SecurityOperations.isUserLoggedIn()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String userId = SecurityOperations.getLoggedUserId();
        subscriptionService.create(userId, offerFilterOptions);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //------------------------------------------ Update ----------------------------------------------------------------
    // Update futures will not be available


    //------------------------------------------ Delete ----------------------------------------------------------------
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/subscription/delete/{subscriptionId}", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteOffer(@PathVariable String subscriptionId) {

        int result = subscriptionService.delete(subscriptionId);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
