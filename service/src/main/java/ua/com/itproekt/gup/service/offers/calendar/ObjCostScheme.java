package ua.com.itproekt.gup.service.offers.calendar;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ObjCostScheme extends ConcurrentLinkedQueue<ObjCost> {

    private int valWeekdays = 100;
    private int valWeekends = 120;
    private int[][] weekdays = {{1,5},{8,12},{15,19},{22,26},{29,31}};
    private int[][] weekends = {{6,7},{13,14},{20,21},{27,28}};

    public ObjCostScheme(){
        init();
    }

    public ObjCostScheme(int[][] weekdays, int[][] weekends){
        this.weekdays = weekdays;
        this.weekends = weekends;
        init();
    }

    public ObjCostScheme(int[][] weekdays, int[][] weekends, int valWeekdays, int valWeekends){
        this.weekdays = weekdays;
        this.weekends = weekends;
        this.valWeekdays = valWeekdays;
        this.valWeekends = valWeekends;
        init();
    }

    public void init(){
        for (int[] days : weekdays) add(addDays(valWeekdays, days));
        for (int[] days : weekends) add(addDays(valWeekends, days));
    }

    private ObjCost addDays(int value, int[] days) {
        ObjCost objCost = new ObjCost(value);
        for (int d=days[0]; d<=days[1]; ++d) objCost.add(d);
        return objCost;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();

        for (ObjCost objCost : this){
            for (Integer day : objCost){
                data.append(objCost.getValue() + "(" + day + ") ");
            }
            data.append("\n");
        }

        return data.toString();
    }

}
