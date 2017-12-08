package ua.com.gup.dto.offer;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.common.model.enumeration.CommonCurrency;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class OfferPriceDTO implements Serializable {
    @ApiModelProperty(position = 0, example = "10000")
    private BigDecimal amount;

    @ApiModelProperty(position = 10, example = "USD")
    private CommonCurrency currency;

    @ApiModelProperty(position = 20, example = "false")
    private Boolean priceWithVAT;

    @ApiModelProperty(position = 30, example = "false")
    private Boolean bargainingPossible;

    public OfferPriceDTO() {
        bargainingPossible = Boolean.FALSE;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CommonCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(CommonCurrency currency) {
        this.currency = currency;
    }

    public Boolean getPriceWithVAT() {
        return priceWithVAT;
    }

    public void setPriceWithVAT(Boolean priceWithVAT) {
        this.priceWithVAT = priceWithVAT;
    }

    public Boolean getBargainingPossible() {
        return bargainingPossible;
    }

    public void setBargainingPossible(Boolean bargainingPossible) {
        this.bargainingPossible = bargainingPossible;
    }
}
