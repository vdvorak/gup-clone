package ua.com.gup.rent.model.mongo.rent.calendar;

import lombok.Data;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;

import java.io.Serializable;
import java.util.Map;

@Data
public class RentOfferCalendarInterval implements Serializable {

    private String rentStartDate;
    private String rentEndDate;
    private Map<String, RentOfferCalendarDay> daysMap;

    public RentOfferCalendarInterval() {
    }

    public RentOfferCalendarInterval(RentOfferCalendarInterval calendarInterval) {
        this.rentStartDate = calendarInterval.getRentStartDate();
        this.rentEndDate = calendarInterval.getRentEndDate();
    }
}
