package ua.com.itproekt.gup.server.api.rest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.offer.Currency;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderComment;
import ua.com.itproekt.gup.model.order.OrderStatus;
import ua.com.itproekt.gup.model.order.OrderType;
import ua.com.itproekt.gup.model.profiles.order.OrderAddress;
import ua.com.itproekt.gup.model.profiles.order.TransportCompany;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/rest/orderService")
public class OrderRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    OffersService offersService;


    // ToDo т.к. "Купить" и собственно "Заказ" доступны только для товаров, которые можно либо купить, либо зазказать,
    // то при создании нужна проверка на тип объявления (чтобы нелья было "купить" то, что "обмен" либо "бесплатно").

    // ToDo написать метод валидатор, который будет проверять "можно ли" купить данное объявление


    //------------------------------------------ Read -----------------------------------------------------------------

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


    //------------------------------------------ Create -----------------------------------------------------------------

    @CrossOrigin
    @RequestMapping(value = "/order/create", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {

        Offer offer = offersService.findById(order.getOfferId());
        if (offer == null) {
            throw new ResourceNotFoundException();
        }

        if (isOrderValid(order)) {
            orderService.create(order);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    //------------------------------------------ Test -----------------------------------------------------------------
    @CrossOrigin
    @RequestMapping(value = "/test/addsomeorders", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> addSomeTestOrders() {

        OrderAddress orderAddress1 = new OrderAddress()
                .setAddress("Kiev, Obolonskaya naberezhnaya, 36. Otdelenie #2")
                .setTransportCompany(TransportCompany.NOVA_POSHTA)
                .setName("Alexander Ko")
                .setPhoneNumber("380505665789");

        OrderComment orderComment1 = new OrderComment()
                .setMessage("Привет! Отправь, пожалуйста, как можно быстрее!")
                .setUserId("571a2fdd681db5eee71086c0");

        Order order1 = new Order()
                .setSafeOrder(false)
                .setOrderAddress(orderAddress1)
                .setOrderStatus(OrderStatus.NEW)
                .setBuyerId("571a2fdd681db5eee71086c0")
                .setSellerId("5720b0a8681da6b00652ed0a")
                .setOrderType(OrderType.PURCHASE);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean isOrderValid(Order order) {
        Offer offer = offersService.findById(order.getOfferId());

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


}
