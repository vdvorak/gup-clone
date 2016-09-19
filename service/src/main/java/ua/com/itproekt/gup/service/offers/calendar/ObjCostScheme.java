package ua.com.itproekt.gup.service.offers.calendar;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ObjCostScheme extends ConcurrentLinkedQueue<ObjCost> {

    private int valWeekdays = 100; // TODO read from file.properies !!!
    private int valWeekends = 120; // TODO read from file.properies !!!
    private Integer[][] weekdays = {{1,5},{8,12},{15,19},{22,26},{29,31}}; // ...
    private Integer[][] weekends = {{6,7},{13,14},{20,21},{27,28}}; // ...

    public ObjCostScheme(){
        init();
    }

    public ObjCostScheme(Integer[][] weekdays, Integer[][] weekends){
        this.weekdays = weekdays;
        this.weekends = weekends;
        init();
    }

    public ObjCostScheme(Integer[][] weekdays, Integer[][] weekends, int valWeekdays, int valWeekends){
        this.weekdays = weekdays;
        this.weekends = weekends;
        this.valWeekdays = valWeekdays;
        this.valWeekends = valWeekends;
        init();
    }

    public void init(){
        addDays(valWeekdays, weekdays);
        addDays(valWeekends, weekends);
    }


    public void addDays(Integer val, Integer day) {
        ObjCost newCost = new ObjCost(val);
        for (ObjCost objCost : this) {
            if (objCost.remove(day)){
                newCost.add(day);
            }
        }
        add(newCost);
    }

    public void addDays(Integer val, Integer[] days) {
        ObjCost newCost = new ObjCost(val);
        for (ObjCost objCost : this) {
            for (Integer day=days[0]; day<=days[1]; ++day) {
                if (objCost.remove(day)){
                    newCost.add(day);
                }
            }
        }
        add(newCost);
    }

    public void addDays(Integer val, Integer[][] weekdays) {
        ObjCost newCost = new ObjCost(val);
        for (ObjCost thisCost : this) {
            for (Integer[] days : weekdays) {
                for (Integer day=days[0]; day<=days[1]; ++day) {
                    if (thisCost.remove(day)){
                        newCost.add(day);
                    }
                }
            }
        }
        add(newCost);
        for (Integer[] days : weekdays) {
            ObjCost objCost = new ObjCost(val);
            for (Integer day=days[0]; day<=days[1]; ++day) {
                if (!newCost.contains(day)) {
                    objCost.add(day);
                }
            }
            add(objCost);
        }
    }

    public boolean isDay(Integer day) {
        for (ObjCost objCost : this)
            if (objCost.contains(day)) return true;
        return false;
    }

    public Integer getValues(Integer[] days){
        Integer values = 0;
        for (ObjCost objCost : this) {
            for (Integer day=days[0]; day<=days[1]; ++day) if (objCost.contains(day)) values += objCost.getValue();
        }
        return values;
    }

    public Integer getValues(Integer day){
        Integer values = 0;
        for (ObjCost objCost : this) {
            if (objCost.contains(day)) values += objCost.getValue();
        }
        return values;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();

        for (ObjCost objCost : this) {
            for (Integer day : objCost) data.append(objCost.getValue() + "(" + day + ") ");
            if (!objCost.isEmpty()) data.append("\n");
        }

        return data.toString();
    }

}
