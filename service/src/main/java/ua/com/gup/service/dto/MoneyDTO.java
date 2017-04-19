package ua.com.gup.service.dto;


import ua.com.gup.domain.enumeration.Currency;

import java.math.BigDecimal;

public class MoneyDTO {

    private BigDecimal amount;
    private Currency currency;

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
}
