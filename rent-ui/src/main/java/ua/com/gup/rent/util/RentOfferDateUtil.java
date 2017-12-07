package ua.com.gup.rent.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RentOfferDateUtil {

    private RentOfferDateUtil() {
    }


    public static long calculateDaysDiffBetweenDates(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static List<LocalDate> getDateRangeBetweenDates(
            LocalDate startDate, LocalDate endDate, boolean inclusiveEndDate) {
        if (inclusiveEndDate) {
            endDate = endDate.plusDays(1);
        }
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(startDate::plusDays)
                .collect(Collectors.toList());
    }
}
