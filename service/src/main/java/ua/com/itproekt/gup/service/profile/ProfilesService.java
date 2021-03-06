package ua.com.itproekt.gup.service.profile;

import ua.com.itproekt.gup.dto.ProfileInfo;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.profiles.ProfileRating;

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

    /**
     * Used for create profile with special roles i.e. admin, moderator, spectator.
     * @param profile the Profile object with email, password and role.
     */
    void createProfileWithRoles(Profile profile);

    /**
     * Create profile.
     *
     * @param profile the profile
     */
    void facebookRegister(Profile profile);

    Profile findById(String id);

    /**
     * Read by id profile.
     *
     * @param id the id
     * @return the profile
     */
    Profile findWholeProfileById(String id);

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
     * Profile with tokenKey Profile.
     *
     * @param uid       the uid
     * @param socWendor the socWendor
     * @return the Profile
     */
    Profile findProfileByUidAndWendor(String uid, String socWendor);

    /**
     * Profile exists with socWendor boolean.
     *
     * @param socWendor the socWendor
     * @return the boolean
     */
    boolean profileExistsWithSocWendor(String socWendor);

    /**
     * Find all profiles entity page.
     *
     * @param profileFilterOptions the profile filter options
     * @return the entity page
     */
    List<Profile> findAllProfiles(ProfileFilterOptions profileFilterOptions);

    /**
     * This method provides additional information for admin.
     *
     * @param profileFilterOptions profile filter options
     * @return
     */
    List<Profile> findAllProfilesForAdmin(ProfileFilterOptions profileFilterOptions);

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

//    /**
//     * Add friend.
//     *
//     * @param profileId       the profile id
//     * @param friendProfileId the friend profile id
//     */
//    void addFriend(String profileId, String friendProfileId);

    /**
     * @param term
     * @return
     */
    Set<String> getMatchedNames(String term);

    /**
     * @param term
     * @return
     */
    List<Profile> getMatchedNamesWithIds(String term);

    /**
     * @param term
     * @return
     */
    List<Profile> getMatchedCompanies(String term);

    /**
     * @param profileOwnerContactListId
     * @param contactId
     */
    void addContactToContactList(String profileOwnerContactListId, String contactId);

    /**
     * @param email
     * @return
     */
    Profile findWholeProfileByEmail(String email);

    /**
     * @param user
     * @return
     */
    boolean isUserModerator(Profile user);

    /**
     * @param userId
     * @return
     */
    boolean isUserAdminById(String userId);

    /**
     * @param seoWord
     * @return
     */
    boolean isSeoWordFree(String seoWord);

    /**
     * @param userId
     * @return
     */
    boolean isUserOnline(String userId);

    /**
     * @param id
     * @return
     */
    ProfileInfo findPublicProfileById(String id);

    /**
     * @param id
     * @return
     */
    ProfileInfo findPrivateProfileByIdAndUpdateLastLoginDate(String id);

    /**
     * @param email
     * @return
     */
    ProfileInfo findPrivateProfileByEmailAndUpdateLastLoginDate(String email);

    /**
     * @param uid
     * @return
     */
    ProfileInfo findPrivateProfileByUidAndUpdateLastLoginDate(String uid, String socWendor);

    /**
     * @param profileFilterOptions
     * @return
     */
    List<ProfileInfo> findAllPublicProfilesWithOptions(ProfileFilterOptions profileFilterOptions);
}
