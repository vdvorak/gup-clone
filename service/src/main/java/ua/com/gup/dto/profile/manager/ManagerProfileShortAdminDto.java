package ua.com.gup.dto.profile.manager;

import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.mongo.composition.domain.profile.ManagerProfile;

import java.util.Set;

public class ManagerProfileShortAdminDto extends ProfileShortAdminDTO {
    private Set<String> users;

    public ManagerProfileShortAdminDto(ManagerProfile profile) {
        super(profile);
        this.users = profile.getUsers();
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }
}
