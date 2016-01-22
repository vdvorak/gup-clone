package ua.com.itproekt.gup.api.rest.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import java.util.Set;

/**
 * Created by Zver on 22.01.2016.
 */

@RestController
public class ProfileSearchRestController {

    @Autowired
    ProfilesService profilesService;

    @RequestMapping("/search/profile")
    public Set<String> getMachedNames(@RequestParam String term){
        return profilesService.getMatchedNames(term);
    }
}
