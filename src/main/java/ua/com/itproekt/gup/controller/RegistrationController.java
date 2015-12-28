package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.model.profiles.verification.VerificationToken;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;

/**
 * Created by RAYANT on 20.11.2015.
 */

@Controller
public class RegistrationController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    ActivityFeedService activityFeedService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
     public void register(@RequestBody Profile profile){

        profilesService.createProfile(profile);
        verificationTokenService.sendEmailRegistrationToken(profile.getId());
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(@RequestParam String token, Model model) {

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);
        String uid = verificationToken.getUserId();

        profileRepository.addUserRole(uid, UserRole.EMAIL_CONFIRMED);
        verificationTokenService.verifyToken(token);

        model.addAttribute("message", "Регистрация подтверждена");
        return "temp";
    }
}
