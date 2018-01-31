package ua.com.gup.rent.model.rent.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonCurrency;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"currency", "dayStatus"}, ignoreUnknown = true)
public class RentOfferCalendarDay {

    private int type; // RentOfferCalendarDayType type
    private BigDecimal price;
    private CommonCurrency currency;
    private RentOfferCalendarDayStatus dayStatus;

    public RentOfferCalendarDay() {
        this.dayStatus = RentOfferCalendarDayStatus.FREE;
    }

    public RentOfferCalendarDay(RentOfferCalendarDayType type) {
        this.type = type.getType();
        this.price = null;
        this.currency = CommonCurrency.UAH;
        this.dayStatus = RentOfferCalendarDayStatus.FREE;
    }

}
