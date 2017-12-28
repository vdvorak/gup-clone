package ua.com.gup.listener.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import ua.com.gup.model.LoggedUser;
import ua.com.gup.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class OAuthAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        String profileId = ((LoggedUser) authentication.getPrincipal()).getProfileId();
        userService.updateLastLoginDate(profileId);
        userService.updateChatUID(profileId, UUID.randomUUID().toString());
    }


}
