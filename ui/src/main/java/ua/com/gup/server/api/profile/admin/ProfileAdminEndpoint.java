package ua.com.gup.server.api.profile.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.dto.profile.CreateProfileDTO;
import ua.com.gup.dto.profile.EditProfileDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.profiles.ProfileFilterOptions;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.security.SecurityUtils;

import java.util.List;

/**
 * Rest controllers for using by administrative module.
 */
@RestController
@RequestMapping("/api/users/admin")
public class ProfileAdminEndpoint {

    @Autowired
    private ProfilesService profilesService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/profiles")
    public ResponseEntity<List<Profile>> findProfilesShortByFilter(ProfileFilterOptions profileFilterOptions) {
        List<Profile> profiles = profilesService.findAllProfilesForAdminShort(profileFilterOptions);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/profiles")
    public ResponseEntity createProfile(@RequestBody CreateProfileDTO profileDTO) {

        if (StringUtils.isEmpty(profileDTO.getEmail())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Profile foundByEmailProfile = profilesService.findProfileByEmail(profileDTO.getEmail());
        if (foundByEmailProfile != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        profilesService.createProfile(profileDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/profiles/{profilePublicId}")
    public ResponseEntity updateProfile(@PathVariable String profilePublicId, @RequestBody EditProfileDTO profileDTO) {

        Profile profile = profilesService.findByPublicId(profilePublicId);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (profile.isBan() || !profile.getActive()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        profilesService.editProfile(profileDTO.updateModel(profile));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/profiles/ban/{id}")
    public ResponseEntity<String> banProfileByID(@PathVariable("id") String id) {
        String loggedUserId = SecurityUtils.getCurrentUserId();
        if (id.equals(loggedUserId)) {
            return new ResponseEntity<>("cant ban you self", HttpStatus.FORBIDDEN);
        }
        Profile profile = profilesService.findById(id);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        profile.setActive(false);
        profile.setBan(true);
        profilesService.editProfile(profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/profiles/unban/{id}")
    public ResponseEntity<String> unbanProfileByID(@PathVariable("id") String id) {
        String loggedUserId = SecurityUtils.getCurrentUserId();
        if (id.equals(loggedUserId)) {
            return new ResponseEntity<>("cant unban you self", HttpStatus.FORBIDDEN);
        }
        Profile profile = profilesService.findById(id);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        profile.setActive(true);
        profile.setBan(false);
        profilesService.editProfile(profile);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
