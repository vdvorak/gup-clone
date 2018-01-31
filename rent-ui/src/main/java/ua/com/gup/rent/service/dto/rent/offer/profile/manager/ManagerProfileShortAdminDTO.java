package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.rent.model.mongo.user.ManagerProfile;

import java.util.Set;

public class ManagerProfileShortAdminDTO extends ProfileShortAdminDTO {
    private Set<String> users;

    public ManagerProfileShortAdminDTO(ManagerProfile profile) {
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
