package ua.com.itproekt.gup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by RAYANT on 24.12.2015.
 */
@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String getLoginForm() {
        return "adminPage";
    }
}
