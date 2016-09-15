package ua.com.itproekt.gup.server.api.rest.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.dao.oauth2.OAuth2AccessTokenRepository;
import ua.com.itproekt.gup.model.login.LoggedUser;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.dto.ProfileInfo;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/rest/profilesService")
public class ProfileRestController {


    @Autowired
    ProfilesService profilesService;

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    BankSession bankSession;

    @Autowired
    OAuth2AccessTokenRepository oAuth2AccessTokenRepository;


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


    /**
     * If User is logged in - return Profile Info, if not - return only status 200 (Ok).
     *
     * @param request
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/profile/read/loggedInProfile", method = RequestMethod.GET)
    public ResponseEntity<ProfileInfo> getLoggedUser(HttpServletRequest request) {

        long startTime = System.currentTimeMillis();

        if (request.getCookies() != null) {

            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("authToken")) {
                    startTime = System.currentTimeMillis();
                    Object principal = oAuth2AccessTokenRepository.findByTokenId(cookie.getValue()).getAuthentication().getUserAuthentication().getPrincipal();
                    System.err.println("principal time: " + (System.currentTimeMillis() - startTime));

                    startTime = System.currentTimeMillis();
                    ProfileInfo profileInfo = profileInfoPreparatorFromPrincipal(principal);
                    System.err.println("profileInfo auth time: " + (System.currentTimeMillis() - startTime));


                    return new ResponseEntity<>(profileInfo, HttpStatus.OK);
                }

                if (cookie.getName().equals("refreshToken")) {
                    startTime = System.currentTimeMillis();
                    Object principal = oAuth2AccessTokenRepository.findByRefreshToken(cookie.getValue()).getAuthentication().getUserAuthentication().getPrincipal();
                    System.err.println("principal time: " + (System.currentTimeMillis() - startTime));


                    startTime = System.currentTimeMillis();
                    ProfileInfo profileInfo = profileInfoPreparatorFromPrincipal(principal);
                    System.err.println("profileInfo refresh time: " + (System.currentTimeMillis() - startTime));


                    return new ResponseEntity<>(profileInfo, HttpStatus.OK);
                }
            }

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Update profile response entity.
     *
     * @param newProfile the new profile with id of entity in request body
     * @return the response status
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile newProfile, HttpServletRequest request) throws AuthenticationCredentialsNotFoundException {
        String loggedUserId = SecurityOperations.getLoggedUserId();

        newProfile.setId(loggedUserId);
        Profile oldProfile = profilesService.findById(loggedUserId);

        if (newProfile.getIdSeoWord() != null) { //if( !newProfile.getIdSeoWord().equals(null) ){
            if (profilesService.isSeoWordFree(newProfile.getIdSeoWord())) {
                if (oldProfile.getId().equals(loggedUserId) || request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
                    changeUserType(newProfile, oldProfile);
                    profilesService.editProfile(newProfile);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if (oldProfile.getId().equals(loggedUserId) || request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            changeUserType(newProfile, oldProfile);
            profilesService.editProfile(newProfile);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/updateFavoriteOffer", method = RequestMethod.POST)
    public ResponseEntity<Void> updateFavoriteOffers(@RequestParam String offerId) {

        Profile profile = profilesService.findById(SecurityOperations.getLoggedUserId());
        Set<String> favoriteOffers = profile.getFavoriteOffers();
        for (String favoriteOffer : favoriteOffers) {
            if (favoriteOffer.equals(offerId)) {
                favoriteOffers.remove(offerId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        favoriteOffers.add(offerId);
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

    /**
     * Change userType in bank
     *
     * @param newProfile
     * @param oldProfile
     */
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


    /**
     * @param principal
     * @return
     */
    private ProfileInfo profileInfoPreparatorFromPrincipal(Object principal) {

        ProfileInfo profileInfo = new ProfileInfo();

        if (principal instanceof LoggedUser) {
            String userId = ((LoggedUser) principal).getProfileId();
            profileInfo = profilesService.findPrivateProfileByIdAndUpdateLastLoginDate(userId);
        }
        return profileInfo;
    }
}