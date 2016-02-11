package ua.com.itproekt.gup.api.rest.dialogues;

/*
 * Created by Fairy on 27.01.2016.
 */

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.privatemessage.DialogueService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.StaticData;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("api/rest/supportDialogueService")
public class SupportDialogueRestController {
    private Logger log = Logger.getLogger("SUPPORT_DIALOGUE_SERVICE");
    private static final String LOGGED_TITLE = "- SupportDialogueRestController - api/rest/supportDialogueService/";

    @Autowired
    DialogueService dialogueService;
    @Autowired
    ProfilesService profileService;

    @RequestMapping(
            value = "dialogue/create",
            method = RequestMethod.POST,
            headers = "Content-Type=application/json")
    public ResponseEntity<Dialogue> addDialogue(@RequestBody Dialogue dialogue, HttpServletRequest req) {
        Profile support = profileService.findByIdWholeProfile(StaticData.SUPPORT_DIALOGUES_ADMIN_ID);
        if (support == null) {
            profileService.createProfile(StaticData.SUPPORT_DIALOGUE_ADMIN);
            support = StaticData.SUPPORT_DIALOGUE_ADMIN;
        }
        dialogue.setMembers(new ArrayList<>());
        dialogue.getMembers().add(new Member(support.getId()));
        Member thisUser = new Member(SecurityOperations.getLoggedUserId());
        if (thisUser.getId() != null) {
            dialogue.getMembers().add(thisUser);
            dialogueService.completeMessage(dialogue.getMessages().get(0), thisUser.getId());
        }

        Dialogue d = dialogueService.addDialogue(dialogue);
        if (thisUser.getId() == null) {
            req.getSession().setAttribute("supportDialogueId", d.getId());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    // this method provide getting all message from existing dialogue.
    @RequestMapping(value = "/dialogue/id/{dialogueId}/assign",
            method = RequestMethod.POST)
    public ResponseEntity<Dialogue> getMessagesForDialogue(@PathVariable("dialogueId") String dialogueId) {
        Dialogue dialogue = dialogueService.findById(dialogueId);
        Profile support = profileService.findByIdWholeProfile(SecurityOperations.getLoggedUserId());
        if(support == null){
            log.log(Level.ERROR, LOGGED_TITLE + "/dialogue/id/{dialogueId}/assign - not logged user try to assign dialogue");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(!(support.getUserRoles().contains(UserRole.ROLE_ADMIN) || support.getUserRoles().contains(UserRole.ROLE_SUPPORT))){
            log.log(Level.ERROR, LOGGED_TITLE + "/dialogue/id/{dialogueId}/assign - not admin id=" + support.getId()
                    + " try to assign dialogue");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        log.log(Level.INFO, LOGGED_TITLE + "/dialogue/id/{dialogueId}/assign - dialogue was find successfully");

        if(dialogue.getMembers().removeIf(m -> m.getId().equals(StaticData.SUPPORT_DIALOGUES_ADMIN_ID))) {
            dialogue.getMembers().add(new Member(support.getId()));
            log.log(Level.INFO, LOGGED_TITLE + "/dialogue/id/{dialogueId}/assign - dialogue was assign by support id = "
                    + SecurityOperations.getLoggedUserId()  + " successfully");
            dialogueService.updateDialogue(dialogue);
        }
        return new ResponseEntity<>(dialogue, HttpStatus.OK);
    }

    // this method provide getting all dialogs for current user.
    @RequestMapping(value = "/dialogue/read/all",
            method = RequestMethod.POST)
    public ResponseEntity<List<Dialogue>> getAllDialogues() {
        List<Dialogue> dialogues = dialogueService.findDialogsForUserAndUpdateUnread(StaticData.SUPPORT_DIALOGUES_ADMIN_ID);
        for (Dialogue d : dialogues) {
            dialogueService.completeMembers(d);
        }
        log.log(Level.INFO, LOGGED_TITLE + "/dialogue/read/all - all dialogues was find successfully for user id=" + SecurityOperations.getLoggedUserId());
        return new ResponseEntity<>(dialogues, HttpStatus.OK);
    }
}
