package ua.com.itproekt.gup.service.offers;

import org.apache.log4j.Logger;

public class RentStatusServiceImpl extends RentStatusService {

    private Logger logger = Logger.getLogger(CalendarStatusServiceImpl.class);

    public RentStatusServiceImpl(Boolean weekdayStatus, Boolean weekendStatus) {
        super(weekdayStatus, weekendStatus);
    }

}
