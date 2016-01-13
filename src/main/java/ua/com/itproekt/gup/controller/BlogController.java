package ua.com.itproekt.gup.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Optical Illusion on 13.01.2016.
 */
@Controller
public class BlogController {

    @RequestMapping("/blog-create")
    public String getBlogCreatePage() {
        return "blog-create";
    }
}
