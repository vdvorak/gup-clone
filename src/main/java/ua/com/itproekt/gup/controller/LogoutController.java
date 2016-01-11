package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Controller
public class LogoutController {

    @Autowired
    private DefaultTokenServices tokenServices;

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {

        String accessToken = null;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("authToken")) {
                accessToken = cookie.getValue();
            }
        }

        Cookie cookieAuthToken = new Cookie("authToken", null);
        cookieAuthToken.setMaxAge(0);
        cookieAuthToken.setPath("/");
        response.addCookie(cookieAuthToken);

        Cookie cookieRefreshToken = new Cookie("refreshToken", null);
        cookieRefreshToken.setMaxAge(0);
        cookieRefreshToken.setPath("/");
        response.addCookie(cookieRefreshToken);

        tokenServices.revokeToken(accessToken);

        return "redirect:/index";
    }
}
