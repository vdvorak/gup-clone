package ua.com.gup.rent.model.rent.price;

import java.io.Serializable;
import java.math.BigDecimal;

public class RentOfferPrice implements Serializable {

    private BigDecimal businessDayCost;
    private BigDecimal weekendDayCost;
    private BigDecimal holidayDayCost;

    public RentOfferPrice() {
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
