package ua.com.itproekt.gup.model.order;


public enum OrderStatus {
    NEW,
    CANCELED_BY_BUYER,
    ACCEPT,
    REJECTED_BY_SELLER,
    SENT,
    RECEIVED,
    WAITING_SELLER_FEEDBACK,
    COMPLETED,
}
