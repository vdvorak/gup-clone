package ua.com.itproekt.gup.server.api.rest.profiles;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import java.util.List;

@RestController
@RequestMapping("/api/rest/admin")
public class ProfileRestAdminController {

    @Autowired
    ProfilesService profilesService;

    /**
     * List all profiles response entity for admins.
     *
     * @param profileFilterOptions the profile filter options (pagination).
     *                             Use "skip" and "limit" in JSON object request body
     * @return the response entity
     * @return the response entity
     */
    @CrossOrigin
    @RequestMapping(value = "/profile/read/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Profile>> listAllProfiles(@RequestBody ProfileFilterOptions profileFilterOptions) {
        List<Profile> profiles = profilesService.findAllProfilesForAdmin(profileFilterOptions);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }




    //ToDo add Preauthorize
    @CrossOrigin
    @RequestMapping(value = "/profile/admin/admin-delete", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> listAllProfiles(@RequestBody String profileId) {


        //additional check before delete profile. Only for administrative staff
        Profile profile = profilesService.findById(profileId);

        if (profile == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        profilesService.deleteProfileById(profileId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
