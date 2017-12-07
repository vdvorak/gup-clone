package ua.com.gup.common.model.uapay.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import ua.com.gup.common.model.uapay.Card;
import ua.com.gup.common.model.uapay.Notify;
import ua.com.gup.common.model.uapay.base.UaPayRequestData;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectPaymentData extends UaPayRequestData{
    private String externalId;
    private Card cardFrom;
    private Card cardTo;
    private Notify notify;
    private Long amount;
    private Integer currency;
    private String description;

    public DirectPaymentData() {
        this.currency = 980; //UAH code
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Card getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(Card cardFrom) {
        this.cardFrom = cardFrom;
    }

    public Card getCardTo() {
        return cardTo;
    }

    public void setCardTo(Card cardTo) {
        this.cardTo = cardTo;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Notify getNotify() {
        return notify;
    }

    public void setNotify(Notify notify) {
        this.notify = notify;
    }
}
