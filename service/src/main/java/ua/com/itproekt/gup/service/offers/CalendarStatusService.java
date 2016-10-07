package ua.com.itproekt.gup.service.offers;

import com.google.gson.Gson;
import ua.com.itproekt.gup.service.offers.calendar.Price;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Конструктор
 * ===========
 * #1. Две стоимости (будни/выходные); Две даты (начальная/конечная):
 *     --------------------------------------------------------------
 *     - За дефолтный период (три полных месяца) устанавливается стоимость с учетом будней и выходных;
 *       -- дефолтный период (три полных месяца) определяется с учетом указанных дат (начальной/конечной)
 *       -- через конструктор с параметрами
 *     - За указанный период (начальной - конечной даты) устанавливается специальная стоимость;
 *       -- можно через дефолтный конструктор (без параметров)
 *
 * #2. Две стоимости (будни/выходные); Без даты (пустой массив):
 *     ---------------------------------------------------------
 *     - За дефолтный период (три полных месяца) устанавливается стоимость с учетом будней и выходных;
 *       -- дефолтный период (три полных месяца) определяется с учетом текущей даты
 *       -- через конструктор с параметрами
 *
 * #3. Одна стоимость (на каждый день); Две даты (начальная/конечная):
 *     ---------------------------------------------------------------
 *     - Только за указанный период (начальной - конечной даты) устанавливается специальная стоимость;
 *       -- Можно устанавливать специальную стоимость только на один день (начальная/конечная даты совпадают)
 *       -- можно через дефолтный конструктор (без параметров)
 *
 * #4. Одна стоимость (на каждый день); Без даты (пустой массив):
 *     ----------------------------------------------------------
 *     - За дефолтный период (три полных месяца) устанавливается специальная стоимость;
 *       -- дефолтный период (три полных месяца) определяется с учетом текущей даты
 *       -- можно через дефолтный конструктор (без параметров)
 *
 * Иннициализация
 * ==============
 * #1. Установка стоимости через конструктор с параметрами;
 *     -- (не является объязательным)
 * #2. Иннициализация периода-дней, через метод addPrices(), согласно ранее указанной стоимости;
 *     -- можно использовать с дефолтным конструктором (без параметров)
 */

public abstract class CalendarStatusService extends ConcurrentLinkedQueue<Price> {

    private static volatile Boolean initDate;
    private static String formatter = "d.MM.yyyy";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
    private Long weekdayPrice,weekendPrice;
    private Long[][] weekdays,weekends;
    private ArrayList<Long> listWeekdays,listWeekends;
    private Gson gson;

    /**
     * TODO:
     */
    public CalendarStatusService(){
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
        gson = new Gson();
    }

    /**
     * TODO:
     */
    public CalendarStatusService(Long weekdayPrice, Long weekendPrice){
        this.weekdayPrice = weekdayPrice;
        this.weekendPrice = weekendPrice;
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
        gson = new Gson();
    }

    public void addPrices(Long price, Long[] days) {
        Price newPrice;
        switch (days.length) {
            case 0:
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                initDate(calendar.getTimeInMillis());
                break;
            case 1:
                initDate(days[0]);
                synchronized (CalendarStatusService.class){
                    newPrice = new Price(price);
                    for (Price curPrice : this)
                        if (curPrice.remove(days[0])) newPrice.add(days[0]);
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

    public String toJson() {
        return gson.toJson(this);
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

    private static String convertDate(Long day) {
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
