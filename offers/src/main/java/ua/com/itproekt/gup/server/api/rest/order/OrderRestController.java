package ua.com.itproekt.gup.server.api.rest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.Currency;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/rest/orderService")
public class OrderRestController {

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
     * @return - order and status code, or just redirect on 404 if order isn't exist
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
     * @return - List of orders and status code
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
     * @param order - order
     * @return - status code if Ok, redirect on 404 if order isn't exist, status code 400 if order is not valid
     */
    @CrossOrigin
    @RequestMapping(value = "/order/create", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createOrder(@Valid @RequestBody Order order) {

        Offer offer = offersService.findById(order.getOfferId());
        if (offer == null) {
            throw new ResourceNotFoundException();
        }

        if (isOrderValid(order, offer)) {
            orderPreparator(order, offer);

            if (order.isSafeOrder()) {
                //ToDo перевод денег на счёт Гупа если ввключён safe order
            }
            orderService.create(order);
            activityFeedService.createEvent(eventPreparator(order));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

//------------------------------------------ Update -------------------------------------------------------------


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

    private void orderPreparator(Order order, Offer offer) {
        order.setOfferTitle(offer.getTitle())
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


    private Event eventPreparator(Order order) {
        Profile profile = profilesService.findById(order.getBuyerId());

        return new Event()
                .setTargetUId(order.getSellerId())
                .setType(EventType.NEW_ORDER)
                .setContentStoreId(order.getOfferId())
                .setContentId(order.getId())
                .setMakerId(order.getBuyerId())
                .setMakerImgId(profile.getImgId())
                .setMakerName(profile.getUsername());
    }


}
