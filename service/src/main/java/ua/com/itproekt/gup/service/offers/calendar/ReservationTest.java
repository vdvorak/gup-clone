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
        System.out.println( foundValues(days,objCosts) );
    }

    public static ObjCost days(int value, int[] days){
        ObjCost objCost = new ObjCost(value);
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
                    // TODO если элемент в списке существует - то нужно его удаить из списка, потому-что этот элемент перекачивал уже в друго список:
                    // TODO нужно когда автор объявления пытается в разрез стандартному тарифу добавить свои изменения по ноавм тарифам в специальные дни...
                    return true;
                }
            }
        }
        return false;
    }

    public static int foundValues(int[] days, List<ObjCost> objCosts){
        int values = 0;
        for (ObjCost objCost : objCosts){
            for (Integer d : objCost){
                for (int day : days){
                    if(day == d){
                        values += objCost.getValue(); //System.out.print(objCost.getValue() + "(" + d + ") ");
                    }
                }
            }
        }
        return values;
    }

}

class ObjCost extends ArrayList<Integer> {

    public ObjCost(){}
    public ObjCost(int value){
        this.value = value;
    }

    private int value = 100;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
