package ua.com.itproekt.gup.server.api.rest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.dto.ProfileInfo;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderStatus;
import ua.com.itproekt.gup.model.order.OrderType;
import ua.com.itproekt.gup.model.order.blockchain_test.transaction.ContractTransaction;
import ua.com.itproekt.gup.model.order.blockchain_test.transaction.MoneyTransferTransaction;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;
import ua.com.itproekt.gup.service.order.blockchain_test.ChainService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.order.OrderService;
import ua.com.itproekt.gup.service.order.blockchain_test.member.BuyerTransactionService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.PaymentMethod;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.TransportCompany;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;


@Controller
@RequestMapping("/api/rest/orderService")
public class OrderRestController {

    private final ResponseEntity<String> ok = new ResponseEntity<>(HttpStatus.OK);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OffersService offersService;

    @Autowired
    private ProfilesService profilesService;

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

//    /**
//     * @param order - order include: offerId, orderAddress, paymentMethod, orderType, orderComment
//     * @return - return status code if Ok, 400 - order not valid, 403 - if user is offer author, 404 - offer not found, 405 - if user is not buyer
//     */
//    @PreAuthorize("isAuthenticated()")
//    @CrossOrigin
//    @RequestMapping(value = "/order/create", method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> createOrder(@Valid @RequestBody Order order) {
//
//        Offer offer = offersService.findById(order.getOfferId());
//        if (offer == null) {
//            return new ResponseEntity<>("Offer was not found", HttpStatus.NOT_FOUND);
//        }
//
//        String userId = SecurityOperations.getLoggedUserId();
//        if (userId.equals(offer.getAuthorId())) {
//            return new ResponseEntity<>("You are not an offer author.", HttpStatus.FORBIDDEN);
//        }
//
//        if (orderService.isOrderValid(order, offer)) {
//            // create order
//            orderService.create(userId, order, offer);
//        } else {
//            return new ResponseEntity<>("Order is not valid", HttpStatus.BAD_REQUEST);
//        }
//        return ok;
//    }

