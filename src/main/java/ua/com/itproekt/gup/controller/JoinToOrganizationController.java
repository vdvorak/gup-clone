package ua.com.itproekt.gup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Zver on 28.01.2016.
 */
@Controller
public class JoinToOrganizationController {

    @RequestMapping("/joinToGUP")
    public String getJoinToOrganizationPage() {
        return "joinToGUP";
    }
}
