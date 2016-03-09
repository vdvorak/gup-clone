package ua.com.itproekt.gup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Created by Fairy on 04.03.2016.
 */
@Controller
public class KvedController {

    @RequestMapping("/addKved")
    public String updateTender() {
        return "tendersAndDoers/addKved";
    }

}
