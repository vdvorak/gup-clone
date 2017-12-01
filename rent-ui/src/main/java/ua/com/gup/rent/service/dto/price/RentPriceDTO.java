package ua.com.gup.rent.service.dto.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentPriceDTO {

    private BigDecimal businessDayCost;
    private BigDecimal weekendDayCost;
    private BigDecimal holidayDayCost;

    public RentPriceDTO() {
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
