package ua.com.gup.rent.model.rent.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferCalendarDay {
    @Getter @Setter
    private int type;
    @Getter @Setter
    private BigDecimal price;
    @Getter @Setter
    private String currency;

    public RentOfferCalendarDay(int type) {
        this.type = type;
    }
}
