package ua.com.itproekt.gup.controller.tendersAndDoers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.doer.Doer;
import ua.com.itproekt.gup.model.tender.doer.DoerFilterOptions;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.nace.NaceService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.TenderService;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

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

// ToDo Delete in the future
    @RequestMapping("/tenders-OLD")
    public String getAllTenders() {
        return "tendersAndDoers/tenders/tenders";
    }


    // ToDo Delete in the future   // ToDo Delete in the future
    @RequestMapping("/tender-old/{id}")
    public String getOldTender(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "tendersAndDoers/tenders/tender-OLD";
    }



    @RequestMapping("/tender/{id}")
    public String getTender(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "tendersAndDoers/tenders/tender";
    }

    @RequestMapping("/tenders")
    public String getDoersRead() {
        return "tendersAndDoers/doers/tendersAndDoersList";
    }


    @RequestMapping("/tender-make")
    public String thenderMake() {
        return "tendersAndDoers/tenders/tender-make";
    }

    @RequestMapping("/tender/id/{id}/update")
    public String updateTender(@PathVariable String id, Model model) {
        Tender tender = tenderService.findById(id);
        if(SecurityOperations.getLoggedUserId() == null){
            return "redirect:/";
        }
        Profile loggedUser = profileService.findWholeProfileById(SecurityOperations.getLoggedUserId());
        boolean admin = loggedUser.getUserRoles().contains(UserRole.ROLE_ADMIN);
        if(tender == null || (!tender.getAuthorId().equals(loggedUser.getId()) && !admin) ){
            System.out.println("!!!!!!!!!!!!!!!! tender.getAuthorId=" + tender.getAuthorId() + "SecurityOperations.getLoggedUserId() = " + SecurityOperations.getLoggedUserId());
            return "redirect:/tenders";
        }
        model.addAttribute("tender", tender);
        return "tendersAndDoers/tenders/tender-edit";
    }
}
