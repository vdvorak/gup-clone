package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.EntityPage;

/**
 * Created by RAYANT on 24.12.2015.
 */
@Controller
public class AdminController {


    @Autowired
    ProfilesService profilesService;


    @RequestMapping("/admin")
    public String getLoginForm() {
        return "adminPage";
    }

    @RequestMapping("/admin-users")
    public String getAdminUsers() {
        return "admin-users";
    }

    @RequestMapping("/admin-offers")
    public String getAdminOffers() {
        return "admin-offers";
    }

    @RequestMapping(value = "/users/getall", method = RequestMethod.POST)
    public EntityPage<Profile> getAllUsers(@RequestParam(name = "skip", required = false, defaultValue = "0") Integer skip,
                               @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        try {
            System.err.println("Voshli");
            ProfileFilterOptions profileFilterOptions = new ProfileFilterOptions();
            profileFilterOptions.setSkip(skip);
            profileFilterOptions.setLimit(limit);
            EntityPage<Profile> profiles = profilesService.findAllProfiles(profileFilterOptions);
            System.err.println("Profiles: " + profiles);
            return profiles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
}
