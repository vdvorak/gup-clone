package ua.com.itproekt.gup.model.subscription;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


/**
 * @author Kobylyatskyy Alexander
 */
@Document(collection = "subscription")
public class Subscription {
    @Id
    private String id;

    private String userId;
    private String email;
    private String authEmail;
    private String notAuthEmail;
    private OfferFilterOptions offerFilterOptions;
    private Long createDate;
    private Long lastCheckDate;

    private String offerFilterOptionsCheckSum; // hash code of the offer filter option

    public Subscription() {
    }

    public Subscription setLastCheckDateAndCreateDateEqualsToCurrentDate() {
        this.lastCheckDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        this.createDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OfferFilterOptions getOfferFilterOptions() {
        return offerFilterOptions;
    }

    public void setOfferFilterOptions(OfferFilterOptions offerFilterOptions) {
        this.offerFilterOptions = offerFilterOptions;
    }

    public Long getLastCheckDate() {
        return lastCheckDate;
    }

    public void setLastCheckDate(Long lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }


    public String getAuthEmail() {
        return authEmail;
    }

    public void setAuthEmail(String authEmail) {
        this.authEmail = authEmail;
    }

    public String getNotAuthEmail() {
        return notAuthEmail;
    }

    public void setNotAuthEmail(String notAuthEmail) {
        this.notAuthEmail = notAuthEmail;
    }

    public String getOfferFilterOptionsCheckSum() {
        return offerFilterOptionsCheckSum;
    }

    public void setOfferFilterOptionsCheckSum(String offerFilterOptionsCheckSum) {
        this.offerFilterOptionsCheckSum = offerFilterOptionsCheckSum;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", authEmail='" + authEmail + '\'' +
                ", notAuthEmail='" + notAuthEmail + '\'' +
                ", offerFilterOptions=" + offerFilterOptions +
                ", createDate=" + createDate +
                ", lastCheckDate=" + lastCheckDate +
                ", offerFilterOptionsCheckSum='" + offerFilterOptionsCheckSum + '\'' +
                '}';
    }
}
