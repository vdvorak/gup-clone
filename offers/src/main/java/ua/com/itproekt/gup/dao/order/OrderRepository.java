package ua.com.itproekt.gup.dao.order;


import ua.com.itproekt.gup.model.order.Order;

public interface OrderRepository {

    void create(Order order);

    Order findById(String id);

    Order findAndUpdate(Order order);

    int delete(String id);
}
