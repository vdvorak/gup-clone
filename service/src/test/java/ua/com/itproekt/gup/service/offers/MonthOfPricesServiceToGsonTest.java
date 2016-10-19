package ua.com.itproekt.gup.service.offers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.itproekt.gup.service.offers.price.PriceOfRent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @see http://www.javacreed.com/gson-deserialiser-example/
 * @see https://www.mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
 * @see http://stackoverflow.com/questions/4905416/how-do-i-add-one-month-to-current-date-in-java
 */

public class MonthOfPricesServiceToGsonTest {

    private static final String formatter = "d.MM.yyyy";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    private final String PATH = "src/test/resources",
            RENTCALENDAR_FILE_NAME = "offerOctoberOfPrices.json",
            RESTORECALENDAR_FILE_NAME = "restoreMonthOfPrices.json",
            RENT_FILE_NAME = "offerRents.json", //FIXME: file.properties
            jsonRestore = "{\n" +
                    "  \"monthOfPrices\": {\n" +
                    "    \"weekday\": {\n" +
                    "      \"price\": 10000\n" +
                    "      ,\"days\": [\"10.10.2016\",\"28.10.2016\"]\n" +
                    "    }\n" +
                    "    ,\"weekend\": {\n" +
                    "      \"price\": 15000\n" +
                    "      ,\"days\": [\"1.10.2016\",\"30.10.2016\"]\n" +
                    "    }\n" +
                    "    ,\"specialdays\": [\n" +
                    "      {\n" +
                    "        \"price\": 11111\n" +
                    "        ,\"days\": [\"31.10.2016\"]\n" +
                    "      }\n" +
                    "      ,{\n" +
                    "        \"price\": 20000\n" +
                    "        ,\"days\": [\"3.10.2016\",\"7.10.2016\"]\n" +
                    "      }\n" +
                    "    ]\n" +
                    "  }\n" +
                    "}";

    private JsonObject jsonCalendars,objJsonRestore,jsonRents;
    private Gson gsonStatusCalendarDefault,gsonStatusCalendar1;
    private Map<String, PriceOfRent> calendarPrices; //TODO: правилА будут хранится в базе (из низ потом будет строиться объект-календаря с ценой за все дни...)
    private Map<String, RentTest> rents; //TODO: общая таблица в базе данных для аренды...
    private OfferPricesService statusCalendar,statusCalendar2,restoreCalendar,restoreCalendar2;
    private Map<String, PriceOfRentsRestore> restore;
    private PriceOfRentsRestore priceCalendar;

    @Before
    public void setUp() {
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENTCALENDAR_FILE_NAME));
            objJsonRestore = (JsonObject) parser.parse(new FileReader(PATH + "/" + RESTORECALENDAR_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        calendarPrices = gson.fromJson(jsonCalendars, new TypeToken<Map<String, PriceOfRent>>(){}.getType());
        restore = gson.fromJson(objJsonRestore, new TypeToken<Map<String, PriceOfRentsRestore>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, RentTest>>(){}.getType());
        gsonStatusCalendarDefault = new Gson();
        gsonStatusCalendar1 = new Gson();

        statusCalendar = new OfferPricesServiceImpl(10000l,15000l); // Устанавливаем цену по умолчанию (на будни и выходные дни)
        statusCalendar2 = new OfferPricesServiceImpl(10000l,15000l);
//        statusCalendar2 = new CalendarStatusServiceImpl();
        priceCalendar = restore.get("priceCalendar");
//        restoreCalendar2 = new CalendarStatusServiceImpl(""); //FIXME: java.util.NoSuchElementException
    }

    @After
    public void tearDown() {
        jsonCalendars = null;
        jsonRents = null;
        gsonStatusCalendarDefault = null;
        gsonStatusCalendar1 = null;
        calendarPrices = null;
        rents = null;
        statusCalendar = null;
        statusCalendar2 = null;
        restoreCalendar = null;
        priceCalendar = null;
        restoreCalendar2 = null;
    }

    /**
     * Устанавливаем цену на специальные дни (специальных дней может быть неогрниченно много в пределах выбранного периода..)
     * (запрос устанавливает: единую стоимость; стоимость на будни и выходные дни; стоимость на специальные дни;)
     */
    @Test
    public void testOwnerToStringPrices(){
        System.out.println("--------------------[ testOwnerToStringPrices ]");
//        statusCalendar.addPrices(calendarPrices.get("scheme5").getPrice(), convertDate(calendarPrices.get("scheme5").getDays()));
//        statusCalendar.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));
        statusCalendar.addPrices(calendarPrices.get("scheme6").getPrice(), convertDate(calendarPrices.get("scheme6").getDays()));
//        statusCalendar.addPrices(calendarPrices.get("scheme7").getPrice(), convertDate(calendarPrices.get("scheme7").getDays()));

        System.out.println(statusCalendar);
//        System.err.println("Calendar-Status (year): " + gsonStatusCalendarDefault.toJson(statusCalendar)); // 'PriceScheme'
    }

    /**
     * Test(s) Scheme-Default
     * Java object to JSON, and assign to a String
     */
    @Test
    public void testOwnerToJSONPrices(){
        System.out.println("--------------------[ testOwnerToJSONPrices ]");
////        statusCalendar.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));
////        statusCalendar.addPrices(calendarPrices.get("scheme5").getPrice(), convertDate(calendarPrices.get("scheme5").getDays()));
//        statusCalendar.addPrices(calendarPrices.get("scheme7").getPrice(), convertDate(calendarPrices.get("scheme7").getDays()));
//
////        System.err.println("Calendar-Status: " + gsonStatusCalendarDefault.toJson(statusCalendar)); // 'PriceScheme'
//        System.err.println("Calendar-Status: " + statusCalendar.toJson()); // 'PriceScheme'
//        System.err.println("Calendar-Price: " + gsonStatusCalendar1.toJson(calendarPrices)); // 'Calendars'
//        System.out.println(statusCalendar);

//        statusCalendar2.addPrices(calendarPrices.get("scheme7").getPrice(), convertDate(calendarPrices.get("scheme7").getDays())); //FIXME !!!
        statusCalendar2.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));
        statusCalendar2.addPrices(calendarPrices.get("scheme8").getPrice(), convertDate(calendarPrices.get("scheme8").getDays())); //FIXME ???
