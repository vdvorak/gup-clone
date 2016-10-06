//package ua.com.itproekt.gup.service.offers.calendar;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.google.gson.reflect.TypeToken;
//import org.apache.log4j.Logger;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
///**
//* @see http://stackoverflow.com/questions/3272454/in-java-get-all-weekend-dates-in-a-given-month
//*/
//
//public class PriceSchemeTest {
//
//    private Logger logger = Logger.getLogger(PriceSchemeTest.class);
//
//    private static String formatter = "d.MM.yyyy";
//    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
//    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    private ArrayList<Long> listWeekdays = new ArrayList<Long>();
//    private ArrayList<Long> listWeekends = new ArrayList<Long>();
//
//
//    public void initDate(int month, int year){
//        java.util.Calendar cal = new GregorianCalendar(year, month, 1);
//        do {
//            int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
//            if (day != java.util.Calendar.SATURDAY && day != java.util.Calendar.SUNDAY) {
//                String strMonth = month<10 ? "0"+month : ""+month;
//                String strDay = cal.get(java.util.Calendar.DAY_OF_MONTH)<10 ? "0"+cal.get(java.util.Calendar.DAY_OF_MONTH) : ""+cal.get(java.util.Calendar.DAY_OF_MONTH);
//                listWeekdays.add(convertDate(strDay + "." + strMonth + "." + year));
//            }
//            cal.add(java.util.Calendar.DAY_OF_YEAR, 1);
//        }  while (cal.get(java.util.Calendar.MONTH) == month);
//
//        cal = new GregorianCalendar(year, month, 1);
//        do {
//            int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
//            if (day == java.util.Calendar.SATURDAY || day == java.util.Calendar.SUNDAY) {
//                String strMonth = month<10 ? "0"+month : ""+month;
//                String strDay = cal.get(java.util.Calendar.DAY_OF_MONTH)<10 ? "0"+cal.get(java.util.Calendar.DAY_OF_MONTH) : ""+cal.get(java.util.Calendar.DAY_OF_MONTH);
//                listWeekends.add(convertDate(strDay + "." + strMonth + "." + year));
//            }
//            cal.add(java.util.Calendar.DAY_OF_YEAR, 1);
//        }  while (cal.get(java.util.Calendar.MONTH) == month);
//    }
//
//    public void initDates(Long[] dates){
//        Arrays.sort(dates);
//        for (Integer year=Integer.valueOf(convertDate(dates[0]).split("\\.")[2]); year<=Integer.valueOf(convertDate(dates[dates.length-1]).split("\\.")[2]); ++year){
//            for (Integer month=Integer.valueOf(convertDate(dates[0]).split("\\.")[1]); month<=Integer.valueOf(convertDate(dates[dates.length-1]).split("\\.")[1]); ++month){
//                initDate(month, year);
//            }
//        }
//    }
//
//    @Test
//    public void testOutDays() {
//        String[] offerCalendar1 = {"22.06.2014","31.06.2014"};
//        initDates(convertDate(offerCalendar1));
//
//        System.out.println("weekdays: " + listWeekdays);
//        System.out.println("weekends: " + listWeekends);
//    }
//
//    /**
//     * <DAY.MONTH.YEAR> >> (Long)
//     * ("30.06.2014" >> 1472936400000)
//     *
//     * @param strDate
//     * @return
//     */
//    private Long convertDate(String strDate) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
//        LocalDate localDate = LocalDate.parse(strDate, formatter);
//        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        return date.getTime();
//    }
//
//    /**
//     * (Long) >> <DAY.MONTH.YEAR>
//     * 1472936400000 >> "30.06.2014"
//     *
//     * @param lDate
//     * @return
//     */
//    private static String convertDate(long lDate) {
//        Date date = new Date(lDate);
//        String strDate = simpleDateFormat.format(date);
//        return strDate;
//    }
//
//    /**
//     * @param strDate
//     * @return
//     */
//    private Long[] convertDate(String[] strDate) {
//        Long[] longDate = new Long[strDate.length];
//        for (int date=0; date<strDate.length; ++date){
//            longDate[date] = convertDate(strDate[date]);
//        }
//        return longDate;
//    }
//
//}
