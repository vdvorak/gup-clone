
package ua.com.gup.listener.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import ua.com.gup.mongo.model.login.LoggedUser;
import ua.com.gup.service.profile.ProfilesService;


public class CustomLogoutHandler implements LogoutHandler {

    @Autowired
    private ProfilesService profilesService;

    /*
    Delete UID for chat in user profile
    **/
    @Override
    public void logout(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) {
        LoggedUser user = (LoggedUser) a.getPrincipal();
        String profileId = user.getProfileId();
        profilesService.updateChatUID(profileId, null);
    }

}
