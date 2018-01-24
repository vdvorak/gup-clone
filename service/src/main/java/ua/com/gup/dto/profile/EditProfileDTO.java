package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.model.mongo.Phone;
import ua.com.gup.mongo.composition.domain.profile.Profile;

import java.util.Set;

public class EditProfileDTO extends ProfileDTO {

    @JsonProperty("mainPhone")
    private Phone mainPhone;

    @JsonProperty("roles")
    private Set<String> roles;

    public EditProfileDTO() {
    }

    public Phone getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(Phone mainPhone) {
        this.mainPhone = mainPhone;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Profile updateModel(Profile profile) {
        profile.setFirstname(this.firstName);
        profile.setLastname(this.lastName);
        profile.setUserType(this.userType);

        //check email not Update if exists
        if (StringUtils.isEmpty(profile.getEmail()) && !StringUtils.isEmpty(this.email)) {
            profile.setEmail(this.email);
        }

        profile.setAddress(this.address);
        profile.setContactList(this.socialsList);
        profile.setStatus(this.status);
        profile.setContact(this.contact);
        profile.setMainPhone(this.mainPhone);
        profile.setUserRoles(this.roles);
        return profile;
    }
}
