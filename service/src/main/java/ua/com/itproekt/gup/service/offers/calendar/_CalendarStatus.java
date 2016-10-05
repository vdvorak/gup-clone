//package ua.com.itproekt.gup.service.offers.calendar;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//import java.util.Calendar;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
//public class CalendarStatus extends ConcurrentLinkedQueue<Price> {
//
//    private static String formatter = "d.MM.yyyy";
//    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
//    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
//    private Long ONE_DAY = 86400000l;
//
//    private static volatile Boolean initDate;
//    private Long weekdayPrice,weekendPrice;
//    private Long[][] weekdays,weekends;
//    private ArrayList<Long> listWeekdays,listWeekends;
//
//    /**
//     * Long weekdayPrice, Long weekendPrice, Integer currMonth
//     */
//    public CalendarStatus(Long weekdayPrice, Long weekendPrice){
//        this.weekdayPrice = weekdayPrice;
//        this.weekendPrice = weekendPrice;
//        initDate = null;
//        listWeekdays = new ArrayList<Long>();
//        listWeekends = new ArrayList<Long>();
//    }
//
//    public void init(){
//        synchronized (CalendarStatus.class){
//            if (initDate==null){
//                weekdays = new Long[1][listWeekdays.size()];
//                weekends = new Long[1][listWeekends.size()];
//                weekdays[0] = listWeekdays.toArray(new Long[listWeekdays.size()]);
//                weekends[0] = listWeekends.toArray(new Long[listWeekends.size()]);
//
//                Price weekdaysPrice = new Price(weekdayPrice);
//                for (Long lWeekday:weekdays[0]) weekdaysPrice.add(lWeekday);
//                add(weekdaysPrice);
//                Price weekendsPrice = new Price(weekendPrice);
//                for (Long lWeekend:weekends[0]) weekendsPrice.add(lWeekend);
//                add(weekendsPrice);
//
////                initDate = new Boolean(true);
//            }
//        }
//    }
//
//    private void initDate(int month, int year){
//        java.util.Calendar cal = new GregorianCalendar(year, (month-1), 1);
//        do {
//            String strMonth = month<10 ? "0" + month : String.valueOf(month),
//                    strDay = cal.get(java.util.Calendar.DAY_OF_MONTH)<10 ? "0"+cal.get(java.util.Calendar.DAY_OF_MONTH) : String.valueOf(cal.get(java.util.Calendar.DAY_OF_MONTH));
//            int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
//            if (day != java.util.Calendar.SATURDAY && day != java.util.Calendar.SUNDAY)
//                listWeekdays.add(convertDate(strDay + "." + strMonth + "." + year));
//            else
//                listWeekends.add(convertDate(strDay + "." + strMonth + "." + year));
//            cal.add(java.util.Calendar.DAY_OF_YEAR, 1);
//        } while (cal.get(java.util.Calendar.MONTH) == (month-1));
//    }
//
//    public void initDate(Long day){
//////        System.err.println( "(day)   month: "+Integer.valueOf(convertDate(day).split("\\.")[1])+";   year: " + Integer.valueOf(convertDate(day).split("\\.")[2]) + ";" );
////        initDate(Integer.valueOf(convertDate(day).split("\\.")[1]), Integer.valueOf(convertDate(day).split("\\.")[2]));
////        init();
//
//        synchronized (CalendarStatus.class) {
//            if (initDate == null) {
//                System.err.println( "(day)   month: "+Integer.valueOf(convertDate(day).split("\\.")[1])+";   year: " + Integer.valueOf(convertDate(day).split("\\.")[2]) + ";" );
//                initDate(Integer.valueOf(convertDate(day).split("\\.")[1]), Integer.valueOf(convertDate(day).split("\\.")[2]));
//                init();
//
//                initDate = new Boolean(true);
//            }
//        }
//    }
//
//    //    public void initDate(Long[] days){
//////        Arrays.sort(days);
//////        for (Integer year=Integer.valueOf(convertDate(days[0]).split("\\.")[2]); year<=Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[2]); ++year){
//////            for (Integer month=Integer.valueOf(convertDate(days[0]).split("\\.")[1]); month<=Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[1]); ++month){
////////                System.err.println( "(days)   month: "+month+";   year: " + year + ";" );
//////                initDate(month, year);
//////            }
//////        }
//////        init();
////
////        synchronized (CalendarStatus.class) {
////            if (initDate == null) {
////                Arrays.sort(days);
////
////                System.err.println( "year-first: " + Integer.valueOf(convertDate(days[0]).split("\\.")[2]) + ";   year-last: " + Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[2]) + ";" );
////                System.err.println( "month-first: " + Integer.valueOf(convertDate(days[0]).split("\\.")[1]) + ";   month-last: " + Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[1]) + ";" );
////
////                for (Integer year=Integer.valueOf(convertDate(days[0]).split("\\.")[2]); year<=Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[2]); ++year){
////                    for (Integer month=Integer.valueOf(convertDate(days[0]).split("\\.")[1]); month<=Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[1]); ++month){
////                        System.err.println( "(days)   month: "+month+";   year: " + year + ";" );
////                        initDate(month, year);
////                    }
////                }
////                init();
////
////                initDate = new Boolean(true);
////            }
////        }
////    }
//    // @see http://stackoverflow.com/questions/4905416/how-do-i-add-one-month-to-current-date-in-java
//    public void initDate(Long[] days){
//        synchronized (CalendarStatus.class) {
//            if (initDate == null) {
//                Arrays.sort(days);
//
//                java.util.Calendar firstCal = new GregorianCalendar(Integer.valueOf(convertDate(days[0]).split("\\.")[2]), (Integer.valueOf(convertDate(days[0]).split("\\.")[1])-1), 1);
//                java.util.Calendar lastCal = new GregorianCalendar(Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[2]), (Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[1])-1), 1);
//
//                System.err.println( Integer.valueOf(convertDate(days[0]).split("\\.")[1]) + "." + Integer.valueOf(convertDate(days[0]).split("\\.")[2])
//                        + " - "
//                        + Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[1]) + "." + Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[2]) );
////                System.err.println( days[0] + " (" + convertDate(firstCal.getTime().getTime()) + ") - "
////                        + days[days.length-1] + " (" + convertDate(lastCal.getTime().getTime()) + ")" );
////                ////////////////////////////////////////////////////////////////////////////////////////////////
////                java.util.Calendar cal = java.util.Calendar.getInstance();
////                String strDt1 = "1.09.2014";
////                try {
////                    cal.setTime(simpleDateFormat.parse(strDt1));
////                } catch (ParseException e) { e.printStackTrace(); }
////                Long dt1 = cal.getTime().getTime();
////                ////////////////
////                cal.add(java.util.Calendar.MONTH, 1);
////                ////////////////
////                String strDt2 = simpleDateFormat.format(cal.getTime());
////                Long dt2 = cal.getTime().getTime();
////
////                System.out.println(strDt1 + " (" + dt1 + ")  |  " + strDt2 + " (" + dt2 + ")");
////                ////////////////////////////////////////////////////////////////////////////////////////////////
//                java.util.Calendar currCal = java.util.Calendar.getInstance();
//                for (currCal = firstCal; currCal.getTimeInMillis()<=lastCal.getTimeInMillis(); firstCal.add(java.util.Calendar.MONTH, 1)){
//                    Integer month = Integer.valueOf(String.valueOf(simpleDateFormat.format(currCal.getTime())).split("\\.")[1]);
//                    Integer year = Integer.valueOf(String.valueOf(simpleDateFormat.format(currCal.getTime())).split("\\.")[2]);
//                    System.out.println("(" + simpleDateFormat.format(currCal.getTime()) + ") month=" + month + "; year=" + year + ";");
//                    initDate(month, year);
//                }
//                ////////////////////////////////////////////////////////////////////////////////////////////////
////                for (Integer year=Integer.valueOf(convertDate(days[0]).split("\\.")[2]); year<=Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[2]); ++year){
////                    for (Integer month=Integer.valueOf(convertDate(days[0]).split("\\.")[1]); month<=Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[1]); ++month){
////                        System.err.println( "(days)   month: "+month+";   year: " + year + ";" );
////                        initDate(month, year);
////                    }
////                }
//                init();
//
//                initDate = new Boolean(true);
//            }
//        }
//    }
//
////    public static void main(String[] args) throws ParseException {
////        java.util.Calendar cal = java.util.Calendar.getInstance();
////        String strDt1 = "1.09.2014";
////        cal.setTime(simpleDateFormat.parse(strDt1));
////        Long dt1 = cal.getTime().getTime();
////
////        cal.add(java.util.Calendar.MONTH, 1);
////        String strDt2 = simpleDateFormat.format(cal.getTime());
////        Long dt2 = cal.getTime().getTime();
////
////        System.out.println(strDt1 + " (" + dt1 + ")  |  " + strDt2 + " (" + dt2 + ")");
////    }
//
//    public void addDays(Long price, Long[] days) {
//        Price newPrice = new Price(price);
//
//        switch (days.length) {
//            case 1:
//                initDate(days[0]);
//                for (Price curPrice : this) {
//                    if (curPrice.remove(days[0])){
//                        newPrice.add(days[0]);
//                    }
//                }
//                add(newPrice);
//                break;
//            case 2:
//                initDate(days);
//                for (Price curPrice : this) {
//                    for (Long day=days[0]; day<=days[1]; day+=ONE_DAY) {
//                        if (curPrice.remove(day)){
//                            newPrice.add(day);
//                        }
//                    }
//                }
//                add(newPrice);
//                break;
//            default:
//                break;
//        }
//    }
//
//    public boolean isDay(Long day) {
//        for (Price prices : this)
//            if (prices.contains(day)) return true;
//        return false;
//    }
//
//    public Long getPrice(Long[] days){
//        Long price = 0l;
//        for (Price prices : this) {
//            for (Long day : days) {
//                if (prices.contains(day)) price += prices.get();
//            }
//        }
//        return price;
//    }
//
//    public Long getPrice(Long day){
//        Long price = 0l;
//        for (Price prices : this) {
//            if (prices.contains(day)) price += prices.get();
//        }
//        return price;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder data = new StringBuilder();
//        for (Price prices : this) {
//            for (Long price : prices) data.append(prices.get() + "(" + convertDate(price) + ") ");
//            if (!prices.isEmpty()) data.append("\n");
//        }
//
//        return data.toString();
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
//}
