package ua.com.itproekt.gup.api.rest.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;
import java.util.Set;

@RestController
public class ProfileSearchRestController {

    @Autowired
    ProfilesService profilesService;

    @RequestMapping("/search/autocomplete/profile")
    public Set<String> getMachedNames(@RequestParam String term){
        return profilesService.getMatchedNames(term);
    }


    @RequestMapping("/search/autocomplete/profile/ids")
    public List<Profile> getMatchedNamesWithIds(@RequestParam String term){

        return profilesService.getMatchedNamesWithIds(term);
    }


}
