package ua.com.itproekt.gup.model.offer;

import org.bson.types.ObjectId;
import ua.com.itproekt.gup.util.OfferUserContactInfo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class RentedOfferPeriodInfo {
    private ObjectId id;
    private Long from;
    private Long to;
    private String profileId;
    private OfferUserContactInfo contactInfo;
    private Long createdDate;

    public RentedOfferPeriodInfo() {
        this.id = new ObjectId();
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public OfferUserContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(OfferUserContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }
}
