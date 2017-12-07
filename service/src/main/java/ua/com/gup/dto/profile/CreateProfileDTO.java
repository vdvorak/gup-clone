package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.config.annotation.Password;
import ua.com.gup.mongo.model.enumeration.UserRole;
import ua.com.gup.mongo.model.profiles.phone.Phone;

import java.util.Set;

public class CreateProfileDTO extends ProfileDTO {

    private Set<UserRole> userRoles;

    @JsonProperty("password")
    @Password
    private String password;
    @JsonProperty("mainPhone")
    private Phone mainPhone;

    public CreateProfileDTO() {
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Phone getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(Phone mainPhone) {
        this.mainPhone = mainPhone;
    }
}
