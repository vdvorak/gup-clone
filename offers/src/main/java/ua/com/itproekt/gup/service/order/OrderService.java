package ua.com.itproekt.gup.service.order;


import ua.com.itproekt.gup.model.order.Order;

public interface OrderService {
    void create(Order order);

    Order findById(String id);

    Order findAndUpdate(Order order);

    int delete(String id);
}
