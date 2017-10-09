package ua.com.gup.model.enumeration;


public enum OfferPaymentType {
    GUP, // безопасная сделка через ГУП
    CASH_ON_DELIVERY, // наложенный платёж
    CARD_PAYMENT, // предоплата
    COURIER, // курьеру, либо при самовывозе & нал
    CASH_PAYMENT, // only нал.
    CASH_LESS_PAYMENT // безнал
}
