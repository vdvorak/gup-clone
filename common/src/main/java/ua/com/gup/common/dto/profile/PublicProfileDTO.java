package ua.com.gup.common.dto.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.Phone;

public class PublicProfileDTO extends DetailProfileDTO {

    @JsonProperty("mainPhoneNumber")
    private String mainPhoneNumber;

    public PublicProfileDTO() {
    }

    public PublicProfileDTO(CommonProfile profile) {
        super(profile);
        //for publicProfile not show email user.
        this.setEmail("[PROTECTED]");
        Phone mainPhone = profile.getMainPhone();
        if (!StringUtils.isEmpty(mainPhone.getPhoneNumber())) {
            this.mainPhoneNumber = mainPhone.getPhoneNumber();
            if (Boolean.TRUE.equals(mainPhone.getHidden())) {
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
