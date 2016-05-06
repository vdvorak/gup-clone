package ua.com.itproekt.gup.service.privatemessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.dao.dialogue.DialogueRepository;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.privatemessages.PrivateMessage;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.StaticData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
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
    public Dialogue updateDialogueWhenAddMsg(Dialogue dialogue){
        updateCountersWhenAddOneMsg(dialogue);
        return dr.save(dialogue);
    }

    @Override
    public void updateDialogueWhenAddMsgProfile(Dialogue dialogue, Profile profile){
        updateCountersWhenAddOneMsgProfile(dialogue, profile);
    }

    @Override
    public List<Dialogue> findDialogues(Member member) {
        return dr.findByMembersIn(member, new Sort(Sort.Direction.DESC, "lustMsgTime"));
    }

    @Override
    public List<Dialogue> findFirstThreeDialogues(Member member) {
        PageRequest pageRequest = new PageRequest(0, 3, new Sort(Sort.Direction.DESC, "lustMsgTime"));
        return dr.findByMembersIn(member, pageRequest);
    }
    @Override
    public Dialogue findById(String id) {
        Dialogue d = dr.findOne(id);
        updateCountersWhenRead(d);
        return d;
    }

    @Override
    public Dialogue findByIdProfile(String id, Profile user) {
        Dialogue d = dr.findOne(id);
        updateCountersWhenReadProfile(d, user);
        return d;
    }

    @Override
    public List<Dialogue> findDialogsForUser(String currentUserId) {
        Member member = new Member();
        member.setId(currentUserId);
        List<Dialogue> d1 = dr.findByMembersIn(member);
        for (Dialogue d : d1) {
            updateCountersWhenRead(d);
        }
        return d1;
    }

    @Override
    public List<Dialogue> findDialogsForUserSimple(String currentUserId){
        Member member = new Member();
        member.setId(currentUserId);
        List<Dialogue> d1 = dr.findByMembersIn(member);
        return d1;
    }


    @Override
    public void completeMembers(Dialogue dialogue) {
        List<Member> members = dialogue.getMembers();
        for (Member m : members) {
            if (m.getId() != null) {
                Profile profile = pr.findById(m.getId());
                m.setName(profile.getUsername());
                m.setUserPicId(profile.getImgId());
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
        return  dialogue.getMembers()
                .stream()
                .anyMatch(m -> m.getId().equals(userId));
    }

    @Override
    public Dialogue findByMembersAndSubject(Dialogue dialogue) {
        return dr.findByMembersAndSubject(dialogue.getMembers(), dialogue.getSubject());
    }


    private void updateCountersWhenRead(Dialogue dialogue){
        if(dialogue == null) return;
        Profile user = pr.findByEmail(SecurityOperations.getCurrentUserEmail());
        if(dialogue.getMessages() != null && dialogue.getMessages().size() > 0) {
            dialogue.getMessages().parallelStream().forEach(m -> {
                if(m.getWhoRead() == null) m.setWhoRead(new ConcurrentSkipListSet<>());
                m.getWhoRead().add(user.getId());
            });
        }
        Integer wasUnread;
        if(dialogue.getUnreadMsgCounter() == null){
            dialogue.setUnreadMsgCounter(new ConcurrentHashMap<>());
            dialogue.getMembers().stream().filter(m -> !m.getId().equals(user.getId())).forEach(m -> {
                int size = 0;
                if (dialogue.getMessages() != null) size = dialogue.getMessages().size();
                dialogue.getUnreadMsgCounter().put(m.getId(), size);
            });
        }
        wasUnread = dialogue.getUnreadMsgCounter().replace(user.getId(), 0);
        if (wasUnread != null) {
            // todo move it to default constructor of Profile.
            if(user.getUnreadMessages() == null) user.setUnreadMessages(0);
            Integer p = user.getUnreadMessages() - wasUnread;
            user.setUnreadMessages(p);
            pr.findProfileAndUpdate(user);
        } else {
            dialogue.getUnreadMsgCounter().put(user.getId(), 0);
        }
        updateDialogue(dialogue);
    }

    private void updateCountersWhenReadProfile(Dialogue dialogue, Profile user) {
        if(dialogue == null) return;
        if(dialogue.getMessages() != null && dialogue.getMessages().size() > 0) {
            dialogue.getMessages().parallelStream().forEach(m -> {
                if(m.getWhoRead() == null) m.setWhoRead(new ConcurrentSkipListSet<>());
                m.getWhoRead().add(user.getId());
            });
        }
        Integer wasUnread;
        if(dialogue.getUnreadMsgCounter() == null){
            dialogue.setUnreadMsgCounter(new ConcurrentHashMap<>());
            dialogue.getMembers().stream().filter(m -> !m.getId().equals(user.getId())).forEach(m -> {
                int size = 0;
                if (dialogue.getMessages() != null) size = dialogue.getMessages().size();
                dialogue.getUnreadMsgCounter().put(m.getId(), size);
            });
        }
        wasUnread = dialogue.getUnreadMsgCounter().replace(user.getId(), 0);
        if (wasUnread != null) {
            // todo move it to default constructor of Profile.
            if(user.getUnreadMessages() == null) {user.setUnreadMessages(0);}
            Integer p = user.getUnreadMessages() - wasUnread;
            user.setUnreadMessages(p);
            pr.findProfileAndUpdate(user);
        } else {
            dialogue.getUnreadMsgCounter().put(user.getId(), 0);
        }
        updateDialogue(dialogue);
    }

    private void updateCountersWhenAddOneMsg(Dialogue dialogue) {
        Profile user = pr.findByEmail(SecurityOperations.getCurrentUserEmail());
        List<Profile> profiles = new ArrayList<>();
        List<String> ids = dialogue.getMembers().parallelStream().map(Member::getId)
                .filter(id -> !id.equals(user.getId()))
                .collect(Collectors.toList());

                ids.parallelStream().forEach(id -> profiles.add(pr.findById(id)));
        profiles.parallelStream().forEach(p -> {
            if (p.getUnreadMessages() == null) p.setUnreadMessages(1);
            else p.setUnreadMessages(p.getUnreadMessages() + 1);
            pr.findProfileAndUpdate(p);
        });

        ids.parallelStream().forEach(id -> {
            if(dialogue.getUnreadMsgCounter().containsKey(id)) {
                dialogue.getUnreadMsgCounter().replace(id, dialogue.getUnreadMsgCounter().get(id) + 1);
            } else {
                dialogue.getUnreadMsgCounter().put(id, dialogue.getMessages().size());
            }
        });
        updateDialogue(dialogue);
    }

    private void updateCountersWhenAddOneMsgProfile(Dialogue dialogue, Profile user) {
        List<Profile> profiles = new ArrayList<>();
        List<String> ids = dialogue.getMembers().parallelStream().map(Member::getId)
                .filter(id -> !id.equals(user.getId()))
                .collect(Collectors.toList());

        ids.parallelStream().forEach(id -> profiles.add(pr.findById(id)));
        profiles.parallelStream().forEach(p -> {
            if (p.getUnreadMessages() == null) p.setUnreadMessages(1);
            else p.setUnreadMessages(p.getUnreadMessages() + 1);
            pr.findProfileAndUpdate(p);
        });

        ids.parallelStream().forEach(id -> {
            if(dialogue.getUnreadMsgCounter().containsKey(id)) {
                dialogue.getUnreadMsgCounter().replace(id, dialogue.getUnreadMsgCounter().get(id) + 1);
            } else {
                dialogue.getUnreadMsgCounter().put(id, dialogue.getMessages().size());
            }
        });
        updateDialogue(dialogue);
    }
}
