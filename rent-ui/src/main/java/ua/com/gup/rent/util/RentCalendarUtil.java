package ua.com.gup.rent.util;

import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class RentCalendarUtil {

    private RentCalendarUtil() {
    }

    public static RentOfferCalendar generateCalendarForDates(LocalDate startDate) {
        return generateCalendarForDates(startDate, startDate.plus(Period.ofYears(1)));
    }

    public static RentOfferCalendar generateCalendarForDates(LocalDate startDate, LocalDate endDate) {
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        RentOfferCalendar rentOfferCalendar = new RentOfferCalendar();
        RentOfferCalendarDay[] rentOfferCalendarDays = new RentOfferCalendarDay[days];
        int i = 0;
        while (startDate.isBefore(endDate)) {
            rentOfferCalendarDays[i++] = new RentOfferCalendarDay(isWeekendDay(startDate.getDayOfWeek()) ? 1 : 0);
            startDate = startDate.plusDays(1);
        }
        rentOfferCalendar.setDays(rentOfferCalendarDays);
        return rentOfferCalendar;
    }

    private static boolean isWeekendDay(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case SATURDAY:
            case SUNDAY:
                return true;
        }
        return false;
    }
}
