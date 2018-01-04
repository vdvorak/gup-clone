package ua.com.gup.rent.model.rent.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ua.com.gup.common.model.enumeration.CommonCurrency;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class RentOfferCalendarDay {
    @Getter
    @Setter
    private int type;
    @Getter
    @Setter
    private BigDecimal price;
    @Getter
    @Setter
    private CommonCurrency currency;

    public RentOfferCalendarDay(int type) {
        this.type = type;
        this.price = null;
        this.currency = CommonCurrency.UAH;
    }

}
