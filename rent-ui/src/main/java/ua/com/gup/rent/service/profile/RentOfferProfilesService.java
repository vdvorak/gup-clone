package ua.com.gup.rent.service.profile;


import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;

/**
 * The interface Profiles service.
 */
public interface RentOfferProfilesService extends CommonProfileService<RentOfferProfile> {

    /**
     * Find profile by it's ID.
     *
     * @param id - the profile ID.
     * @return - the profile.
     */


    RentOfferProfile findByPublicId(String id);


}