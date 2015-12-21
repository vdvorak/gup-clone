package ua.com.itproekt.gup.model.privatemessages;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;


@Document(collection = "dialogue")
public class Dialogue {
    @Id
    private String id;
    private String subject;
    private List<Member> members;
    private List<PrivateMessage> messages;

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

    @Override
    public String toString() {
        return "Dialogue{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", members=" + members.toString() +
                ", messages=" + messages.toString() +
                '}';
    }
}


