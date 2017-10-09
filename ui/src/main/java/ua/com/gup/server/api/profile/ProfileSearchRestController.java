package ua.com.gup.server.api.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.domain.profile.Profile;
import ua.com.gup.service.profile.ProfilesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class ProfileSearchRestController {

    @Autowired
    private ProfilesService profilesService;

    @CrossOrigin
    @RequestMapping("/search/autocomplete/profile")
    public Set<String> getMachedNames(@RequestParam String term) {
        return profilesService.getMatchedNames(term);
    }


    @CrossOrigin
    @RequestMapping("/search/autocomplete/profile/ids")
    public List<Profile> getMatchedNamesWithIds(@RequestParam String term) {
        return profilesService.getMatchedNamesWithIds(term);
    }

    @CrossOrigin
    @RequestMapping("/search/autocomplete/profile/company")
    public List<String> getMatchedCompany(@RequestParam String term) {
        List<String> result = new ArrayList<>();

        List<Profile> profileList = profilesService.getMatchedCompanies(term);

        for (Profile profile : profileList) {
            result.add(profile.getContact().getCompanyName());
        }
        return result;
    }

    @CrossOrigin
    @RequestMapping("/profile/isseowordfree")
    public boolean isProfileSeoWordFree(@RequestParam String seoWord) {
        System.err.println("azaza: " + seoWord);
        return profilesService.isSeoWordFree(seoWord);
    }
}
