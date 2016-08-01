package ua.com.itproekt.gup.util;


public enum PaymentMethod {
    GUP, // безопасная сделка через ГУП
    CASH_ON_DELIVERY, // наложенный платёж
    CARD_PAYMENT, // предоплата
    COURIER, // курьеру, либо при самовывозе
    CASH_LESS_PAYMENT // безнал
}
