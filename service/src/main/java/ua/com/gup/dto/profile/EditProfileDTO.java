package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.profiles.phone.Phone;

public class EditProfileDTO extends ProfileDTO {

    @JsonProperty("mainPhone")
    private Phone mainPhone;

    public EditProfileDTO() {
    }

    public Phone getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(Phone mainPhone) {
        this.mainPhone = mainPhone;
    }


    public Profile updateModel(Profile profile) {
        profile.setFirstname(this.firstName);
        profile.setLastname(this.lastName);
        profile.setUserType(this.userType);

        //check email not Update if exists
        if (StringUtils.isEmpty(profile.getEmail()) && !StringUtils.isEmpty(this.email)) {
            profile.setEmail(this.email);
        }

        profile.setExecutive(this.executive);
        profile.setAddress(this.address);
        profile.setContactPerson(this.contactPerson);
        profile.setContactList(this.socialsList);
        profile.setStatus(this.status);
        profile.setContact(this.contact);
        profile.setMainPhone(this.mainPhone);
        return profile;
    }
}
