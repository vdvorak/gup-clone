package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import ua.com.gup.rent.model.mongo.user.RentOfferUserProfile;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferAdminPrivateProfileDTO;

public class RentOfferUserPrivateProfileDto extends RentOfferAdminPrivateProfileDTO {

    /**
     * public id of manager
     * */
    private String manager;

    private String managerInfo;

    public RentOfferUserPrivateProfileDto(RentOfferUserProfile profile, String mangerPublicId) {
        super(profile);
        this.manager = mangerPublicId;
        this.managerInfo = profile.getManagerInfo();
    }

    public String getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(String managerInfo) {
        this.managerInfo = managerInfo;
    }


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }


}
