//package ua.com.itproekt.gup.service.offers.calendar;
//
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//public class Main {
//
//    static String formatter = "d.MM.yyyy";
//    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
//    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
//
//    private Long[][] weekdays = {{1401570000000l,1401915600000l},{1402174800000l,1402520400000l},{1402779600000l,1403125200000l},{1403384400000l,1403730000000l},{1403989200000l,1404075600000l}};
//    private Long[][] weekends = {{1402002000000l,1402088400000l},{1402606800000l,1402693200000l},{1403211600000l,1403298000000l},{1403816400000l,1403902800000l}};
//
//    public static void main(String[] args) {
////        String strDate = "26.06.2014";
////        long lDate = 1404075600000l;
////        System.out.println("<DAY.MONTH.YEAR> >> (Long): '" + convertDate(strDate) + "'");
//////        System.out.println("(Long) >> <DAY.MONTH.YEAR>: '" + convertDate(lDate) + "'");
//
//        String[][] weekdays = {{"01.06.2014","05.06.2014"},{"08.06.2014","12.06.2014"},{"15.06.2014","19.06.2014"},{"22.06.2014","26.06.2014"},{"29.06.2014","31.06.2014"}};
//        String[][] weekends = {{"06.06.2014","07.06.2014"},{"13.06.2014","14.06.2014"},{"20.06.2014","21.06.2014"},{"27.06.2014","28.06.2014"}};
//
////        Long[][] longWeekdays = convertDate(weekdays);
////        for (int aDate=0; aDate<longWeekdays.length; ++aDate){
////            for (int date=0; date<longWeekdays[aDate].length; ++date){
////                System.out.print(longWeekdays[aDate][date] + " ");
////            }
////            System.out.print(", ");
////        }
//    }
//
////    static Long[][] convertDate(String[][] strDate) {
////        Long[][] longDate = new Long[strDate.length][2];
////        for (int aDate=0; aDate<strDate.length; ++aDate){
////            for (int date=0; date<strDate[aDate].length; ++date){
////                longDate[aDate][date] = convertDate(strDate[aDate][date]);
////            }
////        }
////        return longDate;
////    }
//
//    /**
//     * @param strDate
//     * @return
//     */
//    static Long[] convertDate(String[] strDate) {
//        Long[] longDate = new Long[strDate.length];
//        for (int date=0; date<strDate.length; ++date){
//            longDate[date] = convertDate(strDate[date]);
//        }
//        return longDate;
//    }
//
//    /**
//     * <DAY.MONTH.YEAR> >> (Long)
//     * ("30.06.2014" >> 1472936400000)
//     *
//     * @param strDate
//     * @return
//     */
//    static Long convertDate(String strDate) {
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
//    static String convertDate(long lDate) {
//        Date date = new Date(lDate);
//        String strDate = simpleDateFormat.format(date);
//        return strDate;
//    }
//
//}
