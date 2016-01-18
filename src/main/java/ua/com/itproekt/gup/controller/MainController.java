package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;


@Controller
public class MainController {

    @Autowired
    ProfilesService profilesService;


    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {

        Profile profile = new Profile();
        String userId;

        if (SecurityOperations.isUserLoggedIn()) {
            userId = SecurityOperations.getLoggedUserId();
        } else {
            return "index";
        }

        try {
            profile = profilesService.findById(userId);
        } catch (Exception e) {
            System.out.println("Can't read profile by id: " +userId);
            e.printStackTrace();
        }

        model.addAttribute("profile", profile);
        return "index";
    }
}
