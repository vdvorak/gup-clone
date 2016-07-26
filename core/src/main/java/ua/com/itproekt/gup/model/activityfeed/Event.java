package ua.com.itproekt.gup.model.activityfeed;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document
public class Event {
    @Id
    private String id;

    private EventType type;
    private String targetUId; // user id, who will receive notification


    private String contentStoreId; //content id (order, offer etc)
    private String contentStoreTitle; // title of content store
    private String contentId; // content id in other conten (comment in order etc)


    private String imgId; //picture id

    private String makerId; // user id, who was producer of event
    private String makerName; // user name, who was producer of event


    private Boolean isViewed;


    // ToDo Уведомления должны удаляться через 14 дней после их прочтения
    // либо их можно удалить вручную. Непрочитанные уведомления сами не удаляются (Сейчас они удаляются в любом случае через 14 дней)
    @Indexed(expireAfterSeconds = 60 * 60 * 24 * 14)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdDate;

    public Event() {
        createdDate = LocalDateTime.now();
        isViewed = false;
    }

    public Event(String targetUId, EventType type, String contentStoreId, String makerId) {
        this();
        this.targetUId = targetUId;
        this.type = type;
        this.contentStoreId = contentStoreId;
        this.makerId = makerId;
    }

    public Event(String targetUId, EventType type, String contentStoreId, String contentStoreTitle, String makerId, String makerName, String imgId) {
        this();
        this.targetUId = targetUId;
        this.type = type;
        this.contentStoreId = contentStoreId;
        this.contentStoreTitle = contentStoreTitle;
        this.makerId = makerId;
        this.makerName = makerName;
        this.imgId = imgId;
    }

    public Event(String targetUId, EventType type, String contentStoreId, String contentId, String makerId) {
        this(targetUId, type, contentStoreId, makerId);
        this.contentId = contentId;
    }


    public String getContentStoreId() {
        return contentStoreId;
    }

    public Event setContentStoreId(String contentStoreId) {
        this.contentStoreId = contentStoreId;
        return this;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Event setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getId() {
        return id;
    }

    public Event setId(String id) {
        this.id = id;
        return this;
    }

    public String getTargetUId() {
        return targetUId;
    }

    public Event setTargetUId(String targetUId) {
        this.targetUId = targetUId;
        return this;
    }

    public Boolean getIsViewed() {
        return isViewed;
    }

    public Event setIsViewed(Boolean isViewed) {
        this.isViewed = isViewed;
        return this;
    }

    public EventType getType() {
        return type;
    }

    public Event setType(EventType type) {
        this.type = type;
        return this;
    }

    public String getContentId() {
        return contentId;
    }

    public Event setContentId(String contentId) {
        this.contentId = contentId;
        return this;
    }

    public String getMakerId() {
        return makerId;
    }

    public Event setMakerId(String makerId) {
        this.makerId = makerId;
        return this;
    }

    public String getMakerName() {
        return makerName;
    }

    public Event setMakerName(String makerName) {
        this.makerName = makerName;
        return this;
    }

    public String getImgId() {
        return imgId;
    }

    public Event setImgId(String imgId) {
        this.imgId = imgId;
        return this;
    }

    public Boolean getViewed() {
        return isViewed;
    }

    public Event setViewed(Boolean viewed) {
        isViewed = viewed;
        return this;
    }

    public String getContentStoreTitle() {
        return contentStoreTitle;
    }

    public Event setContentStoreTitle(String contentStoreTitle) {
        this.contentStoreTitle = contentStoreTitle;
        return this;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", targetUId='" + targetUId + '\'' +
                ", contentStoreId='" + contentStoreId + '\'' +
                ", contentStoreTitle='" + contentStoreTitle + '\'' +
                ", contentId='" + contentId + '\'' +
                ", imgId='" + imgId + '\'' +
                ", makerId='" + makerId + '\'' +
                ", makerName='" + makerName + '\'' +
                ", isViewed=" + isViewed +
                ", createdDate=" + createdDate +
                '}';
    }
}
