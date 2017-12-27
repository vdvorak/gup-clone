package ua.com.gup.rent.util;

import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class RentCalendarUtil {
    private RentCalendarUtil() {
    }

    public static RentOfferCalendar generateForYear(LocalDate now) {
        LocalDate firstDay = now.with(firstDayOfYear());
        LocalDate lastDay = now.with(lastDayOfYear()).plusDays(1);
        int days = (int) ChronoUnit.DAYS.between(firstDay, lastDay);
        RentOfferCalendar rentOfferCalendar = new RentOfferCalendar(now.getYear());
        RentOfferCalendarDay[] rentOfferCalendarDays = new RentOfferCalendarDay[days];
        while (firstDay.isBefore(lastDay)) {
            rentOfferCalendarDays[firstDay.getDayOfYear() - 1] = new RentOfferCalendarDay(isWeekendDay(firstDay.getDayOfWeek()) ? 1 : 0);
            firstDay = firstDay.plusDays(1);
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
