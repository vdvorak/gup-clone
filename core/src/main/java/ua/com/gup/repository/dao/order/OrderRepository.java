package ua.com.gup.repository.dao.order;


import ua.com.gup.model.order.Order;
import ua.com.gup.model.order.filter.OrderFilterOptions;

import java.util.List;

public interface OrderRepository {

    void create(Order order);

    Order findById(String id);

    Order findAndUpdate(Order order);

    int delete(String id);

   List<Order> findOrdersWihOptions(OrderFilterOptions orderFilterOptions);
}
