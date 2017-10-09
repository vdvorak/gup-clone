package ua.com.gup.event;


import org.springframework.context.ApplicationEvent;
import ua.com.gup.domain.profile.Profile;

public class OnInitialRegistrationByEmailEvent extends ApplicationEvent {

    public OnInitialRegistrationByEmailEvent(Profile profile) {
        super(profile);
    }

}
