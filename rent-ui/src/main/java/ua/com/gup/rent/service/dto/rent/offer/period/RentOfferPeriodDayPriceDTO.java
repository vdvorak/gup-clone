package ua.com.gup.rent.service.dto.rent.offer.period;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferPeriodDayPriceDTO {

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("amount")
    private BigDecimal amount;

    public RentOfferPeriodDayPriceDTO() {
        currency = "UAH";
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
