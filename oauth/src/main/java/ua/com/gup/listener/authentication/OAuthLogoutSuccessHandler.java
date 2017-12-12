package ua.com.gup.listener.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import ua.com.gup.mongo.model.login.LoggedUser;
import ua.com.gup.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuthLogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment e;


    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication a) throws IOException, ServletException {
        LoggedUser user = (LoggedUser) a.getPrincipal();
        String profileId = user.getProfileId();
        userService.updateChatUID(profileId, null);
        httpServletResponse.sendRedirect(e.getRequiredProperty("application.url"));
    }
}
