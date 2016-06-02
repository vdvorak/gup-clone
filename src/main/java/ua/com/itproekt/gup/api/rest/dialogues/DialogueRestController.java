package ua.com.itproekt.gup.api.rest.dialogues;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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
import ua.com.itproekt.gup.util.SecurityOperations;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

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
    public ResponseEntity<Dialogue> addDialogue(@RequestBody Dialogue dialogue,
                                                UriComponentsBuilder builder) {
        log.log(Level.INFO, LOGGED_TITLE + "dialogue/create Hello =)");
        // To create new it must content one message and at least one member.
        HttpHeaders headers = null;
        try {
            if (dialogue.getMembers() == null || dialogue.getMembers().isEmpty()
                    || dialogue.getMessages() == null || dialogue.getMessages().isEmpty()) {
                log.log(Level.ERROR, LOGGED_TITLE + "dialogue/create - bad json was sent");
                return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }

            addLoggedUserToMembers(dialogue);

            //asking if current dialog already exist
            Dialogue d = dialogueService.findByMembersAndSubject(dialogue);

            if (d != null && dialogue.getMessages() != null && !dialogue.getMessages().isEmpty()) {
                PrivateMessage msg = dialogueService.completeMessage(dialogue.getMessages().get(0), getCurrentUserId());
                d.addMessage(msg);
                dialogue = dialogueService.addDialogue(d);
                log.log(Level.INFO, LOGGED_TITLE + "dialogue/create - dialogue was find and update with new massage ");
            } else {
                dialogue.setMessages(new ArrayList<>());
                if (dialogue.getMessages() != null && !dialogue.getMessages().isEmpty()) {
                    PrivateMessage msg = dialogueService.completeMessage(dialogue.getMessages().get(0), getCurrentUserId());
                    dialogue.addMessage(msg);
                }
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

    // this method provides adding new message to existing dialogue. Id in path is a dialogue id.
    @RequestMapping(
            value = "dialogue/id/{id}/message/create",
            method = RequestMethod.POST,
            headers = "Content-Type=application/json")
    public ResponseEntity<Dialogue> addMessage(@PathVariable String id, @RequestBody PrivateMessage message,
                                               UriComponentsBuilder builder) {
        log.log(Level.INFO, LOGGED_TITLE + "message/create Hello =)");
        Dialogue dialogue = dialogueService.findById(id);
        if (dialogue == null || dialogue.getId() == null) {
            log.log(Level.ERROR, LOGGED_TITLE + "dialogue/id/{id}/message/create - NO SUCH DIALOGUE, id = " + id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        // check if current user have access to existing dialogue
        if (!dialogueService.isUserInDialogue(dialogue, getCurrentUserId())) {
            log.log(Level.ERROR, LOGGED_TITLE + "dialogue/id/{id}/message/create - user id=" + getCurrentUserId()
                    + " who is NOT IN LIST OF MEMBERS try to add new massage to dialogue id= " + id);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        message = dialogueService.completeMessage(message, getCurrentUserId());
        dialogue.addMessage(message);
        dialogue = dialogueService.updateDialogueWhenAddMsg(dialogue);

        log.log(Level.INFO, LOGGED_TITLE + "dialogue/id/{id}/message/create - new message was successfully add to dialogue");
        return new ResponseEntity<>(dialogue, HttpStatus.CREATED);
    }

    // this method provide getting all message from existing dialogue.
    @RequestMapping(value = "/dialogue/read/id/{id}",
            method = RequestMethod.POST)
    public ResponseEntity<Dialogue> getMessagesForDialogue(@PathVariable("id") Dialogue dialogue) {
        log.log(Level.INFO, LOGGED_TITLE + "dialogue/read/id/{id} Hello =)");
        if (dialogue == null) {
            log.log(Level.ERROR, LOGGED_TITLE + "dialogue/read/id/{id} - NO SUCH DIALOGUE");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // check if current user have access to existing dialogue
        if (!dialogueService.isUserInDialogue(dialogue, getCurrentUserId())) {
            log.log(Level.ERROR, LOGGED_TITLE + "/dialogue/read/id/{id} - user id=" + getCurrentUserId()
                    + " who is NOT IN LIST OF MEMBERS try to read dialogue id=" + dialogue.getId());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        dialogueService.completeMembers(dialogue);
        log.log(Level.INFO, LOGGED_TITLE + "/dialogue/read/id/{id} - dialogue was find successfully");
        return new ResponseEntity<>(dialogue, HttpStatus.OK);
    }

    // this method provide getting all message from existing dialogue.
    @RequestMapping(value = "/dialogue/updateRead/{id}",
            method = RequestMethod.POST)
    public void makeDialogueRead(@PathVariable("id") Dialogue dialogue, Principal p) {
        /* // something already set to 0 before running code below
        String userId = SecurityOperations.getLoggedUserId();
        Profile user = profileService.findWholeProfileById(userId);
        user.setUnreadMessages(0);*/
    }

    //
    @RequestMapping(value = "/unread-msg/for-user-id/{id}",
            method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
    public String getUnreadMessagesForUser(@PathVariable("id") String userId) {
        List<Dialogue> dialogues = dialogueService.findDialogsForUserSimple(userId);
        String result = "";
        if (dialogues == null) {
            return result;
        }
        Map<String, PrivateMessage> msgs = new HashMap<>();

//        for (Dialogue d : dialogues) {
//            for (PrivateMessage pm : d.getMessages()) {
//                boolean isUnread = true;
//                for (String reader : pm.getWhoRead()) {
//                    if (reader.equals(userId)) {
//                        isUnread = false;
//                    }
//                    if (isUnread) {
//                        String imgId = profileService.findById(pm.getAuthorId()).getImgId();
//                        pm.setAuthorId(imgId);
//                        pm.getWhoRead().remove(imgId);
//                        msgs.put(d.getId(), pm);
//                    }
//                }
//            }
//        }



        dialogues.stream().filter(d -> (d.getUnreadMsgCounter().get(userId) > 0))
                .forEach(dialogue -> {
                    //find last msg (with latest date);
                    PrivateMessage msg = dialogue.getMessages().
                            stream().
                            filter(m -> m.getDate().equals(dialogue.getLustMsgTime())).
                            findFirst().get();

                    //Look out! GOVNOCOD
                    //Change AuthorId in messages to UserPicId
                    Profile p = profileService.findById(msg.getAuthorId());
                    if (p != null && p.getImgId() != null) {
                        msg.setAuthorId(p.getImgId());
                        msg.getWhoRead().remove(p.getImgId());
                    } else {
                        msg.setAuthorId("noImg");
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
    @RequestMapping(value = "/dialogue/read/all",
            method = RequestMethod.POST
    )
    public ResponseEntity<List<Dialogue>> getAllDialogues() {
        List<Dialogue> dialogues = dialogueService.findDialogsForUserSimple(getCurrentUserId());
        for (Dialogue d : dialogues) {
            dialogueService.completeMembers(d);
        }
        log.log(Level.INFO, LOGGED_TITLE + "/dialogue/read/all - all dialogues was find successfully for user id=" + getCurrentUserId());
        return new ResponseEntity<>(dialogues, HttpStatus.OK);
    }

    // this method providing to updateInvestor dialog.
    @RequestMapping(value = "/dialogue/update/id/{id}",
            method = RequestMethod.POST
    )
    public ResponseEntity<Dialogue> updateDialogue(@PathVariable("id") String id, @RequestBody Dialogue dialogue) {
        if (dialogue == null || dialogueService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (dialogue.getId() == null) {
            dialogue.setId(id);
        }
        //check if current user in dialogue.
        if (!dialogueService.isUserInDialogue(dialogue, getCurrentUserId())) {
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
                        } else {
                            log.log(Level.ERROR, "CAN'T FOUND DIALOGUE WITH id = " + id);
                        }
                        setValue(dialogue);
                    }
                }
        );
    }

    private String getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName(); //get logged in username
        return profileService.findProfileByEmail(email).getId();
    }

    private void addLoggedUserToMembers(Dialogue dialogue) {
        boolean alreadyIn = false;
        for (Member m : dialogue.getMembers()) {
            if (getCurrentUserId().equals(m.getId())) {
                alreadyIn = true;
            }
        }
        if (!alreadyIn) {
            Member member = new Member();
            member.setId(getCurrentUserId());
            dialogue.getMembers().add(member);
        }
    }

    @MessageMapping("/socket-request/dialogue/{dialogId}")
    @SendTo("/topic/socket-response/{dialogId}")
    public SocketResponse response(@DestinationVariable String dialogId, SocketMessage socketmessage, Principal p) throws Exception {
        Profile profile = profileService.findProfileByEmail(p.getName());
        String userId = profile.getId();

        log.log(Level.INFO, LOGGED_TITLE + "message/create Hello =)");
        Dialogue dialogue = dialogueService.findByIdProfile(dialogId, profile);
        /*Dialogue dialogue = dialogueService.findById(dialogId);*/
        if (dialogue == null || dialogue.getId() == null) {
            log.log(Level.ERROR, LOGGED_TITLE + "NO SUCH DIALOGUE, id = " + dialogId);
        }

        // check if current user have access to existing dialogue
        if (!dialogueService.isUserInDialogue(dialogue, userId)) {
            log.log(Level.ERROR, LOGGED_TITLE + " user id=" + userId
                    + " who is NOT IN LIST OF MEMBERS try to add new massage to dialogue id= " + dialogId);
        }

        PrivateMessage privateMessage = new PrivateMessage(socketmessage.getMessage(), userId);
        dialogue.addMessage(privateMessage);
        dialogueService.updateDialogueWhenAddMsgProfile(dialogue, profile);
        log.log(Level.INFO, LOGGED_TITLE + " - new message was successfully add to dialogue");

        return new SocketResponse(privateMessage);
    }

    @MessageMapping("/socket-request/profile/{profileId}")
    @SendTo("/topic/socket-response/{profileId}")
    public SocketResponse subscriptProfile(@DestinationVariable String profileId, SocketMessage socketmessage, Principal p) throws Exception {
        Dialogue dialogue = dialogueService.findById(socketmessage.getMessage(), p.getName());
        for(int j =0; j < dialogue.getMembers().size(); j++){
            Profile profile = profileService.findById(dialogue.getMembers().get(j).getId());
            dialogue.getMembers().get(j).setName(profile.getUsername());
            dialogue.getMembers().get(j).setUserPicId(profile.getImgId());
        }
        return new SocketResponse(dialogue);
    }
}