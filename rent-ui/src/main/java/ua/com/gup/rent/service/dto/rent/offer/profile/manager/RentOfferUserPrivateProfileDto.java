package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import ua.com.gup.rent.model.mongo.user.RentManagerUserInfo;
import ua.com.gup.rent.model.mongo.user.RentOfferUserProfile;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferAdminPrivateProfileDTO;

public class RentOfferUserPrivateProfileDto extends RentOfferAdminPrivateProfileDTO {

    /**
     * public id of manager
     * */
    private String manager;

    /**
     * additional info for manager about user(client)
     * */
    private RentManagerUserInfo managerInfo;

    public RentOfferUserPrivateProfileDto(RentOfferUserProfile profile, String mangerPublicId) {
        super(profile);
        this.manager = mangerPublicId;
        this.managerInfo = profile.getManagerInfo();
    }

    public RentManagerUserInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(RentManagerUserInfo managerInfo) {
        this.managerInfo = managerInfo;
    }


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }


}
