package ua.com.gup.domain.complaint;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;

@Document(collection = ComplaintOffer.COLLECTION_NAME, language = "russian")
public class ComplaintOffer {

    public static final String COLLECTION_NAME = "complaint";
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String initiatorId;

    @Indexed
    private String offerId;

    //private List<ComplaintOfferDescription> descriptions;
    private List<ComplaintOfferType> types;

    private String username;

    private String feedback;

    @Indexed
    private ComplaintOfferStatus status;

    private ZonedDateTime createdDate = ZonedDateTime.now();

    @Indexed
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

//    public List<ComplaintOfferDescription> getDescriptions() {
//        return descriptions;
//    }
//
//    public void setDescriptions(List<ComplaintOfferDescription> descriptions) {
//        this.descriptions = descriptions;
//    }
//
//    public void addDescriptions(ComplaintOfferDescription description) {
//        descriptions.add(description);
//    }
    public List<ComplaintOfferType> getTypes() {
        return types;
    }

    public void setTypes(List<ComplaintOfferType> types) {
        this.types = types;
    }

    public void addType(ComplaintOfferType type) {
        types.add(type);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public ComplaintOfferStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintOfferStatus status) {
        this.status = status;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void updateLastModifiedDate() {
        this.lastModifiedDate =  ZonedDateTime.now();
    }


    @Override
    public String toString() {
        return "ComplaintOffer{" +
                "id='" + id + '\'' +
                ", initiatorId='" + initiatorId + '\'' +
                ", offerId='" + offerId + '\'' +
//                ", descriptions=" + descriptions +
                ", types=" + types +
                ", username='" + username + '\'' +
                ", feedback='" + feedback + '\'' +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
