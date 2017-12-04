package ua.com.gup.listener.authentication;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import ua.com.gup.mongo.model.login.LoggedUser;
import ua.com.gup.service.profile.ProfilesService;


@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    private ProfilesService profilesService;

    /**
     Put UID for chat in user profile
     */
    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {        
        String profileId = ((LoggedUser) event.getAuthentication().getPrincipal()).getProfileId();
        profilesService.updateChatUID(profileId, UUID.randomUUID().toString());
    }

}