//        statusCalendar2.addPrices(calendarPrices.get("scheme6").getPrice(), convertDate(calendarPrices.get("scheme6").getDays()));

        System.out.println(statusCalendar2);
        System.out.println(statusCalendar2.toJson());
    }

    /**
     * Test(s) Scheme-Default
     * Restore JSON to Java Object
     */
    @Test
    public void testOwnerRestoreToObjectPrices(){
        System.err.println("--------------------[ testOwnerRestoreToObjectPrices ]");

//        CalendarRestorePriceClass weekdays = priceCalendar.getWeekdays(),
//                weekends = priceCalendar.getWeekends();
//        CalendarRestorePriceClass[] specials = priceCalendar.getSpecials();
//
////        System.out.println("weekdays " + weekdays);
////        System.out.println("weekends " + weekends);
////
////        System.out.print("specials [");
////        for (CalendarRestorePriceClass special : specials) System.out.print(special);
////        System.out.println("]");
//
//        restoreCalendar = new CalendarStatusServiceImpl(weekdays.getPrice(), weekends.getPrice());
//        for (CalendarRestorePriceClass special : specials) restoreCalendar.addPrices(special.getPrice(), convertDate(special.getDays()));
//
//        System.out.println(restoreCalendar);
//        System.out.println(restoreCalendar.toJson());

        restoreCalendar2 = new OfferPricesServiceImpl(jsonRestore);
        System.err.println(restoreCalendar2);
        System.err.println(restoreCalendar2.toJson());
    }

    @Test
    public void testOwnerIsPrice(){
        System.out.println("--------------------[ testOwnerIsPrice ]");
        statusCalendar.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));
        statusCalendar.addPrices(calendarPrices.get("scheme5").getPrice(), convertDate(calendarPrices.get("scheme5").getDays()));

        System.out.println( "Is Calendar Day-Price(7): " + statusCalendar.isPrice(convertDate("07.10.2016")) );
    }

    @Test
    public void testOwnerGetPrices(){
        System.out.println("--------------------[ testOwnerGetPrices ]");
        statusCalendar.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));
        statusCalendar.addPrices(calendarPrices.get("scheme5").getPrice(), convertDate(calendarPrices.get("scheme5").getDays()));

        System.out.println( "Days(1,3,7) Get-Prices = " + statusCalendar.getPrice(convertDate(rents.get("rent3").getDays())) );
        System.out.println( "Days(31) Get-Prices = " + statusCalendar.getPrice(convertDate(rents.get("rent4").getDays())) );
        System.out.println( "Days(1,3,31) Get-Prices = " + statusCalendar.getPrice(convertDate(rents.get("rent5").getDays())) );
    }

    @Test
    public void testOwnerDelPrices(){
        System.out.println("--------------------[ testOwnerDelPrices ]");
        statusCalendar.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));
        System.out.println(statusCalendar);

//        System.out.println( "Days(5) Del-Price = " + statusCalendar.delPrices(convertDate(rents.get("delete41").getDays())) );
        System.out.println( "Days(10-14) Del-Price = " + statusCalendar.delPrices(convertDate(rents.get("delete42").getDays())) );
        System.err.println(statusCalendar);
    }

    @Test
    public void testOwnerAddRent(){
        System.out.println("--------------------[ testOwnerAddRent ]");
        statusCalendar.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));
        statusCalendar.addRent(convertDate(rents.get("delete41").getDays()));
        statusCalendar.addRent(convertDate(rents.get("delete42").getDays()));

        System.out.println(statusCalendar);
        System.out.println(statusCalendar.jsonRent());
//        System.out.println(statusCalendar.jsonAvailableRent());
        System.err.println(statusCalendar.toRent());
    }

    @Test
    public void testOwnerAddRent2(){
        System.out.println("--------------------[ testOwnerAddRent(2) ]");
        statusCalendar.addPrices(calendarPrices.get("scheme4").getPrice(), convertDate(calendarPrices.get("scheme4").getDays()));
        statusCalendar.addRent(convertDate(rents.get("delete41").getDays()));
        statusCalendar.addRent(convertDate(rents.get("delete42").getDays()));

        System.out.println(statusCalendar.toJson());
        System.out.println(statusCalendar.jsonRent());
//        System.err.println(statusCalendar.jsonOfferMonth());
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
     * @param[] strDate
     * @return[]
     */
    private Long[] convertDate(String[] strDate) {
        Long[] longDate = new Long[strDate.length];
        for (int date=0; date<strDate.length; ++date){
            longDate[date] = convertDate(strDate[date]);
        }
        return longDate;
    }

}
