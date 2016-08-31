package ua.com.itproekt.gup.service.order;


import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;

import java.util.List;

public interface OrderService {
    /**
     *
     * @param order
     */
    void create(Order order);

    /**
     *
     * @param id
     * @return
     */
    Order findById(String id);

    /**
     *
     * @param order
     * @return
     */
    Order findAndUpdate(Order order);

    /**
     *
     * @param id
     * @return
     */
    int delete(String id);

    /**
     *
     * @param orderFilterOptions
     * @return
     */
    List<Order> findOrdersWihOptions(OrderFilterOptions orderFilterOptions);

    /**
     *
     * @param offerId
     * @return
     */
    List<OrderFeedback> findAllFeedbacksForOffer(String offerId);

}
