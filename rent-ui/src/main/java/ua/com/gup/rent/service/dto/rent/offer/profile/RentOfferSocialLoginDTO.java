package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RentOfferSocialLoginDTO {
    @JsonProperty("socWendor")
    private String socWendor;
    @JsonProperty("socWendor")
    private String tokenKey;
    @JsonProperty("uid")
    private String uid;

    public RentOfferSocialLoginDTO() {
    }

    public String getSocWendor() {
        return socWendor;
    }

    public void setSocWendor(String socWendor) {
        this.socWendor = socWendor;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
