package ua.com.gup.rent.event;

import org.springframework.context.ApplicationEvent;

public abstract class RentEvent<T> extends ApplicationEvent {

    public RentEvent(T source) {
        super(source);
    }
}
