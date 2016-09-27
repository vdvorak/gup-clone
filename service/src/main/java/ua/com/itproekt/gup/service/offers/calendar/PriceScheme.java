package ua.com.itproekt.gup.service.offers.calendar;

import org.springframework.beans.factory.annotation.Value;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PriceScheme extends ConcurrentLinkedQueue<Price> {

    @Value("${price.weekdayPrice}")
    private Integer weekdayPrice = 100; //private Integer weekdayPrice;

    @Value("${price.weekendPrice}")
    private Integer weekendPrice = 120; //private Integer weekendPrice;

    private Integer[][] weekdays = {{1,5},{8,12},{15,19},{22,26},{29,31}}; // ...
    private Integer[][] weekends = {{6,7},{13,14},{20,21},{27,28}}; // ...

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

    public PriceScheme(Integer[][] weekdays, Integer[][] weekends){
        this.weekdays = weekdays;
        this.weekends = weekends;
        init();
    }

    public PriceScheme(Integer[][] weekdays, Integer[][] weekends, Integer weekdayPrice, Integer weekendPrice){
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


    public void addDays(Integer price, Integer day) {
        Price newPrice = new Price(price);
        for (Price curPrice : this) {
            if (curPrice.remove(day)){
                newPrice.add(day);
            }
        }
        add(newPrice);
    }

    public void addDays(Integer price, Integer[] days) {
        switch (days.length) {
            case 1:
                addDays(price, days[0]);
                break;
            case 2:
                Price newPrice = new Price(price);
                for (Price curPrice : this) {
                    for (Integer day=days[0]; day<=days[1]; ++day) {
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

    public void addDays(Integer price, Integer[][] weekdays) {
        Price newPrice = new Price(price);
        for (Price thisPrice : this) {
            for (Integer[] days : weekdays) {
                for (Integer day=days[0]; day<=days[1]; ++day) {
                    if (thisPrice.remove(day)){
                        newPrice.add(day);
                    }
                }
            }
        }
        add(newPrice);
        for (Integer[] days : weekdays) {
            Price curPrice = new Price(price);
            for (Integer day=days[0]; day<=days[1]; ++day) {
                if (!newPrice.contains(day)) {
                    curPrice.add(day);
                }
            }
            add(curPrice);
        }
    }

    public boolean isDay(Integer day) {
        for (Price prices : this)
            if (prices.contains(day)) return true;
        return false;
    }

    public Integer getPrice(Integer[] days){
        Integer price = 0;
        for (Price prices : this) {
            for (Integer day=days[0]; day<=days[1]; ++day) if (prices.contains(day)) price += prices.get();
        }
        return price;
    }

    public Integer getPrice(Integer day){
        Integer price = 0;
        for (Price prices : this) {
            if (prices.contains(day)) price += prices.get();
        }
        return price;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();

        for (Price prices : this) {
            for (Integer price : prices) data.append(prices.get() + "(" + price + ") ");
            if (!prices.isEmpty()) data.append("\n");
        }

        return data.toString();
    }

}
