package ua.com.gup.model.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.model.enumeration.Currency;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Money filter model")
public class MoneyFilter {

    @ApiModelProperty("Currency")
    @NotNull
    private Currency currency;

    @ApiModelProperty("From")
    private Double from;

    @ApiModelProperty("To")
    private Double to;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
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
