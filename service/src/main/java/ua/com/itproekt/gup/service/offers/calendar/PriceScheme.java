package ua.com.itproekt.gup.service.offers.calendar;

import org.springframework.beans.factory.annotation.Value;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PriceScheme extends ConcurrentLinkedQueue<Price> {

    static String formatter = "d.MM.yyyy";
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    @Value("${price.weekdayPrice}")
    private Long weekdayPrice = 10000l;

    @Value("${price.weekendPrice}")
    private Long weekendPrice = 12000l;

    private Long[][] weekdays = {{1401570000000l,1401915600000l},{1402174800000l,1402520400000l},{1402779600000l,1403125200000l},{1403384400000l,1403730000000l},{1403989200000l,1404075600000l}}; //private Integer[][] weekdays = {{1,5},{8,12},{15,19},{22,26},{29,31}};
    private Long[][] weekends = {{1402002000000l,1402088400000l},{1402606800000l,1402693200000l},{1403211600000l,1403298000000l},{1403816400000l,1403902800000l}}; //private Integer[][] weekends = {{6,7},{13,14},{20,21},{27,28}};

//    public static void main(String[] args) throws ParseException {
////        Calendar ca1 = Calendar.getInstance();
////
////        ca1.set(2012,9,20);
////
////        int wk=ca1.get(Calendar.WEEK_OF_MONTH);
////        System.out.println("Week of Month :"+wk);
//
////        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        final Date date = dateFormat.parse("2014-09-10 13:45:20");
////        System.out.println(date);
////
////        final Calendar calendar = Calendar.getInstance();
////        calendar.setTime(date);
////
////        System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
////        System.out.println(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY);
//
////        Calendar cal = Calendar.getInstance();
////        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
////        System.out.println(dayofweek);
//
////        String date = "11-02-2000";
////        String[] input = date.split("-");
////        int day = Integer.valueOf(input[0]);
////        int month = Integer.valueOf(input[1]);
////        int year = Integer.valueOf(input[2]);
////        Calendar cal=Calendar.getInstance();
////        cal.set(Calendar.YEAR,year);
////        cal.set(Calendar.MONTH,month);
////        cal.set(Calendar.DATE, day);
////        //since month number starts from 0 (i.e jan 0, feb 1),
////        //we are subtracting original month by 1
////        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
////        System.out.println(days);
//
////        GregorianCalendar cal = new GregorianCalendar();
////        int currday = cal.get(Calendar.DAY_OF_MONTH);
////        int dow = cal.get(Calendar.DAY_OF_WEEK);
////        if (currday < 3){ //only check first 3 days of month
////            boolean bMonthend;
////            if (dow >= 2 && dow <= 6 ){ // = weekday (sat = 7, sun = 1)
////                bMonthend = true;
////            }else{
////                bMonthend = false;
////            }
////        }
//
////        Calendar calendar = Calendar.getInstance();
//////        calendar.set(Calendar.YEAR, 2016);
//////        calendar.set(Calendar.DAY_OF_YEAR, 180);
////
////        System.out.println(calendar.getTime().toString());
////
////        int weekday = calendar.get(Calendar.DAY_OF_WEEK);
////        System.out.println("Weekday: " + weekday);
//    }

    public PriceScheme(){
        init();
    }

    public PriceScheme(Long[][] weekdays, Long[][] weekends){
        this.weekdays = weekdays;
        this.weekends = weekends;
        init();
    }

    public PriceScheme(Long[][] weekdays, Long[][] weekends, Long weekdayPrice, Long weekendPrice){
        this.weekdays = weekdays;
        this.weekends = weekends;
        this.weekdayPrice = weekdayPrice;
        this.weekendPrice = weekendPrice;
        init();
    }

    public void init(){
        addDays(weekdayPrice, weekdays);
        addDays(weekendPrice, weekends);
    }


    public void addDays(Long price, Long day) {
        Price newPrice = new Price(price);
        for (Price curPrice : this) {
            if (curPrice.remove(day)){
                newPrice.add(day);
            }
        }
        add(newPrice);
    }

    public void addDays(Long price, Long[] days) {
        switch (days.length) {
            case 1:
                addDays(price, days[0]);
                break;
            case 2:
                Price newPrice = new Price(price);
                for (Price curPrice : this) {
                    for (Long day=days[0]; day<=days[1]; day+=86400000l) {
                        if (curPrice.remove(day)){
                            newPrice.add(day);
                        }
                    }
                }
                add(newPrice);
                break;
            default:
                break;
        }
    }

    public void addDays(Long price, Long[][] weekdays) {
        Price newPrice = new Price(price);
        for (Price thisPrice : this) {
            for (Long[] days : weekdays) {
                for (Long day=days[0]; day<=days[1]; day+=86400000l) {
                    if (thisPrice.remove(day)){
                        newPrice.add(day);
                    }
                }
            }
        }
        add(newPrice);
        for (Long[] days : weekdays) {
            Price curPrice = new Price(price);
            for (Long day=days[0]; day<=days[1]; day+=86400000l) {
                if (!newPrice.contains(day)) {
                    curPrice.add(day);
                }
            }
            add(curPrice);
        }
    }

    public boolean isDay(Long day) {
        for (Price prices : this)
            if (prices.contains(day)) return true;
        return false;
    }

    public Long getPrice(Long[] days){
        Long price = 0l;
        for (Price prices : this) {
            for (Long day=days[0]; day<=days[1]; day+=86400000l) if (prices.contains(day)) price += prices.get();
        }
        return price;
    }

    public Long getPrice(Long day){
        Long price = 0l;
        for (Price prices : this) {
            if (prices.contains(day)) price += prices.get();
        }
        return price;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();

        for (Price prices : this) {
            for (Long price : prices) data.append(prices.get() + "(" + convertDate(price) + ") ");
            if (!prices.isEmpty()) data.append("\n");
        }

        return data.toString();
    }

    /**
     * (Long) >> <DAY.MONTH.YEAR>
     * 1472936400000 >> "30.06.2014"
     *
     * @param lDate
     * @return
     */
    private static String convertDate(long lDate) {
        Date date = new Date(lDate);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }
}
