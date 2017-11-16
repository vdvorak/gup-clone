package ua.com.gup.api.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping(path = {"/login", "/login/"})
    public String login(Model model) {
        model.addAttribute("title", "title.authorization");
        return "login";
    }

    @GetMapping(path = "/api/user/principal")
    public @ResponseBody
    Principal getUser(Principal principal) {
        return principal;
    }

}
