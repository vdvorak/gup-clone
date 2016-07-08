package ua.com.itproekt.gup.model.subscription;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Document(collection = "subscription")
public class Subscription {
    @Id
    private String id;

    private String userId;
    private OfferFilterOptions offerFilterOptions;
    private Long sinceDate;


    public Subscription() {
    }

    public Subscription(String userId, OfferFilterOptions offerFilterOptions) {
        this.userId = userId;
        this.offerFilterOptions = offerFilterOptions;
    }

    public Subscription setSinceDateEqualsToCurrentDate() {
        this.sinceDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
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

    public OfferFilterOptions getOfferFilterOptions() {
        return offerFilterOptions;
    }

    public void setOfferFilterOptions(OfferFilterOptions offerFilterOptions) {
        this.offerFilterOptions = offerFilterOptions;
    }

    public Long getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(Long sinceDate) {
        this.sinceDate = sinceDate;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", offerFilterOptions=" + offerFilterOptions +
                ", sinceDate=" + sinceDate +
                '}';
    }
}
