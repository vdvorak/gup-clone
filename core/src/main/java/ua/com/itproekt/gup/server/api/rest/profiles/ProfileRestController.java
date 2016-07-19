package ua.com.itproekt.gup.server.api.rest.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.server.api.rest.profiles.dto.ProfileInfo;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


/**
 * The type Profile rest controller.
 */
@RestController
@RequestMapping("/api/rest/profilesService")
public class ProfileRestController {
    /**
     * The Profiles service.
     */
    @Autowired
    ProfilesService profilesService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    BankSession bankSession;

    /**
     * Create profile.
     *
     * @param profile JSON object in request body
     * @return the response status
     */
    @RequestMapping(value = "/profile/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> createProfile(@RequestBody Profile profile) {
        if (profilesService.profileExistsWithEmail(profile.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        profilesService.createProfile(profile);
        verificationTokenService.sendEmailRegistrationToken(profile.getId());

        CreatedObjResp createdObjResp = new CreatedObjResp(profile.getId());

        return new ResponseEntity<>(createdObjResp, HttpStatus.CREATED);
    }

    /**
     * Gets profile by id.
     *
     * @param id the id
     * @return the profile by id
     */
    @RequestMapping(value = "/profile/read/id/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileInfo> getProfileById(@PathVariable String id) {

        ProfileInfo profileInfo = profilesService.findExtendedProfileById(id);

        if (profileInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (SecurityOperations.isUserLoggedIn() && id.equals(SecurityOperations.getLoggedUserId())) {
            return new ResponseEntity<>(profileInfo, HttpStatus.OK);
        } else {
            profileInfo.setContactList(null);
            return new ResponseEntity<>(profileInfo, HttpStatus.OK);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/read/id/{profileId}/wholeProfile", method = RequestMethod.POST)
    public ResponseEntity<Profile> readUserProfile(@PathVariable String profileId, HttpServletRequest request) {
        Profile profile = profilesService.findWholeProfileById(profileId);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String loggedUserId = SecurityOperations.getLoggedUserId();
        if (profile.getId().equals(loggedUserId) || request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/read/loggedInProfile", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Profile> getLoggedUser() {
        Profile profile = profilesService.findWholeProfileById(SecurityOperations.getLoggedUserId());

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    /**
     * Gets profile by username.
     *
     * @param username the username
     * @return the profile by username
     */
    @RequestMapping(value = "/profile/read/username/{username}", method = RequestMethod.POST,
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
    @RequestMapping(value = "/profile/read/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Profile>> listAllProfiles(@RequestBody ProfileFilterOptions profileFilterOptions) {
        EntityPage<Profile> profiles = profilesService.findAllProfiles(profileFilterOptions);

        List<Profile> profilesList = profiles.getEntities();
        for (Profile profile : profilesList) {
            profile.setContactList(null);
        }

        if (profiles.getEntities().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    /**
     * Update profile response entity.
     *
     * @param newProfile the new profile with id of entity in request body
     * @return the response status
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile newProfile, HttpServletRequest request) {
        if (!profilesService.profileExists(newProfile.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Profile oldProfile = profilesService.findById(newProfile.getId());
        String loggedUserId = SecurityOperations.getLoggedUserId();

        if (newProfile.getIdSeoWord() != null) {

            if (profilesService.isSeoWordFree(newProfile.getIdSeoWord())) {
                if (oldProfile.getId().equals(loggedUserId) || request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
                    changeUserType(newProfile, oldProfile);
                    profilesService.editProfile(newProfile);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }


        } else {
            if (oldProfile.getId().equals(loggedUserId) || request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
                changeUserType(newProfile, oldProfile);
                profilesService.editProfile(newProfile);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
    }


    @ResponseBody
    @RequestMapping(value = "/profile/email-check", method = RequestMethod.POST)
    public String idByEmail(@RequestParam String email) {
        Profile profile = profilesService.findProfileByEmail(email);
        if (profile == null) {
            return "NOT FOUND";
        }
        return profile.getId();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/id/{profileId}/myContactList/add", method = RequestMethod.POST)
    public ResponseEntity<Void> addToMyContactList(@PathVariable String profileId) {

        if (!profilesService.profileExists(profileId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        contactId
//        ownerContactListId
        String userId = SecurityOperations.getLoggedUserId();
        profilesService.addContactToContactList(userId, profileId);

//        String authorId = blogPostService.findComment(blogPostId, commentId).getComments().iterator().next().getFromId();
//        activityFeedService.createEvent(new Event(authorId, EventType.BLOG_POST_COMMENT_LIKE, blogPostId, userId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

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


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/join-organization", method = RequestMethod.POST)
    public ResponseEntity<String> joinToOrganization() {

        String userId = SecurityOperations.getLoggedUserId();
        Profile profile = profilesService.findById(userId);

        if (profile.getContact().isMember()) {
            return new ResponseEntity<>("1", HttpStatus.OK);
        } else {
            Integer userBalance = bankSession.getUserBalance(userId);
            if (userBalance >= 50) {
                bankSession.investInOrganization(5555, userId, 50L, 11, "Success");
                profile.getContact().setMember(true);
                profilesService.editProfile(profile);
                return new ResponseEntity<>("2", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("3", HttpStatus.OK);
            }
        }
    }

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