package ua.com.gup.rent.repository.profile;

import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.rent.profiles.RentOfferProfileFilterOptions;
import ua.com.gup.rent.model.rent.profiles.RentOfferProfileRating;
import ua.com.gup.rent.repository.abstracted.generic.RentOfferGenericRepository;

import java.util.List;
import java.util.Set;


public interface RentOfferProfileRepository extends RentOfferGenericRepository<RentOfferProfile, String> {

    RentOfferProfile loadByUsername(String username);

    void createProfile(RentOfferProfile profile);

    /**
     * Find profile and update profile.
     *
     * @param profile the profile
     * @return the profile
     */
    RentOfferProfile findProfileAndUpdate(RentOfferProfile profile);

    /**
     * Delete profile by id int.
     *
     * @param id the id
     * @return the int
     */
    int deleteProfileById(String id);

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

    boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber);

    /**
     * Profile exists with tokenKey boolean.
     *
     * @param uid       the uid
     * @param socWendor the socWendor
     * @return the boolean
     */
    boolean profileExistsWithUidAndWendor(String uid, String socWendor);

    /**
     * Profile exists with tokenKey boolean.
     *
     * @param uid       the uid
     * @param socWendor the socWendor
     * @return the Profile
     */
    RentOfferProfile findProfileByUidAndWendor(String uid, String socWendor);

    RentOfferProfile findProfileByPhoneNumberAndWendor(String phoneNumber, String socWendor);

    /**
     * Find all profiles entity page.
     *
     * @param profileFilterOptions  - the profile filter options
     * @return                      - the list of profiles
     */
    List<RentOfferProfile> findAllProfiles(RentOfferProfileFilterOptions profileFilterOptions);

    RentOfferProfile incMainPhoneViewsAtOne(String profileId);

    /**
     * This method provides additional information for admin.
     *
     * @param profileFilterOptions  - the ProfileFilterOptions object
     * @return                      - the list of relevant to filter profiles
     */
    List<RentOfferProfile> findAllProfilesForAdmin(RentOfferProfileFilterOptions profileFilterOptions);

    boolean profileExistsInUserSocialList(String userId, String profileId);

    void addProfileToUserSocialList(String userId, String profileId);

    void deleteProfileFromUserSocialList(String userId, String profileId);

    /**
     * Find by username profile.
     *
     * @param username the username
     * @return the profile
     */
    RentOfferProfile findByUsername(String username);

    /**
     * Find by profile email.
     *
     * @param email the email
     * @return the profile
     */
    RentOfferProfile findByEmail(String email);

    RentOfferProfile findProfileByMainPhone(String mainPhone);

    /**
     * Profile rating exists boolean.
     *
     * @param profileId         - the profile id
     * @param profileRatingId   - the profile rating id
     * @return                  - the boolean result
     */
    boolean profileRatingExists(String profileId, String profileRatingId);

    /**
     * Create profile rating with incrementation field "point".
     *
     * @param profileId         - the profile id
     * @param profileRating     - the profile rating
     */
    void createProfileRating(String profileId, RentOfferProfileRating profileRating);

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
    RentOfferProfile findProfileRating(String profileId, String profileRatingId);


    Set<String> getMatchedNames(String term);

    /**
     * return list full admin's
     */
    Set<String> getAdminIdAll();

    /**
     * return list by online admin's
     */
    Set<String> getAdminIdAllByOnline();

    /**
     * return random admin
     */
    String getAdminId();

    /**
     * return random admin by online
     */
    String getAdminIdByOnline();

    List<RentOfferProfile> getMatchedNamesToFindWithId(String term);

    /**
     * Find and return profiles with matched companies to input string argument.
     *
     * @param term  - the input string argument with companies name.
     * @return      - the profile list.
     */
    List<RentOfferProfile> getMatchedCompanies(String term);

    /**
     * Find and return profile by it's ID.
     *
     * @param id    - the Profile ID
     * @return      - profile
     */
    RentOfferProfile findById(String id);

    RentOfferProfile findByPublicId(String id);

    /**
     * Find and return profile by it's seoWord.
     *
     * @param seoWord   - the seoWord
     * @return          - the profile
     */
    RentOfferProfile findBySeoWord(String seoWord);

    void incrementProfileStatistic(String profileId, String field);

    void decrementProfileStatistic(String profileId, String field);

    boolean profileExistsByPublicId(String profilePublicId);

    void  save(RentOfferProfile profile);
}
