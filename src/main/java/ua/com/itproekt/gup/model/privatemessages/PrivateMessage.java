package ua.com.itproekt.gup.model.privatemessages;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class PrivateMessage {

//    new MongoId(String id) will return ObjectID;
    private String authorId;
    private Long date;
    private String message;


    public PrivateMessage() {
        this.date = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public PrivateMessage(String msg, String authorId) {
        this.date = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.message = msg;
        this.authorId = authorId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "PrivateMessage{" +
                ", authorId='" + authorId + '\'' +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }
}
