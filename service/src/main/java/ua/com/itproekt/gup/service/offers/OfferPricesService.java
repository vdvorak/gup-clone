package ua.com.itproekt.gup.service.offers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import ua.com.itproekt.gup.service.offers.price.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class OfferPricesService extends ConcurrentLinkedQueue<Price> {

    private static volatile Boolean initDate;
    private static String formatter = "d.MM.yyyy",
            monthOfPrices = "monthOfPrices",
            monthOfRents = "rents";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
    private Long weekdayPrice,weekendPrice;
    private Long[][] weekdays,weekends;
    private ArrayList<Long> listWeekdays,listWeekends;
//    private Gson gson;
    private Deque<Rent> rents;

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
    protected OfferPricesService(){
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
//        gson = new Gson();
        rents = new ArrayDeque<Rent>();
        rents.add(new Rent());
        rents.add(new Rent());
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
    protected OfferPricesService(Long weekdayPrice, Long weekendPrice){
        if (weekdayPrice!=null
                && weekendPrice!=null){
            this.weekdayPrice = weekdayPrice;
            this.weekendPrice = weekendPrice;
        }
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
//        gson = new Gson();
        rents = new ArrayDeque<Rent>();
        rents.add(new Rent());
        rents.add(new Rent());
    }

    /**
     * Restore from Json to Object-Month:
     * ----------------------------------
     * - It restores the state of all prices (previously created) for the entire period;
     */
    protected OfferPricesService(String jsonRestore){
        JsonParser parser = new JsonParser();
        JsonObject objJsonObject = (JsonObject) parser.parse(jsonRestore);
        Gson gson = new Gson();
        Map<String, PriceOfRentsRestore> restores = gson.fromJson(objJsonObject, new TypeToken<Map<String, PriceOfRentsRestore>>(){}.getType());
        PriceOfRentsRestore restore = restores.get(monthOfPrices);
        PriceOfRent weekdays = restore.getWeekday(),
                weekends = restore.getWeekend();
        PriceOfRent[] specialdays = restore.getSpecialdays();

        weekdayPrice = weekdays.getPrice();
        weekendPrice = weekends.getPrice();
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
        for (PriceOfRent specialday : specialdays) addPrices(specialday.getPrice(), convertDate(specialday.getDays()));
//
        rents = new ArrayDeque<Rent>();
        rents.add(new Rent());
        rents.add(new Rent());
    }

    /**
     * Restore from (MonthOfPricesRestore) to Object-Month:
     * ----------------------------------------------------
     * - It restores the state of all prices (previously created) for the entire period;
     */
    protected OfferPricesService(PriceOfRentsRestore restore){
        PriceOfRent weekdays = restore.getWeekday(),
                weekends = restore.getWeekend();
        PriceOfRent[] specialdays = restore.getSpecialdays();

        if (weekdays.getPrice()!=null
                && weekends.getPrice()!=null){
            weekdayPrice = weekdays.getPrice();
            weekendPrice = weekends.getPrice();
        }
        initDate = null;
        listWeekdays = new ArrayList<Long>();
        listWeekends = new ArrayList<Long>();
        if (specialdays!=null)
            for (PriceOfRent specialday : specialdays) addPrices(specialday.getPrice(), convertDate(specialday.getDays()));
        else
            addPrices(0l, new Long[]{});
//
        rents = new ArrayDeque<Rent>();
        rents.add(new Rent());
        rents.add(new Rent());
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
                synchronized (OfferPricesService.class){
                    newPrice = new Price(price);
                    for (Price curPrice : this)
                        if (curPrice.remove(days[0])) newPrice.add(days[0]);
                    if (weekdayPrice==null && weekendPrice==null) removeAll(this); //TODO:
                    add(newPrice);
                }
                break;
            case 2:
                initDate(days);
                synchronized (OfferPricesService.class){
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

    public synchronized void addRent(Long[] days) {
        getAvailables().setRent(false);
        Collection<Long> availables = new TreeSet<Long>(),
                rented = new TreeSet<Long>();
        if (getAvailables().getDays()==null){
            for (Price prices : this)
                for (Long price : prices) availables.add(price);
            getRented().setDays(convertDate(days));
        } else {
            for (String day : getAvailables().getDays()) availables.add(convertDate(day));
            for (String day : getRented().getDays()) rented.add(convertDate(day));
            for (Long day : days) rented.add(day);
            getRented().setDays(convertDate(convertDate(rented)));
        }
        for (Long day : days) availables.remove(day); //TODO: copy to list
        getAvailables().setDays(convertDate(convertDate(availables)));
    }

    public Integer delPrices(Long[] days) {
        Integer del;
        synchronized (OfferPricesService.class){
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

    public Integer delRent(Long[] days) {
        return 0;
    }

    public Boolean isPrice(Long day) {
        for (Price prices : this)
            if (prices.contains(day)) return true;
        return false;
    }

    public Boolean isRent(Long day) {
//        for (Price prices : this)
//            if (prices.contains(day)) return true;
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
    public String toString() throws NoSuchElementException {
        StringBuilder data = new StringBuilder();
        List<Long> isRents = new LinkedList<Long>();
        if (getRented().getDays()!=null) for (String day : getRented().getDays()) isRents.add(convertDate(day));
        for (Price prices : this) {
            for (Long price : prices) data.append(prices.get() + (isRents.contains(price)?"|RENT":"") +"(" + convertDate(price) + ") ");
            if (!prices.isEmpty()) data.append("\n");
        }
        return data.toString();
    }

    public String toRent() {
        StringBuilder data = new StringBuilder();
        for (String monthOfRent : getRented().getDays()) data.append("TRUE(" + monthOfRent + ") ");
        data.append("\n");
        for (String monthOfRent : getAvailables().getDays()) data.append("FALSE(" + monthOfRent + ") ");
        return data.toString();
    }

//    public String jsonRent() {
//        StringBuilder data = new StringBuilder();
//        int rent = 0;
//        data.append("{\n  \"rents\": [");
//        for (String monthOfRent : rents[1].getDays()) {
//            if (0<rent) data.append(",");
//            data.append("\"" + monthOfRent + "\"");
//            ++rent;
//        }
//        data.append("]\n}");
//        return data.toString();
//    }
    public String jsonRent() {
        StringBuilder data = new StringBuilder();
        int available = 0,
                rent = 0;

        data.append("{\n  \"" + monthOfRents + "\": {\n");
        data.append("    \"availables\": [");
        for (String monthOfRent : getAvailables().getDays()) {
            if (0<available) data.append(",");
            data.append("\"" + monthOfRent + "\"");
            ++available;
        }
        data.append("]\n");
        data.append("    ,\"rented\": [");
        for (String monthOfRent : getRented().getDays()) {
            if (0<rent) data.append(",{");
            data.append("\n      {");
            data.append("\n        \"userId\": \"57e440464c8eda79f765532d\"");
            data.append("\n        ,\"day\": \"" + monthOfRent + "\"");
            data.append("\n      }");
            ++rent;
        }
        data.append("\n    ]\n  }\n}");
        return data.toString();
    }

    public synchronized Rent getAvailables(){
        try {
            return rents.getFirst();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    public synchronized Rent getRented(){
        try {
            return rents.getLast();
        } catch (NoSuchElementException e){
            return null;
        }
    }

    /**
     * {
     *     "offerRents": {
     *         "prices": {
     *             "weekday": {
     *                 "price": 10000,
     *                 "days": ["10.10.2016", "31.10.2016"]
     *             },
     *             "weekend": {
     *                 "price": 15000,
     *                 "days": ["1.10.2016", "30.10.2016"]
     *             },
     *             "specialdays": [{
     *                 "price": 20000,
     *                 "days": ["3.10.2016", "7.10.2016"]
     *             }]
     *         },
     *         "rents": {
     *             "availables": ["11.10.2016", "12.10.2016", "13.10.2016", "17.10.2016", "18.10.2016", "19.10.2016", "20.10.2016", "21.10.2016", "24.10.2016", "25.10.2016", "26.10.2016", "27.10.2016", "28.10.2016", "31.10.2016", "1.10.2016", "2.10.2016", "8.10.2016", "9.10.2016", "15.10.2016", "16.10.2016", "22.10.2016", "23.10.2016", "29.10.2016", "30.10.2016", "3.10.2016", "4.10.2016", "6.10.2016", "7.10.2016"],
     *             "rented": [{
     *                 "userId": "57e440464c8eda79f765532d",
     *                 "day": "5.10.2016"
     *             }, {
     *                 "userId": "57e440464c8eda79f765532d",
     *                 "day": "10.10.2016"
     *             }, {
     *                 "userId": "57e440464c8eda79f765532d",
     *                 "day": "14.10.2016"
     *             }]
     *         },
     *         "orders": [{
     *             "orderId": "57f37a5e603eda79f765532d",
     *             "offerId": "57f37a5e6032233325b9f8c9",
     *             "userId": "57e440464c8eda79f765532d",
     *             "pay": 46111,
     *             "days": ["5.10.2016", "10.10.2016", "14.10.2016"]
     *         }, {
     *             "orderId": "57e4ddae4c8ec16d79c41037",
     *             "offerId": "57e4ddae4c8e441cdbb32dae",
     *             "userId": "57e465ba4c8ec16d79c41037",
     *             "pay": 11111,
     *             "days": ["5.10.2016"]
     *         }]
     *     }
     * }
     */
    public String toJson() throws NoSuchElementException {
//        return gson.toJson(this);
        int scheme = 0;
        StringBuilder data = new StringBuilder();
        data.append("{\n  \"" + monthOfPrices + "\": {\n"); //data.append("{\n");
        if (weekdayPrice!=null && weekendPrice!=null){
            for (Price prices : this) {
                if (scheme==0){
                    data.append("    \"weekday\": {\n");
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
                if (scheme==1){
                    data.append("    ,\"weekend\": {\n");
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
                if (scheme==2) data.append("    ,\"specialdays\": [\n");
                if (1<scheme){
                    if (scheme==2) data.append("      {\n");
                    if (2<scheme) data.append("      ,{\n");
                    data.append("        \"price\": " + prices.get() + "\n");
                    data.append("        ,\"days\": [\"" + convertDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + convertDate(lastPrice) + "\"]\n");
                    } else {
                        data.append("]\n");
                    }
                    data.append("      }\n");
                }
                if (scheme==(this.size()-1) && 1<scheme) data.append("    ]\n");
                ++scheme;
            }
        } else {
            if (!this.isEmpty()){ //TODO
                data.append("    \"specialdays\":\n  [\n");
                for (Price prices : this) {
                    if (0 < scheme) data.append("      ,{\n");
                    else data.append("      {\n");
                    data.append("      \"price\": " + prices.get() + "\n");
                    data.append("        ,\"days\": [\"" + convertDate(prices.element()) + "\"");
                    if (1 < prices.size()) {
                        Long lastPrice = 0l;
                        for (Long price : prices) lastPrice = price;
                        data.append(",\"" + convertDate(lastPrice) + "\"  ]\n");
                    } else {
                        data.append("  ]\n");
                    }
                    data.append("      }\n");
                    ++scheme;
                }
                data.append("    ]\n");
            }
        }
        data.append("  }\n}"); //data.append("}");
        return data.toString();
    }

    public String jsonOfferMonth() throws NoSuchElementException { //FIXME:
        return "{\n  \"offerMonth\":" +
                toJson() +
                "," + jsonRent() +
                "}";
    }

    public PriceOfRentsRestore toRestore(){
        JsonParser parser = new JsonParser();
        JsonObject objJsonObject = (JsonObject) parser.parse(toJson());
        Gson gson = new Gson();
        Map<String, PriceOfRentsRestore> restores = gson.fromJson(objJsonObject, new TypeToken<Map<String, PriceOfRentsRestore>>(){}.getType());
        PriceOfRentsRestore restore = restores.get(monthOfPrices);
//        System.err.println(restore);
        return restore;
    }

    private void init(){
        synchronized (OfferPricesService.class){
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
        synchronized (OfferPricesService.class) {
            if (initDate == null) {
                initDate(Integer.valueOf(convertDate(day).split("\\.")[1]), Integer.valueOf(convertDate(day).split("\\.")[2]));
                init();

                initDate = new Boolean(true);
            }
        }
    }

    private void initDate(Long[] days){
        synchronized (OfferPricesService.class) {
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

    /////////////////////////

    private String[] convertDate(Long[] days) {
        String[] strDate = new String[days.length];
        for (int date=0; date<days.length; ++date)
            strDate[date] = convertDate(days[date]);
        return strDate;
    }

    private Long[] convertDate(Collection<Long> days) {
        Long[] lDate = new Long[days.size()];
        int date=0;
        for (Long day : days){
            lDate[date] = day;
            ++date;
        }
        return lDate;
    }

}
