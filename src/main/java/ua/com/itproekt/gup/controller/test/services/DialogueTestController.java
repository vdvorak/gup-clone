package ua.com.itproekt.gup.controller.test.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.itproekt.gup.dao.dialogue.DialogueRepository;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.privatemessages.PrivateMessage;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/*
 * Created by Fairy on 16.01.2016.
 */
@Controller
public class DialogueTestController {
    @Autowired
    DialogueRepository dialogueRepository;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    ProfileRepository profileRepository;

    @RequestMapping("/addDialogue/{numberOfDialogues}")
    public String addDoer(@PathVariable("numberOfDialogues") int numberOfDialogues, Model model) {
//        ProfileFilterOptions pf = new ProfileFilterOptions();
//        pf.setLimit(20);
//        pf.setSkip(0);
        try {
            List<Profile> users = profileRepository.findAllProfiles(new ProfileFilterOptions(0, 200)).getEntities();
            System.out.println("user OK " + users.size());
            List<String> ids = users.stream().map(Profile::getId).collect(Collectors.toList());
            System.out.println("ids Ok " + ids.size());
            for (int i = 0; i < numberOfDialogues; i++) {
                Dialogue dialog = new Dialogue();
                if (!ids.isEmpty()) {
                    Member author = new Member();
                    Member member = new Member();

                    Profile authorP = profilesService.findProfileByEmail(SecurityOperations.getCurrentUserEmail());
                    if(authorP != null) author.setId(authorP.getId());
                    else author.setId(randId(ids));
                    member.setId(randId(ids));

                    ArrayList<Member> members = new ArrayList<>();
                    members.add(author);
                    members.add(member);
                    dialog.setMembers(members);
                }

                PrivateMessage msg1 = new PrivateMessage();
                PrivateMessage msg2 = new PrivateMessage();
                PrivateMessage msg3 = new PrivateMessage();

                msg1.setAuthorId(dialog.getMembers().get(0).getId());
                msg2.setAuthorId(dialog.getMembers().get(1).getId());
                msg3.setAuthorId(dialog.getMembers().get(0).getId());

                msg1.setMessage("Hi!");
                msg2.setMessage("Hello! How are you?");
                msg3.setMessage("I'm fine thanks, and you?");
                List<PrivateMessage> msgs = new ArrayList<>();
                msgs.add(msg1);
                msgs.add(msg2);
                msgs.add(msg3);

                dialog.setMessages(msgs);

                ConcurrentHashMap<String, Integer> umc = dialog.getUnreadMsgCounter();
                umc.put(dialog.getMembers().get(0).getId(), 2);
                umc.put(dialog.getMembers().get(1).getId(), 1);

                dialog.setSubject("very important dialogue " + i);

                dialogueRepository.save(dialog);
            }
        } catch (Exception e){
            System.out.println("PROBLEM!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
        }

        model.addAttribute("message", numberOfDialogues + " test dialogues is created.");
        return "index";
    }

    private String randId (List<String> from){
        if(from.isEmpty()) return null;
        return from.get((int) (Math.random() * 100) % from.size());
    }
}
