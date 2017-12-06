package ua.com.gup.rent.util;

import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarYear;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class RentCalendarUtil {
    private RentCalendarUtil() {
    }

    public static RentOfferCalendarYear generateForYear(LocalDate now) {
        LocalDate firstDay = now.with(firstDayOfYear());
        LocalDate lastDay = now.with(lastDayOfYear()).plusDays(1);
        int days = (int) ChronoUnit.DAYS.between(firstDay, lastDay);
        RentOfferCalendarYear rentOfferCalendarYear = new RentOfferCalendarYear(now.getYear());
        RentOfferCalendarDay[] rentOfferCalendarDays = new RentOfferCalendarDay[days];
        while (firstDay.isBefore(lastDay)) {
            rentOfferCalendarDays[firstDay.getDayOfYear() - 1] = new RentOfferCalendarDay(isWeekendDay(firstDay.getDayOfWeek()) ? 1 : 0);
            firstDay = firstDay.plusDays(1);
        }
        rentOfferCalendarYear.setDays(rentOfferCalendarDays);
        return rentOfferCalendarYear;
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
