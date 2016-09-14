package ua.com.itproekt.gup.service.offers.calendar;

import java.util.ArrayList;
import java.util.List;

public class ReservationTest {

    public static void main(String[] args) {

        int[] days1 = {1,5};
        int[] days2 = {6,7};
        int[] days3 = {6,6};

        List<ObjCost> objCosts = new ArrayList<>();
        objCosts.add(days(100, days1));
        objCosts.add(days(150, days2));
        objCosts.add(days(200, days3));

        printValues(objCosts);
        System.out.println();
        System.out.println( "6 : " + isDay(6, objCosts) ); //System.out.println( "8 : " + isDay(8, objCosts) );
        int[] days = {1,3,7};
        foundValues(days, objCosts);
    }

    public static ObjCost days(int value, int[] days){
        ObjCost objCost = new ObjCost();
        objCost.setValue(value);
        for (int d=days[0]; d<=days[1]; ++d) objCost.add(d);
        return objCost;
    }

    public static void printValues(List<ObjCost> objCosts){
        for (ObjCost objCost : objCosts){
            for (Integer day : objCost) System.out.print(objCost.getValue() + "(" + day + ") ");
            System.out.println();
        }
    }

//    public static void foundValue(int day, List<ObjCost> objCosts){
//        for (ObjCost objCost : objCosts){
////            try {
////                if(day < objCost.size()){
////                    System.out.print(objCost.getValue() + "(" + objCost.get(day) + ") ");
////                }
////            } catch (IndexOutOfBoundsException e){}
//            for (Integer d : objCost){
//                if(day == d){
//                    System.out.print(objCost.getValue() + "(" + d + ") ");
//                }
//            }
//        }
//    }

    public static boolean isDay(int day, List<ObjCost> objCosts){
        for (ObjCost objCost : objCosts){
            for (Integer d : objCost){
                if(day == d){
                    return true;
                }
            }
        }
        return false;
    }

    public static void foundValues(int[] days, List<ObjCost> objCosts){
        for (ObjCost objCost : objCosts){
            for (Integer d : objCost){
                for (int day : days){
                    if(day == d){
                        System.out.print(objCost.getValue() + "(" + d + ") ");
                    }
                }
            }
        }
    }

}

class ObjCost extends ArrayList<Integer> {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}