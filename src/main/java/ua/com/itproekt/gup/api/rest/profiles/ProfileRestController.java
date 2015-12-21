package ua.com.itproekt.gup.api.rest.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.CreatedObjResponse;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.util.HashSet;


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
    PasswordEncoder passwordEncoder;

    /**
     * Create profile.
     *
     * @param profile   JSON object in request body
     * @param ucBuilder the uc builder
     * @return the response status
     */
    @RequestMapping(value = "/profile/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResponse> createProfile(@RequestBody Profile profile, UriComponentsBuilder ucBuilder) {
        String hashedPassword = passwordEncoder.encode(profile.getPassword());
        profile.setPassword(hashedPassword);

        HashSet<UserRole> userRoles = new HashSet<>();
        userRoles.add(UserRole.ROLE_USER);
        profile.setUserRoles(userRoles);

        profilesService.createProfile(profile);

        CreatedObjResponse createdObjResponse = new CreatedObjResponse(profile.getId());
        return new ResponseEntity<>(createdObjResponse, HttpStatus.CREATED);
    }

    /**
     * Gets profile by id.
     *
     * @param id the id
     * @return the profile by id
     */
    @RequestMapping(value = "/profile/read/id/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> getProfileById(@PathVariable("id") String id) {
        Profile profile = profilesService.readById(id);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    /**
     * Gets profile by username.
     *
     * @param username the username
     * @return the profile by username
     */
    @RequestMapping(value = "/profile/read/username/{username}",
            method = RequestMethod.POST,
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
    @RequestMapping(value = "/profile/read/all",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Profile>> listAllProfiles(@RequestBody ProfileFilterOptions profileFilterOptions) {
        EntityPage<Profile> profiles = profilesService.findAllProfiles(profileFilterOptions);
        if (profiles.getEntities().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    /**
     * Update profile response entity.
     *
     * @param newProfile the new profile with id of entity in request body
     * @param ucBuilder  the uc builder
     * @return the response status
     */
    @RequestMapping(value = "/profile/update",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile newProfile, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Profile profile = profilesService.findProfileByEmail(auth.getName());
            newProfile.setId(profile.getId());
            headers.setLocation(ucBuilder.path("/profile/read/id/{id}").buildAndExpand(newProfile.getId()).toUri());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(profilesService.updateProfile(newProfile), headers, HttpStatus.OK);
    }

    /**
     * Delete profile by profile id.
     *
     * @param id the profile id
     * @return the response status
     */
    @RequestMapping(value = "/profile/delete/id/{id}",
            method = RequestMethod.POST)
    public ResponseEntity<Profile> deleteProfile(@PathVariable("id") String id) {
        if (!profilesService.profileExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        profilesService.deleteProfileById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add friend response entity.
     *
     * @param friendID the friend's profile id
     * @return the response status
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/friends/addFriend/{friendID}/",
            method = RequestMethod.POST)
    public ResponseEntity<Void> addFriend(@PathVariable String friendID) {
        if (!profilesService.profileExists(friendID)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String profileId = SecurityOperations.getLoggedUserId();
        profilesService.addFriend(profileId, friendID);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/profile/read/id/{profileId}/userProfile/read",
            method = RequestMethod.POST)
    public ResponseEntity<Profile> readUserProfile(@PathVariable String profileId) {
        Profile profile = profilesService.findUserProfile(profileId);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }
}