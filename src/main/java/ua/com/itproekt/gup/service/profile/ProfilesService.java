package ua.com.itproekt.gup.service.profile;

import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.profiles.ProfileRating;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.List;
import java.util.Set;

/**
 * The interface Profiles service.
 */
public interface ProfilesService {
    /**
     * Create profile.
     *
     * @param profile the profile
     */
    void createProfile(Profile profile);

    Profile findById(String id);

    /**
     * Read by id profile.
     *
     * @param id the id
     * @return the profile
     */
    Profile findByIdWholeProfile(String id);

    /**
     * Update profile profile.
     *
     * @param currentProfile the current profile
     * @return the profile
     */
    Profile editProfile(Profile currentProfile);

    /**
     * Delete profile by id.
     *
     * @param id the id
     */
    void deleteProfileById(String id);

    /**
     * Profile exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean profileExists(String id);

    /**
     * Profile exists with email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean profileExistsWithEmail(String email);

    /**
     * Find all profiles entity page.
     *
     * @param profileFilterOptions the profile filter options
     * @return the entity page
     */
    EntityPage<Profile> findAllProfiles(ProfileFilterOptions profileFilterOptions);

    /**
     * Find profile by username profile.
     *
     * @param username the username
     * @return the profile
     */
/*For message service*/
    Profile findProfileByUsername(String username);

    /**
     * Find profile by email profile.
     *
     * @param email the email
     * @return the profile
     */
    Profile findProfileByEmail(String email);

    /**
     * Create profile rating.
     *
     * @param profileId     the profile id
     * @param profileRating the profile rating
     */
    void createProfileRating(String profileId, ProfileRating profileRating);

    /**
     * Delete profile rating int.
     *
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return the int
     */
    int deleteProfileRating(String profileId, String profileRatingId);

    /**
     * Find profile rating profile.
     *
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return the profile
     */
    Profile findProfileRating(String profileId, String profileRatingId);

    /**
     * Profile rating exists boolean.
     *
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return the boolean
     */
    boolean profileRatingExists(String profileId, String profileRatingId);

    /**
     * Add friend.
     *
     * @param profileId       the profile id
     * @param friendProfileId the friend profile id
     */
    void addFriend(String profileId, String friendProfileId);

    Set<String> getMatchedNames(String term);

    void addContactToContactList(String profileOwnerContactListId, String contactId);
}
