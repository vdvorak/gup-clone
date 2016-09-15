package ua.com.itproekt.gup.service.offers.calendar;

import java.util.Queue;

public class Reservation {

    public boolean isDay(int day, Queue<ObjCost> objCosts){
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

    public int foundValues(int[] days, Queue<ObjCost> objCosts){
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
