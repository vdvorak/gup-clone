package ua.com.itproekt.gup.server.api.rest.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.model.activityfeed.Event;
import ua.com.gup.model.activityfeed.EventType;
import ua.com.gup.model.order.Order;
import ua.com.gup.model.order.OrderFeedback;
import ua.com.gup.model.order.OrderFeedbackOptions;
import ua.com.gup.model.order.OrderStatus;
import ua.com.gup.model.profiles.Profile;
import ua.com.gup.service.activityfeed.ActivityFeedService;
import ua.com.gup.service.order.OrderService;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.SecurityOperations;

import javax.validation.Valid;
import java.util.Map;


@Controller
@RequestMapping("/api/rest/orderService")
public class OrderFeedbackRestController {

    private final ResponseEntity<Void> ok = new ResponseEntity<>(HttpStatus.OK);
    private final ResponseEntity<Void> badRequest = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    private final ResponseEntity<Void> forbidden = new ResponseEntity<>(HttpStatus.FORBIDDEN);
    private final ResponseEntity<Void> notFound = new ResponseEntity<>(HttpStatus.NOT_FOUND);


    @Autowired
    private OrderService orderService;

    @Autowired
    private ProfilesService profilesService;

    @Autowired
    private ActivityFeedService activityFeedService;


    // --------------------------------------------- CREATE --------------------------------------

