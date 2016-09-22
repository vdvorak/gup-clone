//package ua.com.itproekt.gup.service.offers.calendar;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class PriceSchemeTest {
//
//    private static Reservation reservation;
//
//    @Before
//    public void setUp() {
//        reservation = new Reservation();
//    }
//
//    @After
//    public void tearDown() {
//        reservation = null;
//    }
//
////    @Test
////    public void testToString(){
////        PriceScheme priceDefault = new PriceScheme();
////        System.out.println(priceDefault);
////    }
//
////    @Test
////    public void testPriceScheme(){
////        PriceScheme scheme1 = new PriceScheme();
////        Integer[] days = {22,31};
////        scheme1.addDays(150, days);
////        System.out.println(scheme1);
////    }
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
//
////    @Test
////    public void testFoundPrices(){
////        PriceScheme priceDefault = new PriceScheme();
////        int[] days = {1,3,7};
////        System.out.println( reservation.foundValues(days, priceDefault) );
////    }
//
//}
