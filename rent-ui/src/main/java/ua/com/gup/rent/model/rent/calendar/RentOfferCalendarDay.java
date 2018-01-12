package ua.com.gup.rent.model.rent.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ua.com.gup.common.model.enumeration.CommonCurrency;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@NoArgsConstructor
@Getter
@Setter
public class RentOfferCalendarDay {

    private RentOfferCalendarDayType type;
    private BigDecimal price;
    private CommonCurrency currency;

    public RentOfferCalendarDay(RentOfferCalendarDayType type) {
        this.type = type;
        this.price = null;
        this.currency = CommonCurrency.UAH;
    }

}
