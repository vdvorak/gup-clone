package ua.com.gup.rent.model.rent.calendar;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferCalendarYear {
    private Integer year;
    private RentOfferCalendarDay[] days;

    public RentOfferCalendarYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public RentOfferCalendarDay[] getDays() {
        return days;
    }

    public void setDays(RentOfferCalendarDay[] days) {
        this.days = days;
    }
}
