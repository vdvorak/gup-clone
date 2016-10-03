package ua.com.itproekt.gup.service.offers.calendar;

import org.springframework.beans.factory.annotation.Value;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Calendar;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PriceScheme extends ConcurrentLinkedQueue<Price> {

    private static String formatter = "d.MM.yyyy";
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
    private Long ONE_DAY = 86400000l;

//    @Value("${price.weekdayPrice}")
    private Long weekdayPrice;

//    @Value("${price.weekendPrice}")
    private Long weekendPrice;

    private boolean isInit = false;
    private ArrayList<Long> listWeekdays = new ArrayList<Long>();
    private ArrayList<Long> listWeekends = new ArrayList<Long>();
    private Long[][] weekdays; //private Long[][] weekdays = {{1401570000000l,1401915600000l},{1402174800000l,1402520400000l},{1402779600000l,1403125200000l},{1403384400000l,1403730000000l},{1403989200000l,1404075600000l}}; //private Integer[][] weekdays = {{1,5},{8,12},{15,19},{22,26},{29,31}};
    private Long[][] weekends; //private Long[][] weekends = {{1402002000000l,1402088400000l},{1402606800000l,1402693200000l},{1403211600000l,1403298000000l},{1403816400000l,1403902800000l}}; //private Integer[][] weekends = {{6,7},{13,14},{20,21},{27,28}};

    /**
     * Long weekdayPrice, Long weekendPrice, Integer currMonth
     */
//    public PriceScheme(){}
    public PriceScheme(Long weekdayPrice, Long weekendPrice){
        this.weekdayPrice = weekdayPrice;
        this.weekendPrice = weekendPrice;
    }

//    public PriceScheme(Long[][] weekdays, Long[][] weekends){
//        this.weekdays = weekdays;
//        this.weekends = weekends;
//        init();
//    }
//
//    public PriceScheme(Long[][] weekdays, Long[][] weekends, Long weekdayPrice, Long weekendPrice){
//        this.weekdays = weekdays;
//        this.weekends = weekends;
//        this.weekdayPrice = weekdayPrice;
//        this.weekendPrice = weekendPrice;
//        init();
//    }

    public void init(){
        if(!isInit){
            addDays(weekdayPrice, weekdays);
            addDays(weekendPrice, weekends);
            isInit = true;
        }
    }

    private void initDate(int _month, int year){
        int month = _month-1;

        java.util.Calendar cal = new GregorianCalendar(year, month, 1);
        do {
            int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
            if (day != java.util.Calendar.SATURDAY && day != java.util.Calendar.SUNDAY) {
                String strMonth = month<10 ? "0"+month : ""+month;
                String strDay = cal.get(java.util.Calendar.DAY_OF_MONTH)<10 ? "0"+cal.get(java.util.Calendar.DAY_OF_MONTH) : ""+cal.get(java.util.Calendar.DAY_OF_MONTH);
                listWeekdays.add(convertDate(strDay + "." + strMonth + "." + year));
            }
            cal.add(java.util.Calendar.DAY_OF_YEAR, 1);
        }  while (cal.get(java.util.Calendar.MONTH) == month);

        cal = new GregorianCalendar(year, month, 1);
        do {
            int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
            if (day == java.util.Calendar.SATURDAY || day == java.util.Calendar.SUNDAY) {
                String strMonth = month<10 ? "0"+month : ""+month;
                String strDay = cal.get(java.util.Calendar.DAY_OF_MONTH)<10 ? "0"+cal.get(java.util.Calendar.DAY_OF_MONTH) : ""+cal.get(java.util.Calendar.DAY_OF_MONTH);
                listWeekends.add(convertDate(strDay + "." + strMonth + "." + year));
            }
            cal.add(java.util.Calendar.DAY_OF_YEAR, 1);
        }  while (cal.get(java.util.Calendar.MONTH) == month);
    }

    public void initDates(Long day){
        Integer year=Integer.valueOf(convertDate(day).split("\\.")[2]);
        Integer month=Integer.valueOf(convertDate(day).split("\\.")[1]);
        initDate(month, year);
    }

    public void initDates(Long[] days){
        Arrays.sort(days);
        for (Integer year=Integer.valueOf(convertDate(days[0]).split("\\.")[2]); year<=Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[2]); ++year){
            for (Integer month=Integer.valueOf(convertDate(days[0]).split("\\.")[1]); month<=Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[1]); ++month){
                initDate(month, year);
            }
        }
    }

    public void addDays(Long price, Long day) {
        initDates(day);
        weekdays = new Long[1][listWeekdays.size()];
        weekends = new Long[1][listWeekends.size()];
        weekdays[0] = listWeekdays.toArray(new Long[listWeekdays.size()]);
        weekends[0] = listWeekends.toArray(new Long[listWeekdays.size()]);
        init();

        Price newPrice = new Price(price);
        for (Price curPrice : this) {
            if (curPrice.remove(day)){
                newPrice.add(day);
            }
        }
        add(newPrice);
    }

    public void addDays(Long price, Long[] days) {
        initDates(days);
        weekdays = new Long[1][listWeekdays.size()];
        weekends = new Long[1][listWeekends.size()];
        weekdays[0] = listWeekdays.toArray(new Long[listWeekdays.size()]);
        weekends[0] = listWeekends.toArray(new Long[listWeekends.size()]);

//        for (Long lWeekday:weekdays[0]) System.out.print(convertDate(lWeekday) + " ");
//        System.out.println();
//        for (Long lWeekend:weekends[0]) System.out.print(convertDate(lWeekend) + " ");
//        System.out.println();

        init();

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
     * <DAY.MONTH.YEAR> >> (Long)
     * ("30.06.2014" >> 1472936400000)
     *
     * @param strDate
     * @return
     */
    private Long convertDate(String strDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
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
    private static String convertDate(long lDate) {
        Date date = new Date(lDate);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }

    /**
     * @param strDate
     * @return
     */
    private Long[] convertDate(String[] strDate) {
        Long[] longDate = new Long[strDate.length];
        for (int date=0; date<strDate.length; ++date){
            longDate[date] = convertDate(strDate[date]);
        }
        return longDate;
    }
}
