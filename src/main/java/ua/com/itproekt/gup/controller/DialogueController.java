package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.itproekt.gup.dao.dialogue.DialogueRepository;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.DialogueFilterOption;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.privatemessage.DialogueService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * Created by Fairy on 30.11.2015.
 */
@Controller
public class DialogueController {

    @Autowired
    DialogueService dialogueService;
    @Autowired
    private DialogueRepository dialogueRepository;
    @Autowired
    private ProfilesService profileService;

    //----------------------------------- all dialogue  ------
    @RequestMapping(value = "/dialogues", method = RequestMethod.GET)
    public String getAllDialogues() {
        return "redirect:/dialogues/1";
    }

    @RequestMapping(value = "/dialogues/{page}", method = RequestMethod.GET)
    public String getDialoguesPerPage(Model model, HttpServletRequest request,
                                   @PathVariable("page") Integer page) {
        DialogueFilterOption dialogueFilterOption = new DialogueFilterOption();
        dialogueFilterOption.setLimit(5);
        dialogueFilterOption.setSkip((page - 1) * 5);
        String search = "";

//        try {
//            if (request != null && request.getQueryString() != null && request.getQueryString().contains("&")) {
//                search = URLDecoder.decode(request.getQueryString().split("&")[2].substring(7), "UTF-8");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (!search.equals("")) dialogueFilterOption.setSearchField(search);

        Member member = new Member();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        Profile user = profileService.findProfileByEmail(email);
        member.setId(user.getId());
//        member.setName(user.getUsername());
        List<Dialogue> responseDialogues = dialogueRepository.findByMembersIn(member);
        model.addAttribute("dialogues", responseDialogues);
        System.err.println("dialogues: " + responseDialogues);

        model.addAttribute("pageNumber", page);

        if (!search.equals("")) model.addAttribute("search", search);

        System.err.println("search: " + search);

        System.err.println("URL: " + request.getQueryString());
        return "dialogues1";
    }

    //----------------------------------- one dialogue  ------
    @RequestMapping(value = "/dialogue/id/{id}", method = RequestMethod.GET)
    public String getOneDialogue(Model model, HttpServletRequest request,
                                 @PathVariable("id") String id) {
        // test /dialogue/id/56999921901bc136290bfb25
        model.addAttribute("dialogue", dialogueService.findById(id));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" + dialogueService.findById(id));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!" + id);
        return "dialogue";
    }

    //----------------------------------- one dialogue  ------
    @RequestMapping(value = "/dialogue/create", method = RequestMethod.GET)
    public String createDialogue(Model model) {
        return "dialogue-create";
    }



    @RequestMapping(value = "/dialogue/id/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Dialogue getOneDialogue(@PathVariable("id") String id){

        Dialogue dialogue = dialogueService.findById(id);
        for (Member member : dialogue.getMembers()) {
            Profile profile = profileService.findUserProfile(member.getId());
            if(profile != null && profile.getContact() != null) member.setUserPicId(profile.getContact().getPic());
        }

        return dialogue;
    }

}

