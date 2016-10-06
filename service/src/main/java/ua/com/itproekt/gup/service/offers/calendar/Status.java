package ua.com.itproekt.gup.service.offers.calendar;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Status extends ConcurrentLinkedQueue<Long> {

    public Status(){}
    public Status(Boolean status){
        this.status = status;
    }

    private Boolean status;

    public Boolean get() {
        return status;
    }

    public void set(Boolean status) {
        this.status = status;
    }

}
