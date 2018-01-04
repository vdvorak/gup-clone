package ua.com.gup.common.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.profile.ProfileStatistic;

import java.util.Set;

public abstract class DetailProfileDTO extends ProfileDTO {

    @JsonProperty("id")
    protected String publicId;

    @JsonProperty("contactList")
    protected Set<String> contactsList;

    @JsonProperty("imgUrl")
    protected String imageUrl;

    @JsonProperty("createdDate")
    protected Long createdDate;

    @JsonProperty("lastLoginDate")
    protected Long lastLoginDate;

    @JsonProperty("profileStatistic")
    protected ProfileStatistic profileStatistic;

    @JsonProperty("online")
    protected Boolean online;

    public DetailProfileDTO() {
    }

    public DetailProfileDTO(CommonProfile profile) {
        super(profile);
        this.publicId = profile.getPublicId();
        this.contactsList = profile.getSocialList();
        this.imageUrl = profile.getImgUrl();
        this.createdDate = profile.getCreatedDate();
        this.lastLoginDate = profile.getLastLoginDate();
        this.profileStatistic = profile.getProfileStatistic();
        this.online = profile.isOnline();
    }


    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Set<String> getContactsList() {
        return contactsList;
    }

    public void setContactsList(Set<String> contactsList) {
        this.contactsList = contactsList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Long lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public ProfileStatistic getProfileStatistic() {
        return profileStatistic;
    }

    public void setProfileStatistic(ProfileStatistic profileStatistic) {
        this.profileStatistic = profileStatistic;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }


}
