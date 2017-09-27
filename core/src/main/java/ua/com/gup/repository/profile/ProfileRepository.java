package ua.com.gup.repository.profile;

import ua.com.gup.model.profiles.Profile;
import ua.com.gup.model.profiles.ProfileFilterOptions;
import ua.com.gup.model.profiles.ProfileRating;
import ua.com.gup.model.profiles.UserRole;

import java.util.List;
import java.util.Set;

/**
 * The interface Profile repository.
 */
public interface ProfileRepository {
    /**
     * Create profile.
     *
     * @param profile the profile
     */
    void createProfile(Profile profile);

    /**
     * Find profile and update profile.
     *
     * @param profile the profile
     * @return the profile
     */
    Profile findProfileAndUpdate(Profile profile);

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

    boolean profilePublicExists(String id);

    /**
     * Profile exists with email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean profileExistsWithEmail(String email);

    boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber);

    /**
     * Profile exists with socWendor boolean.
     *
     * @param socWendor the socWendor
     * @return the boolean
     */
    boolean profileExistsWithSocWendor(String socWendor);

    /**
     * Profile exists with uid boolean.
     *
     * @param uid the uid
     * @return the boolean
     */
    boolean profileExistsWithUid(String uid);

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
    Profile findProfileByUidAndWendor(String uid, String socWendor);

    Profile findProfileByPhoneNumberAndWendor(String phoneNumber, String socWendor);

    /**
     * Find all profiles entity page.
     *
     * @param profileFilterOptions  - the profile filter options
     * @return                      - the list of profiles
     */
    List<Profile> findAllProfiles(ProfileFilterOptions profileFilterOptions);

    Profile incMainPhoneViewsAtOne(String profileId);

    /**
     * This method provides additional information for admin.
     *
     * @param profileFilterOptions  - the ProfileFilterOptions object
     * @return                      - the list of relevant to filter profiles
     */
    List<Profile> findAllProfilesForAdmin(ProfileFilterOptions profileFilterOptions);

    /**
     * Find by username profile.
     *
     * @param username the username
     * @return the profile
     */
    Profile findByUsername(String username);

    /**
     * Find by profile email.
     *
     * @param email the email
     * @return the profile
     */
    Profile findByEmail(String email);

    Profile findProfileByMainPhone(String mainPhone);

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
     * Add new role to user.
     *
     * @param profileId - the profile ID.
     * @param userRole - the user role.
     */
    void addUserRole(String profileId, UserRole userRole);

    /**
     * Remove specific role from user.
     *
     * @param profileId     - the profile ID
     * @param userRole      - the user role which need to be remove
     */
    void deleteUserRole(String profileId, UserRole userRole);

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

    List<Profile> getMatchedNamesToFindWithId(String term);

    /**
     * Find and return profiles with matched companies to input string argument.
     *
     * @param term  - the input string argument with companies name.
     * @return      - the profile list.
     */
    List<Profile> getMatchedCompanies(String term);

    /**
     * Add new contact to user's contact list.
     *
     * @param profileOwnerContactListId - the user ID in which contact list we will add another contact
     * @param contactId                 - the contact ID of user which we want to add
     */
    void addContactToContactList(String profileOwnerContactListId, String contactId);

    /**
     *
     * @param userId
     * @param profileId
     */
    void addSocialToSocialList(String userId, String profileId);

    /**
     * Find and return profile by it's ID.
     *
     * @param id    - the Profile ID
     * @return      - profile
     */
    Profile findById(String id);

    Profile findByPublicId(String id);

    /**
     * Find and return profile by it's seoWord.
     *
     * @param seoWord   - the seoWord
     * @return          - the profile
     */
    Profile findBySeoWord(String seoWord);

}

