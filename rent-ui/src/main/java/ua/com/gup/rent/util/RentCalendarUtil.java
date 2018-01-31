package ua.com.gup.rent.util;

import org.springframework.util.CollectionUtils;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDayType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class RentCalendarUtil {

    private RentCalendarUtil() {
    }


    public static Map<String, RentOfferCalendarDay> getDaysMapForDates(LocalDate startDate,
                                                                       LocalDate endDate,
                                                                       List<RentOfferCalendarDay> daysList) {
        if (endDate.isBefore(startDate) || CollectionUtils.isEmpty(daysList)) {
            return Collections.EMPTY_MAP;
        }
        Map<String, RentOfferCalendarDay> daysMap = new LinkedHashMap<>((int) RentDateUtil.calculateDaysDiffBetweenDates(startDate, endDate));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int i = 0;
        while (startDate.isBefore(endDate)) {
            daysMap.put(formatter.format(startDate), daysList.get(i++));
            startDate = startDate.plusDays(1);
        }

        return daysMap;
    }

    public static Map<String, RentOfferCalendarDay> getDaysMapForDates(String startDate,
                                                                       String endDate,
                                                                       List<RentOfferCalendarDay> daysList) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);

        return getDaysMapForDates(localStartDate, localEndDate, daysList);
    }


    public static RentOfferCalendar generateCalendarForDates(LocalDate startDate) {
        return generateCalendarForDates(startDate, startDate.plus(Period.ofYears(1)));
    }

    public static RentOfferCalendar generateCalendarForDates(LocalDate startDate, LocalDate endDate) {
        int days = (int) ChronoUnit.DAYS.between(startDate, endDate);
        RentOfferCalendar rentOfferCalendar = new RentOfferCalendar(startDate, endDate);

        RentOfferCalendarDay[] rentOfferCalendarDays = new RentOfferCalendarDay[days];
        int i = 0;
        while (startDate.isBefore(endDate)) {
            rentOfferCalendarDays[i++] = new RentOfferCalendarDay(isWeekendDay(startDate.getDayOfWeek()) ?
                    RentOfferCalendarDayType.WEEKEND : RentOfferCalendarDayType.BUSINESS);
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
