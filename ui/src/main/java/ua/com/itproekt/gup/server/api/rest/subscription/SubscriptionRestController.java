package ua.com.itproekt.gup.server.api.rest.subscription;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    @CrossOrigin
    @RequestMapping(value = "/subscription/read/{subscriptionId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable String subscriptionId) {

        Subscription subscription = subscriptionService.find(subscriptionId);
        if (subscription == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @RequestMapping(value = "/subscription/read/all", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Subscription>> getSubscriptionWithFilter(@RequestBody SubscriptionFilterOptions subscriptionFilterOptions) {

        EntityPage<Subscription> subscriptions = subscriptionService.findWithFilterOption(subscriptionFilterOptions);

        if (subscriptions.getTotalEntities() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }


    //------------------------------------------ Create ----------------------------------------------------------------
    @CrossOrigin
//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/subscription/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
         public ResponseEntity<Void> createSubscription(@RequestBody SubscriptionCreateWrapper subscriptionCreateWrapper) {


        // we can not have empty filter options object
        if (subscriptionCreateWrapper.getOfferFilterOptions() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Subscription subscription = new Subscription();
        subscription.setOfferFilterOptions(subscriptionCreateWrapper.getOfferFilterOptions());

        if (!SecurityOperations.isUserLoggedIn() && StringUtils.isNotBlank(subscriptionCreateWrapper.getNotAuthEmail())) {
                subscription.setNotAuthEmail(subscriptionCreateWrapper.getNotAuthEmail());
                subscriptionService.create(subscription);
                return new ResponseEntity<>(HttpStatus.OK);
        }

        String userId = SecurityOperations.getLoggedUserId();
        subscriptionService.create(userId, subscriptionCreateWrapper.getOfferFilterOptions());
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @RequestMapping(value = "/subscription/create", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<CreatedObjResp> createSubscription(@RequestBody OfferFilterOptions offerFilterOptions) {
//        String email = SecurityOperations.getLoggedUserId();
//        subscriptionService.create(email, offerFilterOptions);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


    //------------------------------------------ Update ----------------------------------------------------------------
    // Update futures will not be available


    //------------------------------------------ Delete ----------------------------------------------------------------
    @CrossOrigin
//    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/subscription/delete/{subscriptionId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOffer(@PathVariable String subscriptionId) {

        int result = subscriptionService.delete(subscriptionId);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
