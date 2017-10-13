package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SocialLoginDTO {
    @JsonProperty("socWendor")
    private String socWendor;
    @JsonProperty("socWendor")
    private String tokenKey;
    @JsonProperty("uid")
    private String uid;

    public SocialLoginDTO() {
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
