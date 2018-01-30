package ua.com.gup.rent.model.mongo.rent.calendar;


import lombok.Data;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
public class RentOfferCalendar {

    private String rentStartDate;
    private String rentEndDate;
    private RentOfferCalendarDay[] days;

    public RentOfferCalendar() {
    }

    public RentOfferCalendar(LocalDate rentStartDate, LocalDate rentEndDate) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.rentStartDate = rentStartDate.format(pattern);
        this.rentEndDate = rentEndDate.format(pattern);
    }

}
