package ua.com.itproekt.gup.server.api.rest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.Currency;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderComment;
import ua.com.itproekt.gup.model.order.OrderStatus;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.order.TransportCompany;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/rest/orderService")
public class OrderRestController {

    //ToDo В любом изменении ордера нам не нужно получать: id пользователя, который делает апдейт
    // нам нужны данные, которы меняются исключительно пользователем


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
     * @param orderFilterOptions - order filter options
     * @return - return List of orders and status code 200
     */
    @CrossOrigin
    @RequestMapping(value = "/order/read/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getOrderById(@Valid @RequestBody OrderFilterOptions orderFilterOptions) {

        List<Order> orderList = orderService.findOrdersWihOptions(orderFilterOptions);

        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }


    //------------------------------------------ Create -------------------------------------------------------------

    /**
     * @param order - order include: offerId, orderAddress, isSafeOrder, orderType, orderComment
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

        if (isOrderValid(order, offer)) {
            newOrderPreparator(userId, order, offer);

            if (order.isSafeOrder()) {
                //ToDo перевод денег на счёт Гупа если ввключён safe order
            }
            orderService.create(order);
            activityFeedService.createEvent(eventPreparatorForSeller(order, EventType.NEW_ORDER));
        } else {
            return badRequest;
        }
        return ok;
    }

//------------------------------------------ Update -------------------------------------------------------------

    /**
     * @param order - updated order. This method can only update order Address only before seller will accept Order.
     * @return - return status 200 code if Ok, 401 - not authorized, 400 - user is not buyer,
     * 404 - not found order, 405 - OrderStatus isn't NEW
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/2", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder2(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();


        //TODO перенести ниже и переписать (доставать пользователя только из Секьюрити
        if (!userId.equals(order.getBuyerId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return notFound;
        }

        if (oldOrder.getOrderStatus() != OrderStatus.NEW) {
            return methodNotAllowed;
        }

        oldOrder.setOrderAddress(order.getOrderAddress());
        orderService.findAndUpdate(oldOrder);

        return ok;
    }


    /**
     * @param order - updated order. This method can only cancel order by buyer (before seller accept)
     * @return - return 200 status code if Ok, 400 - if status not NEW jr if user is not buyer, 401 - not authorized, 404 - not found order
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/3", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder3(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (userId.equals(oldOrder.getBuyerId()) && oldOrder.getOrderStatus() == OrderStatus.NEW) {
            oldOrder.setOrderStatus(OrderStatus.CANCELED_BY_BUYER);
            orderService.findAndUpdate(oldOrder);

            //ToDo Веруть деньги покупателю

            activityFeedService.createEvent(eventPreparatorForSeller(oldOrder, EventType.ORDER_CANCEL_BY_BUYER));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * @param order - updated order. This method can only change Order Status to Accept or Rejected (only by seller)
     * @return - return 200 status code if Ok, 400 - user is not seller, 401 - not authorized, 404 - not found order
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/4", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder4(@Valid @RequestBody Order order) {

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        if (!userId.equals(oldOrder.getSellerId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (order.getOrderStatus() == OrderStatus.ACCEPT) {
            oldOrder.setOrderStatus(OrderStatus.ACCEPT)
                    .setAcceptedDateEqualsToCurrentDate();

            orderService.findAndUpdate(oldOrder);
            activityFeedService.createEvent(eventPreparatorForBuyer(oldOrder, EventType.ORDER_ACCEPTED));
        }

        if (order.getOrderStatus() == OrderStatus.REJECTED_BY_SELLER) {
            oldOrder
                    .setOrderStatus(OrderStatus.REJECTED_BY_SELLER)
                    .setRejectDateEqualsToCurrentDate();
            orderService.findAndUpdate(oldOrder);
            //ToDo Возврат денег покупателю
            activityFeedService.createEvent(eventPreparatorForBuyer(oldOrder, EventType.ORDER_REJECTED_BY_SELLER));
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * @param order - updated order. This method can only change order status to SENT and onl seller. Due to TransportCompany type
     *              you need or not put trackNumber.
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        if (!userId.equals(oldOrder.getSellerId())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if (oldOrder.getOrderAddress().getTransportCompany() != TransportCompany.SELF_PICKED) {
            if (order.getTrackNumber() != null) {
                oldOrder
                        .setTrackNumber(order.getTrackNumber())
                        .setOrderStatus(OrderStatus.SENT)
                        .setSentDateEqualsToCurrentDate();
                orderService.findAndUpdate(oldOrder);
                activityFeedService.createEvent(eventPreparatorForBuyer(oldOrder, EventType.ORDER_SENT));
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * @param order - updated order.
     *              This method can only change order status to RECEIVED from the client (by seller or by buyer).
     * @return - return 200 status code if Ok, 400 - user neither seller nor buyer, 404 - not found order,
     * 406 - buyer can't mark this order like RECEIVED yet
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/6", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder6(@Valid @RequestBody Order order) {


        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
                        .setOrderStatus(OrderStatus.RECEIVED)
                        .setReceivedDateEqualsToCurrentDate();
                orderService.findAndUpdate(oldOrder);
                //ToDo перевести деньги на счёт продавца
                activityFeedService.createEvent(eventPreparatorForBuyer(oldOrder, EventType.ORDER_RECEIVED));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }


        } else {
            if (userId.equals(oldOrder.getBuyerId())) {

                if ((oldOrder.getOrderStatus() == OrderStatus.SENT && (timeNow - oldOrder.getSentDate() > 43200000L))
                        || (oldOrderTransportCompany == TransportCompany.SELF_PICKED && (timeNow - oldOrder.getAcceptDate() > 43200000L))) { // 12 hours
                    oldOrder
                            .setOrderStatus(OrderStatus.RECEIVED)
                            .setReceivedDateEqualsToCurrentDate();
                    orderService.findAndUpdate(oldOrder);
                    //ToDo перевести деньги на счёт продавца
                    activityFeedService.createEvent(eventPreparatorForSeller(oldOrder, EventType.ORDER_RECEIVED));
                }

            } else {
                //ошибка, т.к. ты не покупатель и не продаввец
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * @param order - updated order. Can received only one comment from buyer or from seller to certain order.
     *              Comment length can be from 10 to 500 letters.
     * @return - return 200 status code if Ok, 400 - user neither seller nor buyer, 404 - not found order,
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/7", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrder7(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        if (userId.equals(oldOrder.getBuyerId())) {
            commentUpdaterAndEventSender(userId, order, oldOrder, EventType.ORDER_BUYER_COMMENT);
            return new ResponseEntity<>(HttpStatus.OK);
        }


        if (userId.equals(oldOrder.getSellerId())) {
            commentUpdaterAndEventSender(userId, order, oldOrder, EventType.ORDER_SELLER_COMMENT);
            return new ResponseEntity<>(HttpStatus.OK);
        }


        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    //------------------------------------------ Helpers methods -------------------------------------------------------------
    private boolean isOrderValid(Order order, Offer offer) {

        if (offer.getCurrency() != Currency.UAH) {
            return false;
        }

        if (offer.getPrice() == null) {
            return false;
        }
        if (offer.getPrice() < 1) {
            return false;
        }

        order.setPrice(offer.getPrice());

        return true;
    }

    private void newOrderPreparator(String userId, Order order, Offer offer) {
        order
                .setBuyerId(userId)
                .setSellerId(offer.getAuthorId())
                .setOfferTitle(offer.getTitle())
                .setPrice(offer.getPrice())
                .setSeoKey(offer.getSeoKey())
                .setSeoUrl(offer.getSeoUrl())
                .setOfferMainImageId(findMainOfferPhoto(offer));
    }


    private String findMainOfferPhoto(Offer offer) {

        Map<String, String> imagesMap = offer.getImagesIds();

        for (String key : imagesMap.keySet()) {
            if (imagesMap.get(key).equals("1")) {
                return key;
            }
        }

        return null;
    }


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


    private void commentUpdaterAndEventSender(String userId, Order order, Order oldOrder, EventType eventType) {

        OrderComment newComment = order.getOrderComments().get(0)
                .setDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli())
                .setUserId(userId);

        oldOrder.getOrderComments().add(newComment);
        orderService.findAndUpdate(oldOrder);

        activityFeedService.createEvent(eventPreparatorForSeller(oldOrder, eventType));
    }


}
