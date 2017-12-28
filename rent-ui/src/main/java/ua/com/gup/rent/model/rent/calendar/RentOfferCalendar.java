package ua.com.gup.rent.model.rent.calendar;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class RentOfferCalendar {
    @Setter @Getter
    private int year;
    @Setter @Getter
    private RentOfferCalendarDay[] days;

    public RentOfferCalendar(int year) {
        this.year = year;
    }

}
