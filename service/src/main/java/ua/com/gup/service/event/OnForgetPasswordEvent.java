package ua.com.gup.service.event;

import org.springframework.context.ApplicationEvent;

public class OnForgetPasswordEvent extends ApplicationEvent {

    public OnForgetPasswordEvent(Object source) {
        super(source);
    }
}
