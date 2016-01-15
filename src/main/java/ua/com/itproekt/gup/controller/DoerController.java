package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.tender.doer.Doer;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.doer.DoerService;
import ua.com.itproekt.gup.util.SecurityOperations;

/**
 * Created by Fairy on 15.01.2016.
 */
@Controller
public class DoerController {
    @Autowired
    DoerService doerService;

    @Autowired
    ProfilesService profilesService;

    @RequestMapping("/doer-create")
    public String getDoerCreatePage() {
        return "doer-create";
    }

    @RequestMapping(value = "/doer/{id}", method = RequestMethod.GET)
    public String getDoerRead(Model model, @PathVariable String id) {
        boolean check = false;

        Doer doer = doerService.findById(id);

        if (SecurityOperations.isUserLoggedIn()) {
            String userId = SecurityOperations.getLoggedUserId();
            check = userId.equals(doer.getAuthorId());
            model.addAttribute("check", check);
        }

        Profile profile = profilesService.findById(doer.getAuthorId());
        String userName = profile.getUsername();

        model.addAttribute("username", userName);
        model.addAttribute("check", check);
        model.addAttribute("doer", doer);
        return "doer";
    }
}
