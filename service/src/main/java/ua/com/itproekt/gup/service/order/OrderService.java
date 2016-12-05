package ua.com.itproekt.gup.service.order;


import ua.com.itproekt.gup.dto.OrderInfo;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.order.Order;
import ua.com.itproekt.gup.model.order.OrderFeedback;
import ua.com.itproekt.gup.model.order.filter.OrderFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;

import java.util.List;


/**
 * Interface for working with orders.
 *
 * @author Kobylyatsky Alexander
 */
public interface OrderService {


    /**
     * Create new order.
     *
     * @param userId                                    - the user ID.
     * @param order                                     - the order.
     * @param offer                                     - the offer.
     */
    void create(String userId, Order order, Offer offer);


    /**
     * Create new order.
     *
     * @param order                                     - the order.
     */
    void create(Order order);


    /**
     * Find and return one order by it's ID.
     *
     * @param id                                        - the order ID.
     * @return - the order.
     */
    Order findById(String id);


    /**
     * Update one order.
     *
     * @param order                                     - the order which must be updated.
     * @return                                          - the order after update.
     */
    Order findAndUpdate(Order order);


    /**
     * Delete one order by it's ID.
     *
     * @param id                                        - the ID of the order which must be deleted.
     * @return                                          - the int.
     */
    int delete(String id);


    /**
     * Find and return orders relevant to the OrderFilterOptions.
     *
     * @param orderFilterOptions                        - the OrderFilterOptions.
     * @return                                          - the order list.
     */
    List<Order> findOrdersWihOptions(OrderFilterOptions orderFilterOptions);


    /**
     * Return only current user's orders.
     *
     * @param orderFilterOptions                        - the OrderFilterOptions object.
     * @return                                          - the list of orders.
     */
    List<Order> getAllOrders(OrderFilterOptions orderFilterOptions);


    /**
     * We pass a profile argument to reduce the number of queries in DB
     *
     * @param orderList                                 - the order list.
     * @param profile                                   - the profile.
     * @return                                          - the OrderInfo list.
     */
    List<OrderInfo> orderInfoListPreparatorForPrivate(List<Order> orderList, Profile profile);


    /**
     * Find and return all orders relative to the specific user.
     *
     * @param userId                                    - the user ID for whom we must find all orders.
     * @return                                          - the order list.
     */
    List<Order> findAllOrdersForUser(String userId);


    /**
     * Find and return the list of the feedback relatives to the specific offer.
     *
     * @param offerId                                   - the offer ID.
     * @return                                          - the list of the OrderFeedback.
     */
    List<OrderFeedback> findAllFeedbacksForOffer(String offerId);


    /**
     * Calculate average point of orders for user (seller) from order feedback list.
     *
     * @param orderFeedback                             - the list of the OrderFeedbackList.
     * @return                                          - the average point.
     */
    int calculateAveragePointsForOrderFeedbackList(List<OrderFeedback> orderFeedback);


    /**
     * Count order amount relates for specific offer.
     *
     * @param offerId                                   - offer ID in which we must count order's amount.
     * @return                                          - the orders amount.
     */
    int countOrderAmountForOffer(String offerId);

    /**
     * Find and returns all orders which relates to the specific offer.
     *
     * @param offerId                                   - the offer ID to which we must find orders.
     * @return                                          - the list of the orders.
     */
    List<Order> findAllOrdersForOffer(String offerId);


    /**
     * Calculate average points for the lists of the orders.
     *
     * @param orderList                                 - the Order list.
     * @return                                          - the average amount.
     */
    int calculateAveragePointsForListOfOrders(List<Order> orderList);


    /**
     * Calculate feedback amount for list of orders.
     *
     * @param orderList                                 - the list of the orders.
     * @return                                          - the int result.
     */
    int calculateFeedbackAmountForOrderList(List<OrderInfo> orderList);


    /**
     * Receive order list where user is seller.
     *
     * @param orderInfoList                             - the list of the OrderInfo.
     * @param sellerId                                  - the seller ID.
     * @return                                          - the list of the OrderInfo objects.
     */
    List<OrderInfo> orderInfoSellerListFromTotalOrderListOfUser(List<OrderInfo> orderInfoList, String sellerId);


    /**
     * Receive order list where user is buyer.
     *
     * @param orderInfoList                             - the list of the OrderInfo.
     * @param buyerId                                   - the buyer ID.
     * @return                                          - the list of the OrderInfo objects.
     */
    List<OrderInfo> orderInfoBuyerListFromTotalOrderListOfUser(List<OrderInfo> orderInfoList, String buyerId);


    /**
     * Check is offer valid.
     *
     * @param order                                     - the order.
     * @param offer                                     - the correspond offer.
     * @return                                          - the true or false.
     */
    boolean isOrderValid(Order order, Offer offer);


    /**
     * Check is shipping methods in the order is valid
     *
     * @param order                                     - the order.
     * @param offer                                     - the correspond offer.
     * @return                                          - the true or false.
     */
    boolean isShippingMethodsValid(Order order, Offer offer);


    /**
     * Check is payment method
     *
     * @param order                                     - the order.
     * @param offer                                     - the correspondent offer.
     * @return                                          - the true or false.
     */
    boolean isPaymentMethodsValid(Order order, Offer offer);


    /**
     * Add and update seller note for specific order.
     *
     * @param order                                     - the order.
     * @param sellerNote                                - the seller note.
     */
    void updateSellerNote(Order order, String sellerNote);


    // --------------------------- Orders stage -----------------------------------------------------------------


    /**
     * This method cancel order by buyer and send notification to seller.
     *
     * @param oldOrder                                  - the order.
     */
    void cancelOrderByBuyer(Order oldOrder);


    /**
     * This method change Order Status to ACCEPT and send notification to buyer.
     *
     * @param oldOrder                                  - the order.
     */
    void acceptOrderBySeller(Order oldOrder);


    /**
     * This method change Order Status ORDER_REJECTED_BY_SELLER and send notification to seller.
     *
     * @param oldOrder                                  - the order.
     */
    void rejectedOrderBySeller(Order oldOrder);


    /**
     * This method can only change order status to SENT and only by seller. Due to the TransportCompany type
     * you need or not put trackNumber. Also this method update sentDate field of the order.
     * Also this method send notification to the buyer.
     *
     * @param oldOrder                                  - the old order (version of the order before update).
     */
    void sendOrderBySeller(Order oldOrder);


    /**
     * This method can only change order status to COMPLETED by seller.
     * Send notification to the buyer.
     *
     * @param oldOrder                                  - the old order (version of the order before update).
     * @return                                          - the true if order was "Complete", false - if not.
     */
    boolean completeOrderBySeller(Order oldOrder);


    /**
     * This method can only change order status to COMPLETED by buyer.
     * Send notification to the seller.
     *
     * @param oldOrder                                  - the old order (version of the order before update).
     * @return                                          - the true if order was "Complete", false - if not.
     */
    boolean completeOrderByBuyer(Order oldOrder);


    /**
     * This method for add comment to the order.
     *
     * @param oldOrder                                  - the old order (version of the order before update).
     * @param newOrder                                  - the updated order with new comment.
     * @return                                          - the true if comment was update, false - if not.
     */
    boolean commentUpdateInOrder(Order oldOrder, Order newOrder);
}
