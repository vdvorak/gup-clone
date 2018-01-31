package ua.com.gup.dto.profile.manager;

import ua.com.gup.common.dto.profile.AdminPrivateProfileDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.composition.domain.profile.manager.SaleManagerClientInfo;

public class UserPrivateProfileDto extends AdminPrivateProfileDTO {

    /**
     * public id of manager
     * */
    private String manager;

    private SaleManagerClientInfo managerInfo;

    public UserPrivateProfileDto(Profile profile, String mangerPublicId) {
        super(profile);
        this.manager = mangerPublicId;
        this.managerInfo = profile.getManagerClientInfo();
    }

    public SaleManagerClientInfo getManagerInfo() {
        return managerInfo;
    }

    public void setManagerInfo(SaleManagerClientInfo managerInfo) {
        this.managerInfo = managerInfo;
    }


    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }


}
