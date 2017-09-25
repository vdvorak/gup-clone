package ua.com.gup.domain.email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mail.queue")
public class EmailMessage {

    @Id
    private String id;
    private String userId;
    private Long lastAttemptTimestamp;
    private String from;
    private String replyTo;
    private String[] recipients;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String text;

    public EmailMessage() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getLastAttemptTimestamp() {
        return lastAttemptTimestamp;
    }

    public void setLastAttemptTimestamp(Long lastAttemptTimestamp) {
        this.lastAttemptTimestamp = lastAttemptTimestamp;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
