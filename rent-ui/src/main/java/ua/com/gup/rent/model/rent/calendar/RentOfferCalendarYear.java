package ua.com.gup.rent.model.rent.calendar;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferCalendarYear {
    @Setter @Getter
    private Integer year;
    @Setter @Getter
    private RentOfferCalendarDay[] days;

    public RentOfferCalendarYear(Integer year) {
        this.year = year;
    }

}
