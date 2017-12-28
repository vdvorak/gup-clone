package ua.com.gup.rent.service.dto.rent.offer.profile;

import ua.com.gup.rent.model.mongo.user.RentOfferProfile;

public class RentOfferAdminPrivateProfileDTO extends RentOfferPrivateProfileDTO {

    private Boolean ban;

    public RentOfferAdminPrivateProfileDTO(RentOfferProfile profile) {
        super(profile);
        this.ban = profile.getBan();

    }

    public Boolean getBan() {
        return ban;
    }

    public void setBan(Boolean ban) {
        this.ban = ban;
    }
}
