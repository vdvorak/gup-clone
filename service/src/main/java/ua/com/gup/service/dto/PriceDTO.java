package ua.com.gup.service.dto;


import ua.com.gup.domain.enumeration.Currency;

import java.math.BigDecimal;

public class PriceDTO {

    private BigDecimal amount;

    private Currency currency;

    private Boolean priceWithVAT;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Boolean getPriceWithVAT() {
        return priceWithVAT;
    }

    public void setPriceWithVAT(Boolean priceWithVAT) {
        this.priceWithVAT = priceWithVAT;
    }
}
