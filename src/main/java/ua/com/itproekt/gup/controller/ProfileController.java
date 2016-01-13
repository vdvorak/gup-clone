package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

/**
 * Created by Optical Illusion on 17.11.2015.
 */

@Controller
public class ProfileController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    ActivityFeedService activityFeedService;


    //----------------------------------- read profile for edit-profile page  ------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit-profile/{id}", method = RequestMethod.GET)
    public String editProfilePageById(Model model, @PathVariable("id") String id) {
        Profile profile = new Profile();
        try {
            profile = profilesService.findById(id);
        } catch (Exception e) {
            System.out.println("Can't read profile by id: " + id);
            e.printStackTrace();
        }

        model.addAttribute("profile", profile);
        return "edit-profile";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
    public String editProfilePage(Model model) {
        Profile profile = new Profile();
        String userId;

        if (SecurityOperations.isUserLoggedIn()) {
            userId = SecurityOperations.getLoggedUserId();
        } else {
            return "loginForm";
        }


        try {
            profile = profilesService.findById(userId);
        } catch (Exception e) {
            System.out.println("Can't read profile by id: " +userId);
            e.printStackTrace();
        }

        model.addAttribute("profile", profile);
        return "edit-profile";
    }
}
