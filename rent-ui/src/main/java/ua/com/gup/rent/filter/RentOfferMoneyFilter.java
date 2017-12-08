package ua.com.gup.rent.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.common.model.enumeration.CommonCurrency;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Money filter model")
public class RentOfferMoneyFilter {

    @ApiModelProperty("Currency")
    @NotNull
    private CommonCurrency currency;

    @ApiModelProperty("From")
    private Double from;

    @ApiModelProperty("To")
    private Double to;

    public CommonCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(CommonCurrency currency) {
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
