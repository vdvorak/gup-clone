package ua.com.itproekt.gup.server.api.rest.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.model.login.FormLoggedUser;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.server.api.rest.profiles.dto.ProfileInfo;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/rest/profilesService")
public class ProfileRestController {


    @Autowired
    ProfilesService profilesService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    BankSession bankSession;


    /**
     * Gets profile by id.
     *
     * @param id the id
     * @return the profile by id
     */
    @CrossOrigin
    @RequestMapping(value = "/profile/read/id/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileInfo> getProfileById(@PathVariable String id) {

        ProfileInfo profileInfo = profilesService.findPublicProfileById(id);

        if (profileInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/profile/read/loggedInProfile", method = RequestMethod.GET)
    public ResponseEntity<ProfileInfo> getLoggedUser() {
        ProfileInfo profileInfo = null;

        String loggedUserId = SecurityOperations.getLoggedUserId();
        if (loggedUserId != null) {
            profileInfo = profilesService.findPrivateProfileByIdAndUpdateLastLoginDate(loggedUserId);
            profileInfo.setUserBalance(bankSession.getUserBalance(loggedUserId));
            //FixMe - в будущем добавить вывод бонусного счёта
        }

        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
    }

    /**
     * Update profile response entity.
     *
     * @param newProfile the new profile with id of entity in request body
     * @return the response status
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile newProfile, HttpServletRequest request) {
//        if (!profilesService.profileExists(newProfile.getId())) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Profile  oldProfile = profilesService.findById(newProfile.getId());
//        String loggedUserId = SecurityOperations.getLoggedUserId();
        String loggedUserId = SecurityOperations.getLoggedUserId();
        if (loggedUserId == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Profile oldProfile = profilesService.findById(loggedUserId);

        if (newProfile.getIdSeoWord() != null) {
            if (profilesService.isSeoWordFree(newProfile.getIdSeoWord())) {
                if (oldProfile.getId().equals(loggedUserId) || request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
                    changeUserType(newProfile, oldProfile);
                    profilesService.editProfile(newProfile);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            } else
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            if (oldProfile.getId().equals(loggedUserId) || request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
                changeUserType(newProfile, oldProfile);
                profilesService.editProfile(newProfile);
                return new ResponseEntity<>(HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**
     * Gets profile by username.
     *
     * @param username the username
     * @return the profile by username
     */
    @CrossOrigin
    @RequestMapping(value = "/profile/read/username/{username}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> getProfileByUsername(@PathVariable("username") String username) {
        Profile profile = profilesService.findProfileByUsername(username);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    /**
     * List all profiles response entity.
     *
     * @param profileFilterOptions the profile filter options (pagination).
     *                             Use "skip" and "limit" in JSON object request body
     * @return the response entity
     */
    @CrossOrigin
    @RequestMapping(value = "/profile/read/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProfileInfo>> listAllProfiles(@RequestBody ProfileFilterOptions profileFilterOptions) {
        List<ProfileInfo> profiles = profilesService.findAllPublicProfilesWithOptions(profileFilterOptions);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/profile/email-check", method = RequestMethod.POST)
    public String idByEmail(@RequestParam String email) {
        Profile profile = profilesService.findProfileByEmail(email);
        if (profile == null) {
            return "NOT FOUND";
        }
        return profile.getId();
    }

    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/id/{profileId}/myContactList/add", method = RequestMethod.POST)
    public ResponseEntity<Void> addToMyContactList(@PathVariable String profileId) {

        if (!profilesService.profileExists(profileId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();
        profilesService.addContactToContactList(userId, profileId);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/id/{profileId}/myContactList/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteFromMyContactList(@PathVariable String profileId) {

        if (!profilesService.profileExists(profileId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        Profile profile = profilesService.findById(userId);

        Set<String> contactList = profile.getContactList();

        contactList.remove(profileId);

        profilesService.editProfile(profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/check-user-balance-by-id", method = RequestMethod.POST)
    @ResponseBody
    public Integer checkBalance(@RequestParam("userId") String userId) {

        if (profilesService.profileExists(userId)) {
            return bankSession.getUserBalance(userId);
        }
        return 0;
    }

    private void changeUserType(Profile newProfile, Profile oldProfile) {
        if (!newProfile.getContact().getType().equals(oldProfile.getContact().getType())) {
            switch (newProfile.getContact().getType()) {
                case LEGAL_ENTITY:
                    bankSession.editBalanceTypeEntityOfUser(newProfile.getId(), 2);
                    break;
                case INDIVIDUAL:
                    bankSession.editBalanceTypeEntityOfUser(newProfile.getId(), 3);
                    break;
                case ENTREPRENEUR:
                    bankSession.editBalanceTypeEntityOfUser(newProfile.getId(), 4);
                    break;
            }
        }
    }
}