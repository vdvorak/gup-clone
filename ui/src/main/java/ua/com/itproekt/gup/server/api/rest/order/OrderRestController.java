package ua.com.itproekt.gup.server.api.rest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderStatus;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.TransportCompany;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Controller
@RequestMapping("/api/rest/orderService")
public class OrderRestController {

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


    //------------------------------------------ Read -----------------------------------------------------------------

    /**
     * @param id - order id
     * @return - return order and status code, or just redirect on 404 if order isn't exist
     */
    @CrossOrigin
    @RequestMapping(value = "/order/read/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable String id) {
        Order order = orderService.findById(id);

        if (order == null) {
            throw new ResourceNotFoundException();
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    /**
     * Controller return only user's orders.
     *
     * @param orderFilterOptions - order filter options
     * @return - return List of orders and status code 200
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/order/read/all", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrderAll(@RequestBody OrderFilterOptions orderFilterOptions) {
        return new ResponseEntity<>(orderService.getAllOrders(orderFilterOptions), HttpStatus.OK);
    }


    //------------------------------------------ Create -------------------------------------------------------------

    /**
     * @param order - order include: offerId, orderAddress, paymentMethod, orderType, orderComment
     * @return - return status code if Ok, 400 - order not valid, 403 - if user is offer author, 404 - offer not found, 405 - if user is not buyer
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/create", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@Valid @RequestBody Order order) {

        Offer offer = offersService.findById(order.getOfferId());
        if (offer == null) {
            return notFound;
        }

        String userId = SecurityOperations.getLoggedUserId();
        if (userId.equals(offer.getAuthorId())) {
            return forbidden;
        }

        if (orderService.isOrderValid(order, offer)) {
            // create order
            orderService.create(userId, order, offer);
        } else {
            return badRequest;
        }
        return ok;
    }

//------------------------------------------ Update -------------------------------------------------------------

    /**
     * This method can only update order Address, payment method only before seller will accept Order.
     *
     * @param order - updated order.
     * @return - return status 200 code if Ok, 401 - not authorized, 400 - user is not buyer or not valid payment or shipping method,
     * 404 - not found order or offer, 405 - OrderStatus isn't NEW
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/2", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder2(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Offer offer = offersService.findById(order.getOfferId());
        if (offer == null) {
            return notFound;
        }

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        if (!userId.equals(oldOrder.getBuyerId())) {
            return badRequest;
        }

        if (oldOrder.getOrderStatus() != OrderStatus.NEW) {
            return methodNotAllowed;
        }

        if (!orderService.isShippingMethodsValid(order, offer)) {
            return badRequest;
        }

        if (!orderService.isPaymentMethodsValid(order, offer)) {
            return badRequest;
        }


        oldOrder.setOrderAddress(order.getOrderAddress());
        oldOrder.setPaymentMethod(order.getPaymentMethod());
        orderService.findAndUpdate(oldOrder);

        return ok;
    }


    /**
     * This method can only cancel order by buyer (before seller accept).
     *
     * @param order - updated order.
     * @return - return 200 status code if Ok, 400 - if status not NEW jr if user is not buyer, 401 - not authorized, 404 - not found order
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/3", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder3(@RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        if (userId.equals(oldOrder.getBuyerId()) && oldOrder.getOrderStatus() == OrderStatus.NEW) {
            // cancel order and send notification to seller
            orderService.cancelOrderByBuyer(oldOrder);
        } else {
            return badRequest;
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * This method can only change Order Status to ACCEPT or ORDER_REJECTED_BY_SELLER (only by seller).
     *
     * @param order - updated order.
     * @return - return 200 status code if Ok, 400 - user is not seller, 401 - not authorized, 404 - not found order
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/4", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder4(@Valid @RequestBody Order order) {

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        String userId = SecurityOperations.getLoggedUserId();
        if (!userId.equals(oldOrder.getSellerId())) {
            return badRequest;
        }

        Profile profileOfSeller = profilesService.findById(order.getSellerId());

        if (order.getOrderStatus() == OrderStatus.ACCEPT) {
            oldOrder.setOrderStatus(OrderStatus.ACCEPT)
                    .setAcceptedDateEqualsToCurrentDate();

            orderService.findAndUpdate(oldOrder);

            activityFeedService.createEvent(OrderRestHelper.eventPreparatorForBuyer(profileOfSeller, oldOrder, EventType.ORDER_ACCEPTED));
        }

        if (order.getOrderStatus() == OrderStatus.REJECTED_BY_SELLER) {
            oldOrder
                    .setOrderStatus(OrderStatus.REJECTED_BY_SELLER)
                    .setRejectDateEqualsToCurrentDate();
            orderService.findAndUpdate(oldOrder);
            //ToDo Return money to buyer
            activityFeedService.createEvent(OrderRestHelper.eventPreparatorForBuyer(profileOfSeller, oldOrder, EventType.ORDER_REJECTED_BY_SELLER));
        }

        return ok;
    }


    /**
     * This method can only change order status to SENT and onl seller. Due to TransportCompany type
     * you need or not put trackNumber.
     *
     * @param order - updated order.
     * @return - return 200 status code if Ok, 400 - order doesn't have track number, 401 - not authorized,
     * 404 - not found order, 405 - if TransportCompany was SELF_PICKED - you can't use this method,
     * 406 - if you are not seller
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/5", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder5(@Valid @RequestBody Order order) {


        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        String userId = SecurityOperations.getLoggedUserId();
        if (!userId.equals(oldOrder.getSellerId())) {
            return notAcceptable;
        }

        if (oldOrder.getOrderAddress().getTransportCompany() != TransportCompany.SELF_PICKED) {
            if (order.getTrackNumber() != null) {
                oldOrder
                        .setTrackNumber(order.getTrackNumber())
                        .setOrderStatus(OrderStatus.SENT)
                        .setSentDateEqualsToCurrentDate();
                orderService.findAndUpdate(oldOrder);
                Profile profileOfSeller = profilesService.findById(order.getSellerId());
                activityFeedService.createEvent(OrderRestHelper.eventPreparatorForBuyer(profileOfSeller, oldOrder, EventType.ORDER_SENT));
            } else {
                return badRequest;
            }
        } else {
            return methodNotAllowed;
        }

        return ok;
    }


    /**
     * @param order - updated order.
     *              This method can only change order status to COMPLETED from the client (by seller or by buyer).
     * @return - return 200 status code if Ok, 400 - user neither seller nor buyer, 404 - not found order,
     * 406 - buyer can't mark this order like COMPLETED yet
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/6", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder6(@Valid @RequestBody Order order) {


        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        Long timeNow = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        String userId = SecurityOperations.getLoggedUserId();
        TransportCompany oldOrderTransportCompany = oldOrder.getOrderAddress().getTransportCompany();

        if (userId.equals(oldOrder.getSellerId())) {

            //логика для продавца. Может нажать, если прошёл 31 день с момента отправки (через ТК),
            // либо через 15 дней, если SELF_PICKED


            if ((oldOrderTransportCompany != TransportCompany.SELF_PICKED && (timeNow - oldOrder.getSentDate()) > 2678400000L)
                    || (oldOrderTransportCompany == TransportCompany.SELF_PICKED && (timeNow - oldOrder.getAcceptDate()) > 1296000000L)) { //31 or 15 days
                oldOrder
                        .setOrderStatus(OrderStatus.COMPLETED)
                        .setReceivedDateEqualsToCurrentDate();
                orderService.findAndUpdate(oldOrder);
                //ToDo transfer money to the buyer account
                Profile profileOfSeller = profilesService.findById(order.getSellerId());
                activityFeedService.createEvent(OrderRestHelper.eventPreparatorForBuyer(profileOfSeller, oldOrder, EventType.ORDER_COMPLETED));
            } else {
                return notAcceptable;
            }


        } else {
            if (userId.equals(oldOrder.getBuyerId())) {

                if ((oldOrder.getOrderStatus() == OrderStatus.SENT && (timeNow - oldOrder.getSentDate() > 43200000L))
                        || (oldOrderTransportCompany == TransportCompany.SELF_PICKED && (timeNow - oldOrder.getAcceptDate() > 43200000L))) { // 12 hours
                    oldOrder
                            .setOrderStatus(OrderStatus.COMPLETED)
                            .setReceivedDateEqualsToCurrentDate();
                    orderService.findAndUpdate(oldOrder);
                    //ToDo Make money transfer on seller account
                    Profile profile = profilesService.findById(order.getBuyerId());
                    activityFeedService.createEvent(OrderRestHelper.eventPreparatorForSeller(profile, oldOrder, EventType.ORDER_COMPLETED));
                }

            } else {
                // you are neither buyer nor the seller
                return badRequest;
            }
        }
        return ok;
    }


    /**
     * @param order - updated order. Can received only one comment from buyer or from seller to certain order.
     *              Comment length can be from 10 to 500 letters.
     * @return - return 200 status code if Ok, 400 - user neither seller nor buyer, 404 - not found order.
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/7", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder7(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        Profile profileOfSeller = profilesService.findById(order.getSellerId());
        if (userId.equals(oldOrder.getBuyerId())) {
            OrderRestHelper.commentUpdaterAndEventSender(profileOfSeller, orderService,
                    activityFeedService, userId, order, oldOrder, EventType.ORDER_BUYER_COMMENT);
            return ok;
        }


        Profile profileOfBuyer = profilesService.findById(order.getBuyerId());
        if (userId.equals(oldOrder.getSellerId())) {
            OrderRestHelper.commentUpdaterAndEventSender(profileOfBuyer, orderService,
                    activityFeedService, userId, order, oldOrder, EventType.ORDER_SELLER_COMMENT);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return badRequest;
    }

    /**
     * Add and update seller note for specific order.
     *
     * @param orderId    - the seller ID.
     * @param sellerNote - the text of the seller note.
     * @return - status 200 (OK), 404 (Not Found) - if order was not found, 403 (Forbidden) - if user is not seller.
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/order/update/note/{orderId}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateSellerNote(@PathVariable String orderId, @RequestBody String sellerNote) {

        String userId = SecurityOperations.getLoggedUserId();

        Order order = orderService.findById(orderId);

        if (order == null) {
            return notFound;
        }

        if (!userId.equals(order.getSellerId())) {
            return forbidden;
        }

        orderService.updateSellerNote(order, sellerNote);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
