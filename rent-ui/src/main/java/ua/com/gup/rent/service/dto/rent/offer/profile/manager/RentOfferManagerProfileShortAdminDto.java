package ua.com.gup.rent.service.dto.rent.offer.profile.manager;

import ua.com.gup.rent.model.mongo.user.RentOfferManagerProfile;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferProfileShortAdminDTO;

import java.util.Set;

public class RentOfferManagerProfileShortAdminDto extends RentOfferProfileShortAdminDTO {
    private Set<String> users;

    public RentOfferManagerProfileShortAdminDto(RentOfferManagerProfile profile) {
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
