package ua.com.itproekt.gup.util;


/**
 * Payment methods in the order.
 *
 * @author Kobylyatskyy Alexander
 */
public enum PaymentMethod {
    GUP, // безопасная сделка через ГУП
    CASH_ON_DELIVERY, // наложенный платёж
    CARD_PAYMENT, // предоплата
    CASH_PAYMENT, //COURIER, // курьеру, либо при самовывозе
    CASH_LESS_PAYMENT // безнал
}
