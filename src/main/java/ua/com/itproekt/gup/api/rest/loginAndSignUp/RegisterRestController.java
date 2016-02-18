package ua.com.itproekt.gup.api.rest.loginAndSignUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.model.profiles.verification.VerificationToken;
import ua.com.itproekt.gup.model.projectsAndInvestments.project.Project;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;


@RestController
public class RegisterRestController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@RequestBody Profile profile){

        if(profilesService.profileExistsWithEmail(profile.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            profilesService.createProfile(profile);
            verificationTokenService.sendEmailRegistrationToken(profile.getId());

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
