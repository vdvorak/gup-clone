package ua.com.gup.service.profile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.dto.profile.CreateProfileDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.profiles.ProfileRating;
import ua.com.gup.repository.profile.ProfileFilter;

import java.util.List;
import java.util.Set;

/**
 * The interface Profiles service.
 */
public interface ProfilesService extends CommonProfileService<Profile>{
    void createProfile(CreateProfileDTO profile);

    /**
     * Find profile by it's ID.
     *
     * @param id - the profile ID.
     * @return - the profile.
     */
    Profile findById(String id);



    /**
     * Update profile profile.
     *
     * @param currentProfile - the profile with updated data.
     * @return - the updated profile.
     */
    @Deprecated
    Profile editProfile(Profile currentProfile);

    void updateProfile(Profile profile);

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
     * Return list of profiles for admin-panel in short and light version without unnecessary fields.
     *
     * @param profileFilter - the profile filter
     * @return - the list of profiles
     */
    Page<ProfileShortAdminDTO> findAllProfilesForAdminShort(ProfileFilter profileFilter, Pageable pageable);

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

    /**
     * Search for matched user names and return set of them.
     *
     * @param term - the part of name that must be searched.
     * @return - the set of the user names.
     */
    Set<String> getMatchedNames(String term);

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

    ProfileDTO findPublicProfileByPublicId(String id);

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

    ProfileDTO findPrivateProfileDTOByPublicId(String publicId);

    ProfileDTO findPrivateProfileDTOForAdminByPublicId(String publicId);

    ProfileDTO findPrivateProfileDTOByPhoneNumberd(String phoneNumber, String socWendor);


    /**
     * Delete one contact from user contact list.
     *
     * @param profileId - the ID of the profile which must be deleted.
     */
    void deleteFromMyContactList(String profileId);

    void banProfile(Profile profile, String privateExp, String publicExp);

    void unbanProfile(Profile profile);

    Page<ProfileShortAdminDTO> findByRole(String role, Pageable pageable);


}