    /**
     * 1. Получает информацию о заказе (из фронта):
     *    + ID-покупатель
     *    + ID-продавец
     *    + стоимость
     *    + объявление (ID, название, описание)
     *
     * 2. Нужно по ID-покупателю вытащить из банка доступный баланс
     *
     * 3. Создать транзакцию типа-MoneyTransfer и отправить в блокчейн - эта транзакция создается со стороны покупателя...
     *    Сохранить ХЕШ-транзакции в ГУПе ( создать заказ-order в ГУПе )
     *
     * 4. После этого продавец должен проверить заказ (если по условию все хорошо) - тогда нужно подтвердить выполнение этого заказа - эта транзакция создается со стороны продавца...
     *    Создать транзакцию типа-Contract и отправить в блокчейн ( результатом этого заказа может быть: ПОДТВЕРЖДЕНИЕ-ОТМЕНА... )
     *
     * 5. После этого покупателю должно прийти уведомление об успешном выполнении сделки (контракта)
     *    ( И успешно-завершенный заказ должен попасть в историю покупок... )
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/order/create/offer/{seoUrl}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateBuyerNote(@PathVariable String seoUrl) {
        Offer offer = offersService.findBySeoUrlAndIncViews(seoUrl);
        if (offer == null) {
            return new ResponseEntity<>("Offer was not found", HttpStatus.NOT_FOUND);
        } else if (offer.isDeleted()) {
            return new ResponseEntity<>("Offer was deleted", HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        if (userId!=null){
            if (!userId.equals(offer.getAuthorId())){
                try {
                    ChainService bankService = new ChainService(new BuyerTransactionService(new MoneyTransferTransaction(userId, new Date().getTime(), offer.getSeoUrl())));

//                    ChainService service = new ChainService(new ContractTransactionService(new String[] {offer.getAuthorId(), userId}, offer.getSeoUrl()));

//                    System.err.println("------------------------------------------------------------------------------");
//                    System.err.println("_hash:      " + service.getHash());
//                    System.err.println("PUBLIC-KEY: " + service.getKeyPair().readPublic());
//                    System.err.println("------------------------------------------------------------------------------");

                    okhttp3.Response response = bankService.confirm();
                    // create order
                    if (response.code()==200) {
//                      bankService.getTransaction().getTransaction().
                        Order order = new Order();
                        order.setOfferId(offer.getId());
                        order.setPaymentMethod(PaymentMethod.CARD_PAYMENT);
//                        order.setPublicKey(service.getKeyPair().readPublic());
//                        order.setHashTransaction(service.getHash());
                        order.setSeoUrl(offer.getSeoUrl());
                        order.setSeoKey(offer.getSeoKey());
                        order.setOrderType(OrderType.PURCHASE);
                        orderService.create(userId, order, offer);
                        return new ResponseEntity<>(response.body().string(), HttpStatus.OK);
                    }
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                } catch (NullPointerException | NoSuchProviderException | NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | IOException | SignatureException e){
                    System.err.println( e.getMessage() );
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>("You can't be author.", HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>("You are not an Authorize.", HttpStatus.FORBIDDEN);
        }
    }

//------------------------------------------ Update -------------------------------------------------------------

    /**
     * This method can only update order Address and/or payment method only before seller will accept Order.
     *
     * @param order - updated order.
     * @return - return status 200 code if Ok, 401 - not authorized, 400 - user is not buyer or not valid payment or shipping method,
     * 404 - not found order or offer, 405 - OrderStatus isn't NEW
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/2", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateOrder2(@Valid @RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Offer offer = offersService.findById(order.getOfferId());
        if (offer == null) {
            return new ResponseEntity<>("Offer was not found", HttpStatus.NOT_FOUND);
        }

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return new ResponseEntity<>("Order was not found", HttpStatus.NOT_FOUND);
        }

        if (!oldOrder.getBuyerId().equals(userId)) {
            return new ResponseEntity<>("Current user is not a buyer", HttpStatus.BAD_REQUEST);
        }

        if (oldOrder.getOrderStatus() != OrderStatus.NEW) {
            return new ResponseEntity<>("You can change address or payment only of the order which has status New", HttpStatus.BAD_REQUEST);
        }

        if (!orderService.isShippingMethodsValid(order, offer)) {
            return new ResponseEntity<>("Shipping method is not valid", HttpStatus.BAD_REQUEST);
        }

        if (!orderService.isPaymentMethodsValid(order, offer)) {
            return new ResponseEntity<>("Payment method is not valid", HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<String> updateOrder3(@RequestBody Order order) {

        String userId = SecurityOperations.getLoggedUserId();

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return new ResponseEntity<>("Order was not found", HttpStatus.NOT_FOUND);
        }

        if (oldOrder.getBuyerId().equals(userId) && oldOrder.getOrderStatus() == OrderStatus.NEW) {
            // cancel order and send notification to seller
            orderService.cancelOrderByBuyer(oldOrder);
        } else {
            return new ResponseEntity<>("Current user is not a buyer or previous Order Status is not a New", HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<String> updateOrder4(@Valid @RequestBody Order order) {

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return new ResponseEntity<>("Order was not found", HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        if (!oldOrder.getSellerId().equals(userId)) {
            return new ResponseEntity<>("Current user is not a seller", HttpStatus.BAD_REQUEST);
        }

        if (order.getOrderStatus() == OrderStatus.ACCEPT) {
            orderService.acceptOrderBySeller(oldOrder);
        }

        if (order.getOrderStatus() == OrderStatus.REJECTED_BY_SELLER) {
            orderService.rejectedOrderBySeller(oldOrder);
        }

        return ok;
    }


    /**
     * This method can only change order status to SENT and only by seller. Due to the TransportCompany type
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
    public ResponseEntity<String> updateOrder5(@Valid @RequestBody Order order) {

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return new ResponseEntity<>("Order was not found", HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        if (!oldOrder.getSellerId().equals(userId)) {
            return new ResponseEntity<>("You are not an seller.", HttpStatus.FORBIDDEN);
        }

        if (oldOrder.getOrderAddress().getTransportCompany() != TransportCompany.SELF_PICKED && order.getTrackNumber() != null) {
            oldOrder.setTrackNumber(order.getTrackNumber());
            orderService.sendOrderBySeller(oldOrder);
        } else {
            return new ResponseEntity<>("In the previous order TransportCompany was Self_Picked and the truckNumber in the order is null", HttpStatus.BAD_REQUEST);
        }
        return ok;
    }


    /**
     * This method can only change order status to COMPLETED from the client (by seller or by buyer).
     *
     * @param order - updated order.
     * @return - return 200 status code if Ok, 400 - user neither seller nor buyer, 404 - not found order,
     * 406 - buyer can't mark this order like COMPLETED yet
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/6", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateOrder6(@Valid @RequestBody Order order) {

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return new ResponseEntity<>("Order was not found", HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        if (oldOrder.getSellerId().equals(userId)) {

            if (orderService.completeOrderBySeller(oldOrder)) {
                return ok;
            } else {
                return new ResponseEntity<>("Order can't be complete now. To early to make it complete.", HttpStatus.BAD_REQUEST);
            }

        } else {
            if (oldOrder.getBuyerId().equals(userId)) {

                if (orderService.completeOrderByBuyer(oldOrder)) {
                    return ok;
                } else {
                    return new ResponseEntity<>("Order can't be complete now. To early to make it complete.", HttpStatus.BAD_REQUEST);
                }

            } else {
                // you are neither buyer nor the seller
                return new ResponseEntity<>("You are neither buyer nor the seller.", HttpStatus.BAD_REQUEST);
            }
        }
    }


    /**
     * This method for add comment to the order.
     *
     * @param order - updated order. Can received only one comment from buyer or from seller to certain order.
     *              Comment length can be from 10 to 500 letters.
     * @return - return 200 status code if Ok, 400 - user neither seller nor buyer, 404 - not found order.
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/order/update/7", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateOrder7(@Valid @RequestBody Order order) {

        Order oldOrder = orderService.findById(order.getId());
        if (oldOrder == null) {
            return new ResponseEntity<>("Order was not found", HttpStatus.NOT_FOUND);
        }

        if (orderService.commentUpdateInOrder(oldOrder, order)) {
            return ok;
        }
        return new ResponseEntity<>("You are neither buyer nor the seller.", HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<String> updateSellerNote(@PathVariable String orderId, @RequestBody String sellerNote) {

        String userId = SecurityOperations.getLoggedUserId();

        Order order = orderService.findById(orderId);

        if (order == null) {
            return new ResponseEntity<>("Order was not found", HttpStatus.NOT_FOUND);
        }

        if (!order.getSellerId().equals(userId)) {
            new ResponseEntity<>("Current user is not seller in this order", HttpStatus.FORBIDDEN);
        }

        orderService.updateSellerNote(order, sellerNote);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
