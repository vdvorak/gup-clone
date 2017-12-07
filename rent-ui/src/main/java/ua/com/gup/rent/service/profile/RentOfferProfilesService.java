package ua.com.gup.rent.service.profile;

import ua.com.gup.mongo.composition.domain.profile.Profile;

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
    Profile findById(String id);

    Profile findByPublicId(String id);

}
