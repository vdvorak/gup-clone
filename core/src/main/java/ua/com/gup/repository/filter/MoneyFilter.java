package ua.com.gup.repository.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.enumeration.Currency;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel(description = "Money filter model")
public class MoneyFilter {

    @ApiModelProperty("Currency")
    @NotNull
    private Currency currency;

    @ApiModelProperty("Range of amount")
    private Range<BigDecimal> amount;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Range<BigDecimal> getAmount() {
        return amount;
    }

    public void setAmount(Range<BigDecimal> amount) {
        this.amount = amount;
    }
}
