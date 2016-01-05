package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.TenderService;
import ua.com.itproekt.gup.util.EntityPage;

/**
 * Created by RAYANT on 24.12.2015.
 */
@Controller
public class AdminController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    TenderService tenderService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin")
    public String getLoginForm() {
        return "adminPage";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-users")
    public String getAdminUsers() {
        return "admin-users";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-offers")
    public String getAdminOffers() {
        return "admin-offers";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admin-tenders")
    public String getAdminTenders() {
        return "admin-tenders";
    }


    @RequestMapping("/admin-admins")
    public String getAdmins() {
        return "admin-admins";
    }

    @RequestMapping(value = "/users/getall", method = RequestMethod.POST)
    @ResponseBody
     public EntityPage<Profile> getAllUsers(@RequestParam(name = "skip", required = false, defaultValue = "0") Integer skip,
                                            @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {
        try {
            System.err.println("Voshli в профили");
            ProfileFilterOptions profileFilterOptions = new ProfileFilterOptions();
            profileFilterOptions.setSkip(skip);
            profileFilterOptions.setLimit(limit);
            EntityPage<Profile> profiles = profilesService.findAllProfiles(profileFilterOptions);
            System.err.println("Profiles: " + profiles);
            return profiles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    @RequestMapping(value = "/users/getallTender", method = RequestMethod.POST)
    public EntityPage<Tender> getAllTender(@RequestParam(name = "skip", required = false, defaultValue = "0") Integer skip,
                                           @RequestParam(name = "limit", required = false, defaultValue = "10") Integer limit) {

        System.err.println("Filter options skip: " + skip + "Filter options limit: " + limit);

        try {
            System.err.println("Voshli в тендеры");
            TenderFilterOptions tenderFilterOptions = new TenderFilterOptions();
            tenderFilterOptions.setSkip(skip);
            tenderFilterOptions.setLimit(limit);
            Profile profile = profilesService.findById("565c64e3e7b876fde83b7489");
            EntityPage<Tender> tenders = tenderService.findWihOptions(tenderFilterOptions, profile);
            System.err.println("Tenders: " + tenders);
            return tenders;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
}
