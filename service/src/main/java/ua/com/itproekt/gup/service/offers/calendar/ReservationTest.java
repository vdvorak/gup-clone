//package ua.com.itproekt.gup.service.offers.calendar;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//public class ReservationTest {
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
//    @Test
//    public void testToString(){
//        ObjCostScheme objCostDefault = new ObjCostScheme();
//        System.out.println(objCostDefault);
//    }
//
//    @Test
//    public void testIsDay(){
//        ObjCostScheme objCostDefault = new ObjCostScheme();
//        System.out.println( "6 : " + reservation.isDay(6, objCostDefault) ); //System.out.println( "8 : " + isDay(8, objCosts) );
//    }
//
//    @Test
//    public void testFoundValues(){
//        ObjCostScheme objCostDefault = new ObjCostScheme();
//        int[] days = {1,3,7};
//        System.out.println( reservation.foundValues(days, objCostDefault) );
//    }
//
//}
