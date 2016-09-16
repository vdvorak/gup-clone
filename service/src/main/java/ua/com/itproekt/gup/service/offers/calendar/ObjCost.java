package ua.com.itproekt.gup.service.offers.calendar;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ObjCost  extends ConcurrentLinkedQueue<Integer> {

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
