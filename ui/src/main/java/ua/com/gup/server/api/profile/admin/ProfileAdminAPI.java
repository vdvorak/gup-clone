package ua.com.gup.server.api.profile.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.domain.profile.Profile;
import ua.com.gup.model.profiles.ProfileFilterOptions;
import ua.com.gup.model.profiles.admin.Admin;
import ua.com.gup.model.profiles.admin.Admins;
import ua.com.gup.service.profile.ProfilesService;

import java.util.List;

/**
 * Rest controllers for using by administrative module.
 */
@RestController
@RequestMapping("/api/rest/admin")
public class ProfileAdminAPI {

    @Autowired
    private ProfilesService profilesService;


    @CrossOrigin
    @RequestMapping(value = "/search/admin/id/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admins> getAdminIdAll() {
        return new ResponseEntity<>(new Admins(profilesService.getAdminIdAll()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/search/admin/id/all-by-online", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admins> getAdminIdAllByOnline() {
        return new ResponseEntity<>(new Admins(profilesService.getAdminIdAllByOnline()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/search/admin/id", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admin> getAdminId() {
        return new ResponseEntity<>(new Admin(profilesService.getAdminId()), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/search/admin/id-by-online", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Admin> getAdminIdByOnline() {
        return new ResponseEntity<>(new Admin(profilesService.getAdminIdByOnline()), HttpStatus.OK);
    }


    /**
     * List all profiles response entity for admins.
     *
     * @param profileFilterOptions the profile filter options (pagination).
     *                             Use "skip" and "limit" in JSON object request body
     * @return the response entity
     */
    //ToDo add Preauthorize
    @CrossOrigin
    @RequestMapping(value = "/profile/read/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Profile>> listAllProfiles(@RequestBody ProfileFilterOptions profileFilterOptions) {
        List<Profile> profiles = profilesService.findAllProfilesForAdmin(profileFilterOptions);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }


    /**
     * List all profiles response entity for admins in short and light version without unnecessary fields.
     *
     * @param profileFilterOptions the profile filter options (pagination).
     *                             Use "skip" and "limit" in JSON object request body
     * @return the response entity
     */
    //ToDo add Preauthorize
    @CrossOrigin
    @RequestMapping(value = "/profile/read/short/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Profile>> listAllProfilesShort(@RequestBody ProfileFilterOptions profileFilterOptions) {
        List<Profile> profiles = profilesService.findAllProfilesForAdminShort(profileFilterOptions);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    //ToDo add Preauthorize
    @CrossOrigin
    @RequestMapping(value = "/profile/role/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> editAdminRole(@RequestBody Profile profile) {
        profilesService.editProfile(profile);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //ToDo add Preauthorize
    @CrossOrigin
    @RequestMapping(value = "/profile/admin/admin-delete", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> listAllProfiles(@RequestBody String profileId) {
        //additional check before delete profile. Only for administrative staff
        Profile profile = profilesService.findById(profileId);

        if (profile == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        profilesService.deleteProfileById(profileId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
