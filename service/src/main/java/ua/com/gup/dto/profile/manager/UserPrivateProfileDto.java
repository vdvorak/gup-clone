package ua.com.gup.dto.profile.manager;

import ua.com.gup.common.dto.profile.AdminPrivateProfileDTO;
import ua.com.gup.mongo.composition.domain.profile.SaleManagerUserInfo;
import ua.com.gup.mongo.composition.domain.profile.UserProfile;

public class UserPrivateProfileDto extends AdminPrivateProfileDTO {

    /**
     * public id of manager
     * */
    private String manager;

    private SaleManagerUserInfo managerInfo;

    public UserPrivateProfileDto(UserProfile profile, String mangerPublicId) {
        super(profile);
        this.manager = mangerPublicId;
        this.managerInfo = profile.getManagerInfo();
    }

    public SaleManagerUserInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(SaleManagerUserInfo managerInfo) {
        this.managerInfo = managerInfo;
    }


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }


}
