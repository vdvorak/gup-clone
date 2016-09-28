package ua.com.itproekt.gup.service.offers.calendar;

import org.springframework.beans.factory.annotation.Value;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Calendar;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PriceScheme extends ConcurrentLinkedQueue<Price> {

    private static String formatter = "d.MM.yyyy";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
    private Long ONE_DAY = 86400000l;

    @Value("${price.weekdayPrice}")
    private Long weekdayPrice = 10000l;

    @Value("${price.weekendPrice}")
    private Long weekendPrice = 12000l;

    private Long[][] weekdays = {{1401570000000l,1401915600000l},{1402174800000l,1402520400000l},{1402779600000l,1403125200000l},{1403384400000l,1403730000000l},{1403989200000l,1404075600000l}}; //private Integer[][] weekdays = {{1,5},{8,12},{15,19},{22,26},{29,31}};
    private Long[][] weekends = {{1402002000000l,1402088400000l},{1402606800000l,1402693200000l},{1403211600000l,1403298000000l},{1403816400000l,1403902800000l}}; //private Integer[][] weekends = {{6,7},{13,14},{20,21},{27,28}};

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
                for (Long day=days[0]; day<=days[1]; day+=ONE_DAY) {
                    if (thisPrice.remove(day)){
                        newPrice.add(day);
                    }
                }
            }
        }
        add(newPrice);
        for (Long[] days : weekdays) {
            Price curPrice = new Price(price);
            for (Long day=days[0]; day<=days[1]; day+=ONE_DAY) {
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
            for (Long day=days[0]; day<=days[1]; day+=ONE_DAY) if (prices.contains(day)) price += prices.get();
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
