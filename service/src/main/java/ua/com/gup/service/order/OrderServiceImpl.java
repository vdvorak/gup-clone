package ua.com.gup.service.order;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.enumeration.Currency;
import ua.com.gup.dto.OrderInfo;
import ua.com.gup.domain.Event;
import ua.com.gup.model.activityfeed.EventType;
import ua.com.gup.model.order.Order;
import ua.com.gup.model.order.OrderComment;
import ua.com.gup.model.order.OrderFeedback;
import ua.com.gup.model.order.OrderStatus;
import ua.com.gup.model.order.filter.OrderFilterOptions;
import ua.com.gup.model.profiles.Profile;
import ua.com.gup.repository.dao.order.OrderRepository;
import ua.com.gup.service.activityfeed.ActivityFeedService;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.PaymentMethod;
import ua.com.gup.util.SecurityOperations;
import ua.com.gup.util.TransportCompany;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of the OrderService interface.
 *
 * @author Kobylyatskyy Alexander
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProfilesService profilesService;

    @Autowired
    private ActivityFeedService activityFeedService;


    /**
     * Create new order.
     *
     * @param order - the order.
     */
    @Override
    public void create(Order order) {
        Order newOrder = new Order()
                .setOfferId(order.getOfferId())
                .setBuyerId(order.getBuyerId())
                .setSellerId(order.getSellerId())
                .setPrice(order.getPrice())
                .setOfferTitle(order.getOfferTitle())
                .setOfferMainImageId(order.getOfferMainImageId())
                .setCreatedDateEqualsToCurrentDate()
                .setOrderAddress(order.getOrderAddress())
                .setOrderStatus(OrderStatus.NEW)
                .setSellerNote(null) //when order create - buyer can not change seller note
                .setOrderType(order.getOrderType())
                .setOrderComments(order.getOrderComments())
                .setPaymentMethod(order.getPaymentMethod());

        orderRepository.create(newOrder);
        order.setId(newOrder.getId());
    }

    @Override
    public void create(String userId, Order order, Offer offer) {


        // fill order with additional data
        newOrderPreparator(userId, order, offer);

        if (order.getPaymentMethod() == PaymentMethod.GUP) {
            //ToDo money transfer on GUP's account if type of order is GUP
        }

        Order newOrder = new Order()
                .setOfferId(order.getOfferId())
                .setBuyerId(order.getBuyerId())
                .setSellerId(order.getSellerId())
                .setPrice(order.getPrice())
                .setOfferTitle(order.getOfferTitle())
                .setOfferMainImageId(order.getOfferMainImageId())
                .setCreatedDateEqualsToCurrentDate()
                .setPublicKey(order.getPublicKey())
                .setHashTransaction(order.getHashTransaction())
                .setOrderAddress(order.getOrderAddress())
                .setOrderStatus(OrderStatus.NEW)
                .setSellerNote(null) //when order create - buyer can not change seller note
                .setOrderType(order.getOrderType())
                .setOrderComments(order.getOrderComments())
                .setPaymentMethod(order.getPaymentMethod());

        orderRepository.create(newOrder);
        order.setId(newOrder.getId());

        // prepare send notifications
        Profile profile = profilesService.findById(order.getBuyerId());
        activityFeedService.createEvent(eventPreparatorForSeller(profile, order, EventType.NEW_ORDER));
    }

    @Override
    public Order findById(String id) {
        return orderRepository.findById(id);
    }


    @Override
    public Order findAndUpdate(Order order) {
        Order newOrder = new Order()
                .setOfferId(order.getOfferId())
                .setBuyerId(order.getBuyerId())
                .setSellerId(order.getSellerId())
                .setOrderAddress(order.getOrderAddress())
                .setSentDate(order.getSentDate())
                .setReceivedDate(order.getReceivedDate())
                .setCompleteDate(order.getCompleteDate())
                .setAcceptDate(order.getAcceptDate())
                .setOrderStatus(order.getOrderStatus())
                .setOrderType(order.getOrderType())
                .setOrderComments(order.getOrderComments())
                .setPaymentMethod(order.getPaymentMethod())
                .setOrderFeedback(order.getOrderFeedback());

        return orderRepository.findAndUpdate(newOrder);

    }

    @Override
    public int delete(String id) {
        return orderRepository.delete(id);
    }

    @Override
    public List<Order> findOrdersWihOptions(OrderFilterOptions orderFilterOptions) {
        return orderRepository.findOrdersWihOptions(orderFilterOptions);
    }


    @Override
    public List<Order> findAllOrdersForUser(String userId) {
        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setBuyerId(userId);
        orderFilterOptions.setBuyerId(userId);
        return orderRepository.findOrdersWihOptions(orderFilterOptions);
    }


    @Override
    public List<OrderFeedback> findAllFeedbacksForOffer(String offerId) {
        List<OrderFeedback> orderFeedbackList = new ArrayList<>();

        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setOfferId(offerId);
        List<Order> orderList = orderRepository.findOrdersWihOptions(orderFilterOptions);
        for (Order order : orderList) {
            orderFeedbackList.add(order.getOrderFeedback());
        }

        return orderFeedbackList;
    }


    @Override
    public int calculateAveragePointsForListOfOrders(List<Order> orderList) {
        int res = 0;
        int count = 0;

        for (Order order : orderList) {
            if (order.getOrderFeedback() != null) {
                res = res + order.getOrderFeedback().getPoint();
                count++;
            }
        }

        if (res == 0) {
            return 0;
        }

        return (res * 10) / count;
    }


    @Override
    public int calculateAveragePointsForOrderFeedbackList(List<OrderFeedback> orderFeedback) {

        if (orderFeedback.size() < 1) {
            return 0;
        }

        int res = 0;
        int count = 0;

        for (OrderFeedback feedback : orderFeedback) {
            res = res + feedback.getPoint();
            count++;
        }

        if (res == 0) {
            return 0;
        }

        return (res * 10) / count;
    }


    @Override
    public int countOrderAmountForOffer(String offerId) {
        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setOfferId(offerId);
        return orderRepository.findOrdersWihOptions(orderFilterOptions).size();
    }


    @Override
    public List<Order> findAllOrdersForOffer(String offerId) {
        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setOfferId(offerId);
        return orderRepository.findOrdersWihOptions(orderFilterOptions);
    }


