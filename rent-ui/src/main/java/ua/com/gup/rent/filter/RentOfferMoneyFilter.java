package ua.com.gup.rent.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.rent.model.enumeration.RentOfferCurrency;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Money filter model")
public class RentOfferMoneyFilter {

    @ApiModelProperty("Currency")
    @NotNull
    private RentOfferCurrency currency;

    @ApiModelProperty("From")
    private Double from;

    @ApiModelProperty("To")
    private Double to;

    public RentOfferCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(RentOfferCurrency currency) {
        this.currency = currency;
    }

    public Double getFrom() {
        return from;
    }

    public void setFrom(Double from) {
        this.from = from;
    }

    public Double getTo() {
        return to;
    }

    public void setTo(Double to) {
        this.to = to;
    }
}
