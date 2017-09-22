package ua.com.gup.service.event;


import org.springframework.context.ApplicationEvent;
import ua.com.gup.model.profiles.Profile;

public class OnInitialRegistrationByEmailEvent extends ApplicationEvent {

    public OnInitialRegistrationByEmailEvent(Profile profile) {
        super(profile);
    }

}
