//package ua.com.itproekt.gup.service.offers.calendar;
//
//import com.google.gson.*;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//
//public class ReservationTest {
//
//    private JsonArray jsonReservations;
//    private Reservations reservations;
//    private final String PATH = "src/test/java/ua/com/itproekt/gup/service/offers/calendar";
//    private final String FILE_NAME = "reservations.json";
//
//
//    @Before
//    public void setUp() {
//        JsonParser parser = new JsonParser();
//        Gson         gson = new Gson();
//
//        try {
////            jsonReservation = (JsonObject) parser.parse(new FileReader("service/src/test/java/ua/com/itproekt/gup/service/offers/calendar/reservations.json"));
//            jsonReservations = (JsonArray) parser.parse(new FileReader(PATH + "/" + FILE_NAME));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
////        reservation = gson.fromJson( jsonReservation, Reservation.class );
//        reservations = gson.fromJson( jsonReservations, Reservations.class );
//    }
//
//    @After
//    public void tearDown() {
//        jsonReservations = null;
//    }
//
////    @Test
////    public void testToString(){
////        PriceScheme priceDefault = new PriceScheme();
////        System.out.println(priceDefault);
////    }
//
//    @Test
//    public void testPriceScheme(){
////        PriceScheme scheme1 = new PriceScheme();
//////        scheme1.addDays(reservation.getPrice(), reservation.getDays());
////        scheme1.addDays(reservations.getReservations().get("scheme1").getPrice(), reservations.getReservations().get("scheme1").getDays());
////        System.out.println(scheme1);
//
//        System.out.println( reservations.getReservations() );
//    }
//
////    @Test
////    public void testPriceScheme(){
////        PriceScheme scheme1 = new PriceScheme();
////        scheme1.addDays(150, 31);
////        Integer[] days = {22,30};
////        scheme1.addDays(150, days);
////        System.out.println(scheme1);
////    }
//
////    @Test
////    public void testGetPrice(){
////        PriceScheme scheme1 = new PriceScheme();
////        scheme1.addDays(150, 31);
////        Integer[] days = {22,30};
////        scheme1.addDays(150, days);
////        System.out.println( "price = " + scheme1.getPrice(6) );
////    }
////
////    @Test
////    public void testGetPrices(){
////        PriceScheme scheme1 = new PriceScheme();
////        scheme1.addDays(150, 31);
////        Integer[] days = {22,30};
////        scheme1.addDays(150, days);
////        Integer[] reservDays = {18,25};
////        System.out.println( "price(s) = " + scheme1.getPrice(reservDays) );
////    }
////
////    @Test
////    public void testIsDay(){
////        PriceScheme priceDefault = new PriceScheme();
////        System.out.println( "is price(6): " + priceDefault.isDay(6) );
////    }
//
////    @Test
////    public void testFoundPrices(){
////        PriceScheme priceDefault = new PriceScheme();
////        int[] days = {1,3,7};
////        System.out.println( reservation.foundValues(days, priceDefault) );
////    }
//
//}
