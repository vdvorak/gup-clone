package ua.com.gup.rent.repository.profile;

import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;


public interface RentOfferProfileRepository extends RentOfferGenericRepository<RentOfferProfile, String> {

    RentOfferProfile loadByUsername(String username);
}
