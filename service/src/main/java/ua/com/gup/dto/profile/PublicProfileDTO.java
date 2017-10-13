package ua.com.gup.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;
import ua.com.gup.domain.profile.Profile;
import ua.com.gup.model.profiles.phone.Phone;

public class PublicProfileDTO extends DetailProfileDTO {

    @JsonProperty("mainPhoneNumber")
    private String mainPhoneNumber;

    public PublicProfileDTO() {
    }

    public PublicProfileDTO(Profile profile) {
        super(profile);
        Phone mainPhone = profile.getMainPhone();
        if (!StringUtils.isEmpty(mainPhone.getPhoneNumber())) {
            this.mainPhoneNumber = mainPhone.getPhoneNumber();
            if (Boolean.TRUE.equals(mainPhone.isHidden())) {
                this.mainPhoneNumber = this.mainPhoneNumber.replaceAll("(?s).", "*");
            }
        }

    }

    public String getMainPhoneNumber() {
        return mainPhoneNumber;
    }

    public void setMainPhoneNumber(String mainPhoneNumber) {
        this.mainPhoneNumber = mainPhoneNumber;
    }
}
