//package ua.com.itproekt.gup.dao.profile;
//
//import ua.com.itproekt.gup.model.profiles.LawyerProfile;
//import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
//import ua.com.itproekt.gup.model.profiles.ProfileRating;
//import ua.com.itproekt.gup.model.profiles.UserRole;
//
//import java.util.List;
//import java.util.Set;
//
///**
// * The interface LawyerProfile repository.
// */
//public interface LawyerProfileRepository {
//    /**
//     * Create profile.
//     *
//     * @param lawyerProfile the profile
//     */
//    void createProfile(LawyerProfileRepository lawyerProfile);
//
//    /**
//     * Find profile and update profile.
//     *
//     * @param lawyerProfile the profile
//     * @return the profile
//     */
//    LawyerProfile findProfileAndUpdate(LawyerProfile lawyerProfile);
//
//    /**
//     * Delete profile by id int.
//     *
//     * @param id the id
//     * @return the int
//     */
//    int deleteProfileById(String id);
//
//    /**
//     * Profile exists boolean.
//     *
//     * @param id the id
//     * @return the boolean
//     */
//    boolean profileExists(String id);
//
//    boolean profilePublicExists(String id);
//
//    /**
//     * Profile exists with email boolean.
//     *
//     * @param email the email
//     * @return the boolean
//     */
//    boolean profileExistsWithEmail(String email);
//
//    boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber);
//
//    /**
//     * Profile exists with tokenKey boolean.
//     *
//     * @param uid       the uid
//     * @param socWendor the socWendor
//     * @return the LawyerProfile
//     */
//    LawyerProfile findProfileByUidAndWendor(String uid, String socWendor);
//
//    LawyerProfile findProfileByPhoneNumberAndWendor(String phoneNumber, String socWendor);
//
//    /**
//     * Find all profiles entity page.
//     *
//     * @param profileFilterOptions  - the profile filter options
//     * @return                      - the list of profiles
//     */
//    List<LawyerProfile> findAllProfiles(ProfileFilterOptions profileFilterOptions);
//
//    LawyerProfile incMainPhoneViewsAtOne(String profileId);
//
//    /**
//     * This method provides additional information for admin.
//     *
//     * @param profileFilterOptions  - the ProfileFilterOptions object
//     * @return                      - the list of relevant to filter profiles
//     */
//    List<LawyerProfile> findAllProfilesForAdmin(ProfileFilterOptions profileFilterOptions);
//
//    /**
//     * Find by username profile.
//     *
//     * @param username the username
//     * @return the profile
//     */
//    LawyerProfile findByUsername(String username);
//
//    /**
//     * Find by profile email.
//     *
//     * @param email the email
//     * @return the profile
//     */
//    LawyerProfile findByEmail(String email);
//
//    LawyerProfile findProfileByMainPhone(String mainPhone);
//
//    /**
//     * Profile rating exists boolean.
//     *
//     * @param profileId         - the profile id
//     * @param profileRatingId   - the profile rating id
//     * @return                  - the boolean result
//     */
//    boolean profileRatingExists(String profileId, String profileRatingId);
//
//    /**
//     * Create profile rating with incrementation field "point".
//     *
//     * @param profileId         - the profile id
//     * @param profileRating     - the profile rating
//     */
//    void createProfileRating(String profileId, ProfileRating profileRating);
//
//    /**
//     * Delete profile rating int.
//     *
//     * @param profileId       the profile id
//     * @param profileRatingId the profile rating id
//     * @return the int
//     */
//    int deleteProfileRating(String profileId, String profileRatingId);
//
//    /**
//     * Find profile rating profile.
//     *
//     * @param profileId       the profile id
//     * @param profileRatingId the profile rating id
//     * @return the profile
//     */
//    LawyerProfile findProfileRating(String profileId, String profileRatingId);
//
//    /**
//     * Add new role to user.
//     *
//     * @param profileId - the profile ID.
//     * @param userRole - the user role.
//     */
//    void addUserRole(String profileId, UserRole userRole);
//
//    /**
//     * Remove specific role from user.
//     *
//     * @param profileId     - the profile ID
//     * @param userRole      - the user role which need to be remove
//     */
//    void deleteUserRole(String profileId, UserRole userRole);
//
//    Set<String> getMatchedNames(String term);
//
//    List<LawyerProfile> getMatchedNamesToFindWithId(String term);
//
//    /**
//     * Find and return profiles with matched companies to input string argument.
//     *
//     * @param term  - the input string argument with companies name.
//     * @return      - the profile list.
//     */
//    List<LawyerProfile> getMatchedCompanies(String term);
//
//    /**
//     * Add new contact to user's contact list.
//     *
//     * @param profileOwnerContactListId - the user ID in which contact list we will add another contact
//     * @param contactId                 - the contact ID of user which we want to add
//     */
//    void addContactToContactList(String profileOwnerContactListId, String contactId);
//
//    /**
//     *
//     * @param userId
//     * @param profileId
//     */
//    void addSocialToSocialList(String userId, String profileId);
//
//    /**
//     * Find and return profile by it's ID.
//     *
//     * @param id    - the Profile ID
//     * @return      - profile
//     */
//    LawyerProfile findById(String id);
//
//    LawyerProfile findByPublicId(String id);
//
//    /**
//     * Find and return profile by it's seoWord.
//     *
//     * @param seoWord   - the seoWord
//     * @return          - the profile
//     */
//    LawyerProfile findBySeoWord(String seoWord);
//
//}
//
