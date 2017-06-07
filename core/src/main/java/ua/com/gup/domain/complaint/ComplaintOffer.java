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

    private String description;

    private List<ComplaintOfferType> types;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ComplaintOfferType> getTypes() {
        return types;
    }

    public void setTypes(List<ComplaintOfferType> types) {
        this.types = types;
    }

    public void addType(ComplaintOfferType type) {
        types.add(type);
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
                ", description='" + description + '\'' +
                ", types=" + types +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
