package ua.com.itproekt.gup.service.order;


import ua.com.itproekt.gup.dto.OrderInfo;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;

import java.util.List;

public interface OrderService {
    /**
     * @param order
     */
    void create(Order order);

    /**
     * @param id
     * @return
     */
    Order findById(String id);

    /**
     * @param order
     * @return
     */
    Order findAndUpdate(Order order);

    /**
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * @param orderFilterOptions
     * @return
     */
    List<Order> findOrdersWihOptions(OrderFilterOptions orderFilterOptions);


    /**
     * Return OrderInfo list for private use
     *
     * @param orderFilterOptions
     * @return
     */
    List<OrderInfo> findOrderInfoWithOptionsForPrivate(OrderFilterOptions orderFilterOptions);


    /**
     * @param userId
     * @return
     */
    List<Order> findAllOrdersForUser(String userId);


    /**
     * @param offerId
     * @return
     */
    List<OrderFeedback> findAllFeedbacksForOffer(String offerId);

    /**
     * Calculate average point of orders for user (seller) from order feedback list
     *
     * @param orderFeedback
     * @return
     */
    int calculateAveragePointsForOrderFeedbackList(List<OrderFeedback> orderFeedback);


    /**
     * @param offerId
     * @return
     */
    int countOrderAmountForOffer(String offerId);

    /**
     * @param offerId
     * @return
     */
    List<Order> findAllOrdersForOffer(String offerId);

    /**
     * @param orderList
     * @return
     */
    int calculateAveragePointsForListOfOrders(List<Order> orderList);

}
