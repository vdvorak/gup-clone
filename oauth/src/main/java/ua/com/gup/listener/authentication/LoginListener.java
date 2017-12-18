package ua.com.gup.listener.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import ua.com.gup.model.LoggedUser;
import ua.com.gup.service.UserService;

import java.util.UUID;


@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;

    /**
     * Put UID for chat in user profile
     */
    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
        String profileId = ((LoggedUser) event.getAuthentication().getPrincipal()).getProfileId();
        userService.updateChatUID(profileId, UUID.randomUUID().toString());
    }

}
