package ua.com.itproekt.gup.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.order.OrderRepository;
import ua.com.itproekt.gup.dto.OrderInfo;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.order.OrderStatus;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProfilesService profilesService;


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
                .setOrderType(order.getOrderType())
                .setOrderComments(order.getOrderComments())
                .setPaymentMethod(order.getPaymentMethod());

        orderRepository.create(newOrder);
        order.setId(newOrder.getId());
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


    /**
     * Return list of order info with additional fields
     *
     * @param orderFilterOptions
     * @return
     */
    @Override
    public List<OrderInfo> findOrderInfoWithOptionsForPrivate(OrderFilterOptions orderFilterOptions) {
        List<Order> orderList = orderRepository.findOrdersWihOptions(orderFilterOptions);
        return orderInfoListPreparatorForPrivate(orderList);
    }


    @Override
    public List<Order> findAllOrdersForUser(String userId) {
        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setBuyerId(userId);
        orderFilterOptions.setBuyerId(userId);
        return orderRepository.findOrdersWihOptions(orderFilterOptions);
    }

    /**
     * @param offerId
     * @return
     */
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


    /**
     * @param orderList
     * @return
     */
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

    /**
     * Calculate average point of orders for user (seller) from order feedback list
     *
     * @param orderFeedback
     * @return
     */
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

    /**
     * @param offerId
     * @return
     */
    @Override
    public int countOrderAmountForOffer(String offerId) {
        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setOfferId(offerId);
        return orderRepository.findOrdersWihOptions(orderFilterOptions).size();
    }

    /**
     * @param offerId
     * @return
     */
    @Override
    public List<Order> findAllOrdersForOffer(String offerId) {
        OrderFilterOptions orderFilterOptions = new OrderFilterOptions();
        orderFilterOptions.setOfferId(offerId);
        return orderRepository.findOrdersWihOptions(orderFilterOptions);
    }


    /**
     * @param orderList
     * @return
     */
    @Override
    public List<OrderInfo> orderInfoListPreparatorForPrivate(List<Order> orderList) {
        List<OrderInfo> orderInfoList = new ArrayList<>();

        for (Order order : orderList) {
            orderInfoList.add(singleOrderInfoPreparatorForPrivate(order));
        }
        return orderInfoList;

    }


    /**
     * Transform single order into orderInfo object with additional field;
     *
     * @param order
     * @return
     */
    private OrderInfo singleOrderInfoPreparatorForPrivate(Order order) {
        OrderInfo orderInfo = new OrderInfo();

        Profile buyer = profilesService.findById(order.getBuyerId());
        Profile seller = profilesService.findById(order.getSellerId());

        orderInfo.setOrder(order)
                .setBuyerImg(buyer.getImgId())
                .setBuyerName(buyer.getUsername())
                .setSellerImg(seller.getImgId())
                .setSellerName(seller.getUsername());

        return orderInfo;
    }

    /**
     * @param orderList
     * @return
     */
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

    /**
     * @param orderInfoList
     * @param sellerId
     * @return
     */
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

    /**
     * @param orderInfoList
     * @param buyerId
     * @return
     */
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
