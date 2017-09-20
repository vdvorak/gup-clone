package ua.com.gup.service.profile;

import ua.com.gup.dto.OfferRegistration;
import ua.com.gup.dto.ProfileInfo;
import ua.com.gup.model.profiles.Profile;
import ua.com.gup.model.profiles.ProfileFilterOptions;
import ua.com.gup.model.profiles.ProfileRating;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * The interface Profiles service.
 */
public interface ProfilesService {
    /**
     * Create profile.
     *
     * @param profile                   - the profile.
     */
    void createProfile(Profile profile);

    /**
     * Used for create profile with special roles i.e. admin, moderator, spectator.
     *
     * @param profile                   - the Profile object with email, password and role.
     */
    void createProfileWithRoles(Profile profile);

    /**
     * Create profile based on information contains in the OfferRegistration object.
     *
     * @param offerRegistration         - the OfferRegistration object.
     * @return                          - the registered profile.
     */
    Profile createProfileFromOfferRegistration(OfferRegistration offerRegistration);

    /**
     * Create profile.
     *
     * @param profile                   - the Profile object.
     */
    void facebookRegister(Profile profile);


    /**
     * Find profile by it's ID.
     *
     * @param id                        - the profile ID.
     * @return                          - the profile.
     */
    Profile findById(String id);

    Profile findByPublicId(String id);

    /**
     * Find and return whole profile by it's ID.
     *
     * @param id                        - the profile ID.
     * @return                          - the profile.
     */
    Profile findWholeProfileById(String id);

    /**
     * Find whole profile by email.
     *
     * @param email                     - the profile email.
     * @return                          - the profile.
     */
    Profile findWholeProfileByEmail(String email);


    /**
     * Update profile profile.
     *
     * @param currentProfile            - the profile with updated data.
     * @return                          - the updated profile.
     */
    Profile editProfile(Profile currentProfile);

    /**
     * Delete profile by it's ID.
     *
     * @param id                        - the profile ID.
     */
    void deleteProfileById(String id);

    /**
     * Profile exists boolean.
     *
     * @param id                        - the profile ID.
     * @return                          - the boolean answer.
     */
    boolean profileExists(String id);

    boolean profilePublicExists(String id);

    /**
     * Profile exists with email boolean.
     *
     * @param email                     - the profile email.
     * @return                          - the boolean
     */
    boolean profileExistsWithEmail(String email);

    boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber);

    /**
     * Profile exists with uid boolean.
     *
     * @param uid                       - the uid.
     * @return                          - the boolean.
     */
    boolean profileExistsWithUid(String uid);

    /**
     * Profile exists with tokenKey boolean.
     *
     * @param uid                       - the uid.
     * @param socWendor                 - the socWendor.
     * @return                          - the boolean.
     */
    boolean profileExistsWithUidAndWendor(String uid, String socWendor);

    /**
     * Profile with tokenKey Profile.
     *
     * @param uid                       - the uid.
     * @param socWendor                 - the socWendor.
     * @return                          - the Profile.
     */
    Profile findProfileByUidAndWendor(String uid, String socWendor);

    Profile findProfileByPhoneNumberAndWendor(String phoneNumber, String socWendor);

    /**
     * Profile exists with socWendor boolean.
     *
     * @param socWendor                 - the socWendor.
     * @return                          - the boolean.
     */
    boolean profileExistsWithSocWendor(String socWendor);

    /**
     * Find all profiles entity page.
     *
     * @param profileFilterOptions      - the profile filter options.
     * @return                          - the list of profiles.
     */
    List<Profile> findAllProfiles(ProfileFilterOptions profileFilterOptions);

    /**
     * This method provides additional information for admin.
     *
     * @param profileFilterOptions      - the profile filter options.
     * @return                          - the list of profiles.
     */
    List<Profile> findAllProfilesForAdmin(ProfileFilterOptions profileFilterOptions);

    /**
     * Return list of profiles for admin-panel in short and light version without unnecessary fields.
     *
     * @param profileFilterOptions      - the profile filter options
     * @return                          - the list of profiles
     */
    List<Profile> findAllProfilesForAdminShort(ProfileFilterOptions profileFilterOptions);

    /**
     * Find profile by profile's username.
     *
     * @param username                  - the username.
     * @return                          - the profile.
     */
    Profile findProfileByUsername(String username);

    /**
     * Find profile by email profile.
     *
     * @param email                     - the email.
     * @return                          - the profile.
     */
    Profile findProfileByEmail(String email);

    Profile findProfileByMainPhone(String mainPhone);

    /**
     * Create profile rating.
     *
     * @param profileId                 - the profile ID.
     * @param profileRating             - the profile rating.
     */
    void createProfileRating(String profileId, ProfileRating profileRating);

    /**
     * Delete profile rating int.
     *
     * @param profileId                 - the profile ID
     * @param profileRatingId           - the profile rating ID
     * @return - the int.
     */
    int deleteProfileRating(String profileId, String profileRatingId);

    /**
     * Find profile rating profile.
     *
     * @param profileId                 - the profile id.
     * @param profileRatingId           - the profile rating id.
     * @return                          - the profile
     */
    Profile findProfileRating(String profileId, String profileRatingId);

    /**
     * Profile rating exists boolean.
     *
     * @param profileId                 - the profile id.
     * @param profileRatingId           - the profile rating id.
     * @return the boolean
     */
    boolean profileRatingExists(String profileId, String profileRatingId);

