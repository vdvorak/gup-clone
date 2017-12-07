package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.StringUtils;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.rent.profiles.phone.RentOfferPhone;

public class RentOfferPublicProfileDTO extends RentOfferDetailProfileDTO {

    @JsonProperty("mainPhoneNumber")
    private String mainPhoneNumber;

    public RentOfferPublicProfileDTO() {
    }

    public RentOfferPublicProfileDTO(RentOfferProfile profile) {
        super(profile);
        //for publicProfile not show email user.
        this.setEmail("[PROTECTED]");
        RentOfferPhone mainPhone = profile.getMainPhone();
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
