//package ua.com.itproekt.gup.server.api.rest.offers;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//
//public class Main {
//
//    public static void main(String[] args) {
//        Long lDate = convertDate1("30.06.2014");
//        System.out.println("convertDate = " + lDate);
//
//        String strDate = convertDate2(lDate);
//        System.out.println("convertDate = " + strDate);
//
////        try {
////            String addDate = addDate();
////            System.out.println("addDate = " + addDate);
////        } catch (ParseException e) {}
//
//        try {
//            String addDate = addDate(5);
//            System.out.println("addDate = " + addDate);
//        } catch (ParseException e) {}
//
//
////        System.out.println("------------------------------------------------------------");
////
////        String currDate = convertDate22();
////        String lastDate = "16.09.2016";
////        System.out.println("date: '" + currDate + "' last-date: '" + lastDate + "'");
////
////        String addCurrDate = convertDate22();
////        String addLastDate1 = "16.09.2016";
////        String addLastDate2 = "7.10.2016"; // 7.03.2016   7.10.2016
////        int addMonth = 3;
////
////        try {
////            System.out.println("(add)date: '" + addDate(addCurrDate, addMonth) + "' (add)last-date: '" + addDate(addLastDate1, addMonth) + "'");
////        } catch (ParseException e) {}
////
////        try {
////            if(isExpired(addCurrDate, addLastDate2)) {
////                System.out.println("(add)date: '" + addDate(addCurrDate, addMonth) + "' (add)last-date: '" + addDate(addLastDate2, addMonth) + "'");
////            } else {
////                System.out.println("(add)date: '" + addDate(addCurrDate, addMonth) + "' (add)last-date: '" + addDate(addCurrDate, addMonth) + "'");
////            }
////        } catch (ParseException e) {}
//
//
//        System.out.println("============================================================");
//
//        long addCurrDate = getCurrDate1();
//        long addLastDate1 = convertDate1("16.09.2016");
//        long addLastDate2 = convertDate1("07.10.2016"); // 7.03.2016   7.10.2016
//        int addMonth = 3;
//
//        try {
//            System.out.println("(add)date: '" + addDate(addMonth) + "' (add)last-date: '" + addDate(addLastDate1, addMonth) + "'");
//        } catch (ParseException e) { System.err.println("Incorrect Data Format"); }
//
//        try {
//            if(isExpired(addCurrDate, addLastDate2)) {
//                System.out.println("(add)date: '" + addDate(addMonth) + "' (add)last-date: '" + addDate(addLastDate2, addMonth) + "'");
//            } else {
//                System.out.println("(add)date: '" + addDate(addMonth) + "' (add)last-date: '" + addDate(addMonth) + "'");
//            }
//        } catch (ParseException e) { System.err.println("Incorrect Data Format"); }
//    }
//
//
//    static final String formatter = "d.MM.yyyy";
//    static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
//    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
//
//
//    // >> 1472936400000
//    private static Long getCurrDate1() {
//        return convertDate1(getCurrDate2());
//    }
//    // >> "30.06.2014"
//    private static String getCurrDate2() {
//        Date date = new Date();
//        String strDate = simpleDateFormat.format(date);
//        return strDate;
//    }
//
//    // "30.06.2014" >> 1472936400000
//    private static Long convertDate1(String strDate) {
//        LocalDate localDate = LocalDate.parse(strDate, dateTimeFormatter);
//        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        return date.getTime();
//    }
//    // 1472936400000 >> "30.06.2014"
//    private static String convertDate2(long lDate) {
//        Date date = new Date(lDate);
//        String strDate = simpleDateFormat.format(date);
//        return strDate;
//    }
//
////    private static String addDate() throws ParseException {
////        Calendar c = Calendar.getInstance();
////        c.setTime(simpleDateFormat.parse(getCurrDate2()));
////        return simpleDateFormat.format(c.getTime());
////    }
//
//    private static String addDate(int iMonth) throws ParseException {
//        Calendar c = Calendar.getInstance();
//        c.setTime(simpleDateFormat.parse(getCurrDate2()));
//        c.add(Calendar.MONTH, iMonth);
//        return simpleDateFormat.format(c.getTime());
//    }
//    private static String addDate(String lastDate, int iMonth) throws ParseException {
//        Calendar c = Calendar.getInstance();
//        c.setTime(simpleDateFormat.parse(lastDate));
//        c.add(Calendar.MONTH, iMonth);
//        return simpleDateFormat.format(c.getTime());
//    }
//    private static String addDate(long lastDate, int iMonth) throws ParseException {
//        Calendar c = Calendar.getInstance();
//        c.setTime(simpleDateFormat.parse(convertDate2(lastDate)));
//        c.add(Calendar.MONTH, iMonth);
//        return simpleDateFormat.format(c.getTime());
//    }
//
//    private static boolean isExpired(long currDate, long lastDate) {
//        return (currDate < lastDate) ? true : false;
//    }
//    private static boolean isExpired(String currDate, String lastDate) {
//        return (convertDate1(currDate) < convertDate1(lastDate)) ? true : false;
//    }
//
//}
