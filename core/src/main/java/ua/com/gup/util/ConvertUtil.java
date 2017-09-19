package ua.com.itproekt.gup.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

public class ConvertUtil {

    private static final String formatter = "d.MM.yyyy";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    /**
     * <DAY.MONTH.YEAR> >> (Long)
     * ("30.06.2014" >> 1472936400000)
     *
     * @param strDate
     * @return
     */
    public static Long toDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.UK); //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(strDate, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date.getTime();
    }

    /**
     * (Long) >> <DAY.MONTH.YEAR>
     * 1472936400000 >> "30.06.2014"
     *
     * @param lDate
     * @return
     */
    public static String toDate(long lDate) {
        Date date = new Date(lDate);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }

    /**
     * @param[] strDate
     * @return[]
     */
    public static Long[] toDate(String[] strDate) {
        Long[] longDate = new Long[strDate.length];
        for (int date=0; date<strDate.length; ++date){
            longDate[date] = toDate(strDate[date]);
        }
        return longDate;
    }

    public static String[] toDate(Long[] days) {
        String[] strDate = new String[days.length];
        for (int date=0; date<days.length; ++date)
            strDate[date] = toDate(days[date]);
        return strDate;
    }

    public static Long[] toDate(Collection<Long> days) {
        Long[] lDate = new Long[days.size()];
        int date=0;
        for (Long day : days){
            lDate[date] = day;
            ++date;
        }
        return lDate;
    }

}
