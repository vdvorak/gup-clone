package ua.com.gup.common.dto.profile.manager;

import ua.com.gup.common.dto.profile.AdminPrivateProfileDTO;
import ua.com.gup.common.model.mongo.CommonProfile;

import java.util.Set;

public class ManagerPrivateProfileDto  extends AdminPrivateProfileDTO {
    /**
     * users public id of manager
     * */
    private Set<String> users ;


    public ManagerPrivateProfileDto(CommonProfile profile, Set<String> users) {
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
