package ua.com.gup.rent.repository.profile;

import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;


public interface RentOfferProfileRepository extends RentOfferGenericRepository<RentOfferProfile, String> {


    /**
     * Find and return profile by it's ID.
     *
     * @param id - the Profile ID
     * @return - profile
     */
    RentOfferProfile findById(String id);

    RentOfferProfile findByPublicId(String id);


    void save(RentOfferProfile profile);
}
