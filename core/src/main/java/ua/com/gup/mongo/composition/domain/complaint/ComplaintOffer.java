package ua.com.gup.mongo.composition.domain.complaint;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.mongo.model.enumeration.ComplaintOfferStatus;
import ua.com.gup.mongo.model.enumeration.ComplaintOfferType;

import java.util.Date;
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

    private Date createdDate; //private ZonedDateTime createdDate;
//    private long createdDateLong;

    private Date lastModifiedDate; //private ZonedDateTime lastModifiedDate;
//    @Indexed
//    private long lastModifiedDateLong;

    public ComplaintOffer(){
        createdDate = new Date(); //createdDate = ZonedDateTime.now();
        lastModifiedDate = new Date(); //lastModifiedDate = ZonedDateTime.now();
    }


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

    public Date getCreatedDate() { //public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

//    public void setCreatedDateLong(long createdDateLong) {
//        this.createdDateLong = createdDateLong;
//    }
//
//    public long getCreatedDateLong() {
//        return createdDateLong;
//    }

    public void setCreatedDate(Date createdDate) { //public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() { //public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

//    public void setLastModifiedDateLong(long lastModifiedDateLong) {
//        this.lastModifiedDateLong = lastModifiedDateLong;
//    }
//
//    public long getLastModifiedDateLong() {
//        return lastModifiedDateLong;
//    }

    public void setLastModifiedDate(Date lastModifiedDate) { //public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void updateLastModifiedDate() {
        lastModifiedDate =  new Date(); //this.lastModifiedDate =  ZonedDateTime.now();
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
//                ", createdDateLong=" + createdDateLong +
                ", lastModifiedDate=" + lastModifiedDate +
//                ", lastModifiedDateLong=" + lastModifiedDateLong +
                '}';
    }
}
