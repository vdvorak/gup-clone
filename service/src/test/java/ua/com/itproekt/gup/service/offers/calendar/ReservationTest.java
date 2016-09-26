package ua.com.itproekt.gup.service.offers.calendar;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import org.apache.log4j.Logger;

//@RunWith(SpringJUnit4ClassRunner.class)
////@SpringApplicationConfiguration(classes = ReservationTest.class)
//@WebAppConfiguration
public class ReservationTest {

    private JsonObject jsonReservations;
    private Map<String, Reservation> reservations;
    private final String PATH = "src/test/java/ua/com/itproekt/gup/service/offers/calendar"; // "service/src/test/java/ua/com/itproekt/gup/service/offers/calendar/reservations1.json"
    private final String FILE_NAME = "reservations.json";
    private Logger logger = Logger.getLogger(ReservationTest.class);

    @Before
    public void setUp() {
        JsonParser parser = new JsonParser();
        Gson         gson = new Gson();

        try {
            jsonReservations = (JsonObject) parser.parse(new FileReader(PATH + "/" + FILE_NAME));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        reservations = gson.fromJson(jsonReservations, new TypeToken<Map<String, Reservation>>(){}.getType());
    }

    @After
    public void tearDown() {
        jsonReservations = null;
        reservations = null;
    }

//    @Test
//    public void testToString(){
//        PriceScheme priceDefault = new PriceScheme();
//        System.out.println(priceDefault);
//    }

    /**
     * @see http://www.javacreed.com/gson-deserialiser-example/
     * @see http://stackoverflow.com/questions/20523693/how-to-de-serialize-a-mapstring-object-with-gson
     */
//    @Test
//    public void testPriceScheme(){
//        PriceScheme scheme1 = new PriceScheme();
//        scheme1.addDays(reservations.get("scheme1").getPrice(), reservations.get("scheme1").getDays());
//        System.out.println(scheme1);
//    }

    @Test
    public void testPriceScheme(){
        PriceScheme scheme1 = new PriceScheme();
        scheme1.addDays(reservations.get("scheme2").getPrice(), reservations.get("scheme2").getDays());
        scheme1.addDays(reservations.get("scheme3").getPrice(), reservations.get("scheme3").getDays());
        System.out.println(scheme1);
    }

//    @Test
//    public void testGetPrice(){
//        PriceScheme scheme1 = new PriceScheme();
//        scheme1.addDays(150, 31);
//        Integer[] days = {22,30};
//        scheme1.addDays(150, days);
//        System.out.println( "price = " + scheme1.getPrice(6) );
//    }
//
//    @Test
//    public void testGetPrices(){
//        PriceScheme scheme1 = new PriceScheme();
//        scheme1.addDays(150, 31);
//        Integer[] days = {22,30};
//        scheme1.addDays(150, days);
//        Integer[] reservDays = {18,25};
//        System.out.println( "price(s) = " + scheme1.getPrice(reservDays) );
//    }
//
//    @Test
//    public void testIsDay(){
//        PriceScheme priceDefault = new PriceScheme();
//        System.out.println( "is price(6): " + priceDefault.isDay(6) );
//    }

//    @Test
//    public void testFoundPrices(){
//        PriceScheme priceDefault = new PriceScheme();
//        int[] days = {1,3,7};
//        System.out.println( reservation.foundValues(days, priceDefault) );
//    }

}
