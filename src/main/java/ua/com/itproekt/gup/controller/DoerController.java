package ua.com.itproekt.gup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fairy on 15.01.2016.
 */
@Controller
public class DoerController {
    @RequestMapping("/doer-create")
    public String getBlogCreatePage() {
        return "doer-create";
    }
}
