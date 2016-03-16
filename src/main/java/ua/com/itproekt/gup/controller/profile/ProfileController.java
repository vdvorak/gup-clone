package ua.com.itproekt.gup.controller.profile;

import com.google.gson.Gson;
import org.apache.commons.lang3.EnumUtils;
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

//    //----------------------------------- read profile for edit-profile page  ------
//    @Deprecated
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @RequestMapping(value = "/edit-profile/{id}", method = RequestMethod.GET)
//    public String editProfilePageById(Model model, @PathVariable("id") String id) {
//        Profile profile = new Profile();
//        try {
//            profile = profilesService.findWholeProfileById(id);
//        } catch (Exception e) {
//            System.out.println("Can't read profile by id: " + id);
//            e.printStackTrace();
//        }
//
//        model.addAttribute("profile", profile);
//        return "profile/edit-profile";
//    }

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




// ToDo Delete after normal edit-profile.jsp will work
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping(value = "/edit-profile-old", method = RequestMethod.GET)
    public String editProfileOldPage(Model model, HttpServletRequest request,
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
        return "edit-profileOLD";
    }








    @RequestMapping(value = "/profile/list")
    public String getProfileListWithFO(@RequestParam(required = false, defaultValue = "0") int pageNum,
                                       @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String type,
                                       Model model) {
        ProfileFilterOptions profileFO = new ProfileFilterOptions();
        Contact contact = new Contact();
        if (name != null) {
            profileFO.setSearchField(name);
        }

        if (type != null && EnumUtils.isValidEnum(UserType.class, type.toUpperCase())) {
            contact.setType(EnumUtils.getEnum(UserType.class, type.toUpperCase()));
        }

        profileFO.setSkip(pageNum);
        profileFO.setContact(contact);

        model.addAttribute("profileFO", new Gson().toJson(profileFO) );
        return "profile/profileList";
    }
}
