package ua.com.gup.rent.service.profile;


import ua.com.gup.rent.model.mongo.user.RentOfferProfile;

/**
 * The interface Profiles service.
 */
public interface RentOfferProfilesService {

    /**
     * Find profile by it's ID.
     *
     * @param id - the profile ID.
     * @return - the profile.
     */
    RentOfferProfile findById(String id);

    RentOfferProfile findByPublicId(String id);


}