//    /**
//     * Add friend.
//     *
//     * @param profileId               - the profile id.
//     * @param friendProfileId         - the friend profile id.
//     */
//    void addFriend(String profileId, String friendProfileId);

    /**
     * Search for matched user names and return set of them.
     *
     * @param term                      - the part of name that must be searched.
     * @return                          - the set of the user names.
     */
    Set<String> getMatchedNames(String term);

    /**
     * Search for matched all admin ids and return set of them.
     */
    Set<String> getAdminIdAll();

    Set<String> getAdminIdAllByOnline();

    /**
     * Search for matched admin id and return of his.
     */
    String getAdminId();

    String getAdminIdByOnline();

    /**
     * Search for matched user ID's and return set of them.
     *
     * @param term                      - the ID or ID's part of the user.
     * @return                          - the list of matched profiles.
     */
    List<Profile> getMatchedNamesWithIds(String term);

    /**
     * Search for matched companies and return set of the profiles.
     *
     * @param term                      - the company name or it's part.
     * @return                          - the list of profiles.
     */
    List<Profile> getMatchedCompanies(String term);

    /**
     * Add contact (profile) to eht contact list.
     *
     * @param profileOwnerContactListId - the profile which add contact.
     * @param contactId                 - the profile ID which must be added.
     */
    void addContactToContactList(String profileOwnerContactListId, String contactId);

    /**
     *
     * @param profileOwnerSocialListId
     * @param url
     */
    void addSocialToSocialList(String profileOwnerSocialListId, String url);


    /**
     * Check is user moderator or not.
     *
     * @param user                      - the profile.
     * @return                          - the true or false.
     */
    boolean isUserModerator(Profile user);

    /**
     * Check is user Administrator by it's ID.
     *
     * @param userId                    - the user ID.
     * @return                          - the true or false.
     */
    boolean isUserAdminById(String userId);

    /**
     * Check is SeoWord is free.
     *
     * @param seoWord                   - the seoWord.
     * @return                          - true or false.
     */
    boolean isSeoWordFree(String seoWord);

    /**
     * Check is user online or not.
     *
     * @param userId                    - the user ID.
     * @return                          - true or false.
     */
    boolean isUserOnline(String userId);

    /**
     * This method find and return profile by it's ID but previously delete some fields.
     *
     * @param id                        - the profile ID.
     * @return                          - the profile.
     */
    ProfileInfo findPublicProfileById(String id);

    ProfileInfo findPublicProfileByPublicId(String id);

    /**
     * Find private profile by ID and update login date.
     *
     * @param id                        - the profile ID.
     * @return                          - the ProfileInfo object.
     */
    ProfileInfo findPrivateProfileByIdAndUpdateLastLoginDate(String id);

    ProfileInfo incMainPhoneViewsAtOne(String id);

    /**
     * Find private profile by email and update login date.
     *
     * @param email                     - the profile email.
     * @return                          - the ProfileInfo object.
     */
    ProfileInfo findPrivateProfileByEmailAndUpdateLastLoginDate(String email);

    ProfileInfo findPrivateProfileByEmail(String email);

    /**
     * Find private profile by UID and socVendor and update login date.
     *
     * @param uid                       - the profile UID.
     * @param socWendor                 - the profile socVendor.
     * @return                          - the ProfileInfo object.
     */
    ProfileInfo findPrivateProfileByUidAndUpdateLastLoginDate(String uid, String socWendor);

    ProfileInfo findPrivateProfileByPhoneNumberdAndUpdateLastLoginDate(String PhoneNumberd, String socWendor);

    /**
     * Find public profiles with filter options.
     *
     * @param profileFilterOptions      - the ProfileFilterOptions object.
     * @return                          - the list of the ProfileInfo objects.
     */
    List<ProfileInfo> findAllPublicProfilesWithOptions(ProfileFilterOptions profileFilterOptions);

    /**
     * If User is logged in - return Profile Info, if not - return null;
     *
     * @param request                   - the HttpServletRequest object.
     * @return                          - the ProfileInfo object if user is loggedIn, or null if not.
     */
    ProfileInfo getLoggedUser(HttpServletRequest request);


    /**
     * Delete one contact from user contact list.
     *
     * @param profileId                 - the ID of the profile which must be deleted.
     */
    void deleteFromMyContactList(String profileId);

    void deleteFromMySocialList(String profileId);

    /**
     * Add or delete offer into offer favorite list.
     *
     * @param offerId - the offer ID which must be add or delete to/from offer favorite list.
     */
    void updateFavoriteOffers(String offerId);
}
