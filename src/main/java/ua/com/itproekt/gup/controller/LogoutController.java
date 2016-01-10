package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.itproekt.gup.service.profile.ProfilesService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;



@Controller
public class LogoutController {

    @RequestMapping(value = "/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookieAuthToken = new Cookie("authToken", null);
        cookieAuthToken.setMaxAge(0);
        cookieAuthToken.setPath("/");
        response.addCookie(cookieAuthToken);

        Cookie cookieRefreshToken = new Cookie("refreshToken", null);
        cookieRefreshToken.setMaxAge(0);
        cookieRefreshToken.setPath("/");
        response.addCookie(cookieRefreshToken);

        return "redirect:/index";
    }
}
