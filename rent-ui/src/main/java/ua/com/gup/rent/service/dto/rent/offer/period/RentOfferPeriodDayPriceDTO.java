package ua.com.gup.rent.service.dto.rent.offer.period;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferPeriodDayPriceDTO {
    @JsonProperty("currency")
    private String currency = "UAH";
    @JsonProperty("amount")
    private BigDecimal amount;

}
