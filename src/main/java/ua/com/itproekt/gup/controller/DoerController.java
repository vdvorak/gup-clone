package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.tender.doer.Doer;
import ua.com.itproekt.gup.model.tender.doer.DoerFilterOptions;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.doer.DoerService;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

/*
 * Created by Fairy on 15.01.2016.
 */
@Controller
public class DoerController {
    @Autowired
    DoerService doerService;

    @Autowired
    ProfilesService profilesService;

    @RequestMapping("/doers")
    public String getDoersRead(Model model) {

        DoerFilterOptions doerFilterOptions = new DoerFilterOptions();
        doerFilterOptions.setLimit(10000);
        EntityPage<Doer> doerEntityPage = doerService.findWihOptions(doerFilterOptions);
        model.addAttribute("doerPages", doerEntityPage);
        return "doers";
    }

    @RequestMapping("/doer-create")
    public String getDoerCreatePage() {
        return "doer-create";
    }

    @RequestMapping("/doer/update/{id}")
    public String getDoerEditPage(Model model, @PathVariable String id) {
        Doer doer = doerService.findById(id);
        if (SecurityOperations.isUserLoggedIn()) {
            String userId = SecurityOperations.getLoggedUserId();
            if(!userId.equals(doer.getAuthorId())){
                // todo FORBIDDEN
            }
        }
        model.addAttribute("doer", doer);
        return "doer-edit";
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

        Profile profile = profilesService.findByIdWholeProfile(doer.getAuthorId());
        String userName = profile.getUsername();

        if(userName != null && userName.length() > 0) model.addAttribute("username", userName);
        else model.addAttribute("username", "Anonimous");
        model.addAttribute("check", check);
        model.addAttribute("doer", doer);
        return "doer";
    }
}
