package ua.com.gup.dto.profile.manager;

import ua.com.gup.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.composition.domain.profile.UserProfile;

public class UserProfileShortAdminDto extends ProfileShortAdminDTO {

    private String managerId;

    public UserProfileShortAdminDto(UserProfile profile) {
        super(profile);
        this.managerId = ((UserProfile)profile).getManager();
    }
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
