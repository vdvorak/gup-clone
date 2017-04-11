package ua.com.itproekt.gup.subscription;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ua.com.itproekt.gup.model.subscription.Subscription;
import ua.com.itproekt.gup.model.subscription.filter.SubscriptionFilterOptions;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.util.List;


@Controller
@RequestMapping("/api/rest/")
public class SubscriptionRestController {

    //------------------------------------------ Read -----------------------------------------------------------------

    @CrossOrigin
    @RequestMapping(value = "/subscription/read/{subscriptionId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable String subscriptionId) {
//TODO        Subscription subscription = subscriptionService.find(subscriptionId);
//TODO        if (subscription == null) {
//TODO            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//TODO        }
//TODO        return new ResponseEntity<>(subscription, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/subscription/read/my", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Subscription>> getAllCurrentUserSubscriptions() {
//TODO        String userId = SecurityOperations.getLoggedUserId();
//TODO        if (userId == null) {
//TODO            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//TODO        }
//TODO        SubscriptionFilterOptions subscriptionFilterOptions = new SubscriptionFilterOptions();
//TODO        subscriptionFilterOptions.setUserId(userId);
//TODO        return new ResponseEntity<>(subscriptionService.findWithFilterOption(subscriptionFilterOptions), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @CrossOrigin
    @RequestMapping(value = "/subscription/read/all", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Subscription>> getSubscriptionWithFilter(@RequestBody SubscriptionFilterOptions subscriptionFilterOptions) {
//TODO        List<Subscription> subscriptions = subscriptionService.findWithFilterOption(subscriptionFilterOptions);
//TODO        if (subscriptions.size() == 0) {
//TODO            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//TODO        }
//TODO        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //------------------------------------------ Create ----------------------------------------------------------------
//TODO    @CrossOrigin
//TODO    @RequestMapping(value = "/subscription/create", method = RequestMethod.POST,
//TODO            consumes = MediaType.APPLICATION_JSON_VALUE)
//TODO    public ResponseEntity<String> createSubscription(@RequestBody SubscriptionCreateWrapper subscriptionCreateWrapper) {
//TODO        if (subscriptionCreateWrapper.getOfferFilterOptions() == null || StringUtils.isBlank(subscriptionCreateWrapper.getEmail())) {
//TODO            return new ResponseEntity<>("FilterOption or email is not present in your request.", HttpStatus.BAD_REQUEST);
//TODO        }
//TODO        if (subscriptionService.create(subscriptionCreateWrapper.getEmail(), subscriptionCreateWrapper.getOfferFilterOptions())) {
//TODO            return new ResponseEntity<>(HttpStatus.OK);
//TODO        } else {
//TODO            return new ResponseEntity<>("The user already has the same subscription for the current email.", HttpStatus.BAD_REQUEST);
//TODO        }
//TODO    }


    //------------------------------------------ Update ----------------------------------------------------------------


    //------------------------------------------ Delete ----------------------------------------------------------------
//TODO    @CrossOrigin
//TODO    @RequestMapping(value = "/subscription/delete/{subscriptionId}", method = RequestMethod.DELETE)
//TODO    public ResponseEntity<Void> deleteOffer(@PathVariable String subscriptionId) {
//TODO
//TODO        int result = subscriptionService.delete(subscriptionId);
//TODO
//TODO        if (result == 0) {
//TODO            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//TODO        }
//TODO        return new ResponseEntity<>(HttpStatus.OK);
//TODO    }

}
