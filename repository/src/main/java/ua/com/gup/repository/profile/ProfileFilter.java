package ua.com.gup.repository.profile;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.common.model.enumeration.CommonUserRole;

import java.util.Set;

public class ProfileFilter {
    @ApiModelProperty(position = 10, example = "username")
    private String username;
    @ApiModelProperty(position = 20, example = "email")
    private String email;
    @ApiModelProperty(position = 30, example = "id100000623")
    private String publicId;
    @ApiModelProperty(position = 40, example = "[ROLE_ADMIN | ROLE_MODERATOR | ROLE_MANAGER | ROLE_USER]")
    private Set<CommonUserRole> userRoles;
    @ApiModelProperty(position = 50, example = "0930000000")
    private String mainPhone;
    @ApiModelProperty(position = 60, example = "0930000000")
    private String additionalPhone;

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
}
