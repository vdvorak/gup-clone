package ua.com.gup.rent.service.dto.rent.offer.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferPriceDTO implements Serializable {

    private BigDecimal businessDayCost;
    private BigDecimal weekendDayCost;
    private BigDecimal holidayDayCost;

    public RentOfferPriceDTO() {
    }

    public BigDecimal getBusinessDayCost() {
        return businessDayCost;
    }

    public void setBusinessDayCost(BigDecimal businessDayCost) {
        this.businessDayCost = businessDayCost;
    }

    public BigDecimal getWeekendDayCost() {
        return weekendDayCost;
    }

    public void setWeekendDayCost(BigDecimal weekendDayCost) {
        this.weekendDayCost = weekendDayCost;
    }

    public BigDecimal getHolidayDayCost() {
        return holidayDayCost;
    }

    public void setHolidayDayCost(BigDecimal holidayDayCost) {
        this.holidayDayCost = holidayDayCost;
    }
}
