package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.dao.dialogue.DialogueRepository;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.privatemessage.DialogueService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.StaticData;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * Created by Fairy on 28.01.2016.
 */
@Controller
public class SupportDialogueController {

    @Autowired
    DialogueService dialogueService;
    @Autowired
    private DialogueRepository dialogueRepository;
    @Autowired
    private ProfilesService profileService;

    //----------------------------------- all dialogue  ------
    @RequestMapping(value = "/support/dialogues/unassigned", method = RequestMethod.GET)
    public String getAllDialogues() {
        return "redirect:/support/dialogues/unassigned/1";
    }

    @RequestMapping(value = "/support/dialogues/unassigned/{page}", method = RequestMethod.GET)
    public String getDialoguesPerPage(Model model, @PathVariable("page") Integer page) {
        int limit = 5;
        int skip = (page - 1) * 5;
        Member supportAdmin = new Member(StaticData.SUPPORT_DIALOGUES_ADMIN_ID);
        PageRequest pageRequest = new PageRequest(skip, limit, new Sort(Sort.Direction.ASC, "lustMsgTime"));
        List<Dialogue> responseDialogues = dialogueRepository.findByMembersIn(supportAdmin, pageRequest);
        model.addAttribute("dialogues", responseDialogues);
        System.err.println("dialogues: " + responseDialogues);

        model.addAttribute("pageNumber", page);
        return "supportDialogues";
    }

    //----------------------------------- one dialogue  ------
    @RequestMapping(value = "/support/dialogue/id/{id}", method = RequestMethod.GET)
    public String getOneDialogue(Model model, HttpServletRequest request,
                                 @PathVariable("id") String id) {
        Dialogue dialogue = dialogueService.findById(id);
        completeMembers(dialogue);
        model.addAttribute("dialogue", dialogue);
        return "dialogues/dialogue";
    }

    @RequestMapping(value = "/support/dialogue/id/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Dialogue getOneDialogue(@PathVariable("id") String id){
        Dialogue dialogue = dialogueService.findById(id);
        completeMembers(dialogue);
        return dialogue;
    }

    //----------------------------------- one dialogue  ------
    @RequestMapping(value = "/support/dialogue/create", method = RequestMethod.GET)
    public String createDialogue(Model model) {
        return "dialogues/dialogue-create";
    }

    @RequestMapping(value = "/support/dialogue/update", method = RequestMethod.POST)
    @ResponseBody
    public void update( @RequestBody Dialogue dialogue){

        Member member;
        for (int i = 0; i < dialogue.getMembers().size(); i++) {
            member =  dialogue.getMembers().get(i);
            if(!profileService.profileExists(member.getId())){
                Profile profile = profileService.findProfileByEmail(member.getId());
                if(profile!=null){
                    member.setId(profile.getId());
                }else{
                    dialogue.getMembers().remove(member);
                }
            }
        }

        dialogueService.updateDialogue(dialogue);
    }

    private void completeMembers(Dialogue dialogue){
        for (Member member : dialogue.getMembers()) {
            Profile profile = profileService.findById(member.getId());
            if(profile != null && profile.getContact() != null) member.setUserPicId(profile.getContact().getPic());
            if(profile != null && profile.getUsername() != null && profile.getUsername().length() > 1) member.setName(profile.getUsername());
            else member.setName("Anonymous" );
        }
    }

}

