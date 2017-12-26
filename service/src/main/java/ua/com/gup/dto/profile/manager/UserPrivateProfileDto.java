package ua.com.gup.dto.profile.manager;

import ua.com.gup.dto.profile.AdminPrivateProfileDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.composition.domain.profile.UserProfile;

public class UserPrivateProfileDto extends AdminPrivateProfileDTO {

    /**
     * public id of manager
     * */
    private String manager;

    private String managerInfo;

    public UserPrivateProfileDto(UserProfile profile, String mangerPublicId) {
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
