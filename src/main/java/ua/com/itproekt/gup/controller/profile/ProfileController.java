package ua.com.itproekt.gup.controller.profile;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ProfileController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    ActivityFeedService activityFeedService;


    @RequestMapping("/profile/id/{profileId}")
    public String getProfileById(@PathVariable String profileId, Model model) {
        model.addAttribute("profileId", profileId);
        return "profile/profile";
    }

    //----------------------------------- read profile for edit-profile page  ------
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit-profile/{id}", method = RequestMethod.GET)
    public String editProfilePageById(Model model, @PathVariable("id") String id) {
        Profile profile = new Profile();
        try {
            profile = profilesService.findWholeProfileById(id);
        } catch (Exception e) {
            System.out.println("Can't read profile by id: " + id);
            e.printStackTrace();
        }

        model.addAttribute("profile", profile);
        return "profile/edit-profile";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping(value = "/edit-profile", method = RequestMethod.GET)
    public String editProfilePage(Model model, HttpServletRequest request,
                                  @RequestParam(required = false) String profileId) {
        if (profileId == null) {
            profileId = SecurityOperations.getLoggedUserId();
        } else {
            if(!profilesService.profileExists(profileId)) {
                throw new ResourceNotFoundException();
            }

            if(!profileId.equals(SecurityOperations.getLoggedUserId()) || !request.isUserInRole(UserRole.ROLE_ADMIN.toString())){
                throw new AccessDeniedException("Edit profile can only owner or admin");
            }

            return "loginForm";
        }


        model.addAttribute("profileId", profileId);
        return "profile/edit-profile";
    }

    @RequestMapping("/profile/list")
    public String getProfileList(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                 @RequestParam(required = false, defaultValue = "") String name,
                                 Model model) {
        ProfileFilterOptions profileFO = new ProfileFilterOptions();
        if (!name.isEmpty()) {
            profileFO.setSearchField(name);
        }
        profileFO.setSkip(pageNumber);

        model.addAttribute("profileFO", new Gson().toJson(profileFO) );
        return "profile/profileList";
    }

//    @RequestMapping(value = "/profile/list", method = RequestMethod.POST)
//    public String getProfileList(@ModelAttribute ProfileFilterOptions profileFO, Model model) {
//        model.addAttribute("profileFO", profileFO);
//        return "profile/profileList";
//    }
}
