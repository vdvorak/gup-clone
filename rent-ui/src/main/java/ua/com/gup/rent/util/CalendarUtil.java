package ua.com.gup.rent.util;

import ua.com.gup.rent.model.CalendarDay;
import ua.com.gup.rent.model.CalendarYear;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;
import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class CalendarUtil {
    private CalendarUtil() {
    }

    public static CalendarYear generateForYear(LocalDate now) {
        LocalDate firstDay = now.with(firstDayOfYear());
        LocalDate lastDay = now.with(lastDayOfYear()).plusDays(1);
        int days = (int) ChronoUnit.DAYS.between(firstDay, lastDay);
        CalendarYear calendarYear = new CalendarYear(now.getYear());
        CalendarDay[] calendarDays = new CalendarDay[days];
        while (firstDay.isBefore(lastDay)) {
            calendarDays[firstDay.getDayOfYear() - 1] = new CalendarDay(isWeekendDay(firstDay.getDayOfWeek()) ? 1 : 0);
            firstDay = firstDay.plusDays(1);
        }
        calendarYear.setDays(calendarDays);
        return calendarYear;
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
