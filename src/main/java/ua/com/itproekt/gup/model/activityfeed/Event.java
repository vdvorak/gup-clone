package ua.com.itproekt.gup.model.activityfeed;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document(collection = "events")
public class Event {
    @Id
    private String id;
    private String uId;
    private Boolean viewed;
    private EventType type;
    private String contentId;
    private String creatorEventId;

    @Indexed(expireAfterSeconds = 60*60*24*14)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdDate;
//    private String userCreatorEventName;
//    private Boolean hidden;

    public Event() {
        createdDate = LocalDateTime.now();
    }

    public Event(String uId, EventType type, String contentId, String creatorEventId) {
        this();
        this.uId = uId;
        this.type = type;
        this.contentId = contentId;
        this.creatorEventId = creatorEventId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getCreatorEventId() {
        return creatorEventId;
    }

    public void setCreatorEventId(String creatorEventId) {
        this.creatorEventId = creatorEventId;
    }

}
