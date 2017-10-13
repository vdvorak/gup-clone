package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.model.profiles.phone.Phone;
import ua.com.gup.domain.profile.Profile;

public class PrivateProfileDTO extends DetailProfileDTO {

    @JsonProperty("mainPhone")
    private Phone mainPhone;

    public PrivateProfileDTO() {
    }

    public PrivateProfileDTO(Profile profile) {
        super(profile);
        this.mainPhone = profile.getMainPhone();
    }

    public Phone getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(Phone mainPhone) {
        this.mainPhone = mainPhone;
    }

}
