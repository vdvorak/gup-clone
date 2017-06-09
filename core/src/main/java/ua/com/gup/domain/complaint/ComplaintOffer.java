package ua.com.gup.domain.complaint;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

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

    private ZonedDateTime createdDate;
    private long createdDateLong;

    private ZonedDateTime lastModifiedDate;
    @Indexed
    private long lastModifiedDateLong;

    public ComplaintOffer(){
        createdDate = ZonedDateTime.now();
        lastModifiedDate = ZonedDateTime.now();
//        createdDateLong = toDateTime(createdDate).getMillis();
//        lastModifiedDateLong = toDateTime(lastModifiedDate).getMillis();
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

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDateLong(long createdDateLong) {
        this.createdDateLong = createdDateLong;
    }

    public long getCreatedDateLong() {
        return createdDateLong;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
//        createdDateLong = toDateTime(createdDate).getMillis();
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDateLong(long lastModifiedDateLong) {
        this.lastModifiedDateLong = lastModifiedDateLong;
    }

    public long getLastModifiedDateLong() {
        return lastModifiedDateLong;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
//        lastModifiedDateLong = toDateTime(lastModifiedDate).getMillis();
    }

    public void updateLastModifiedDate() {
        this.lastModifiedDate =  ZonedDateTime.now();
    }


//    private DateTime toDateTime(final ZonedDateTime zdt) {
//        return new DateTime(zdt.toInstant().toEpochMilli(), DateTimeZone.forID(zdt.getOffset().getId()));
//    }


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
                ", createdDateLong=" + createdDateLong +
                ", lastModifiedDate=" + lastModifiedDate +
                ", lastModifiedDateLong=" + lastModifiedDateLong +
                '}';
    }
}
