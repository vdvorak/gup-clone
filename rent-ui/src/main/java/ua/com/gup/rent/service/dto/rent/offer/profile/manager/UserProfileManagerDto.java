package ua.com.gup.rent.service.dto.rent.offer.profile.manager;


import lombok.Data;
import ua.com.gup.common.dto.profile.AdminPrivateProfileDTO;
import ua.com.gup.rent.model.mongo.user.RentManagerUserInfo;
import ua.com.gup.rent.model.mongo.user.RentOfferManagerProfile;
import ua.com.gup.rent.model.mongo.user.RentOfferUserProfile;

@Data
public class UserProfileManagerDto extends AdminPrivateProfileDTO {
    private ManagerInfoUserProfileShortDto  managerInfo;

    public UserProfileManagerDto(RentOfferUserProfile profile, RentOfferManagerProfile manager) {
        super(profile);
        RentManagerUserInfo managerInfo = profile.getManagerInfo();
        if(managerInfo != null) {
            this.managerInfo = new ManagerInfoUserProfileShortDto(managerInfo, manager);
        }
    }
}
