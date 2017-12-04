package ua.com.gup.rent.util;

import ua.com.gup.rent.model.rent.calendar.RentCalendarDay;
import ua.com.gup.rent.model.rent.calendar.RentCalendarYear;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class RentCalendarUtil {
    private RentCalendarUtil() {
    }

    public static RentCalendarYear generateForYear(LocalDate now) {
        LocalDate firstDay = now.with(firstDayOfYear());
        LocalDate lastDay = now.with(lastDayOfYear()).plusDays(1);
        int days = (int) ChronoUnit.DAYS.between(firstDay, lastDay);
        RentCalendarYear rentCalendarYear = new RentCalendarYear(now.getYear());
        RentCalendarDay[] rentCalendarDays = new RentCalendarDay[days];
        while (firstDay.isBefore(lastDay)) {
            rentCalendarDays[firstDay.getDayOfYear() - 1] = new RentCalendarDay(isWeekendDay(firstDay.getDayOfWeek()) ? 1 : 0);
            firstDay = firstDay.plusDays(1);
        }
        rentCalendarYear.setDays(rentCalendarDays);
        return rentCalendarYear;
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
