package ua.com.itproekt.gup.model.privatemessages;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public class PrivateMessage {

//    new MongoId(String id) will return ObjectID;
    private String authorId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    private String message;


    public PrivateMessage() {
        this.setDate(LocalDateTime.now());
    }

    public PrivateMessage(String msg, String authorId) {
        this.date = LocalDateTime.now();
        this.message = msg;
        this.authorId = authorId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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
