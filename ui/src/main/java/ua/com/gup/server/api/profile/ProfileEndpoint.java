package ua.com.gup.server.api.profile;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.dto.profile.EditProfileDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.security.SecurityUtils;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/rest/profilesService")
public class ProfileEndpoint {

    private final Logger log = LoggerFactory.getLogger(ProfileEndpoint.class);

    @Autowired
    private ProfilesService profilesService;

    /**
     * Gets profile by profile public id.
     *
     * @param publicId profile public id
     * @return the profile by id
     */
    @RequestMapping(value = "/profile/{publicId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable("publicId") String publicId) {
        ProfileDTO profileInfo = profilesService.findPublicProfileByPublicId(publicId);
        if (profileInfo == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profileInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/profile/mainPhoneViews/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/contacts/toggle/{publicProfileId}", method = RequestMethod.POST)
    public ResponseEntity<String> toggleProfileInMyContactList(@PathVariable String publicProfileId) {
        if (!profilesService.profileExistsByPublicId(publicProfileId)) {
            return new ResponseEntity<>("Target profile was not found", HttpStatus.NOT_FOUND);
        }

        String userId = SecurityUtils.getCurrentUserId();
        profilesService.toggleProfileInUserSocialList(userId, publicProfileId);
        log.debug("{User: " + publicProfileId + ", toggled to/from: " + userId + " contact list}");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Delete one contact from user contact list.
     *
     * @param profileId - the ID of the profile which must be deleted.
     * @return - status 404 if target profile was not found, status 200 if profile was deleted from contact list.
     */
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
     * Update profile.
     *
     * @param editProfileDTO the new profile with id of entity in request body
     * @return the response status, Forbidden (403) if: main email is empty for profile which has social vendor "gup.com.ua"
     * 403 can also be if someone profile already exist with new email.
     */
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
        profilesService.updateProfile(editProfileDTO.updateModel(oldProfile));

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/soc-edit", method = RequestMethod.POST)
    public ResponseEntity<Profile> updateSocProfile(@RequestBody Profile newProfile, HttpServletRequest request)
            throws AuthenticationCredentialsNotFoundException {
        String loggedUserId = SecurityUtils.getCurrentUserId();
        newProfile.setId(loggedUserId);
        Profile oldProfile = profilesService.findById(loggedUserId);

        if (newProfile.getIdSeoWord() != null) { //if( !newProfile.getIdSeoWord().equals(null) ){
            if (profilesService.isSeoWordFree(newProfile.getIdSeoWord())) {
                if (oldProfile.getId().equals(loggedUserId) || request.isUserInRole(CommonUserRole.ROLE_ADMIN.toString())) {
                    changeUserType(newProfile, oldProfile);
                    profilesService.updateProfile(newProfile);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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

}