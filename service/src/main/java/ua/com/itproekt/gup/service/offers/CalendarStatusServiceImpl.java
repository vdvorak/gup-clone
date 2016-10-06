package ua.com.itproekt.gup.service.offers;

import org.apache.log4j.Logger;

public class CalendarStatusServiceImpl extends CalendarStatusService {

    private Logger logger = Logger.getLogger(CalendarStatusServiceImpl.class);

    public CalendarStatusServiceImpl(Long weekdayPrice, Long weekendPrice) {
        super(weekdayPrice, weekendPrice);
    }

}
