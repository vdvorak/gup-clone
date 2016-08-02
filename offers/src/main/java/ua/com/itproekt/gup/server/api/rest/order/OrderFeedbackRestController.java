package ua.com.itproekt.gup.server.api.rest.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.order.OrderStatus;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.validation.Valid;


@Controller
@RequestMapping("/api/rest/orderService")
public class OrderFeedbackRestController {


    private final ResponseEntity<Void> ok = new ResponseEntity<>(HttpStatus.OK);
    private final ResponseEntity<Void> badRequest = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    private final ResponseEntity<Void> forbidden = new ResponseEntity<>(HttpStatus.FORBIDDEN);
    private final ResponseEntity<Void> notFound = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    private final ResponseEntity<Void> methodNotAllowed = new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    private final ResponseEntity<Void> notAcceptable = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);


    @Autowired
    OrderService orderService;
    @Autowired
    ProfilesService profilesService;
    @Autowired
    OffersService offersService;
    @Autowired
    ActivityFeedService activityFeedService;


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

        if (!userId.equals(oldOrder.getBuyerId())) {
            return badRequest;
        }


        feedbackPreparatorFromBuyer(order, oldOrder);
        return ok;
    }


    /**
     * Method can be called only if order status is WAITING_SELLER_FEEDBACK and oly by seller;
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

        if (!userId.equals(oldOrder.getSellerId())) {
            return badRequest;
        }


        feedbackPreparatorFromSeller(order, oldOrder);
        return ok;
    }


    // --------------------------------------------- UPDATE --------------------------------------

    /**
     * @param order
     * @return
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

        if (!userId.equals(oldOrder.getBuyerId())) {
            return forbidden;
        }

        if (oldOrder.getOrderStatus() != OrderStatus.COMPLETED) {
            return badRequest;
        }

        editFeedbackPreparatorFromBuyer(order, oldOrder);

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
        eventPreparatorForSeller(order, EventType.ORDER_BUYER_COMMENT);

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

        eventPreparatorForSeller(order, EventType.ORDER_BUYER_COMMENT_EDIT);
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

        eventPreparatorForBuyer(order, EventType.ORDER_COMPLETED);

        orderService.findAndUpdate(oldOrder);

    }


}
