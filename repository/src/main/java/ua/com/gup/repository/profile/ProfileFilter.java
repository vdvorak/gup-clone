package ua.com.gup.repository.profile;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.common.model.enumeration.CommonUserRole;

import java.util.Set;

public class ProfileFilter {
    @ApiModelProperty(position = 10, example = "username")
    private String username;
    @ApiModelProperty(position = 20, example = "email")
    private String email;
    @ApiModelProperty(position = 30, example = "public user id")
    private String publicId;
    @ApiModelProperty(position = 40, example = "ROLE_ADMIN | ROLE_MODERATOR | ROLE_MANAGER | ROLE_USER")
    private Set<CommonUserRole> userRoles;
    @ApiModelProperty(position = 50, example = "main phone")
    private String mainPhone;
    @ApiModelProperty(position = 60, example = "additional phone")
    private String additionalPhone;
    @ApiModelProperty(position = 70, example = "ASC|DESC")
    private String dtRegistrationSort;
    @ApiModelProperty(position = 80, example = "ASC|DESC")
    private String dtLastLoginSort;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Set<CommonUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<CommonUserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getAdditionalPhone() {
        return additionalPhone;
    }

    public void setAdditionalPhone(String additionalPhone) {
        this.additionalPhone = additionalPhone;
    }

    public String getDtRegistrationSort() {
        return dtRegistrationSort;
    }

    public void setDtRegistrationSort(String dtRegistrationSort) {
        this.dtRegistrationSort = dtRegistrationSort;
    }

    public String getDtLastLoginSort() {
        return dtLastLoginSort;
    }

    public void setDtLastLoginSort(String dtLastLoginSort) {
        this.dtLastLoginSort = dtLastLoginSort;
    }
}
