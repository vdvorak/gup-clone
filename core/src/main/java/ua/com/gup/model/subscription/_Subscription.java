//package ua.com.gup.model.subscription;
//
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import ua.com.gup.model.offer.filter.OfferFilterOptions;
//
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//@Document(collection = "subscription")
//public class Subscription {
//    @Id
//    private String id;
//
//    private String userId;
//    private OfferFilterOptions offerFilterOptions;
//    private Long createDate;
//    private Long lastCheckDate;
//
//
//    public Subscription() {
//    }
//
//    public Subscription(String userId, OfferFilterOptions offerFilterOptions) {
//        this.userId = userId;
//        this.offerFilterOptions = offerFilterOptions;
//    }
//
//    public Subscription setLastCheckDateAndCreateDateEqualsToCurrentDate() {
//        this.lastCheckDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
//        this.createDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
//        return this;
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
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public OfferFilterOptions getOfferFilterOptions() {
//        return offerFilterOptions;
//    }
//
//    public void setOfferFilterOptions(OfferFilterOptions offerFilterOptions) {
//        this.offerFilterOptions = offerFilterOptions;
//    }
//
//    public Long getLastCheckDate() {
//        return lastCheckDate;
//    }
//
//    public void setLastCheckDate(Long lastCheckDate) {
//        this.lastCheckDate = lastCheckDate;
//    }
//
//    public Long getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Long createDate) {
//        this.createDate = createDate;
//    }
//
//    @Override
//    public String toString() {
//        return "Subscription{" +
//                "id='" + id + '\'' +
//                ", userId='" + userId + '\'' +
//                ", offerFilterOptions=" + offerFilterOptions +
//                ", createDate=" + createDate +
//                ", lastCheckDate=" + lastCheckDate +
//                '}';
//    }
//}
