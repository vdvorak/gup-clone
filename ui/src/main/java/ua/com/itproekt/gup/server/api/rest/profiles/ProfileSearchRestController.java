package ua.com.itproekt.gup.server.api.rest.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class ProfileSearchRestController {

    @Autowired
    private ProfilesService profilesService;

    @RequestMapping("/search/autocomplete/profile")
    public Set<String> getMachedNames(@RequestParam String term) {
        return profilesService.getMatchedNames(term);
    }


    @RequestMapping("/search/autocomplete/profile/ids")
    public List<Profile> getMatchedNamesWithIds(@RequestParam String term) {
        return profilesService.getMatchedNamesWithIds(term);
    }

    @RequestMapping("/search/autocomplete/profile/company")
    public List<String> getMatchedCompany(@RequestParam String term) {
        List<String> result = new ArrayList<>();

        List<Profile> profileList = profilesService.getMatchedCompanies(term);

        for (Profile profile : profileList) {
            result.add(profile.getContact().getCompanyName());
        }
        return result;
    }

    @RequestMapping("/profile/isseowordfree")
    public boolean isProfileSeoWordFree(@RequestParam String seoWord) {
        System.err.println("azaza: " + seoWord);
        return profilesService.isSeoWordFree(seoWord);
    }
}
