package ua.com.itproekt.gup.dao.dialogue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.itproekt.gup.model.privatemessages.Dialogue;
import ua.com.itproekt.gup.model.privatemessages.Member;

import java.util.List;

public interface DialogueRepository  extends MongoRepository<Dialogue, String> {
    List<Dialogue> findByMembers(List<Member> members);
    List<Dialogue> findByMembersIn(Member member);
    List<Dialogue> findByMembersIn(Member member, Sort sort);
    List<Dialogue> findByMembersIn(Member member, Pageable pageable);
    Dialogue findByMembersAndSubject(List<Member> members, String subject);

//    EntityPage<Dialogue> findWihOptions(DialogueFilterOption dialogueFilterOption);
//    @Query(value = "{ 'userId' : ?0, 'questions.questionID' : ?1 }", fields = "{ 'questions.questionID' : 1 }")
//    List<PracticeQuestion> findByUserIdAndQuestionsQuestionID(int userId, int questionID);

    Page<Dialogue> queryFirst10ByMembersIn(Member member, Pageable pageable);
}
