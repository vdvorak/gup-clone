package ua.com.itproekt.gup.server.api.rest.order.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderComment;
import ua.com.itproekt.gup.model.order.OrderStatus;
import ua.com.itproekt.gup.model.order.OrderType;
import ua.com.itproekt.gup.model.profiles.order.OrderAddress;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.util.TransportCompany;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderTestRestController {


    @Autowired
    OrderService orderService;


    //------------------------------------------ Test -----------------------------------------------------------------
    @CrossOrigin
    @RequestMapping(value = "/order/test/addSomeOrders", method = RequestMethod.GET,
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
        List<OrderComment> commentList1 = new ArrayList<>();
        commentList1.add(orderComment1);

        Order order1 = new Order()
                .setSafeOrder(false)
                .setOrderAddress(orderAddress1)
                .setOrderStatus(OrderStatus.NEW)
                .setBuyerId("571a2fdd681db5eee71086c0")
                .setSellerId("5720b0a8681da6b00652ed0a")
                .setOrderType(OrderType.PURCHASE)
                .setOrderComments(commentList1);

        orderService.create(order1);

        //--------------------
        OrderAddress orderAddress2 = new OrderAddress()
                .setAddress("Kiev, Geroev Dnipra. Otdelenie #77")
                .setTransportCompany(TransportCompany.NOVA_POSHTA)
                .setName("Nikolas Cage")
                .setPhoneNumber("3809995573");

        OrderComment orderComment2 = new OrderComment()
                .setMessage("Добрый день! Сегодня постараюсь отправить.")
                .setUserId("571a2fdd681db5eee71086c0");
        List<OrderComment> commentList2 = new ArrayList<>();
        commentList2.add(orderComment2);

        Order order2 = new Order()
                .setSafeOrder(false)
                .setOrderAddress(orderAddress2)
                .setOrderStatus(OrderStatus.ACCEPT)
                .setBuyerId("56e6cbb5e4b00942b3340123")
                .setSellerId("571a2fdd681db5eee71086c0")
                .setOrderType(OrderType.PURCHASE)
                .setOrderComments(commentList2);
//--------------------

        orderService.create(order2);

        OrderAddress orderAddress3 = new OrderAddress()
                .setAddress("Lugansk, kv.Uzhni #35")
                .setTransportCompany(TransportCompany.DELIVERY)
                .setName("Sasha Grey")
                .setPhoneNumber("380505698723");

        OrderComment orderComment3 = new OrderComment()
                .setMessage("Спасибо, что забрали посылку!")
                .setUserId("56e6cbb5e4b00942b3340123");

        List<OrderComment> commentList3 = new ArrayList<>();
        commentList3.add(orderComment3);

        Order order3 = new Order()
                .setSafeOrder(true)
                .setOrderAddress(orderAddress3)
                .setOrderStatus(OrderStatus.SENT)
                .setBuyerId("5720b0a8681da6b00652ed0a")
                .setSellerId("56e6cbb5e4b00942b3340123")
                .setOrderType(OrderType.PURCHASE)
                .setOrderComments(commentList3);

        orderService.create(order3);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
