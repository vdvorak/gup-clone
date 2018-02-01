package ua.com.gup.common.dto.profile;

import ua.com.gup.common.model.mongo.CommonProfile;

import java.util.Set;
import java.util.stream.Collectors;

public class ProfileShortAdminDTO {
    private String firstname;
    private String lastname;
    private Set<String> userRoles;
    private String publicId;
    private String imageUrlLarge;
    private String imageUrlSmall;
    private Long createdDate;
    private Long lastLogin;
    private String socWendor;
    private Boolean active;
    private String email;
    private Boolean ban;


    public ProfileShortAdminDTO(CommonProfile profile) {
        this.firstname = profile.getFirstname();
        this.lastname = profile.getLastname();
        this.userRoles = profile.getUserRoles();
        this.publicId = profile.getPublicId();
        if (profile.getImageLarge() != null) {
            this.imageUrlLarge = profile.getImageLarge().getUrl();
        }
        if (profile.getImageSmall() != null) {
            this.imageUrlSmall = profile.getImageSmall().getUrl();
        }
        this.createdDate = profile.getCreatedDate();
        this.lastLogin = profile.getLastLoginDate();
        this.socWendor = profile.getSocWendor();
        this.active = profile.getActive();
        this.ban = profile.getBan();
        this.email = profile.getEmail();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<String> userRoles) {
        this.userRoles = userRoles;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getImageUrlLarge() {
        return imageUrlLarge;
    }

    public void setImageUrlLarge(String imageUrlLarge) {
        this.imageUrlLarge = imageUrlLarge;
    }

    public String getImageUrlSmall() {
        return imageUrlSmall;
    }

    public void setImageUrlSmall(String imageUrlSmall) {
        this.imageUrlSmall = imageUrlSmall;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getSocWendor() {
        return socWendor;
    }

    public void setSocWendor(String socWendor) {
        this.socWendor = socWendor;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getBan() {
        return ban;
    }

    public void setBan(Boolean ban) {
        this.ban = ban;
    }
}
