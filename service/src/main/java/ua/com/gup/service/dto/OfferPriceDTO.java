package ua.com.gup.service.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.enumeration.Currency;

import java.math.BigDecimal;

@ApiModel
public class OfferPriceDTO {
    @ApiModelProperty(position = 0, example = "10000")
    private BigDecimal amount;

    @ApiModelProperty(position = 10, example = "USD")
    private Currency currency;

    @ApiModelProperty(position = 20, example = "false")
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
