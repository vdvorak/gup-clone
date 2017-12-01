package ua.com.gup.rent.repository.profile;

import ua.com.gup.rent.model.mongo.user.RentProfile;
import ua.com.gup.rent.repository.abstracted.generic.RentGenericRepository;


public interface RentProfileRepository extends RentGenericRepository<RentProfile, String> {

    RentProfile loadByUsername(String username);
}
