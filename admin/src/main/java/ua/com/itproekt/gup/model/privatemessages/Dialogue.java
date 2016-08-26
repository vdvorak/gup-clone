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
    private List<Member> members ;
    private List<PrivateMessage> messages;
    private ConcurrentHashMap<String, Integer> unreadMsgCounter;
    private Long lustMsgTime;

    public Dialogue() {
        members = new ArrayList<>();
        messages = new ArrayList<>();
        unreadMsgCounter = new ConcurrentHashMap<>();
        lustMsgTime = 0l;
    }

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
        setLustMsgTime();
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

    public Long getLustMsgTime() {
        return lustMsgTime;
    }

    public void setLustMsgTime(Long lustMsgTime) {
        this.lustMsgTime = lustMsgTime;
        setLustMsgTime();
    }

    public void setLustMsgTime(){
        Long time = messages.stream().mapToLong(PrivateMessage::getDate).max().getAsLong();
        if(time > lustMsgTime) lustMsgTime = time;
    }

    public void addMessage(PrivateMessage msg){
        messages.add(msg);
        Long time = msg.getDate();
        if(time > lustMsgTime) lustMsgTime = time;
    }

    @Override
    public String toString() {
        return "Dialogue{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", members=" + members +
                ", messages=" + messages +
                ", unreadMsgCounter=" + unreadMsgCounter +
                ", lustMsgTime=" + lustMsgTime +
                '}';
    }
}


