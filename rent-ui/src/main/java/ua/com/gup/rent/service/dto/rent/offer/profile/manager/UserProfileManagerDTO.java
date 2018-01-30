package ua.com.gup.rent.service.dto.rent.offer.profile.manager;


import lombok.Data;
import ua.com.gup.common.dto.profile.AdminPrivateProfileDTO;
import ua.com.gup.rent.model.mongo.user.ExtendManagerUserInfo;
import ua.com.gup.rent.model.mongo.user.ManagerProfile;
import ua.com.gup.rent.model.mongo.user.UserProfile;

@Data
public class UserProfileManagerDTO extends AdminPrivateProfileDTO {
    private ManagerInfoUserProfileShortDTO managerInfo;

    public UserProfileManagerDTO(UserProfile profile, ManagerProfile manager) {
        super(profile);
        ExtendManagerUserInfo managerInfo = profile.getManagerInfo();
        if(managerInfo != null) {
            this.managerInfo = new ManagerInfoUserProfileShortDTO(managerInfo, manager);
        }
    }
}
