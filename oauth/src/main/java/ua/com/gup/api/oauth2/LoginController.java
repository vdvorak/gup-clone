package ua.com.gup.api.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

    @GetMapping(path = {"/login", "/login/"})
    public String login(Model model) {
        model.addAttribute("title", "title.authorization");
        return "login";
    }


}
