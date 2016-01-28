package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.tender.Tender;
import ua.com.itproekt.gup.model.tender.TenderFilterOptions;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.privatemessage.DialogueService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.tender.TenderService;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.util.*;

/**
 * Created by RAYANT on 20.11.2015.
 */

@Controller
public class AccountController {

    @Autowired
    ProfilesService profilesService;

    @Autowired
    DialogueService dialogueService;

    @Autowired
    TenderService tenderService;
    @Autowired
    ActivityFeedService activityFeedService;

    BankSession session = new BankSession();

    private static Map<String,Integer> storedSMScodes = new HashMap<>();

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/prioffice", method = RequestMethod.GET)
    public String privatOfice(Model model) {
        String loggedUserId = SecurityOperations.getLoggedUserId();
        Profile profile = profilesService.findById(SecurityOperations.getLoggedUserId());
        System.err.println("Деньги пришли: " + session.getUserBalance(loggedUserId));
        model.addAttribute("profile", profile);
        model.addAttribute("balance", session.getUserBalance(loggedUserId));

        List<Dialogue> dialogues = dialogueService.findFirstThreeDialogues(new Member(profile.getId()));
        model.addAttribute("dialogues", dialogues);

        TenderFilterOptions tf = new TenderFilterOptions();
        tf.setAuthorId(profile.getId());
        tf.setLimit(3);
        List<Tender> tenders = tenderService.findWihOptions(tf, profile).getEntities();
        model.addAttribute("tenders", tenders);
        return "prioffice";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String accountFound(Model model) {
        String loggedUserEmail = SecurityOperations.getLoggedUserEmail();
        Profile profile = profilesService.findProfileByEmail(loggedUserEmail);
        model.addAttribute("profile", profile);
        model.addAttribute("balance", session.getUserBalance(loggedUserEmail));
        return "account";
    }

    @RequestMapping(value = "/account/getLiqPayParam", method = RequestMethod.POST)
    @ResponseBody
    public String[] getLiqPayParam(@RequestParam("amount") Long amount) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> result = session.liqPayGenerateParamForHtmlForm(auth.getName(), amount);
        return new String[]{result.get("data"), result.get("signature")};
    }

    @RequestMapping(value = "/account/generate-code", method = RequestMethod.POST)
    @ResponseBody
    public String transfer(@RequestParam("recipient") String recipient, @RequestParam("amountSend") Long amount) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer code = new Random().nextInt(9999);
        Profile profile = profilesService.findProfileByEmail(auth.getName());
        storedSMScodes.put(auth.getName(), code);
        
        return "success";
    }

    @RequestMapping(value = "/account/investment", method = RequestMethod.POST)
    public String invest(@RequestParam("amountInvest") Integer amount) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Integer balance = session.getUserBalance(auth.getName());
        if (amount * 100 <= balance) session.investInOrganization(0, auth.getName(), amount*100, 0, "success");
        return "redirect:";
    }
}
