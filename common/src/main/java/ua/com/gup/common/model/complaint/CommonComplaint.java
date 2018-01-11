package ua.com.gup.common.model.complaint;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CommonComplaint implements Serializable {

    public static final String COLLECTION_NAME = "complaint";

    @Id
    private String id;

    private ComplaintInitiator initiator;

    @Indexed
    private String offerId;

    private String description;

    private List<ComplaintOfferType> types;

    @Indexed
    private ComplaintOfferStatus status;

    private Date createdDate; //private ZonedDateTime createdDate;

    private Date lastModifiedDate; //private ZonedDateTime lastModifiedDate;

    public CommonComplaint(){
        createdDate = new Date(); //createdDate = ZonedDateTime.now();
        lastModifiedDate = new Date(); //lastModifiedDate = ZonedDateTime.now();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ComplaintInitiator getInitiator() {
        return initiator;
    }

    public void setInitiator(ComplaintInitiator initiator) {
        this.initiator = initiator;
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

    public Date getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }


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
                ", initiator=" + initiator +
                ", offerId='" + offerId + '\'' +
                ", description='" + description + '\'' +
                ", types=" + types +
                ", status=" + status +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}
