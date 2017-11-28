package ua.com.gup.server.api.profile;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.dto.profile.EditProfileDTO;
import ua.com.gup.dto.profile.ProfileDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.enumeration.UserRole;
import ua.com.gup.mongo.model.profiles.CheckMainPhone;
import ua.com.gup.mongo.model.profiles.ProfileFilterOptions;
import ua.com.gup.mongo.model.profiles.phone.*;
import ua.com.gup.service.profile.ProfilesService;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import ua.com.gup.util.security.SecurityUtils;


@RestController
@RequestMapping("/api/rest/profilesService")
public class ProfileEndpoint {

    private final Logger log = LoggerFactory.getLogger(ProfileEndpoint.class);

    @Autowired
    private ProfilesService profilesService;

    /**
     * Gets profile by id.
     *
     * @param publicId the id
     * @return the profile by id
     */
    @CrossOrigin
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable("id") String publicId) {
        ProfileDTO profileInfo = profilesService.findPublicProfileByPublicId(publicId);
        if (profileInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
    }
    @CrossOrigin
    @RequestMapping(value = "/profile/mainPhoneViews/{id}", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileDTO> countPhoneViewsProfileById(@PathVariable String id) {
        ProfileDTO profileInfo = profilesService.incMainPhoneViewsAtOne(id);

        if (profileInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
    }
    /**
     * Add one contact (user) to the current user contact list.
     *
     * @param publicProfileId - the public profile ID of the user, which must be added to the contact list.
     * @return - the status code Not_Found if target user was not fount,
     * status code OK if user was successfully deleted.
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/contacts/toggle/{publicProfileId}", method = RequestMethod.POST)
    public ResponseEntity<String> toggleProfileInMyContactList(@PathVariable String publicProfileId) {
        if (!profilesService.profileExistsByPublicId(publicProfileId)) {
            return new ResponseEntity<>("Target profile was not found", HttpStatus.NOT_FOUND);
        }
        String userId = SecurityUtils.getCurrentUserId();
        if (userId != null) {
            profilesService.toggleProfileInUserSocialList(userId, publicProfileId);
            log.debug("{User: " + publicProfileId + ", toggled to/from: " + userId + " contact list}");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.PROXY_AUTHENTICATION_REQUIRED);
        }
    }
    /**
     * Delete one contact from user contact list.
     *
     * @param profileId - the ID of the profile which must be deleted.
     * @return - status 404 if target profile was not found, status 200 if profile was deleted from contact list.
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/id/{profileId}/mySocialList/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteFromMySocialList(@PathVariable String profileId) {
        if (!profilesService.profileExists(profileId)) { //TODO: need make test...
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        profilesService.deleteFromMyContactList(profileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * If User is logged in - return Profile Info, if not - return only status 200 (Ok).
     *
     * @param request - the HttpServletRequest object.
     * @return - in any case return status 200 (OK), but it will be with ProfileDTO object in the response body if
     * the user is loggedIn, and there will empty response body if the user is not loggedIn.
     */
    @CrossOrigin
    @RequestMapping(value = "/profile/read/loggedInProfile", method = RequestMethod.GET)
    public ResponseEntity<ProfileDTO> getLoggedUser(HttpServletRequest request) throws Exception {
        ProfileDTO profileInfo = profilesService.getLoggedUser(request);
        if (profileInfo != null) {
            return new ResponseEntity<>(profileInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
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
    public ResponseEntity<List<ProfileDTO>> listAllProfiles(@RequestBody ProfileFilterOptions profileFilterOptions) {
        List<ProfileDTO> profiles = profilesService.findAllPublicProfilesWithOptions(profileFilterOptions);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }


    /**
     * Check if profile with specific email exist
     *
     * @param email - the email of the target profile.
     * @return - string "NOT FOUND" or user ID.
     */
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
    @ResponseBody
    @RequestMapping(value = "/profile/mainphone-check", method = RequestMethod.POST)
    public ResponseEntity<List<Profile>> idByMainPhone(@RequestBody CheckMainPhone checkMainPhone) {
        List<Profile> profiles = new ArrayList<>();
        for (String mainPhone : checkMainPhone.getMainPhones()) {
            System.err.println(mainPhone);
            Profile profile = profilesService.findProfileByMainPhone(mainPhone);
            if (profile != null) profiles.add(profile);
        }

        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    /* ************************************************************************************************************** */
    private Long parsePhone(String phone) {
        phone = phone.replaceAll("\\D+", "");

        int strStart = 0, strLast = phone.length();
        if (0 < (phone.length() - 10)) strStart = phone.length() - 10;
        phone = phone.substring(strStart, strLast);

        return Long.parseLong(phone);
    }

    private DBStorePhones dbStorePhones(String userId, List<Long> mainPhones, ProfileStorePhones profileStorePhones) {
        List<Long> contactPhones = profileStorePhones.getContactPhones().stream()
                .map(x -> x.getNumberPhone())
                .collect(Collectors.toList());

        return new DBStorePhones(userId, mainPhones, contactPhones);
    }

    private ProfileStorePhones profileStorePhones(String userId) {
        Profile profile = profilesService.findById(userId);
        if (profile == null) return null;
        DBStorePhones dbStorePhones = profile.getStorePhones();
        if (dbStorePhones == null) return null;
        List<PhoneSynhronize> oContactPhones = dbStorePhones.getContactPhones().stream()
                .map(x -> (profilesService.findProfileByMainPhone(String.valueOf(x)) != null) ? (new PhoneSynhronize(x, true)) : (new PhoneSynhronize(x, false)))
                .collect(Collectors.toList());

        ProfileStorePhones profileStorePhones = new ProfileStorePhones();
        profileStorePhones.setContactPhones(oContactPhones);
        return profileStorePhones;
    }

    @CrossOrigin
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/synchronization-phones", method = RequestMethod.POST)
    public ResponseEntity<String> synchronizationPhones(@RequestBody StorePhones storePhones) {
        String userId = SecurityUtils.getCurrentUserId();
        storePhones.setIdUser(userId);
        ProfileStorePhones profileStorePhones = new ProfileStorePhones(storePhones.getContactPhones());
        DBStorePhones newStorePhones = dbStorePhones(storePhones.getIdUser(), storePhones.getMainPhones(), profileStorePhones);
        System.err.println(newStorePhones); //TODO  DBStorePhones{idUser='591c5ee20f664e17d30eb225', mainPhones=[380991234567], contactPhones=[380994444444, 380934311043, 380970072837, 380939325476]}
        Profile profile = profilesService.findById(userId);
        if (profile.getId().equals(userId)) {
            if (profile.getStorePhones() == null) {
                profile.setStorePhones(newStorePhones);
                profilesService.editProfile(profile);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                if (profile.getStorePhones().equals(newStorePhones)) {
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    profile.setStorePhones(newStorePhones);
                    profilesService.editProfile(profile);
                    return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @CrossOrigin
    @ResponseBody
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/synchronization-phones", method = RequestMethod.GET)
    public ResponseEntity<ProfileIdStorePhones> synchronizationPhones() {
        String userId = SecurityUtils.getCurrentUserId();

        ProfileStorePhones profileStorePhones = profileStorePhones(userId);
        List<PhoneSynhronizeID> contactIdPhones = new ArrayList<>();
        for (PhoneSynhronize contactPhone : profileStorePhones.getContactPhones()) {
            Profile profile = profilesService.findProfileByMainPhone(String.valueOf(contactPhone.getNumberPhone()));
            PhoneSynhronizeID contactIdPhone = (profile == null)
                    ? new PhoneSynhronizeID(contactPhone.getNumberPhone(), null)
                    : new PhoneSynhronizeID(contactPhone.getNumberPhone(), profile.getId());
            contactIdPhones.add(contactIdPhone);
        }
        ProfileIdStorePhones profileIdStorePhones = new ProfileIdStorePhones(contactIdPhones);

        return new ResponseEntity<>(profileIdStorePhones, HttpStatus.OK);
    }


    /**
     * Update profile.
     *
     * @param editProfileDTO the new profile with id of entity in request body
     * @return the response status, Forbidden (403) if: main email is empty for profile which has social vendor "gup.com.ua"
     * 403 can also be if someone profile already exist with new email.
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public ResponseEntity<Void> updateProfile(@RequestBody EditProfileDTO editProfileDTO) throws AuthenticationCredentialsNotFoundException {

        if (StringUtils.isEmpty(editProfileDTO.getEmail())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        String loggedUserId = SecurityUtils.getCurrentUserId();
        Profile foundByEmailProfile = profilesService.findProfileByEmail(editProfileDTO.getEmail());

        if (foundByEmailProfile != null) {
            if (!foundByEmailProfile.getId().equals(loggedUserId) || foundByEmailProfile.isBan() || !foundByEmailProfile.getActive()) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        Profile oldProfile = profilesService.findById(loggedUserId);
        profilesService.editProfile(editProfileDTO.updateModel(oldProfile));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/ban/id/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> banProfileByID(@PathVariable("id") String id, HttpServletRequest request)
            throws AuthenticationCredentialsNotFoundException {
        String loggedUserId = SecurityUtils.getCurrentUserId();
        Profile profile = profilesService.findById(id);
        if (id.equals(loggedUserId)) {
            return new ResponseEntity<>("cant ban you self", HttpStatus.FORBIDDEN); //TODO: 403
        }
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //TODO: 204
        }

        if (request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            profile.setActive(false);
            profile.setBan(true);
            profilesService.editProfile(profile);
            return new ResponseEntity<>("User: '" + id + "' successfully banned", HttpStatus.OK); //TODO: 200
        }

        return new ResponseEntity<>("IS NEED ROLE_USER", HttpStatus.FORBIDDEN); //TODO: 403
    }

    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/unban/id/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> unbanProfileByID(@PathVariable("id") String id, HttpServletRequest request)
            throws AuthenticationCredentialsNotFoundException {
        String loggedUserId = SecurityUtils.getCurrentUserId();
        Profile profile = profilesService.findById(id);
        if (id.equals(loggedUserId)) {
            return new ResponseEntity<>("cant ban you self", HttpStatus.FORBIDDEN); //TODO: 403
        }
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); //TODO: 204
        }

        if (request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            profile.setActive(true);
            profile.setBan(false);
            profilesService.editProfile(profile);
            return new ResponseEntity<>("User: '" + id + "' successfully unbanned", HttpStatus.OK); //TODO: 200
        }

        return new ResponseEntity<>("IS NEED ROLE_USER", HttpStatus.FORBIDDEN); //TODO: 403
    }


    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/soc-edit", method = RequestMethod.POST)
    public ResponseEntity<Profile> updateSocProfile(@RequestBody Profile newProfile, HttpServletRequest request)
            throws AuthenticationCredentialsNotFoundException {
        String loggedUserId = SecurityUtils.getCurrentUserId();
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
        try {
            /* Edit Profile */
//            Profile profileEdit = profilesService.findPrivateProfileDTOByUid(newProfile.getUid(), newProfile.getSocWendor()).getProfile();
//            String id = profileEdit.getId();
//            profileEdit = newProfile;
//            profileEdit.setId(id);
//            profileEdit.setImgUrl("");
//            profilesService.editProfile(profileEdit);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }


    /**
     * Change profile status
     *
     * @param profile - the profile.
     * @return status 200 if ok
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/status/update", method = RequestMethod.POST)
    public ResponseEntity<Void> updateStatus(@RequestBody Profile profile) {
        String loggedUserId = SecurityUtils.getCurrentUserId();
        Profile oldProfile = profilesService.findById(loggedUserId);
        if (StringUtils.isEmpty(profile.getStatus())) {
            oldProfile.setStatus("");
        } else {
            oldProfile.setStatus(profile.getStatus());
        }

        profilesService.editProfile(oldProfile);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Add or delete offer into offer favorite list.
     *
     * @param offerId - the offer ID which must be add or delete to/from offer favorite list.
     * @return - status OK in the all cases.
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/updateFavoriteOffer", method = RequestMethod.POST)
    public ResponseEntity<Void> updateFavoriteOffers(@RequestParam String offerId) {
        profilesService.updateFavoriteOffers(offerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * Return current users balance.
     *
     * @return - the user balance.
     */
    @CrossOrigin
    @RequestMapping(value = "/check-user-balance-by-id", method = RequestMethod.POST)
    @ResponseBody
    public Integer checkBalance() {
        String loggedUserId = SecurityUtils.getCurrentUserId();
        return 0;
    }


    /**
     * Change userType in bank
     *
     * @param newProfile - the new version of the profile.
     * @param oldProfile - the old profile.
     */
    private void changeUserType(Profile newProfile, Profile oldProfile) {
        if (!newProfile.getUserType().equals(oldProfile.getUserType())) {
            switch (newProfile.getUserType()) {
                case LEGAL_ENTITY:
                    break;
                case INDIVIDUAL:
                    break;
                case ENTREPRENEUR:
                    break;
            }
        }
    }


    /**
     * Find and return all financial information for current user. It is include fields: "balance", "bonusBalance" and
     * "internalTransactionList" - history of the users transactions.
     *
     * @return - the result and status 200 (OK).
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/bank/financeInfo/read", method = RequestMethod.GET)
    public ResponseEntity<String> bankTest() {
        String loggedUserId = SecurityUtils.getCurrentUserId();
        return new ResponseEntity<>((String) null, HttpStatus.OK);
    }
}