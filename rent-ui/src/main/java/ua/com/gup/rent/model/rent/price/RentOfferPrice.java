package ua.com.gup.rent.model.rent.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferPrice implements Serializable {
    private BigDecimal businessDayCost;
    private BigDecimal weekendDayCost;
    private BigDecimal holidayDayCost;
    private BigDecimal prepaymentPercent;

}
