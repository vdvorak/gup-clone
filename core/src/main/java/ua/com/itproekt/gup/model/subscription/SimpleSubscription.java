package ua.com.itproekt.gup.model.subscription;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Document(collection = "simplesubscription")
public class SimpleSubscription {
    @Id
    private String id;

    private String email;
    private OfferFilterOptions offerFilterOptions;
    private Long createDate;
    private Long lastCheckDate;


    public SimpleSubscription() {
    }

    public SimpleSubscription(String email, OfferFilterOptions offerFilterOptions) {
        this.email = email;
        this.offerFilterOptions = offerFilterOptions;
    }

    public SimpleSubscription setLastCheckDateAndCreateDateEqualsToCurrentDate() {
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

    @Override
    public String toString() {
        return "Subscription{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", offerFilterOptions=" + offerFilterOptions +
                ", createDate=" + createDate +
                ", lastCheckDate=" + lastCheckDate +
                '}';
    }
}
