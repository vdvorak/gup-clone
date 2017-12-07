package ua.com.gup.rent.service.dto.rent.offer.period;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferPeriodDTO {

    @JsonProperty("startDate")
    private LocalDate startDate;
    @JsonProperty("endDate")
    private LocalDate endDate;
    @JsonProperty("days")
    private RentOfferPeriodDayDTO[] days;

    public RentOfferPeriodDTO() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public RentOfferPeriodDayDTO[] getDays() {
        return days;
    }

    public void setDays(RentOfferPeriodDayDTO[] days) {
        this.days = days;
    }
}
