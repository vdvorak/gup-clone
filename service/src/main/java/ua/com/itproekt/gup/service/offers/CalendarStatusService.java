package ua.com.itproekt.gup.service.offers;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.service.offers.calendar.Price;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public abstract class CalendarStatusService extends ConcurrentLinkedQueue<Price> {

    private static volatile Boolean initDate;
    private static String formatter = "d.MM.yyyy";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
    private Long weekdayPrice,weekendPrice;
    private Long[][] weekdays,weekends;
    private ArrayList<Long> listWeekdays,listWeekends;
//    private Gson gson;

    /**
     * #3. One cost (per day); Two dates (start/stop):
     *     -------------------------------------------
     *     - Only during this period (initial - final date) established a special tax;
     *       -- it can set a special price of only one day (start/end dates are the same)
     *       -- possible through the default constructor (without parameters)
     *
     * #4. One cost (per day); Undated (empty array):
     *     ------------------------------------------
     *     - During the default period (three full months) established a special tax;
     *       -- default period (three full months) is determined based on the current date
     *       -- possible through the default constructor (without parameters)
     */
    protected CalendarStatusService(){
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
//        gson = new Gson();
    }

    /**
     * #1. One cost (per day); Two dates (start/stop):
     *     -------------------------------------------
     *     - During the default period (three full months) is established taking into account the cost of weekdays and weekends;
     *       -- default period (three full months) is determined based on the specified dates (start/end)
     *       -- through constructor with parameters
     *     - During this period (start-end dates) established a special tax;
     *       -- possible through the default constructor (without parameters)
     *
     * #2. Two costs (weekdays/weekend); Undated (empty array):
     *     ----------------------------------------------------
     *     - During the default period (three full months) is established taking into account the cost of weekdays and weekends;
     *       -- default period (three full months) is determined based on the current date
     *       -- through constructor with parameters
     */
    protected CalendarStatusService(Long weekdayPrice, Long weekendPrice){
        if (weekdayPrice!=null
                && weekendPrice!=null){
            this.weekdayPrice = weekdayPrice;
            this.weekendPrice = weekendPrice;
        }
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
//        gson = new Gson();
    }

    /**
     * Restore from Json to Object-Calendar;
     * -------------------------------------
     */
    protected CalendarStatusService(String jsonRestore){
        //TODO:
    }

    /**
     * #1. Setting costs of used constructor with parameters;
     *     -- (optional)
     * #2. Initialization-days period, used a method addPrices(), according to the previously specified value;
     *     -- it can be used with the default constructor (without parameters)
     * FIXME: it is problem add several specials (if only NULL init month)
     */
    public void addPrices(Long price, Long[] days) {
        Price newPrice;
        switch (days.length) {
            case 0:
                if (weekdayPrice==null && weekendPrice==null) weekdayPrice = weekendPrice = price; //TODO:
                initDate(java.util.Calendar.getInstance().getTimeInMillis());
                break;
            case 1:
                initDate(days[0]);
                synchronized (CalendarStatusService.class){
                    newPrice = new Price(price);
                    for (Price curPrice : this)
                        if (curPrice.remove(days[0])) newPrice.add(days[0]);
                    if (weekdayPrice==null && weekendPrice==null) removeAll(this); //TODO:
                    add(newPrice);
                }
                break;
            case 2:
                initDate(days);
                synchronized (CalendarStatusService.class){
                    newPrice = new Price(price);
                    java.util.Calendar lastDate = new GregorianCalendar(Integer.valueOf(convertDate(days[1]).split("\\.")[2]), (Integer.valueOf(convertDate(days[1]).split("\\.")[1])-1), Integer.valueOf(convertDate(days[1]).split("\\.")[0]));
                    for (java.util.Calendar currDate = new GregorianCalendar(Integer.valueOf(convertDate(days[0]).split("\\.")[2]), (Integer.valueOf(convertDate(days[0]).split("\\.")[1])-1), Integer.valueOf(convertDate(days[0]).split("\\.")[0])); currDate.getTimeInMillis()<=lastDate.getTimeInMillis(); currDate.add(java.util.Calendar.DATE, 1)) {
                        for (Price currPrice : this)
                            if (currPrice.remove(currDate.getTimeInMillis())) newPrice.add(currDate.getTimeInMillis());
                    }
                    if (weekdayPrice==null && weekendPrice==null) removeAll(this); //TODO:
                    add(newPrice);
                }
                break;
            default:
                break;
        }
    }

    public Integer delPrices(Long[] days) {
        Integer del;
        synchronized (CalendarStatusService.class){
            del = 0;
            switch (days.length) {
                case 1:
                    for (Price curPrice : this)
                        if (curPrice.remove(days[0])) del++;
                    break;
                case 2:
                    java.util.Calendar lastDate = new GregorianCalendar(Integer.valueOf(convertDate(days[1]).split("\\.")[2]), (Integer.valueOf(convertDate(days[1]).split("\\.")[1])-1), Integer.valueOf(convertDate(days[1]).split("\\.")[0]));
                    for (java.util.Calendar currDate = new GregorianCalendar(Integer.valueOf(convertDate(days[0]).split("\\.")[2]), (Integer.valueOf(convertDate(days[0]).split("\\.")[1])-1), Integer.valueOf(convertDate(days[0]).split("\\.")[0])); currDate.getTimeInMillis()<=lastDate.getTimeInMillis(); currDate.add(java.util.Calendar.DATE, 1)){
                        for (Price currPrice : this)
                            if (currPrice.remove(currDate.getTimeInMillis())) del++;
                    }
                    break;
                default:
                    break;
            }
        }
        return del;
    }

    public Boolean isPrice(Long day) {
        for (Price prices : this)
            if (prices.contains(day)) return true;
        return false;
    }

    public Long getPrice(Long day){
        Long price = 0l;
        for (Price prices : this)
            if (prices.contains(day)) price += prices.get();
        return price;
    }

    public Long getPrice(Long[] days){
        Long price = 0l;
        for (Price prices : this) {
            for (Long day : days)
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

    public String toJson() throws NoSuchElementException {
//        return gson.toJson(this);
        int scheme = 0;
        StringBuilder data = new StringBuilder();
        data.append("{\n");
        if (weekdayPrice!=null && weekendPrice!=null){
            for (Price prices : this) {
                if (scheme==0){
                    data.append("  \"weekdays\": {\n");
                    data.append("    \"price\": " + prices.get() + "\n");
                    data.append("    ,\"days\": [\"" + convertDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + convertDate(lastPrice) + "\"]\n");
                    } else {
                        data.append("]\n");
                    }
                    data.append("  }\n");
                }
                if (scheme==1){
                    data.append("  ,\"weekends\": {\n");
                    data.append("    \"price\": " + prices.get() + "\n");
                    data.append("    ,\"days\": [\"" + convertDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + convertDate(lastPrice) + "\"]\n");
                    } else {
                        data.append("]\n");
                    }
                    data.append("  }\n");
                }
                if (scheme==2) data.append("  ,\"specials\": [\n");
                if (1<scheme){
                    if (scheme==2) data.append("    {\n");
                    if (2<scheme) data.append("    ,{\n");
                    data.append("      \"price\": " + prices.get() + "\n");
                    data.append("      ,\"days\": [\"" + convertDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + convertDate(lastPrice) + "\"]\n");
                    } else {
                        data.append("]\n");
                    }
                    data.append("    }\n");
                }
                if (scheme==(this.size()-1) && 1<scheme) data.append("  ]\n");
                ++scheme;
            }
        } else {
            if (!this.isEmpty()){ //TODO
                data.append("  \"specials\":\n  [\n");
                for (Price prices : this) {
                    if (0 < scheme) data.append("    ,{\n");
                    else data.append("    {\n");
                    data.append("      \"price\": " + prices.get() + "\n");
                    data.append("      ,\"days\": [\"" + convertDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + convertDate(lastPrice) + "\"]\n");
                    } else {
                        data.append("]\n");
                    }
                    data.append("    }\n");
                    ++scheme;
                }
                data.append("  ]\n");
            }
        }
        data.append("}");
        return data.toString();
    }

    private void init(){
        synchronized (CalendarStatusService.class){
            if (initDate==null){
                weekdays = new Long[1][listWeekdays.size()];
                weekends = new Long[1][listWeekends.size()];
                weekdays[0] = listWeekdays.toArray(new Long[listWeekdays.size()]);
                weekends[0] = listWeekends.toArray(new Long[listWeekends.size()]);

                Price weekdaysPrice = new Price(weekdayPrice);
                for (Long lWeekday:weekdays[0]) weekdaysPrice.add(lWeekday);
                add(weekdaysPrice);
                Price weekendsPrice = new Price(weekendPrice);
                for (Long lWeekend:weekends[0]) weekendsPrice.add(lWeekend);
                add(weekendsPrice);
            }
        }
    }

    private void initDate(Integer month, Integer year){
        java.util.Calendar cal = new GregorianCalendar(year, (month-1), 1);
        do {
            String strMonth = month<10 ? "0" + month : String.valueOf(month),
                    strDay = cal.get(java.util.Calendar.DAY_OF_MONTH)<10 ? "0"+cal.get(java.util.Calendar.DAY_OF_MONTH) : String.valueOf(cal.get(java.util.Calendar.DAY_OF_MONTH));
            int day = cal.get(java.util.Calendar.DAY_OF_WEEK);
            if (day != java.util.Calendar.SATURDAY && day != java.util.Calendar.SUNDAY)
                listWeekdays.add(convertDate(strDay + "." + strMonth + "." + year));
            else
                listWeekends.add(convertDate(strDay + "." + strMonth + "." + year));
            cal.add(java.util.Calendar.DAY_OF_YEAR, 1);
        } while (cal.get(java.util.Calendar.MONTH) == (month-1));
    }

    private void initDate(Long day){
        synchronized (CalendarStatusService.class) {
            if (initDate == null) {
                initDate(Integer.valueOf(convertDate(day).split("\\.")[1]), Integer.valueOf(convertDate(day).split("\\.")[2]));
                init();

                initDate = new Boolean(true);
            }
        }
    }

    private void initDate(Long[] days){
        synchronized (CalendarStatusService.class) {
            if (initDate == null) {
                Arrays.sort(days);
                java.util.Calendar lastCal = new GregorianCalendar(Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[2]), (Integer.valueOf(convertDate(days[days.length-1]).split("\\.")[1])-1), 1);
                for (java.util.Calendar currCal = new GregorianCalendar(Integer.valueOf(convertDate(days[0]).split("\\.")[2]), (Integer.valueOf(convertDate(days[0]).split("\\.")[1])-1), 1); currCal.getTimeInMillis()<=lastCal.getTimeInMillis(); currCal.add(java.util.Calendar.MONTH, 1))
                    initDate(Integer.valueOf(String.valueOf(simpleDateFormat.format(currCal.getTime())).split("\\.")[1]), Integer.valueOf(String.valueOf(simpleDateFormat.format(currCal.getTime())).split("\\.")[2]));
                init();

                initDate = new Boolean(true);
            }
        }
    }

    private Long convertDate(String day) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(day, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return date.getTime();
    }

    private String convertDate(Long day) {
        Date date = new Date(day);
        String strDate = simpleDateFormat.format(date);
        return strDate;
    }

    private Long[] convertDate(String[] days) {
        Long[] lDate = new Long[days.length];
        for (int date=0; date<days.length; ++date)
            lDate[date] = convertDate(days[date]);
        return lDate;
    }
}
