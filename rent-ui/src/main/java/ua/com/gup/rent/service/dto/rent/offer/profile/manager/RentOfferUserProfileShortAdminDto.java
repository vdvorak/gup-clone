package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferProfileShortAdminDTO;

public class RentOfferUserProfileShortAdminDto extends RentOfferProfileShortAdminDTO {

    private String managerId;

    public RentOfferUserProfileShortAdminDto(RentOfferProfile profile, String managerPublicId) {
        super(profile);
        this.managerId = managerPublicId;
    }
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
