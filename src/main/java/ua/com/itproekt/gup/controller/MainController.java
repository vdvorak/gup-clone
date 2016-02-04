package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;


@Controller
public class MainController {

    @Autowired
    ProfilesService profilesService;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {

        if (SecurityOperations.isUserLoggedIn()) {
            String userId = SecurityOperations.getLoggedUserId();
            Profile profile = profilesService.findById(userId);

            model.addAttribute("profile", profile);
        }

        model.addAttribute("profileFO", new ProfileFilterOptions());

        return "index";
    }


    @RequestMapping(value = {"/indexOld"})
    public String indexOLD(Model model) {

        if (SecurityOperations.isUserLoggedIn()) {
            String userId = SecurityOperations.getLoggedUserId();
            Profile profile = profilesService.findById(userId);

            model.addAttribute("profile", profile);
        }

        model.addAttribute("profileFO", new ProfileFilterOptions());

        return "indexOLD";
    }


}
