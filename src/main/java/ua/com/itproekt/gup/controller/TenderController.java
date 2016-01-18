package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.nace.NaceService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.TenderService;
import ua.com.itproekt.gup.util.EntityPage;

/**
 * Created by qz on 1/12/2016.
 */
@Controller
public class TenderController {

    @Autowired
    TenderService tenderService;

    @Autowired
    ProfilesService profileService;

    @Autowired
    NaceService naceService;

    @Autowired
    StorageService storageService;


    @RequestMapping("/tender")
    public String getTender(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Profile profile = profileService.findProfileByEmail(auth.getName());
        if (profile == null) profile = new Profile();

        TenderFilterOptions tenderFilterOptions = new TenderFilterOptions();

        EntityPage<Tender> tenders = tenderService.findWihOptions(tenderFilterOptions, profile);
        model.addAttribute("tenders", tenders);
        return "tender";
    }

}
