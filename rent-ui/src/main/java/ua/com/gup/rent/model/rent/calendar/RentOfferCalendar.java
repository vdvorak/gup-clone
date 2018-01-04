package ua.com.gup.rent.model.rent.calendar;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
public class RentOfferCalendar {

    private RentOfferCalendarDay[] days;
    private String startDate;
    private String endDate;

}
