package ua.com.gup.rent.repository.profile;

import ua.com.gup.rent.model.mongo.user.RentProfile;
import ua.com.gup.rent.repository.abstracted.generic.GenericRepository;

public interface RentProfileRepository extends GenericRepository<RentProfile, String> {

    RentProfile loadByUsername(String username);
}
