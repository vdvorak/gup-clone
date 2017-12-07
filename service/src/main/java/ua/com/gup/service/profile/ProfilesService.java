package ua.com.gup.service.profile;

import ua.com.gup.dto.profile.CreateProfileDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.profiles.ProfileFilterOptions;
import ua.com.gup.mongo.model.profiles.ProfileRating;
import ua.com.gup.dto.profile.ProfileDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * The interface Profiles service.
 */
public interface ProfilesService {
    void createProfile(CreateProfileDTO profile);

    /**
     * Create profile.
     *
     * @param profile - the profile.
     */
    void createProfile(Profile profile);


    /**
     * Create profile.
     *
     * @param profile - the Profile object.
     */
    void facebookRegister(Profile profile);


    /**
     * Find profile by it's ID.
     *
     * @param id - the profile ID.
     * @return - the profile.
     */
    Profile findById(String id);

    Profile findByPublicId(String id);

    /**
     * Find and return whole profile by it's ID.
     *
     * @param id - the profile ID.
     * @return - the profile.
     */
    Profile findWholeProfileById(String id);

    /**
     * Find whole profile by email.
     *
     * @param email - the profile email.
     * @return - the profile.
     */
    Profile findWholeProfileByEmail(String email);


    /**
     * Update profile profile.
     *
     * @param currentProfile - the profile with updated data.
     * @return - the updated profile.
     */
    Profile editProfile(Profile currentProfile);

    /**
     * Delete profile by it's ID.
     *
     * @param id - the profile ID.
     */
    void deleteProfileById(String id);

    /**
     * Profile exists boolean.
     *
     * @param id - the profile ID.
     * @return - the boolean answer.
     */
    boolean profileExists(String id);

    boolean profileExistsByPublicId(String id);


    /**
     * Profile exists with email boolean.
     *
     * @param email - the profile email.
     * @return - the boolean
     */
    boolean profileExistsWithEmail(String email);

    boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber);

    /**
     * Profile exists with tokenKey boolean.
     *
     * @param uid       - the uid.
     * @param socWendor - the socWendor.
     * @return - the boolean.
     */
    boolean profileExistsWithUidAndWendor(String uid, String socWendor);

    /**
     * Profile with tokenKey Profile.
     *
     * @param uid       - the uid.
     * @param socWendor - the socWendor.
     * @return - the Profile.
     */
    Profile findProfileByUidAndWendor(String uid, String socWendor);

    Profile findProfileByPhoneNumberAndWendor(String phoneNumber, String socWendor);

    /**
     * This method provides additional information for admin.
     *
     * @param profileFilterOptions - the profile filter options.
     * @return - the list of profiles.
     */
    List<Profile> findAllProfilesForAdmin(ProfileFilterOptions profileFilterOptions);

    /**
     * Return list of profiles for admin-panel in short and light version without unnecessary fields.
     *
     * @param profileFilterOptions - the profile filter options
     * @return - the list of profiles
     */
    List<Profile> findAllProfilesForAdminShort(ProfileFilterOptions profileFilterOptions);

    /**
     * Find profile by profile's username.
     *
     * @param username - the username.
     * @return - the profile.
     */
    Profile findProfileByUsername(String username);

    /**
     * Find profile by email profile.
     *
     * @param email - the email.
     * @return - the profile.
     */
    Profile findProfileByEmail(String email);

    Profile findProfileByMainPhone(String mainPhone);

    /**
     * Create profile rating.
     *
     * @param profileId     - the profile ID.
     * @param profileRating - the profile rating.
     */
    void createProfileRating(String profileId, ProfileRating profileRating);

    /**
     * Delete profile rating int.
     *
     * @param profileId       - the profile ID
     * @param profileRatingId - the profile rating ID
     * @return - the int.
     */
    int deleteProfileRating(String profileId, String profileRatingId);

    /**
     * Find profile rating profile.
     *
     * @param profileId       - the profile id.
     * @param profileRatingId - the profile rating id.
     * @return - the profile
     */
    Profile findProfileRating(String profileId, String profileRatingId);

    /**
     * Profile rating exists boolean.
     *
     * @param profileId       - the profile id.
     * @param profileRatingId - the profile rating id.
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
     * @param term - the part of name that must be searched.
     * @return - the set of the user names.
     */
    Set<String> getMatchedNames(String term);


    /**
     * Search for matched admin id and return of his.
     */
    String getAdminId();

    String getAdminIdByOnline();

    /**
     * Search for matched user ID's and return set of them.
     *
     * @param term - the ID or ID's part of the user.
     * @return - the list of matched profiles.
     */
    List<Profile> getMatchedNamesWithIds(String term);

    /**
     * Search for matched companies and return set of the profiles.
     *
     * @param term - the company name or it's part.
     * @return - the list of profiles.
     */
    List<Profile> getMatchedCompanies(String term);


    void toggleProfileInUserSocialList(String userId, String publicProfileId);


    /**
     * Check is SeoWord is free.
     *
     * @param seoWord - the seoWord.
     * @return - true or false.
     */
    boolean isSeoWordFree(String seoWord);

    /**
     * This method find and return profile by it's ID but previously delete some fields.
     *
     * @param id - the profile ID.
     * @return - the profile.
     */
    ProfileDTO findPublicProfileById(String id);

    ProfileDTO findPublicProfileByPublicId(String id);

    /**
     * Find private profile by ID and update login date.
     *
     * @param id - the profile ID.
     * @return - the ProfileDTO object.
     */
    ProfileDTO findPrivateProfileByIdAndUpdateLastLoginDate(String id);

    ProfileDTO incMainPhoneViewsAtOne(String id);

    /**
     * Find private profile by email and update login date.
     *
     * @param email - the profile email.
     * @return - the ProfileDTO object.
     */
    ProfileDTO findPrivateProfileByEmailAndUpdateLastLoginDate(String email);

    /**
     * Find private profile by UID and socVendor and update login date.
     *
     * @param uid       - the profile UID.
     * @param socWendor - the profile socVendor.
     * @return - the ProfileDTO object.
     */
    ProfileDTO findPrivateProfileDTOByUid(String uid, String socWendor);

    ProfileDTO findPrivateProfileDTOByPhoneNumberd(String phoneNumber, String socWendor);

    /**
     * Find public profiles with filter options.
     *
     * @param profileFilterOptions - the ProfileFilterOptions object.
     * @return - the list of the ProfileDTO objects.
     */
    List<ProfileDTO> findAllPublicProfilesWithOptions(ProfileFilterOptions profileFilterOptions);

    /**
     * If User is logged in - return Profile Info, if not - return null;
     *
     * @param request - the HttpServletRequest object.
     * @return - the ProfileDTO object if user is loggedIn, or null if not.
     */
    ProfileDTO getLoggedUser(HttpServletRequest request) throws Exception;


    /**
     * Delete one contact from user contact list.
     *
     * @param profileId - the ID of the profile which must be deleted.
     */
    void deleteFromMyContactList(String profileId);

    /**
     * Add or delete offer into offer favorite list.
     *
     * @param offerId - the offer ID which must be add or delete to/from offer favorite list.
     */
    void updateFavoriteOffers(String offerId);
    
    void updateChatUID(String profileId, String uid);    
}
