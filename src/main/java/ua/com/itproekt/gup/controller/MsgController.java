package ua.com.itproekt.gup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.privatemessages.PrivateMessage;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.privatemessage.DialogueService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MsgController {
    @Autowired
    DialogueService ds;
    @Autowired
    ProfilesService ps;

    @RequestMapping("/msg")
    public String getLoginForm(
             Model model) {

        List<Dialogue> dialogues = ds.findAll();
        if(dialogues.size() > 0) {
            Dialogue d = ds.findAll().get(0);
            System.out.println("Dialogue not 0! = " + d);


            List<PrivateMessage> m = d.getMessages();
            if(m.size() > 0) {
                System.out.println("MESSAGES! = " + m.get(0));
            } else {
                System.out.println("no messages :(");
            }
        }else {
            System.out.println("dialogue is empty :( ");
        }

        return "msg";
    }

    @RequestMapping(value="/msg", method = RequestMethod.POST)
    public String sentMSG(
            HttpServletRequest req,
            Model model) {

        Dialogue d = new Dialogue();

        String from = req.getParameter("from");
        Profile author = ps.findProfileByUsername(from);
        Member mFrom = new Member();
        mFrom.setId(author.getId());
        mFrom.setName(author.getUsername());


        String to = req.getParameter("to");
        Profile receiver = ps.findProfileByUsername(to);
        Member mTo = new Member();
        mTo.setId(receiver.getId());
        mTo.setName(receiver.getUsername());

        List<Member> members = new ArrayList<>();
        members.add(mFrom);
        members.add(mTo);

        PrivateMessage msg = new PrivateMessage();
        msg.setAuthorId(author.getId());

        LocalDateTime date = LocalDateTime.now();
        msg.setDate(date);

        String message = req.getParameter("message");
        msg.setMessage(message);
        List<PrivateMessage> messages = new ArrayList<PrivateMessage>();

        d.setMembers(members);
        d.setMessages(messages);
        d.setSubject(req.getParameter("subject"));

        ds.addDialogue(d);
        return "msg";
    }
}

