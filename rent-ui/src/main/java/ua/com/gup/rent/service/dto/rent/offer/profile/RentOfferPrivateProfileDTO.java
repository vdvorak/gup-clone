package ua.com.gup.rent.service.dto.rent.offer.profile;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.rent.profiles.phone.RentOfferPhone;

public class RentOfferPrivateProfileDTO extends RentOfferDetailProfileDTO {

    @JsonProperty("mainPhone")
    private RentOfferPhone mainPhone;

    @JsonProperty("chatUID")
    private String chatUID;

    public RentOfferPrivateProfileDTO() {
    }

    public RentOfferPrivateProfileDTO(RentOfferProfile profile) {
        super(profile);
        this.mainPhone = profile.getMainPhone();
        this.chatUID = profile.getChatUID();
    }

    public RentOfferPhone getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(RentOfferPhone mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getChatUID() {
        return chatUID;
    }

    public void setChatUID(String chatUID) {
        this.chatUID = chatUID;
    }

}
