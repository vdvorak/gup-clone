package ua.com.gup.rent.model.rent.calendar;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RentCalendarYear {
    private Integer year;
    private RentCalendarDay[] days;

    public RentCalendarYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public RentCalendarDay[] getDays() {
        return days;
    }

    public void setDays(RentCalendarDay[] days) {
        this.days = days;
    }
}
