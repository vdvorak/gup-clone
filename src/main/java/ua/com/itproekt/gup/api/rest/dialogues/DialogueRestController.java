package ua.com.itproekt.gup.api.rest.dialogues;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.privatemessages.PrivateMessage;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.privatemessage.DialogueService;
import ua.com.itproekt.gup.service.profile.ProfilesService;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/rest/dialogueService")
public class DialogueRestController {
    private Logger log = Logger.getLogger("dialogueService");
    private static final String LOGGED_TITLE = "- DialogueRestController - api/rest/dialogueService/";

    @Autowired
    DialogueService dialogueService;

    @Autowired
    ProfilesService profileService;

    // This method provide adding new dialogue
    // Or adding new massage for existing dialogue. In this case dialogue will be spots by list of members and subject.
    @RequestMapping(
            value = "dialogue/create",
            method = RequestMethod.POST,
            headers = "Content-Type=application/json")
    public ResponseEntity<Dialogue> addDialogue (@RequestBody Dialogue dialogue,
                                                UriComponentsBuilder builder) {
        log.log(Level.INFO, LOGGED_TITLE + "dialogue/create Hello =)");
        // To create new it must content one message and at least one member.
        HttpHeaders headers = null;
        try {
            if(dialogue.getMembers() == null || dialogue.getMembers().isEmpty()
                    || dialogue.getMessages() == null || dialogue.getMessages().isEmpty()){
                log.log(Level.ERROR, LOGGED_TITLE + "dialogue/create - bad json was sent");
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
            // hear we always expect exactly one massage.
            // If we work with new dialogue it will be first message,
            // if we work with existing dialogue, message will be added to message list of this dialogue.
            PrivateMessage msg = dialogueService.completeMessage(dialogue.getMessages().get(0), getCurrentUserId());

            addLoggedUserToMembers(dialogue);

            //asking if current dialog already exist
            Dialogue d = dialogueService.findByMembersAndSubject(dialogue);

            if(d != null) {
                d.getMessages().add(msg);
                dialogue = dialogueService.addDialogue(d);
                log.log(Level.INFO, LOGGED_TITLE + "dialogue/create - dialogue was find and update with new massage ");
            } else {
                List<PrivateMessage> messages = new ArrayList<>();
                messages.add(msg);
                dialogue.setMessages(messages);
                dialogue = dialogueService.addDialogue(dialogue);
                log.log(Level.INFO, LOGGED_TITLE + "dialogue/create - dialogue was created successfully");
            }

            headers = new HttpHeaders();
            headers.setLocation(
                    builder.path("api/rest/dialogueService/dialogue/read/id/{id}")
                            .buildAndExpand(dialogue.getId()).toUri());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    // this method provide adding new message to existing dialogue. Id in path is a dialogue id.
    @RequestMapping(
            value = "dialogue/id/{id}/message/create",
            method = RequestMethod.POST,
            headers = "Content-Type=application/json")
    public ResponseEntity<Dialogue> addMessage (@PathVariable String id, @RequestBody PrivateMessage message,
                                                 UriComponentsBuilder builder) {
        log.log(Level.INFO, LOGGED_TITLE + "message/create Hello =)");
        Dialogue dialogue = dialogueService.findById(id);
        if (dialogue == null || dialogue.getId() == null) {
            log.log(Level.ERROR, LOGGED_TITLE + "dialogue/id/{id}/message/create - NO SUCH DIALOGUE, id = " + id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        // check if current user have access to existing dialogue
        if(!dialogueService.isUserInDialogue(dialogue, getCurrentUserId())){
            log.log(Level.ERROR, LOGGED_TITLE + "dialogue/id/{id}/message/create - user id=" + getCurrentUserId()
                    + " who is NOT IN LIST OF MEMBERS try to add new massage to dialogue id= " + id);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        message = dialogueService.completeMessage(message, getCurrentUserId());
        dialogue.getMessages().add(message);
        dialogue = dialogueService.updateDialogueWhenAddMsg(dialogue);

        log.log(Level.INFO, LOGGED_TITLE + "dialogue/id/{id}/message/create - new message was successfully add to dialogue");
        return new ResponseEntity<>(dialogue, HttpStatus.CREATED);
    }

    // this method provide getting all message from existing dialogue.
    @RequestMapping(value="/dialogue/read/id/{id}",
            method=RequestMethod.POST)
    public ResponseEntity<Dialogue> getMessagesForDialogue(@PathVariable("id") Dialogue dialogue) {
        log.log(Level.INFO, LOGGED_TITLE + "dialogue/read/id/{id} Hello =)");
        if (dialogue == null) {
            log.log(Level.ERROR, LOGGED_TITLE + "dialogue/read/id/{id} - NO SUCH DIALOGUE");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // check if current user have access to existing dialogue
        if(!dialogueService.isUserInDialogue(dialogue, getCurrentUserId())){
            log.log(Level.ERROR, LOGGED_TITLE + "/dialogue/read/id/{id} - user id=" + getCurrentUserId()
                    + " who is NOT IN LIST OF MEMBERS try to read dialogue id=" + dialogue.getId());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        dialogueService.completeMembers(dialogue);
        log.log(Level.INFO, LOGGED_TITLE + "/dialogue/read/id/{id} - dialogue was find successfully");
        return new ResponseEntity<>(dialogue, HttpStatus.OK);
    }

    //
    @RequestMapping(value="/unread-msg/for-user-id/{id}",
            method=RequestMethod.POST)
    public String getUnreadMessagesForUser(@PathVariable("id") String userId) {
        System.out.println("---------------------------in /unread-msg/for-user-id/{id} ---------------");
        List<Dialogue> dialogues = dialogueService.findDialogues(new Member(userId));
        String result = "";
        if(dialogues == null){
            return result;
        }

        Map<String, PrivateMessage> msgs = new HashMap<>();
        dialogues.stream().filter(d -> (d.getUnreadMsgCounter().get(userId) > 0))
                .forEach(dialogue -> {
                    //find last msg (with latest date);
                    PrivateMessage msg = dialogue.getMessages().
                            stream().
                            filter(m -> m.getDate().equals(dialogue.getLustMsgTime())).
                            findFirst().get();

                    //Look out! GOVNOCOD
                    //Change AuthorId in messages to UserPicId
                    Profile p = profileService.findUserProfile(msg.getAuthorId());
                    if(p != null && p.getContact() != null && p.getContact().getPic() != null){
                        msg.setAuthorId(p.getContact().getPic());
                    }else {
                        msg.setAuthorId("");
                    }

                    //put into map
                    msgs.put(dialogue.getId(), msg);
                });



        ObjectMapper mapper = new ObjectMapper();

        try {
            result = mapper.writeValueAsString(msgs);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    // this method provide getting all dialogs for current user.
    @RequestMapping(value="/dialogue/read/all",
            method=RequestMethod.POST
            )
    public ResponseEntity<List<Dialogue>> getAllDialogues(){
        List<Dialogue> dialogues = dialogueService.findDialogsForUserAndUpdateUnread(getCurrentUserId());
        for(Dialogue d: dialogues){
            dialogueService.completeMembers(d);
        }
        log.log(Level.INFO, LOGGED_TITLE + "/dialogue/read/all - all dialogues was find successfully for user id=" + getCurrentUserId());
        return new ResponseEntity<>(dialogues, HttpStatus.OK);
    }

    // this method providing to updateInvestor dialog.
    @RequestMapping(value="/dialogue/update/id/{id}",
            method=RequestMethod.POST
    )
    public ResponseEntity<Dialogue> updateDialogue(@PathVariable("id") String id, @RequestBody Dialogue dialogue){
        if(dialogue == null || dialogueService.findById(id) == null){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(dialogue.getId()== null) {
            dialogue.setId(id);
        }
        //check if current user in dialogue.
        if(!dialogueService.isUserInDialogue(dialogue,getCurrentUserId())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        dialogue = dialogueService.updateDialogue(dialogue);
        return new ResponseEntity<>(dialogue, HttpStatus.OK);
    }

    @InitBinder
    protected void dialogueBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Dialogue.class,
                new PropertyEditorSupport() {
                    @Override
                    public void setAsText(String id) {
                        Dialogue dialogue = null;
                        if (id != null && !id.trim().isEmpty()) {
                            dialogue = dialogueService.findById(id);
                        }else {
                            log.log(Level.ERROR, "CAN'T FOUND DIALOGUE WITH id = " + id);
                        }
                        setValue(dialogue);
                    }
                }
        );
    }

    private String getCurrentUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        return profileService.findProfileByEmail(email).getId();
    }

    private void addLoggedUserToMembers(Dialogue dialogue){
        boolean alreadyIn = false;
        for(Member m : dialogue.getMembers()){
            if(getCurrentUserId().equals(m.getId())) {
                alreadyIn = true;
            }
        }
        if(!alreadyIn) {
            Member member = new Member();
            member.setId(getCurrentUserId());
            dialogue.getMembers().add(member);
        }
    }
}