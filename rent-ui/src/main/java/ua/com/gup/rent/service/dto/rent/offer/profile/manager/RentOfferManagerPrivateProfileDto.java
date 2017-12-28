package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import ua.com.gup.rent.model.mongo.user.RentOfferManagerProfile;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferAdminPrivateProfileDTO;

import java.util.Set;

public class RentOfferManagerPrivateProfileDto extends RentOfferAdminPrivateProfileDTO {

    /**
     * users public id of manager
     * */
    private Set<String> users ;


    public RentOfferManagerPrivateProfileDto(RentOfferManagerProfile profile, Set<String> users) {
        super(profile);
        this.users = users;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }
}
