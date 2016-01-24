package ua.com.itproekt.gup.model.privatemessages;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Document(collection = "dialogue")
public class Dialogue {
    @Id
    private String id;
    private String subject;
    private List<Member> members = new ArrayList<>();
    private List<PrivateMessage> messages = new ArrayList<>();
    private ConcurrentHashMap<String, Integer> unreadMsgCounter = new ConcurrentHashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<PrivateMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<PrivateMessage> messages) {
        this.messages = messages;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public ConcurrentHashMap<String, Integer> getUnreadMsgCounter() {
        return unreadMsgCounter;
    }

    public void setUnreadMsgCounter(ConcurrentHashMap<String, Integer> unreadMsgCounter) {
        this.unreadMsgCounter = unreadMsgCounter;
    }

    @Override
    public String toString() {
        return "Dialogue{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", members=" + members +
                ", messages=" + messages +
                ", unreadMsgCounter=" + unreadMsgCounter +
                '}';
    }
}


