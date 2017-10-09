package ua.com.gup.event;

import org.springframework.context.ApplicationEvent;
import ua.com.gup.domain.profile.Profile;

public class OnForgetPasswordEvent extends ApplicationEvent {

    public OnForgetPasswordEvent(Profile profile) {
        super(profile);
    }
}
