package ua.com.itproekt.gup.service.privatemessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.dialogue.DialogueRepository;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.privatemessages.PrivateMessage;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
public class DialogueServiceImpl implements DialogueService {

    @Autowired
    DialogueRepository dr;

    @Autowired
    ProfileRepository pr;

    @Override
    public List<Dialogue> findAll() {
        return dr.findAll();
    }

    @Override
    public Dialogue addDialogue(Dialogue dialogue) {
        return dr.insert(dialogue);
    }

    @Override
    public Dialogue updateDialogue(Dialogue dialogue) {
        return dr.save(dialogue);
    }

    @Override
    public Dialogue updateDialogueWhenAddMsg(Dialogue dialogue) {
        updateCountersWhenAddOneMsg(dialogue);
        return dr.save(dialogue);
    }

    @Override
    public List<Dialogue> findDialogues(Member member) {
        return dr.findByMembersIn(member);
    }

    @Override
    public Dialogue findById(String id) {
        Dialogue d = dr.findOne(id);
        updateCountersWhenRead(d);
        return d;
    }

    @Override
    public List<Dialogue> findDialogsForUser(String currentUserId) {
        Member member = new Member();
        member.setId(currentUserId);
        List<Dialogue> d1 = dr.findByMembersIn(member);
        System.out.println("!!!!!!!! findByMembersIn !!!!!!!! d1.isEmpty() = " + d1.isEmpty());
        for (Dialogue d : d1) {
            System.out.println("DialogueServiceImpl.findDialogsForUser() d = " + d);
            updateCountersWhenRead(d);
        }
        return d1;
    }

    @Override
    public void completeMembers(Dialogue dialogue) {
        List<Member> members = dialogue.getMembers();
        for (Member m : members) {
            if (m.getId() != null) {
                Profile profile = pr.findProfileById(m.getId());
                m.setName(profile.getUsername());
                m.setUserPicId(profile.getContact().getPic());
            }
        }
    }

    @Override
    public PrivateMessage completeMessage(PrivateMessage message, String authorId) {
        message.setAuthorId(authorId);
        return message;
    }

    @Override
    public boolean isUserInDialogue(Dialogue dialogue, String userId) {
        if (dialogue.getMembers() != null) {
            for (Member m : dialogue.getMembers()) {
                if (userId.equals(m.getId())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Dialogue findByMembersAndSubject(Dialogue dialogue) {
        return dr.findByMembersAndSubject(dialogue.getMembers(), dialogue.getSubject());
    }


    private void updateCountersWhenRead(Dialogue dialogue) {
        Profile user = pr.findByEmail(SecurityOperations.getCurrentUserEmail());
        dialogue.getMessages().parallelStream().forEach(m -> m.getWhoRead().add(user.getId()));
        Integer wasUnread = dialogue.getUnreadMsgCounter().replace(user.getId(), 0);
        if (wasUnread != null) {
            Integer p = user.getUnreadMessages().get() - wasUnread;
            user.setUnreadMessages(new AtomicInteger(p));
            pr.updateProfile(user);
        }
        updateDialogue(dialogue);
    }

    private void updateCountersWhenAddOneMsg(Dialogue dialogue) {
        Profile user = pr.findByEmail(SecurityOperations.getCurrentUserEmail());
        List<Profile> profiles = new ArrayList<>();
        List<String> ids = dialogue.getMembers().parallelStream().map(Member::getId)
                .filter(id -> !id.equals(user.getId()))
                .collect(Collectors.toList());

                ids.parallelStream().forEach(id -> profiles.add(pr.findProfileById(id)));
        profiles.parallelStream().forEach(p -> {
            p.getUnreadMessages().incrementAndGet();
            pr.updateProfile(p);
        });

        //TODO Пофиксить null pointer
//        ids.parallelStream().forEach(id -> {
//            dialogue.getUnreadMsgCounter().replace(id, dialogue.getUnreadMsgCounter().get(id) + 1);
//        });
        updateDialogue(dialogue);
    }
}