    /**
     * Method can be called only if order status is RECEIVED and oly by buyer;
     *
     * @param order - can include only OrderFeedback with String feedback and int point;
     * @return - status code 200 if Ok, 400 - user not buyer or Order doesn't have status RECEIVED,
     * 404 - not found order.
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/feedback/createBuyerFeedback", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> buyerFeedback(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        if (oldOrder.getOrderStatus() != OrderStatus.COMPLETED) {
            return badRequest;
        }

        if (!oldOrder.getBuyerId().equals(userId)) {
            return badRequest;
        }


        feedbackPreparatorFromBuyer(order, oldOrder);
        return ok;
    }


    /**
     * Method can be called only if order status is COMPLETED and oly by seller;
     *
     * @param order - can include only OrderFeedback with String orderId, String feedback, int point;
     * @return - status code 200 if Ok, 400 - user not seller or Order doesn't have status COMPLETE
     * or order doesn't have feedback,
     * 404 - not found order.
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/feedback/createSellerFeedback", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createSellerFeedback(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        if (oldOrder.getOrderStatus() != OrderStatus.COMPLETED) {
            return badRequest;
        }

        if (oldOrder.getOrderFeedback() == null) {
            return badRequest;
        }

        if (!oldOrder.getSellerId().equals(userId)) {
            return badRequest;
        }


        feedbackPreparatorFromSeller(order, oldOrder);
        return ok;
    }


    // --------------------------------------------- UPDATE --------------------------------------

    /**
     * Edit buyer feedback
     *
     * @param order - must contain orderID, orderFeedback.buyerFeedbackList
     * @return - status code 200 if Ok, 404 - not found order or feedback, 403 - user is not buyer in this order
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/feedback/editBuyerFeedback", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editBuyerFeedback(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        if (oldOrder.getOrderFeedback() == null) {
            return notFound;
        }

        if (!oldOrder.getBuyerId().equals(userId)) {
            return forbidden;
        }

        editFeedbackPreparatorFromBuyer(order, oldOrder);

        return ok;
    }


    /**
     * @param orderFeedbackOptions - enum LIKE, DISLIKE or SPAM
     * @param orderId              - order id
     * @return - status code 200 if all is Ok, 403 - if current user is buyer,
     * 404 - order or feedback in order is not found.
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/feedback/addBuyerFeedbackOption/{orderId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addBuyerFeedbackOption(@Valid @RequestBody OrderFeedbackOptions orderFeedbackOptions, @PathVariable String orderId) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(orderId);
        if (oldOrder == null) {
            return notFound;
        }

        if (oldOrder.getOrderFeedback() == null) {
            return notFound;
        }

        if (oldOrder.getBuyerId().equals(userId)) {
            return forbidden;
        }

        newBuyerFeedbackOptionPreparator(oldOrder, orderFeedbackOptions, userId);

        return ok;
    }

    // -------------------------------------------------------- Add options for feedback (like, dislike, spam) ---------

    /**
     * @param orderFeedbackOptions - the order feedback option.
     * @param orderId              - order id
     * @return - status code 200 if all is Ok, 403 - if current user is seller,
     * 404 - order or feedback in order is not found.
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/feedback/addSellerFeedbackOption/{orderId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addSellerFeedbackOption(@Valid @RequestBody OrderFeedbackOptions orderFeedbackOptions, @PathVariable String orderId) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(orderId);
        if (oldOrder == null) {
            return notFound;
        }

        if (oldOrder.getOrderFeedback() == null) {
            return notFound;
        }

        if (oldOrder.getBuyerId().equals(userId)) {
            return forbidden;
        }

        newSellerFeedbackOptionPreparator(oldOrder, orderFeedbackOptions, userId);

        return ok;
    }


    // --------------------------------------------- DELETE --------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/feedback/deleteBuyerFeedback", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteBuyerFeedback(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        if (oldOrder.getOrderFeedback() == null) {
            return notFound;
        }

        if (!oldOrder.getBuyerId().equals(userId)) {
            return forbidden;
        }

        oldOrder.setOrderFeedback(null);
        orderService.findAndUpdate(oldOrder);
        activityFeedService.createEvent(eventPreparatorForSeller(oldOrder, EventType.ORDER_BUYER_DELETE_FEEDBACK));

        return ok;
    }


    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/feedback/deleteSellerFeedback", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteSellerFeedback(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        if (oldOrder.getOrderFeedback() == null) {
            return notFound;
        }

        if (!oldOrder.getSellerId().equals(userId)) {
            return forbidden;
        }

        oldOrder.getOrderFeedback().setSellerComment(null);

        clearSellerFeedback(oldOrder);

        orderService.findAndUpdate(oldOrder);
        activityFeedService.createEvent(eventPreparatorForSeller(oldOrder, EventType.ORDER_SELLER_DELETE_FEEDBACK));

        return ok;
    }


    //------------------------------------------ Helpers methods -----------------------------------------------------


    private Event eventPreparatorForSeller(Order order, EventType eventType) {
        Profile profile = profilesService.findById(order.getBuyerId());

        return new Event()
                .setTargetUId(order.getSellerId())
                .setType(eventType)
                .setContentStoreId(order.getOfferId())
                .setContentStoreTitle(order.getOfferTitle())
                .setContentId(order.getId())
                .setMakerId(order.getBuyerId())
                .setImgId(order.getOfferMainImageId())
                .setMakerName(profile.getUsername());
    }

    private Event eventPreparatorForBuyer(Order order, EventType eventType) {
        Profile profile = profilesService.findById(order.getSellerId());

        return new Event()
                .setTargetUId(order.getBuyerId())
                .setType(eventType)
                .setContentStoreId(order.getOfferId())
                .setContentStoreTitle(order.getOfferTitle())
                .setContentId(order.getId())
                .setMakerId(order.getSellerId())
                .setImgId(order.getOfferMainImageId())
                .setMakerName(profile.getUsername());
    }


    /**
     * Prepare feedback from buyer, update order with this feedback and send Event notification to seller,
     * change order status to WAITING_SELLER_FEEDBACK.
     *
     * @param order
     * @param oldOrder
     */
    private void feedbackPreparatorFromBuyer(Order order, Order oldOrder) {

        OrderFeedback orderFeedback = new OrderFeedback();

        int point = order.getOrderFeedback().getPoint();
        String feedbackText = order.getOrderFeedback().getBuyerFeedbackList().get(0).getFeedbackText();


        orderFeedback.addNewBuyerFeedback(feedbackText, point);


        oldOrder.setOrderFeedback(orderFeedback);

        activityFeedService.createEvent(eventPreparatorForSeller(order, EventType.ORDER_BUYER_COMMENT));

        orderService.findAndUpdate(oldOrder);
    }


    /**
     * Update previous buyer feedback
     *
     * @param order
     * @param oldOrder
     */
    private void editFeedbackPreparatorFromBuyer(Order order, Order oldOrder) {

        OrderFeedback oldOrderFeedback = oldOrder.getOrderFeedback();

        String feedbackText = order.getOrderFeedback().getBuyerFeedbackList().get(0).getFeedbackText();

        oldOrderFeedback.addUpdatedBuyerFeedback(feedbackText);

        activityFeedService.createEvent(eventPreparatorForSeller(order, EventType.ORDER_BUYER_COMMENT_EDIT));

        orderService.findAndUpdate(oldOrder);
    }


