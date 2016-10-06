package ua.com.itproekt.gup.service.offers.calendar;

import org.apache.log4j.Logger;

public class CalendarStatusOwner extends CalendarStatus {

    private Logger logger = Logger.getLogger(CalendarStatusOwner.class);

    public CalendarStatusOwner(Long weekdayPrice, Long weekendPrice) {
        super(weekdayPrice, weekendPrice);
    }

}
