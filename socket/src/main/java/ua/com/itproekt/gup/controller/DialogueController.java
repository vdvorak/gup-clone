package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.dao.dialogue.DialogueRepository;
//import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.DialogueFilterOption;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.privatemessage.DialogueService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.EntityPage;
import ua.com.itproekt.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        model.addAttribute("userid", user.getId());
        member.setId(user.getId());
//        member.setName(user.getUsername());
        List<Dialogue> responseDialogues = dialogueRepository.findByMembersIn(member);
        model.addAttribute("dialogues", responseDialogues);
        System.err.println("dialogues: " + responseDialogues);

        model.addAttribute("pageNumber", page);

        if (!search.equals("")) model.addAttribute("search", search);

        System.err.println("search: " + search);

        System.err.println("URL: " + request.getQueryString());
        return "dialogues/dialogues1";
    }

    // additional method by VS
    @RequestMapping(value = "/init_dialogues/all", method = RequestMethod.GET)
    @ResponseBody
    List<Dialogue> getAllDialogues2(){
        Member member = new Member();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();          //get logged in username
        Profile user = profileService.findProfileByEmail(email);
        member.setId(user.getId());
        List<Dialogue> responseDialogues = dialogueRepository.findByMembersIn(member);
        for(int i=0; i < responseDialogues.size(); i++){
            List<Member> members = responseDialogues.get(i).getMembers();
            for(int j =0; j < members.size(); j++){
                Profile profile = profileService.findById(members.get(j).getId());
                responseDialogues.get(i).getMembers().get(j).setName(profile.getUsername());
                responseDialogues.get(i).getMembers().get(j).setUserPicId(profile.getImgId());
            }
        }
        return responseDialogues;
    }

    //----------------------------------- one dialogue  ------
    @RequestMapping(value = "/dialogue/id/{id}", method = RequestMethod.GET)
    public String getOneDialogue(Model model, HttpServletRequest request,
                                 @PathVariable("id") String id) {
        Dialogue dialogue = dialogueService.findById(id);
        if(!dialogueService.isUserInDialogue(dialogue, SecurityOperations.getLoggedUserId())){
            return "redirect:/dialogues/1";
        }
        completeMembers(dialogue);
        model.addAttribute("dialogue", dialogue);
        return "dialogues/dialogue";
    }

    @RequestMapping(value = "/dialogue/id/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Dialogue getOneDialogue(@PathVariable("id") String id){
        Dialogue dialogue = dialogueService.findById(id);
        if(!dialogueService.isUserInDialogue(dialogue, SecurityOperations.getLoggedUserId())){
            return null;
        }
        completeMembers(dialogue);
        return dialogue;
    }

    //----------------------------------- one dialogue  ------
    @RequestMapping(value = "/dialogue/create", method = RequestMethod.GET)
    public String createDialogue(Model model) {
        return "dialogues/dialogue-create";
    }

    //----------------------------------- one dialogue  ------
    @RequestMapping(value = "/dialogue/create/with/{userId}", method = RequestMethod.GET)
    public String createDialogueWith(@PathVariable String userId, Model model) {
        Profile profile = profileService.findWholeProfileById(userId);
        if(profile == null || SecurityOperations.getLoggedUserId() == null){
            return "";
        }
        List<Member> members = new ArrayList<>();
        members.add(new Member(userId));
        members.add(new Member(SecurityOperations.getLoggedUserId()));
        List<Dialogue> result = dialogueRepository.findByMembers(members);
        if(result.isEmpty()){
            Dialogue dialogue = new Dialogue();
            dialogue.setMembers(members);
            dialogue = dialogueService.addDialogue(dialogue);
            return "redirect:/dialogue/id/" + dialogue.getId();
        }
        Dialogue dialogue = result.stream().filter(m -> m.getSubject() == null).findFirst().get();
        model.addAttribute("dialogue", dialogue);
        return "redirect:/dialogue/id/" + dialogue.getId();
    }

    @RequestMapping(value = "/dialogue/update", method = RequestMethod.POST)
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
            if(profile != null) member.setUserPicId(profile.getImgId());
            if(profile != null && profile.getUsername() != null && profile.getUsername().length() > 1) member.setName(profile.getUsername());
            else member.setName("Anonymous" );
        }
    }

    //additional method by VS
    @RequestMapping(value = "/dialogue/init/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public Dialogue initDialog (@PathVariable String userId, Model model) {
        Dialogue dialogue = new Dialogue();
        Profile profile = profileService.findWholeProfileById(userId);
        if (profile == null || SecurityOperations.getLoggedUserId() == null) {
            return null;
        }


        /*
        List<Member> members = new ArrayList<>();
        members.add(new Member(userId));
        members.add(new Member(SecurityOperations.getLoggedUserId()));
        List<Dialogue> result = dialogueRepository.findByMembers(members);
        */

        //can not find findByMembers method implementation. Old implementation should be fixed. solution below will not work for more than 2 users in a dialogue
        List<Member> members1 = new ArrayList<>();
        List<Member> members2 = new ArrayList<>();
        members1.add(new Member(userId));
        members1.add(new Member(SecurityOperations.getLoggedUserId()));
        members2.add(new Member(SecurityOperations.getLoggedUserId()));
        members2.add(new Member(userId));

        List<Dialogue> result1 = dialogueRepository.findByMembers(members1);
        List<Dialogue> result2 = dialogueRepository.findByMembers(members2);

        if (result1.isEmpty() && result2.isEmpty()) {
            dialogue.setMembers(members1);
            dialogue = dialogueService.addDialogue(dialogue);
        } else {
            if(result1.isEmpty()){
                dialogue = result2.stream().filter(m -> m.getSubject() == null).findFirst().get();
            }else if(result2.isEmpty()){
                dialogue = result1.stream().filter(m -> m.getSubject() == null).findFirst().get();
            }
        }

        if(!dialogueService.isUserInDialogue(dialogue, SecurityOperations.getLoggedUserId())){
            return null;
        }
        completeMembers(dialogue);
        return dialogue;
    }
}

