package ua.com.gup.model.order;

/**
 * This class describe different stages of order.
 */
public enum OrderStatus {
    NEW,
    CANCELED_BY_BUYER,
    ACCEPT,
    REJECTED_BY_SELLER,
    SENT,
    COMPLETED,
    FAILED,
}
