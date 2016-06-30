//package ua.com.itproekt.gup.model.activityfeed;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.time.LocalDateTime;
//
//@Document
//public class Event {
//    @Id
//    private String id;
//
//    private EventType type;
//    private String targetUId;
//    private String contentStoreId;
//    private String contentId;
//
//    private String makerId;
//    private String makerName;
//    private String makerImgId;
//
//    private Boolean isViewed;
//
//    @Indexed(expireAfterSeconds = 60*60*24*14)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    private LocalDateTime createdDate;
//
//    public Event() {
//        createdDate = LocalDateTime.now();
//    }
//
//    public Event(String targetUId, EventType type, String contentStoreId, String makerId) {
//        this();
//        this.targetUId = targetUId;
//        this.type = type;
//        this.contentStoreId = contentStoreId;
//        this.makerId = makerId;
//    }
//
//    public Event(String targetUId, EventType type, String contentStoreId, String contentId, String makerId) {
//        this(targetUId, type, contentStoreId, makerId);
//        this.contentId = contentId;
//    }
//
//    public String getContentStoreId() {
//        return contentStoreId;
//    }
//
//    public void setContentStoreId(String contentStoreId) {
//        this.contentStoreId = contentStoreId;
//    }
//
//    public LocalDateTime getCreatedDate() {
//        return createdDate;
//    }
//
//    public void setCreatedDate(LocalDateTime createdDate) {
//        this.createdDate = createdDate;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTargetUId() {
//        return targetUId;
//    }
//
//    public void setTargetUId(String targetUId) {
//        this.targetUId = targetUId;
//    }
//
//    public Boolean getIsViewed() {
//        return isViewed;
//    }
//
//    public void setIsViewed(Boolean isViewed) {
//        this.isViewed = isViewed;
//    }
//
//    public EventType getType() {
//        return type;
//    }
//
//    public void setType(EventType type) {
//        this.type = type;
//    }
//
//    public String getContentId() {
//        return contentId;
//    }
//
//    public void setContentId(String contentId) {
//        this.contentId = contentId;
//    }
//
//    public String getMakerId() {
//        return makerId;
//    }
//
//    public void setMakerId(String makerId) {
//        this.makerId = makerId;
//    }
//
//    public String getMakerName() {
//        return makerName;
//    }
//
//    public void setMakerName(String makerName) {
//        this.makerName = makerName;
//    }
//
//    public String getMakerImgId() {
//        return makerImgId;
//    }
//
//    public void setMakerImgId(String makerImgId) {
//        this.makerImgId = makerImgId;
//    }
//
//    public Boolean getViewed() {
//        return isViewed;
//    }
//
//    public void setViewed(Boolean viewed) {
//        isViewed = viewed;
//    }
//}
