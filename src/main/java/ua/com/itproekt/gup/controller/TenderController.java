package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.nace.NaceService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.TenderService;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.security.Security;

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


    @RequestMapping("/tenders")
    public String getAllTenders(Model model) {
        return "tenders";
    }

    @RequestMapping("/tender/{id}")
    public String getTender(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "tender";
    }

    @RequestMapping("/tender-make")
    public String thenderMake() {
        return "tender-make";
    }

    @RequestMapping("/tender/id/{id}/update")
    public String updateTender(@PathVariable String id, Model model) {
        Tender tender = tenderService.findById(id);
        if(SecurityOperations.getLoggedUserId() == null){
            return "redirect:/";
        }
        if(tender == null || !tender.getAuthorId().equals(SecurityOperations.getLoggedUserId())){
            System.out.println("!!!!!!!!!!!!!!!! tender.getAuthorId=" + tender.getAuthorId() + "SecurityOperations.getLoggedUserId() = " + SecurityOperations.getLoggedUserId());
            return "redirect:/tenders";
        }
        model.addAttribute("tender", tender);
        return "tender-edit";
    }
}
