package ua.com.itproekt.gup.service.privatemessage;

import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.Member;
import ua.com.itproekt.gup.model.privatemessages.PrivateMessage;
import ua.com.itproekt.gup.model.profiles.Profile;

import java.util.List;

public interface DialogueService {
    List<Dialogue> findAll();
    Dialogue addDialogue(Dialogue dialogue);
    Dialogue updateDialogue(Dialogue dialogue);

    List<Dialogue> findDialogues(Member member);
    List<Dialogue> findFirstThreeDialogues(Member member);

    Dialogue findById(String Id);

    List<Dialogue> findDialogsForUser(String currentUserId);

    List<Dialogue> findDialogsForUserSimple(String currentUserId);

    void completeMembers(Dialogue dialogue);

    PrivateMessage completeMessage(PrivateMessage message, String authorId);

    boolean isUserInDialogue(Dialogue dialogue, String userId);

    Dialogue findByMembersAndSubject(Dialogue dialogue);

    Dialogue updateDialogueWhenAddMsg(Dialogue dialogue);

    void updateDialogueWhenAddMsgProfile(Dialogue dialogue, Profile profile);

    Dialogue findByIdProfile(String dialogId, Profile profile);
}
