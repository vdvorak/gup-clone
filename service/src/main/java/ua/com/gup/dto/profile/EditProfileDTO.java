package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.model.profiles.phone.Phone;
import ua.com.gup.domain.profile.Profile;

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
        profile.setEmail(this.email);
        profile.setExecutive(this.executive);
        profile.setAddress(this.address);
        profile.setContactPerson(this.contactPerson);
        profile.setImgId(this.imageId);
        profile.setContactList(this.socialsList);
        profile.setStatus(this.status);
        profile.setContact(this.contact);
        profile.setMainPhone(this.mainPhone);
        return profile;
    }
}
