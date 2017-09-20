package ua.com.gup.model.offer;


import ua.com.gup.util.OfferUserContactInfo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Reservation {
    private String profileId;
    private OfferUserContactInfo userContactInfo;
    private Integer period;
    private Long createdDate;

    public Reservation setCreatedDateEqualsToCurrentDate() {
        this.createdDate = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
        return this;
    }

    //-----------------------------------------------------------------------------------

    public OfferUserContactInfo getUserContactInfo() {
        return userContactInfo;
    }

    public Reservation setUserContactInfo(OfferUserContactInfo userContactInfo) {
        this.userContactInfo = userContactInfo;
        return this;
    }

    public String getProfileId() {
        return profileId;
    }

    public Reservation setProfileId(String profileId) {
        this.profileId = profileId;
        return this;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public Reservation setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Integer getPeriod() {
        return period;
    }

    public Reservation setPeriod(Integer period) {
        this.period = period;
        return this;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "profileId='" + profileId + '\'' +
                ", userContactInfo=" + userContactInfo +
                ", period=" + period +
                ", createdDate=" + createdDate +
                '}';
    }
}
