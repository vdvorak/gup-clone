package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import ua.com.gup.common.dto.profile.AdminPrivateProfileDTO;
import ua.com.gup.rent.model.mongo.user.ManagerProfile;

import java.util.Set;

public class ManagerPrivateProfileDTO extends AdminPrivateProfileDTO {

    /**
     * users public id of manager
     * */
    private Set<String> users ;


    public ManagerPrivateProfileDTO(ManagerProfile profile, Set<String> users) {
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
