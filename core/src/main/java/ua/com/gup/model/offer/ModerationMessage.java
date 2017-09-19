package ua.com.gup.model.offer;


import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ModerationMessage {
    @Size(min = 5, max = 300)
    private String message;
    private Long createdDate;
    private boolean isRead;

    public ModerationMessage setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ModerationMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public ModerationMessage setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public boolean isRead() {
        return isRead;
    }

    public ModerationMessage setIsRead(boolean isRead) {
        this.isRead = isRead;
        return this;
    }


    public ModerationMessage setCreatedModeratorMessageDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    @Override
    public String toString() {
        return "ModerationMessage{" +
                "message='" + message + '\'' +
                ", createdDate=" + createdDate +
                ", isRead=" + isRead +
                '}';
    }
}
