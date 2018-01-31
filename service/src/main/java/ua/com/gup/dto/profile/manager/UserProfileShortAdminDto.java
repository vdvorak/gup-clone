package ua.com.gup.dto.profile.manager;

import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;

public class UserProfileShortAdminDto extends ProfileShortAdminDTO {

    private String managerId;

    public UserProfileShortAdminDto(Profile profile, String managerPublicId) {
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
