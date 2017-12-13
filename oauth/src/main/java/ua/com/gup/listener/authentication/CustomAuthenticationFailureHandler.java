package ua.com.gup.listener.authentication;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.UserService;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae) throws IOException, ServletException {        
        String redirectUrl = "login?error";
        //banned
        if (ae instanceof LockedException) {
            String username = hsr.getParameter("username");
            Profile findProfileByEmail = userService.findProfileByEmail(username);
            redirectUrl += "&blocked";
            //if have public reason add to url
            if (findProfileByEmail.getBanExplanation() != null
                    && !StringUtils.isEmpty(findProfileByEmail.getBanExplanation().getPublicExplanation())) {                                
                String publicExplanation = findProfileByEmail.getBanExplanation().getPublicExplanation();
                redirectUrl += "&reason=" + URLEncoder.encode(publicExplanation, "UTF-8");
            } 
            
        }else if (ae instanceof BadCredentialsException) {
            redirectUrl += "&bad_credentials";
        } else {
            
        }
        hsr1.sendRedirect(redirectUrl);
        
    }
}
