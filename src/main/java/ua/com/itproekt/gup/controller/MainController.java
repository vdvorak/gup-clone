package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;


@Controller
public class MainController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    BankSession bankSession;

    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/check-balance", method = RequestMethod.POST)
    @ResponseBody
    public Integer checkBalance() {
        if (SecurityOperations.isUserLoggedIn()) {
            String userId = SecurityOperations.getLoggedUserId();
            return bankSession.getUserBalance(userId);
        }
        return 0;
    }

    @RequestMapping(value = {"/indexOld"})
    public String indexOLD(Model model) {

        if (SecurityOperations.isUserLoggedIn()) {
            String userId = SecurityOperations.getLoggedUserId();
            Profile profile = profilesService.findWholeProfileById(userId);

            model.addAttribute("profile", profile);
        }

        model.addAttribute("profileFO", new ProfileFilterOptions());

        return "indexOLD";
    }
}
