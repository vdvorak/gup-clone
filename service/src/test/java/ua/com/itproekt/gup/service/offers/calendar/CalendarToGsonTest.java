package ua.com.itproekt.gup.service.offers.calendar;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @see http://www.javacreed.com/gson-deserialiser-example/
 * @see https://www.mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/
 * @see http://stackoverflow.com/questions/4905416/how-do-i-add-one-month-to-current-date-in-java
 */

public class CalendarToGsonTest {

    private Logger logger = Logger.getLogger(CalendarToGsonTest.class);

    static String formatter = "d.MM.yyyy";
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter, Locale.ENGLISH);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);

    private final String PATH = "src/test/resources"; //TODO: file.properties
    private final String RENTCALENDAR_FILE_NAME = "offerCalendar1.json"; //TODO: file.properties
    private final String RENT_FILE_NAME = "offerRents.json"; //TODO: file.properties

    private JsonObject jsonCalendars,jsonRents;
    private Gson gsonSchemeDefault,gsonCalendar1;
    private Map<String, Calendar> priceCalendar; //TODO: правилА будут хранится в базе (из низ потом будет строиться объект-календаря с ценой за все дни...)
    private Map<String, Rent> rents; //TODO: общая таблица в базе данных для аренды...
    private CalendarStatus statusCalendar;

    @Before
    public void setUp() {
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENTCALENDAR_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        priceCalendar = gson.fromJson(jsonCalendars, new TypeToken<Map<String, Calendar>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, Rent>>(){}.getType());
        gsonSchemeDefault = new Gson();
        gsonCalendar1 = new Gson();

        statusCalendar = new CalendarStatus(10000l,15000l); // Устанавливаем цену по умолчанию (на будни и выходные дни)
    }

    @After
    public void tearDown() {
        jsonCalendars = null;
        jsonRents = null;
        gsonSchemeDefault = null;
        gsonCalendar1 = null;
        priceCalendar = null;
        rents = null;
        statusCalendar = null;
    }

    /**
     * Устанавливаем цену на специальные дни (специальных дней может быть неогрниченно много в пределах выбранного периода..)
     * (запрос устанавливает: единую стоимость; стоимость на будни и выходные дни; стоимость на специальные дни;)
     */
    @Test
    public void testCalendarToStringDefault(){
        System.out.println("--------------------[ testCalendarToStringDefault ]");
//        statusCalendar.addDays(priceCalendar.get("scheme5").getPrice(), convertDate(priceCalendar.get("scheme5").getDays()));
//        statusCalendar.addDays(priceCalendar.get("scheme4").getPrice(), convertDate(priceCalendar.get("scheme4").getDays()));
        statusCalendar.addDays(priceCalendar.get("scheme6").getPrice(), convertDate(priceCalendar.get("scheme6").getDays()));

        System.out.println(statusCalendar);
    }

    /**
     * Test(s) Scheme-Default
     * Java object to JSON, and assign to a String
     */
    @Test
    public void testCalendarToJSON(){
        System.out.println("--------------------[ testCalendarToJSON ]");
        statusCalendar.addDays(priceCalendar.get("scheme4").getPrice(), convertDate(priceCalendar.get("scheme4").getDays()));
        statusCalendar.addDays(priceCalendar.get("scheme5").getPrice(), convertDate(priceCalendar.get("scheme5").getDays()));

        System.err.println("Calendar-Status: " + gsonSchemeDefault.toJson(statusCalendar)); // 'PriceScheme'
        System.err.println("Calendar-Price: " + gsonCalendar1.toJson(priceCalendar)); // 'Calendars'
        System.out.println(statusCalendar);
    }

    @Test
    public void testCalendarIsPriceDefault(){
        System.out.println("--------------------[ testCalendarIsDayDefault ]");
        statusCalendar.addDays(priceCalendar.get("scheme4").getPrice(), convertDate(priceCalendar.get("scheme4").getDays()));
        statusCalendar.addDays(priceCalendar.get("scheme5").getPrice(), convertDate(priceCalendar.get("scheme5").getDays()));

        System.out.println( "Is Calendar Day-Price(7): " + statusCalendar.isPrice(convertDate("07.10.2016")) );
    }

    @Test
    public void testGetPricesDefault(){
        System.out.println("--------------------[ testGetPricesDefault ]");
        statusCalendar.addDays(priceCalendar.get("scheme4").getPrice(), convertDate(priceCalendar.get("scheme4").getDays()));
        statusCalendar.addDays(priceCalendar.get("scheme5").getPrice(), convertDate(priceCalendar.get("scheme5").getDays()));

        System.out.println( "Days(1,3,7) Get-Prices = " + statusCalendar.getPrice(convertDate(rents.get("rent3").getDays())) );
        System.out.println( "Days(31) Get-Prices = " + statusCalendar.getPrice(convertDate(rents.get("rent4").getDays())) );
        System.out.println( "Days(1,3,31) Get-Prices = " + statusCalendar.getPrice(convertDate(rents.get("rent5").getDays())) );
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
