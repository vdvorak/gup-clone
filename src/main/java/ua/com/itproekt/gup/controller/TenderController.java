package ua.com.itproekt.gup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qz on 1/12/2016.
 */
@Controller
public class TenderController {

    @RequestMapping("/tender")
    public String getTender() {
        return "tender";
    }

    @RequestMapping("/tender-make")
    public String getTenderMake() {
        return "tender-make";
    }
}