//    /**
//     * @param orderList
//     * @return
//     */
//    @Override
//    public List<OrderInfo> orderInfoListPreparatorForPrivate(List<Order> orderList) {
//        List<OrderInfo> orderInfoList = new ArrayList<>();
//
//        for (Order order : orderList) {
//            orderInfoList.add(singleOrderInfoPreparatorForPrivate(order));
//        }
//        return orderInfoList;
//
//    }


    @Override
    public List<OrderInfo> orderInfoListPreparatorForPrivate(List<Order> orderList, Profile profile) {
        List<OrderInfo> orderInfoList = new ArrayList<>();

        for (Order order : orderList) {
            orderInfoList.add(singleOrderInfoPreparatorForPrivate(order, profile));
        }
        return orderInfoList;

    }


    @Override
    public int calculateFeedbackAmountForOrderList(List<OrderInfo> orderList) {
        int result = 0;
        for (OrderInfo orderInfo : orderList) {
            if (orderInfo.getOrder().getOrderFeedback() != null) {
                result++;
            }
        }
        return result;
    }


    @Override
    public List<OrderInfo> orderInfoSellerListFromTotalOrderListOfUser(List<OrderInfo> orderInfoList, String sellerId) {

        List<OrderInfo> orderInfoListSeller = new ArrayList<>();

        for (OrderInfo orderInfo : orderInfoList) {
            if (orderInfo.getOrder().getSellerId().equals(sellerId)) {
                orderInfoListSeller.add(orderInfo);
            }
        }

        return orderInfoListSeller;
    }


    @Override
    public List<OrderInfo> orderInfoBuyerListFromTotalOrderListOfUser(List<OrderInfo> orderInfoList, String buyerId) {

        List<OrderInfo> orderInfoListBuyer = new ArrayList<>();

        for (OrderInfo orderInfo : orderInfoList) {
            if (orderInfo.getOrder().getSellerId().equals(buyerId)) {
                orderInfoListBuyer.add(orderInfo);
            }
        }

        return orderInfoListBuyer;
    }


    @Override
    public List<Order> getAllOrders(OrderFilterOptions orderFilterOptions) {
        String userId = SecurityOperations.getLoggedUserId();

        orderFilterOptions
                .setBuyerId(userId)
                .setSellerId(userId);

        return findOrdersWihOptions(orderFilterOptions);
    }


    @Override
    public boolean isOrderValid(Order order, Offer offer) {
        if (offer.getPrice().getCurrency() != Currency.UAH) {
            return false;
        }

        if (offer.getPrice() == null) {
            return false;
        }

        //todo maybe in future
        /*if (offer.getPrice() < 1) {
            return false;
        }*/

        if (!isShippingMethodsValid(order, offer)) {
            return false;
        }

        if (!isPaymentMethodsValid(order, offer)) {
            return false;
        }

        order.setPrice(offer.getPrice().getAmount().longValue());

        return true;
    }


    @Override
    public boolean isShippingMethodsValid(Order order, Offer offer) {
        //todo future
       /* TransportCompany orderTransportCompany = order.getOrderAddress().getTransportCompany();
        Set<TransportCompany> availableShippingMethodsList = offer.getAvailableShippingMethods();

        for (TransportCompany offerTransportCompany : availableShippingMethodsList) {

            if (orderTransportCompany == offerTransportCompany) {
                return true;
            }
        }*/
        return false;
    }

    @Override
    public boolean isPaymentMethodsValid(Order order, Offer offer) {
        //todo future
        /*PaymentMethod orderPaymentMethod = order.getPaymentMethod();
        Set<PaymentMethod> offerPaymentMethodsList = offer.getAvailablePaymentMethods();
        for (PaymentMethod offerPaymentMethod : offerPaymentMethodsList) {
            if (orderPaymentMethod == offerPaymentMethod) {
                return true;
            }
        }*/
        return false;
    }


    @Override
    public void updateSellerNote(Order order, String sellerNote) {
        order.setSellerNote(sellerNote);
        findAndUpdate(order);
    }

    @Override
    public void cancelOrderByBuyer(Order oldOrder) {
        oldOrder.setOrderStatus(OrderStatus.CANCELED_BY_BUYER);
        findAndUpdate(oldOrder);

        //ToDo Веруть деньги покупателю

        Profile profile = profilesService.findById(oldOrder.getBuyerId());
        activityFeedService.createEvent(eventPreparatorForSeller(profile, oldOrder, EventType.ORDER_CANCEL_BY_BUYER));
    }


    @Override
    public void acceptOrderBySeller(Order oldOrder) {
        oldOrder.setOrderStatus(OrderStatus.ACCEPT)
                .setAcceptedDateEqualsToCurrentDate();

        findAndUpdate(oldOrder);

        Profile profileOfSeller = profilesService.findById(oldOrder.getSellerId());

        // send notification to buyer
        activityFeedService.createEvent(eventPreparatorForBuyer(profileOfSeller, oldOrder, EventType.ORDER_ACCEPTED));
    }


    @Override
    public void rejectedOrderBySeller(Order oldOrder) {
        oldOrder
                .setOrderStatus(OrderStatus.REJECTED_BY_SELLER)
                .setRejectDateEqualsToCurrentDate();

        findAndUpdate(oldOrder);
        //ToDo Return money to buyer

        Profile profileOfSeller = profilesService.findById(oldOrder.getSellerId());
        activityFeedService.createEvent(
                eventPreparatorForBuyer(profileOfSeller, oldOrder, EventType.ORDER_REJECTED_BY_SELLER));
    }


    @Override
    public void sendOrderBySeller(Order oldOrder) {

        oldOrder.setOrderStatus(OrderStatus.SENT)
                .setSentDateEqualsToCurrentDate();
        findAndUpdate(oldOrder);

        Profile profileOfSeller = profilesService.findById(oldOrder.getSellerId());

        // send notification to the buyer
        activityFeedService.createEvent(eventPreparatorForBuyer(profileOfSeller, oldOrder, EventType.ORDER_SENT));
    }


    @Override
    public boolean completeOrderBySeller(Order oldOrder) {

        Long timeNow = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        TransportCompany oldOrderTransportCompany = oldOrder.getOrderAddress().getTransportCompany();

        //логика для продавца. Может нажать, если прошёл 31 день с момента отправки (через ТК),
        // либо через 15 дней, если SELF_PICKED

        if ((oldOrderTransportCompany != TransportCompany.SELF_PICKED && (timeNow - oldOrder.getSentDate()) > 2678400000L)
                || (oldOrderTransportCompany == TransportCompany.SELF_PICKED && (timeNow - oldOrder.getAcceptDate()) > 1296000000L)) { //31 or 15 days
            oldOrder
                    .setOrderStatus(OrderStatus.COMPLETED)
                    .setReceivedDateEqualsToCurrentDate();

            findAndUpdate(oldOrder);
            //ToDo transfer money to the buyer account
            Profile profileOfSeller = profilesService.findById(oldOrder.getSellerId());

            // send notification to the buyer
            activityFeedService.createEvent(eventPreparatorForBuyer(profileOfSeller, oldOrder, EventType.ORDER_COMPLETED));
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean completeOrderByBuyer(Order oldOrder) {

        Long timeNow = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        TransportCompany oldOrderTransportCompany = oldOrder.getOrderAddress().getTransportCompany();


        if ((oldOrder.getOrderStatus() == OrderStatus.SENT && (timeNow - oldOrder.getSentDate() > 43200000L))
                || (oldOrderTransportCompany == TransportCompany.SELF_PICKED && (timeNow - oldOrder.getAcceptDate() > 43200000L))) { // 12 hours
            oldOrder
                    .setOrderStatus(OrderStatus.COMPLETED)
                    .setReceivedDateEqualsToCurrentDate();
            findAndUpdate(oldOrder);
            //ToDo Make money transfer on seller account
            Profile profile = profilesService.findById(oldOrder.getBuyerId());

            // send notification to the seller
            activityFeedService.createEvent(eventPreparatorForSeller(profile, oldOrder, EventType.ORDER_COMPLETED));
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean commentUpdateInOrder(Order oldOrder, Order newOrder) {

        String userId = SecurityOperations.getLoggedUserId();

        Profile profileOfSeller = profilesService.findById(oldOrder.getSellerId());

        if (oldOrder.getBuyerId().equals(userId)) {
            commentUpdaterAndEventSender(profileOfSeller, userId, newOrder, oldOrder, EventType.ORDER_BUYER_COMMENT);
            return true;
        }


        Profile profileOfBuyer = profilesService.findById(oldOrder.getBuyerId());
        if (oldOrder.getSellerId().equals(userId)) {
            commentUpdaterAndEventSender(profileOfBuyer, userId, newOrder, oldOrder, EventType.ORDER_SELLER_COMMENT);
            return true;
        }

        return false;
    }


    // --------------------------------------- Helpers methods --------------------------------------------------


    private void commentUpdaterAndEventSender(Profile profile, String userId, Order order, Order oldOrder, EventType eventType) {

        // get comment from new order
        OrderComment newComment = order.getOrderComments().get(0)
                .setDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli())
                .setUserId(userId);

        oldOrder.getOrderComments().add(newComment);
        findAndUpdate(oldOrder);

        // send notification to user
        activityFeedService.createEvent(eventPreparatorForSeller(profile, oldOrder, eventType));
    }


    /**
     * Prepare new order - filling with additional data.
     *
     * @param userId - the user ID.
     * @param order  - the order.
     * @param offer  - the offer.
     */
    private void newOrderPreparator(String userId, Order order, Offer offer) {
            order
                .setBuyerId(userId)
                .setSellerId(offer.getAuthorId())
                .setOfferTitle(offer.getTitle())
                .setPrice(offer.getPrice().getAmount().longValue())
                //todo  vdvorak
                //.setSeoKey(offer.getSeoKey())
                .setSeoUrl(offer.getSeoUrl())
                .setOfferMainImageId(findMainOfferPhoto(offer));
    }


    /**
     * Find and return main image ID.
     *
     * @param offer - the offer.
     * @return - the main photo ID.
     */
    private String findMainOfferPhoto(Offer offer) {
        List<String> imageListId = offer.getImageIds();
        if (imageListId != null) {
            for (String imageId : imageListId) {
                if (StringUtils.isNotBlank(imageId)) {
                    return imageId;
                }
            }
        }
        return null;
    }


    /**
     * Transform single order into orderInfo object with additional field.
     *
     * @param order - the order.
     * @return - the OrderInfo object.
     */
    private OrderInfo singleOrderInfoPreparatorForPrivate(Order order, Profile profile) {
        OrderInfo orderInfo = new OrderInfo();

        String profileId = profile.getId();
        String profileImage = profile.getImgId();
        String profileUserName = profile.getUsername();
        String profileImageUrl = profile.getImgUrl();


        if (order.getSellerId().equals(profileId)) {
            Profile buyer = profilesService.findById(order.getBuyerId());
            orderInfo.setOrder(order)
                    .setBuyerImgUrl(buyer.getImgUrl())
                    .setBuyerImg(buyer.getImgId())
                    .setBuyerName(buyer.getUsername())
                    .setSellerImg(profileImage)
                    .setSellerImgUrl(profileImageUrl)
                    .setSellerName(profileUserName);

            return orderInfo;
        } else {
            Profile seller = profilesService.findById(order.getBuyerId());
            orderInfo.setOrder(order)
                    .setBuyerImgUrl(profileImageUrl)
                    .setBuyerImg(profileImage)
                    .setBuyerName(profileUserName)
                    .setSellerImgUrl(seller.getImgUrl())
                    .setSellerImg(seller.getImgId())
                    .setSellerName(seller.getUsername());

            return orderInfo;
        }
    }


    /**
     * Prepare event for buer.
     *
     * @param profile   - the profile.
     * @param order     - the order.
     * @param eventType - the event type.
     * @return - the prepared event.
     */
    private Event eventPreparatorForSeller(Profile profile, Order order, EventType eventType) {

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


    /**
     * Prepare event for seller.
     *
     * @param profile   - the profile.
     * @param order     - the order.
     * @param eventType - the event type.
     * @return - the prepared event.
     */
    private Event eventPreparatorForBuyer(Profile profile, Order order, EventType eventType) {

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
     * Calculate average points for one certain offer
     *
     * @param offer - offer
     * @return
     */
//    private Integer calcAvgOfferPoints(Offer offer) {
//        String offerId = offer.getId();
//        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
//        orderFilterOptions.setOfferId(offerId);
//
//        int pointSum = 0;
//        int count = 0;
//
//        List<Order> orderList = findOrdersWihOptions(orderFilterOptions);
//        for (Order order : orderList) {
//            if (order.getOrderFeedback() != null) {
//                count++;
//                pointSum = pointSum + order.getOrderFeedback().getPoint();
//            }
//        }
//
//        // return average or return zero
//        return (count != 0) ? (pointSum / count) * 100 : count;
//    }


    /**
     * Calculate average user points based on his orders feedback
     *
     * @param profile - profile
     * @return
     */
//    private Integer calcAvgUserOrderPoints(Profile profile) {
//        String userId = profile.getId();
//        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
//        orderFilterOptions.setSellerId(userId);
//
//        int pointSum = 0;
//        int count = 0;
//
//        List<Order> orderList = findOrdersWihOptions(orderFilterOptions);
//
//        for (Order order : orderList) {
//            if (order.getOrderFeedback() != null) {
//                count++;
//                pointSum = pointSum + order.getOrderFeedback().getPoint();
//            }
//        }
//
//        // return average or return zero
//        return (count != 0) ? (pointSum / count) * 100 : count;
//    }

}
