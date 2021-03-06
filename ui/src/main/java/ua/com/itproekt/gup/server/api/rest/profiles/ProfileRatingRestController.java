package ua.com.itproekt.gup.server.api.rest.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileRating;
import ua.com.itproekt.gup.service.profile.ProfilesService;

/**
 * The type Profile rating rest controller.
 */
@RestController
@RequestMapping("/api/rest/profilesService")
public class ProfileRatingRestController {
    /**
     * The Profiles service.
     */
    @Autowired
    ProfilesService profilesService;

    /**
     * Gets profile rating by profile id and profile rating id. All params in path variables.
     *
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return the profile rating by id's
     */
    @RequestMapping(value = "/profile/read/id/{profileId}/profileRating/{profileRatingId}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> getProfileRatingById(@PathVariable String profileId, @PathVariable String profileRatingId) {
        Profile profile = profilesService.findProfileRating(profileId, profileRatingId);
        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    /**
     * Create profile rating.
     *
     * @param profileId     the profile id in path variable
     * @param profileRating the profile rating in body request
     * @return the response status
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/profile/read/id/{profileId}/profileRating/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createProfileRating(@PathVariable String profileId,
                                                    @RequestBody ProfileRating profileRating,
                                                    UriComponentsBuilder ucBuilder) {
        profilesService.createProfileRating(profileId, profileRating);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/profile/read/id/{profileId}/profileRating/{profileRatingId}")
                .buildAndExpand(profileId, profileRating.getProfileRatingId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Delete profile rating by profile rating id. All params in path variables.
     *
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return the response status
     */
    @RequestMapping(value = "/profile/read/id/{profileId}/profileRating/{profileRatingId}/delete",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Profile> deleteProfileRating(@PathVariable String profileId,
                                                       @PathVariable String profileRatingId) {
        if (!profilesService.profileRatingExists(profileId, profileRatingId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        profilesService.deleteProfileRating(profileId, profileRatingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
