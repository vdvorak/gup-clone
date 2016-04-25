package ua.com.itproekt.gup.controller.auth;

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

import java.util.HashMap;
import java.util.Map;


@Controller
public class RegistrationController {

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    VerificationTokenService verificationTokenService;

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
