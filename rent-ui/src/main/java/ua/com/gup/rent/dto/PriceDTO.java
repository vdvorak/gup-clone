package ua.com.gup.rent.dto;

import java.math.BigDecimal;

public class PriceDTO {

    private BigDecimal businessDayCost;
    private BigDecimal weekendDayCost;
    private BigDecimal holidayDayCost;

    public PriceDTO() {
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
