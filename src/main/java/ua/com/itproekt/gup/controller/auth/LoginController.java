package ua.com.itproekt.gup.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/loginForm")
    public String getLoginForm() {
        return "loginForm";
    }
}