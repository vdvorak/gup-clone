package ua.com.itproekt.gup.service.offers.calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReservationTest {

    private static Reservation reservation;

    @Before
    public void setUp() {
        reservation = new Reservation();
    }

    @After
    public void tearDown() {
        reservation = null;
    }

//    @Test
//    public void testToString(){
//        ObjCostScheme objCostDefault = new ObjCostScheme();
//        System.out.println(objCostDefault);
//    }

//    @Test
//    public void testCostScheme(){
//        ObjCostScheme objCostScheme1 = new ObjCostScheme();
//        Integer[] days = {22,31};
//        objCostScheme1.addDays(150, days);
//        System.out.println(objCostScheme1);
//    }

//    @Test
//    public void testCostScheme(){
//        ObjCostScheme objCostScheme1 = new ObjCostScheme();
//        objCostScheme1.addDays(150, 31);
//        Integer[] days = {22,30};
//        objCostScheme1.addDays(150, days);
//        System.out.println(objCostScheme1);
//    }

    @Test
    public void testGetValue(){
        ObjCostScheme objCostScheme1 = new ObjCostScheme();
        objCostScheme1.addDays(150, 31);
        Integer[] days = {22,30};
        objCostScheme1.addDays(150, days);
        System.out.println( "cost = " + objCostScheme1.getValues(6) );
    }

    @Test
    public void testGetValues(){
        ObjCostScheme objCostScheme1 = new ObjCostScheme();
        objCostScheme1.addDays(150, 31);
        Integer[] days = {22,30};
        objCostScheme1.addDays(150, days);
        Integer[] reservDays = {18,25};
        System.out.println( "cost(s) = " + objCostScheme1.getValues(reservDays) );
    }

    @Test
    public void testIsDay(){
        ObjCostScheme objCostDefault = new ObjCostScheme();
        System.out.println( "6 : " + objCostDefault.isDay(6) );
    }

//    @Test
//    public void testFoundValues(){
//        ObjCostScheme objCostDefault = new ObjCostScheme();
//        int[] days = {1,3,7};
//        System.out.println( reservation.foundValues(days, objCostDefault) );
//    }

}
