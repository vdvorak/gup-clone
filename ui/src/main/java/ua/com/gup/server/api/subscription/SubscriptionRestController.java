//package ua.com.itproekt.gup.server.api.rest.subscription;
//
//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import ua.com.itproekt.gup.model.subscription.Subscription;
//import ua.com.itproekt.gup.model.subscription.filter.SubscriptionFilterOptions;
//import ua.com.itproekt.gup.service.subscription.SubscriptionService;
//import ua.com.itproekt.gup.util.SecurityOperations;
//
//import java.util.List;
//
//
///**
// * REST controllers for work with subscriptions.
// *
// * @author Kobylyatskyy Alexander
// */
//@Controller
//@RequestMapping("/swagger/rest/")
//public class SubscriptionRestController {
//
//    @Autowired
//    private SubscriptionService subscriptionService;
//
//
//    //------------------------------------------ Read -----------------------------------------------------------------
//
//    /**
//     * Find and return one subscription by it's ID.
//     *
//     * @param subscriptionId - the subscription ID.
//     * @return - the subscription.
//     */
//    @CrossOrigin
//    @RequestMapping(value = "/subscription/read/{subscriptionId}", method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable String subscriptionId) {
//
//        Subscription subscription = subscriptionService.find(subscriptionId);
//        if (subscription == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(subscription, HttpStatus.OK);
//    }
//
//
//    /**
//     * Return all current user's subscriptions.
//     *
//     * @return - the current user's subscriptions.
//     */
//    @CrossOrigin
//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value = "/subscription/read/my", method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Subscription>> getAllCurrentUserSubscriptions() {
//
//        String userId = SecurityOperations.getLoggedUserId();
//
//        if (userId == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        SubscriptionFilterOptions subscriptionFilterOptions = new SubscriptionFilterOptions();
//        subscriptionFilterOptions.setUserId(userId);
//        return new ResponseEntity<>(subscriptionService.findWithFilterOption(subscriptionFilterOptions), HttpStatus.OK);
//    }
//
//    // FixMe Think, do we really need this method?
////    @CrossOrigin
////    @RequestMapping(value = "/subscription/read/all", method = RequestMethod.POST,
////            produces = MediaType.APPLICATION_JSON_VALUE)
////    public ResponseEntity<List<Subscription>> getSubscriptionWithFilter(@RequestBody SubscriptionFilterOptions subscriptionFilterOptions) {
////
////        List<Subscription> subscriptions = subscriptionService.findWithFilterOption(subscriptionFilterOptions);
////
////        if (subscriptions.size() == 0) {
////            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////        }
////
////        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
////    }
//
//
//    //------------------------------------------ Create ----------------------------------------------------------------
//    @CrossOrigin
//    @RequestMapping(value = "/subscription/create", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> createSubscription(@RequestBody SubscriptionCreateWrapper subscriptionCreateWrapper) {
//
//        // we can not have empty filter options object
//        if (subscriptionCreateWrapper.getOfferFilterOptions() == null || StringUtils.isBlank(subscriptionCreateWrapper.getEmail())) {
//            return new ResponseEntity<>("FilterOption or email is not present in your request.", HttpStatus.BAD_REQUEST);
//        }
//
//        if (subscriptionService.create(subscriptionCreateWrapper.getEmail(), subscriptionCreateWrapper.getOfferFilterOptions())) {
//            return new ResponseEntity<>(HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("The user already has the same subscription for the current email.", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//
//    //------------------------------------------ Update ----------------------------------------------------------------
//    // Update futures will not be available
//
//
//    //------------------------------------------ Delete ----------------------------------------------------------------
//    @CrossOrigin
//    @RequestMapping(value = "/subscription/delete/{subscriptionId}", method = RequestMethod.DELETE)
//    public ResponseEntity<Void> deleteOffer(@PathVariable String subscriptionId) {
//
//        int result = subscriptionService.delete(subscriptionId);
//
//        if (result == 0) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
//}