    /**
     * Prepare feedback from seller, update order with this feedback and send Event notification to seller,
     * change order status to COMPLETED.
     *
     * @param order
     * @param oldOrder
     */
    private void feedbackPreparatorFromSeller(Order order, Order oldOrder) {

        oldOrder.getOrderFeedback().setSellerComment(order.getOrderFeedback().getSellerComment());
        oldOrder.getOrderFeedback().setSellerCommentDateToCurrentDate();

        activityFeedService.createEvent(eventPreparatorForBuyer(order, EventType.ORDER_COMPLETED));
        orderService.findAndUpdate(oldOrder);

    }


    private void clearSellerFeedback(Order oldOrder) {
        oldOrder.getOrderFeedback()
                .setSellerComment(null)
                .setSellerCommentDate(null)
                .setSellerFeedbackOptionsMap(null);
    }

    /**
     * After options will be updated, method will create notification for buyer.
     *
     * @param oldOrder
     * @param orderFeedbackOption
     * @param userId
     */
    private void newBuyerFeedbackOptionPreparator(Order oldOrder, OrderFeedbackOptions orderFeedbackOption, String userId) {

        Map<String, OrderFeedbackOptions> map = oldOrder.getOrderFeedback().getBuyerFeedbackOptionsMap();

        feedbackMapRepeatOptionsResolver(map, userId, orderFeedbackOption);

        oldOrder.getOrderFeedback().setBuyerFeedbackOptionsMap(map);
        orderService.findAndUpdate(oldOrder);

        switch (orderFeedbackOption) {
            case LIKE:
                activityFeedService.createEvent(eventPreparatorForBuyer(oldOrder, EventType.ORDER_BUYER_FEEDBACK_LIKE));
                break;
            case DISLIKE:
                activityFeedService.createEvent(eventPreparatorForBuyer(oldOrder, EventType.ORDER_BUYER_FEEDBACK_DISLIKE));
                break;
            case SPAM:
                activityFeedService.createEvent(eventPreparatorForBuyer(oldOrder, EventType.ORDER_BUYER_FEEDBACK_SPAM));
                break;
        }
    }


    private void newSellerFeedbackOptionPreparator(Order oldOrder, OrderFeedbackOptions orderFeedbackOption, String userId) {

        Map<String, OrderFeedbackOptions> map = oldOrder.getOrderFeedback().getSellerFeedbackOptionsMap();

        feedbackMapRepeatOptionsResolver(map, userId, orderFeedbackOption);

        oldOrder.getOrderFeedback().setSellerFeedbackOptionsMap(map);
        orderService.findAndUpdate(oldOrder);

        switch (orderFeedbackOption) {
            case LIKE:
                activityFeedService.createEvent(eventPreparatorForSeller(oldOrder, EventType.ORDER_SELLER_FEEDBACK_LIKE));
                break;
            case DISLIKE:
                activityFeedService.createEvent(eventPreparatorForSeller(oldOrder, EventType.ORDER_SELLER_FEEDBACK_DISLIKE));
                break;
            case SPAM:
                activityFeedService.createEvent(eventPreparatorForSeller(oldOrder, EventType.ORDER_SELLER_FEEDBACK_SPAM));
                break;
        }
    }


    /**
     * If user send exist option he made in past - it will be remove.
     * If user send another option - it will be create and old option (if exist) will be remove
     *
     * @param map
     * @param userId
     * @param orderFeedbackOptions
     */
    private void feedbackMapRepeatOptionsResolver(Map<String, OrderFeedbackOptions> map, String userId, OrderFeedbackOptions orderFeedbackOptions) {
        if (map.containsKey(userId)) {
            if (map.get(userId) == orderFeedbackOptions) {
                map.remove(userId);
            } else {
                map.remove(userId);
                map.put(userId, orderFeedbackOptions);
            }
        } else {
            map.put(userId, orderFeedbackOptions);
        }
    }


}
