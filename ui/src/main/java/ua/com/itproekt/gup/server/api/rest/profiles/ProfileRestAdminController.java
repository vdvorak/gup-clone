package ua.com.itproekt.gup.server.api.rest.profiles;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Rest controllers for using by administrative module.
 */
@RestController
@RequestMapping("/api/rest/admin")
public class ProfileRestAdminController {

    @Autowired
    private ProfilesService profilesService;


    @CrossOrigin
    @RequestMapping(value = "/search/admin/id/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAdminIdAll() {
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(new Admins(profilesService.getAdminIdAll())), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/search/admin/id/all-by-online", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAdminIdAllByOnline() {
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(new Admins(profilesService.getAdminIdAllByOnline())), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/search/admin/id", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAdminId() {
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(new Admin(profilesService.getAdminId())), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/search/admin/id-by-online", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAdminIdByOnline() {
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(new Admin(profilesService.getAdminIdByOnline())), HttpStatus.OK);
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


    class Admin {
        private String id;

        public Admin(String id){
            this.id = id;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
    }

    class Admins {
        private Set<Admin> ids;

        public Admins(Set<String> ids){
            this.ids = new HashSet<>();
            for (String id: ids)
                this.ids.add(new Admin(id));
        }

        public Set<Admin> getIds() {
            return ids;
        }
        public void setIds(Set<Admin> ids) {
            this.ids = ids;
        }
    }
}
