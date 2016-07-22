//package ua.com.itproekt.gup.controller;
//
//import org.codehaus.jackson.map.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import ua.com.itproekt.gup.bank_api.BankSession;
//import ua.com.itproekt.gup.bank_api.entity.ExternalTransaction;
//import ua.com.itproekt.gup.model.activityfeed.Event;
//import ua.com.itproekt.gup.model.activityfeed.EventFilterOptions;
//import ua.com.itproekt.gup.model.offer.Offer;
//import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
//import ua.com.itproekt.gup.model.privatemessages.Dialogue;
//import ua.com.itproekt.gup.model.privatemessages.Member;
//import ua.com.itproekt.gup.model.profiles.Profile;
//import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
//import ua.com.itproekt.gup.service.offers.OffersService;
//import ua.com.itproekt.gup.service.privatemessage.DialogueService;
//import ua.com.itproekt.gup.service.profile.ProfilesService;
//import ua.com.itproekt.gup.util.EntityPage;
//import ua.com.itproekt.gup.util.SecurityOperations;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//
//@Controller
//public class AccountController {
//
//    @Autowired
//    ProfilesService profilesService;
//
//    @Autowired
//    DialogueService dialogueService;
//
//
//
//
//    @Autowired
//    ActivityFeedService activityFeedService;
//
////    @Autowired
////    BlogPostService blogPostService;
//
//    @Autowired
//    OffersService offersService;
//
//    BankSession session = new BankSession();
//
//    private static Map<String,Integer> storedSMScodes = new HashMap<>();
//
//    final int contentEntitiesLimit = 5;
//    final int eventEntitiesLimit = 50;
//
//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value = "/prioffice", method = RequestMethod.GET)
//    public String privatOfice(Model model) {
//        String loggedUserId = SecurityOperations.getLoggedUserId();
//        Profile profile = profilesService.findWholeProfileById(SecurityOperations.getLoggedUserId());
//
//        String authId = profile.getId();
//
//        model.addAttribute("profile", profile);
//        model.addAttribute("curentBalance", session.getUserBalance(authId));
//
//
//        List<Dialogue> dialogues = dialogueService.findFirstThreeDialogues(new Member(profile.getId()));
//        model.addAttribute("dialogues", dialogues);
//
//        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();
//        offerFilterOptions.setAuthorId(authId);
//        offerFilterOptions.setLimit(contentEntitiesLimit);
//        offerFilterOptions.setCreatedDateSortDirection("DESC");
//        List<Offer> offers = offersService.findOffersWihOptions(offerFilterOptions).getEntities();
//        model.addAttribute("offers", offers);
//
//        EventFilterOptions ef = new EventFilterOptions();
//        ef.setLimit(eventEntitiesLimit);
//        ef.setSkip(0);
//        ef.setTargetUId(profile.getId());
//        EntityPage<Event> events = activityFeedService.findEventsWithOptions(ef);
//        model.addAttribute("events", events.getEntities());
//
//
//        String balanceStr = session.getExternalTransactionsByUserId(authId);
//        ObjectMapper mapper = new ObjectMapper();
//        List<ExternalTransaction> balance = null;
//        //JSON from URL to Object
//        try {
//            balance = mapper.readValue(balanceStr, List.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        model.addAttribute("balance", balance);
//
//        return "prioffice";
//    }
//
//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value = "/account", method = RequestMethod.GET)
//    public String accountFound(Model model) {
//        String loggedUserEmail = SecurityOperations.getLoggedUserEmail();
//        Profile profile = profilesService.findProfileByEmail(loggedUserEmail);
//        model.addAttribute("profile", profile);
//        model.addAttribute("balance", session.getUserBalance(loggedUserEmail));
//        return "account";
//    }
//
//    @RequestMapping(value = "/account/getLiqPayParam", method = RequestMethod.POST)
//    @ResponseBody
//    public String[] getLiqPayParam(@RequestParam("amount") Long amount) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Map<String, String> result = session.liqPayGenerateParamForHtmlForm(profilesService.findProfileByEmail(auth.getName()).getId(), amount);
//        return new String[]{result.get("data"), result.get("signature")};
//    }
//
//    @RequestMapping(value = "/account/generate-code", method = RequestMethod.POST)
//    @ResponseBody
//    public String transfer(@RequestParam("recipient") String recipient, @RequestParam("amountSend") Long amount) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Integer code = new Random().nextInt(9999);
//        Profile profile = profilesService.findProfileByEmail(auth.getName());
//        storedSMScodes.put(auth.getName(), code);
//
//        return "success";
//    }
//
//    @RequestMapping(value = "/account/investment", method = RequestMethod.POST)
//    public String invest(@RequestParam("amountInvest") Integer amount) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Integer balance = session.getUserBalance(auth.getName());
//        if (amount * 100 <= balance) session.investInOrganization(0, auth.getName(), amount*100, 0, "success");
//        return "redirect:";
//    }
//}
