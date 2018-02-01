package ua.com.gup.rent.service.dto.rent.offer.calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferCalendarDTO implements Serializable {


    @JsonProperty("startDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonProperty("endDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
    @JsonProperty("days")
    private List<RentOfferCalendarDay> days;

}
