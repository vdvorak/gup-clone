package ua.com.itproekt.gup.service.offers;

import org.apache.log4j.Logger;

public class CalendarRentServiceImpl extends CalendarRentService {

    private Logger logger = Logger.getLogger(CalendarPriceServiceImpl.class);

    public CalendarRentServiceImpl(Boolean weekdayStatus, Boolean weekendStatus) {
        super(weekdayStatus, weekendStatus);
    }

}
