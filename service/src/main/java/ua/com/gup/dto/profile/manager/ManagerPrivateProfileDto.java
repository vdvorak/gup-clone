package ua.com.gup.dto.profile.manager;

import ua.com.gup.common.dto.profile.AdminPrivateProfileDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;

import java.util.Set;

public class ManagerPrivateProfileDto extends AdminPrivateProfileDTO {

    /**
     * users public id of manager
     * */
    private Set<String> users ;


    public ManagerPrivateProfileDto(Profile profile, Set<String> users) {
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
