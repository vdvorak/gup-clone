package ua.com.itproekt.gup.service.offers.calendar;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * @see http://www.javacreed.com/gson-deserialiser-example/
 * @see http://stackoverflow.com/questions/20523693/how-to-de-serialize-a-mapstring-object-with-gson
 */

//@RunWith(SpringJUnit4ClassRunner.class)
////@SpringApplicationConfiguration(classes = ReservationTest.class)
//@WebAppConfiguration
public class CalendarTest {

    private Logger logger = Logger.getLogger(CalendarTest.class);

    private final String PATH = "src/test/java/ua/com/itproekt/gup/service/offers/calendar"; // "service/src/test/java/ua/com/itproekt/gup/service/offers/calendar/reservations1.json" //TODO: file.properties
    private final String RESERVATION_FILE_NAME = "offerCalendar1.json"; //TODO: file.properties
    private final String RENT_FILE_NAME = "offerRents.json"; //TODO: file.properties

    private JsonObject jsonCalendars,jsonRents;
    private Map<String, Calendar> calendar1;
    private Map<String, Rent> rents;
    PriceScheme schemeDefault,scheme1,scheme2;

    @Before
    public void setUp() {
        JsonParser parser = new JsonParser();
        Gson         gson = new Gson();
        try {
            jsonCalendars = (JsonObject) parser.parse(new FileReader(PATH + "/" + RESERVATION_FILE_NAME));
            jsonRents = (JsonObject) parser.parse(new FileReader(PATH + "/" + RENT_FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        calendar1 = gson.fromJson(jsonCalendars, new TypeToken<Map<String, Calendar>>(){}.getType());
        rents = gson.fromJson(jsonRents, new TypeToken<Map<String, Rent>>(){}.getType());

        schemeDefault = new PriceScheme();
        scheme1 = new PriceScheme();
        scheme2 = new PriceScheme();
    }

    @After
    public void tearDown() {
        jsonCalendars = null;
        jsonRents = null;
        calendar1 = null;
        rents = null;
        schemeDefault = null;
        scheme1 = null;
        scheme2 = null;
    }


    /**
     * Test(s) Scheme-Default
     */
    @Test
    public void testCalendarToStringDefault(){
        System.out.println("--------------------[ testCalendarToStringDefault ]");
        System.out.println(schemeDefault);
    }

    @Test
    public void testCalendarIsDayDefault(){
        System.out.println("--------------------[ testCalendarIsDayDefault ]");
        System.out.println( "Is Calendar-Day(6): " + schemeDefault.isDay(6) );
    }

    @Test
    public void testGetPricesDefault(){
        System.out.println("--------------------[ testGetPricesDefault ]");
        System.out.println( "Days(1,3,7) Get-Prices = " + schemeDefault.getPrice(rents.get("rent2").getDays()) );
    }


    /**
     * Test(s) Scheme-1
     */
    @Test
    public void testScheme1(){
        System.out.println("--------------------[ testScheme1 ]");
        scheme1.addDays(calendar1.get("scheme1").getPrice(), calendar1.get("scheme1").getDays());
        System.out.println(scheme1);
    }

    @Test
    public void testGetPriceScheme1(){
        System.out.println("--------------------[ testGetPriceScheme1 ]");
        scheme1.addDays(calendar1.get("scheme2").getPrice(), calendar1.get("scheme2").getDays());
        scheme1.addDays(calendar1.get("scheme3").getPrice(), calendar1.get("scheme3").getDays());
        System.out.println( "Day(6) Get-Price = " + scheme1.getPrice(6) );
    }

    @Test
    public void testGetPeriodPricesScheme1(){
        System.out.println("--------------------[ testGetPeriodPricesScheme1 ]");
        scheme1.addDays(calendar1.get("scheme2").getPrice(), calendar1.get("scheme2").getDays());
        scheme1.addDays(calendar1.get("scheme3").getPrice(), calendar1.get("scheme3").getDays());
        System.out.println( "Period(18-25) Get-Prices = " + scheme1.getPrice(rents.get("rent1").getDays()) );
    }


    /**
     * Test(s) Scheme-2
     */
    @Test
    public void testScheme2(){
        System.out.println("--------------------[ testScheme2 ]");
        scheme2.addDays(calendar1.get("scheme2").getPrice(), calendar1.get("scheme2").getDays());
        scheme2.addDays(calendar1.get("scheme3").getPrice(), calendar1.get("scheme3").getDays());
        System.out.println(scheme2);
    }

}
