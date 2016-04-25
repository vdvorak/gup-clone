package ua.com.itproekt.gup.controller.profile;

import com.google.gson.Gson;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.exception.ResourceNotFoundException;
import ua.com.itproekt.gup.model.profiles.*;
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

    @RequestMapping("/profile")
    public String getProfileById(@RequestParam String id) {
        if (!profilesService.profileExists(id)) {
            throw new ResourceNotFoundException();
        }

        //если владелец, то перенаправить на prioffice
        //если владелец, то перенаправить на prioffice
        //если владелец, то перенаправить на prioffice
//        if (profileId.equals(SecurityOperations.getLoggedUserId())) {
//            return "prioffice";
//
//        }

//        model.addAttribute("profileId", profileId);
        return "profile/profile";
    }

//----------------------------------- read profile for edit-profile page  ------


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
}
