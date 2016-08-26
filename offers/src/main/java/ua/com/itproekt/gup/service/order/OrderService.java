package ua.com.itproekt.gup.service.order;


import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;

import java.util.List;

public interface OrderService {
    void create(Order order);

    Order findById(String id);

    Order findAndUpdate(Order order);

    int delete(String id);

    List<Order> findOrdersWihOptions(OrderFilterOptions orderFilterOptions);



    List<OrderFeedback> findAllFeedbacksForOffer(String offerId);

}
