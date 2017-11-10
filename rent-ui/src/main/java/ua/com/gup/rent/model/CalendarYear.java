package ua.com.gup.rent.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarYear {
    private Integer year;
    private CalendarDay[] days;

    public CalendarYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public CalendarDay[] getDays() {
        return days;
    }

    public void setDays(CalendarDay[] days) {
        this.days = days;
    }
}
