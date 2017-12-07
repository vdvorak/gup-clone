package ua.com.gup.rent.service.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.gup.dto.profile.PrivateProfileDTO;
import ua.com.gup.dto.profile.ProfileDTO;
import ua.com.gup.dto.profile.PublicProfileDTO;
import ua.com.gup.mongo.composition.domain.oauth2.OAuth2AuthenticationAccessToken;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.login.LoggedUser;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;
import ua.com.gup.util.LogUtil;
import ua.com.gup.util.security.SecurityUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class RentOfferProfilesServiceImpl implements RentOfferProfilesService {
    private final Logger log = LoggerFactory.getLogger(RentOfferProfilesServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RentOfferProfileRepository rentOfferProfileRepository;


    @Override
    public RentOfferProfile findById(String id) {
        return rentOfferProfileRepository.findById(id);
    }

    @Override
    public Profile findByPublicId(String id) {
        return rentOfferProfileRepository.findByPublicId(id);
    }


    @Override
    public Profile findWholeProfileById(String id) {
        return rentOfferProfileRepository.findById(id);
    }


    @Override
    public Profile editProfile(Profile profile) {
        return rentOfferProfileRepository.findProfileAndUpdate(profile);
    }


    @Override
    public void deleteProfileById(String id) {
        rentOfferProfileRepository.deleteProfileById(id);
    }


    @Override
    public boolean profileExists(String id) {
        return rentOfferProfileRepository.profileExists(id);
    }

    @Override
    public boolean profileExistsByPublicId(String id) {
        return rentOfferProfileRepository.profileExistsByPublicId(id);
    }

    @Override
    public boolean profileExistsWithEmail(String email) {
        return rentOfferProfileRepository.profileExistsWithEmail(email);
    }

    @Override
    public boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber) {
        return rentOfferProfileRepository.profileExistsWithMainPhoneNumber(mainPhoneNumber);
    }


    @Override
    public boolean profileExistsWithUidAndWendor(String uid, String socWendor) {
        return rentOfferProfileRepository.profileExistsWithUidAndWendor(uid, socWendor);
    }


    @Override
    public List<Profile> findAllProfilesForAdmin(ProfileFilterOptions profileFilterOptions) {
        return rentOfferProfileRepository.findAllProfilesForAdmin(profileFilterOptions);
    }


    @Override
    public List<Profile> findAllProfilesForAdminShort(ProfileFilterOptions profileFilterOptions) {
        List<Profile> fullProfiles = rentOfferProfileRepository.findAllProfilesForAdmin(profileFilterOptions);
        removeUnnecessaryFieldsFromProfileForAdminUse(fullProfiles);

        return fullProfiles;
    }

    @Override
    public Profile findProfileByUsername(String username) {
        return rentOfferProfileRepository.findByUsername(username);
    }


    @Override
    public Profile findProfileByEmail(String email) {
        return rentOfferProfileRepository.findByEmail(email);
    }

    @Override
    public Profile findProfileByMainPhone(String mainPhone) {
        return rentOfferProfileRepository.findProfileByMainPhone(mainPhone);
    }


    @Override
    public Profile findWholeProfileByEmail(String email) {
        return rentOfferProfileRepository.findByEmail(email);
    }


    @Override
    public boolean isSeoWordFree(String seoWord) {
        Profile profile = rentOfferProfileRepository.findBySeoWord(seoWord);
        return profile == null;
    }


    @Override
    public void createProfileRating(String profileId, ProfileRating profileRating) {
        ProfileRating newProfileRating = new ProfileRating()
                .setEarnPoints(profileRating.getEarnPoints())
                .setShortDescription(profileRating.getShortDescription())
                .setLongDescription(profileRating.getLongDescription())
                .setIdAttachedFile(profileRating.getIdAttachedFile())
                .setCreatedDateEqualsToCurrentDate();
        rentOfferProfileRepository.createProfileRating(profileId, newProfileRating);
        profileRating.setProfileRatingId(newProfileRating.getProfileRatingId());
    }


    @Override
    public int deleteProfileRating(String profileId, String profileRatingId) {
        return rentOfferProfileRepository.deleteProfileRating(profileId, profileRatingId);
    }


    @Override
    public Profile findProfileRating(String profileId, String profileRatingId) {
        return rentOfferProfileRepository.findProfileRating(profileId, profileRatingId);
    }


    @Override
    public boolean profileRatingExists(String profileId, String profileRatingId) {
        return rentOfferProfileRepository.profileRatingExists(profileId, profileRatingId);
    }


    @Override
    public Set<String> getMatchedNames(String term) {
        return rentOfferProfileRepository.getMatchedNames(term);
    }


    @Override
    public Set<String> getAdminIdAll() {
        return rentOfferProfileRepository.getAdminIdAll();
    }

    @Override
    public Set<String> getAdminIdAllByOnline() {
        return rentOfferProfileRepository.getAdminIdAllByOnline();
    }

    @Override
    public String getAdminId() {
        return rentOfferProfileRepository.getAdminId();
    }

    @Override
    public String getAdminIdByOnline() {
        return rentOfferProfileRepository.getAdminIdByOnline();
    }


    @Override
    public List<Profile> getMatchedNamesWithIds(String term) {
        return rentOfferProfileRepository.getMatchedNamesToFindWithId(term);
    }


    @Override
    public List<Profile> getMatchedCompanies(String term) {
        return rentOfferProfileRepository.getMatchedCompanies(term);
    }


    @Override
    public void toggleProfileInUserSocialList(String userId, String publicProfileId) {
        if (rentOfferProfileRepository.profileExistsInUserSocialList(userId, publicProfileId)) {
            rentOfferProfileRepository.deleteProfileFromUserSocialList(userId, publicProfileId);
            rentOfferProfileRepository.decrementProfileStatistic(publicProfileId, "inContactsCounter");
        } else {
            rentOfferProfileRepository.addProfileToUserSocialList(userId, publicProfileId);
            rentOfferProfileRepository.incrementProfileStatistic(publicProfileId, "inContactsCounter");
        }
    }


    @Override
    public ProfileDTO findPrivateProfileByIdAndUpdateLastLoginDate(String id) {
        Profile profile = findById(id);
        profile.setLastLoginDateEqualsToCurrentDate();
        rentOfferProfileRepository.findProfileAndUpdate(profile);
        return new PrivateProfileDTO(profile);
    }

    @Override
    public ProfileDTO incMainPhoneViewsAtOne(String id) {
        Profile profile = rentOfferProfileRepository.incMainPhoneViewsAtOne(id);
        if (profile != null) {
            return new PublicProfileDTO(profile);
        } else {
            return null;
        }
    }


    @Override
    public ProfileDTO findPrivateProfileByEmailAndUpdateLastLoginDate(String email) {
        Profile profile = findProfileByEmail(email);
        profile.setLastLoginDateEqualsToCurrentDate();
        rentOfferProfileRepository.findProfileAndUpdate(profile);
        ProfileDTO profileInfo = new PrivateProfileDTO(findProfileByEmail(email));
        return profileInfo;
    }


    @Override
    public ProfileDTO findPublicProfileById(String id) {

        Profile profile = findById(id);
        if (profile != null) {
            return new PublicProfileDTO(profile);
        } else {
            return null;
        }
    }


    @Override
    public ProfileDTO findPublicProfileByPublicId(String id) {
        Profile profile = findByPublicId(id);
        if (profile != null) {
            return new PublicProfileDTO(profile);
        } else {
            return null;
        }
    }


    @Override
    public List<ProfileDTO> findAllPublicProfilesWithOptions(ProfileFilterOptions profileFilterOptions) {
        return getListOfPublicProfilesWithOptions(rentOfferProfileRepository.findAllProfiles(profileFilterOptions));
    }


    @Override
    public ProfileDTO findPrivateProfileDTOByUid(String uid, String socWendor) {
        return new PrivateProfileDTO(rentOfferProfileRepository.findProfileByUidAndWendor(uid, socWendor));
    }

    @Override
    public ProfileDTO findPrivateProfileDTOByPhoneNumberd(String phoneNumber, String socWendor) {
        return new PrivateProfileDTO(rentOfferProfileRepository.findProfileByPhoneNumberAndWendor(phoneNumber, socWendor));
    }


    @Override
    public Profile findProfileByUidAndWendor(String uid, String socWendor) {
        return rentOfferProfileRepository.findProfileByUidAndWendor(uid, socWendor);
    }

    @Override
    public Profile findProfileByPhoneNumberAndWendor(String phoneNumber, String socWendor) {
        return rentOfferProfileRepository.findProfileByPhoneNumberAndWendor(phoneNumber, socWendor);
    }

    /**
     * If User is logged in - return Profile Info, if not - return null;
     *
     * @param request - the HttpServletRequest object.
     * @return - the ProfileDTO object if user is loggedIn, or null if not.
     */
    @Override
    public ProfileDTO getLoggedUser(HttpServletRequest request) throws Exception {
        ProfileDTO profileInfo = null;
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                Object principal = null;
                if (cookie.getName().equals("authToken")) {
                    try {
                        principal = oAuth2AccessTokenRepository.findByTokenId(cookie.getValue()).getAuthentication().getUserAuthentication().getPrincipal();
                    } catch (Exception e) {
                       log.info("{}", LogUtil.getExceptionStackTrace(e));
                       return null;
                    }
                } else if (cookie.getName().equals("refreshToken")) {
                    List<OAuth2AuthenticationAccessToken> oAuth2AuthenticationAccessTokens = null;
                    try {
                        oAuth2AuthenticationAccessTokens = oAuth2AccessTokenRepository.findByRefreshToken(cookie.getValue());
                    } catch (Exception e) {
                        log.info(LogUtil.getExceptionStackTrace(e));
                        return null;
                    }
                    if (oAuth2AuthenticationAccessTokens != null && oAuth2AuthenticationAccessTokens.size() > 0) {
                        principal = oAuth2AuthenticationAccessTokens.get(oAuth2AuthenticationAccessTokens.size() - 1).getAuthentication().getUserAuthentication().getPrincipal();
                    }
                }
                if (principal != null) {
                    profileInfo = profileInfoPreparatorFromPrincipal(principal);
                }
            }
        }

        return profileInfo;
    }


    @Override
    public void deleteFromMyContactList(String profileId) {
        String userId = SecurityUtils.getCurrentUserId();

        Profile profile = findById(userId);

        Set<ProfileContactList> contactList = profile.getContactList(); //Map<String, String> contactList = profile.getContactList(); //Set<String> contactList = profile.getContactList();
        contactList.remove(profileId);
        profile.setContactList(contactList);

        editProfile(profile);
    }


    @Override
    public void updateFavoriteOffers(String offerId) {
        Profile profile = findById(SecurityUtils.getCurrentUserId());
        Set<String> favoriteOffers = profile.getFavoriteOffers();
        for (String favoriteOffer : favoriteOffers) {
            if (favoriteOffer.equals(offerId)) {
                favoriteOffers.remove(offerId);
                return;
            }
        }
        favoriteOffers.add(offerId);
    }


    /**
     * Create and prepare ProfileDTO object from Principal object.
     *
     * @param principal - the principal object.
     * @return - the Profile info object.
     */
    private ProfileDTO profileInfoPreparatorFromPrincipal(Object principal) {

        ProfileDTO profileInfo = new PrivateProfileDTO();

        if (principal instanceof LoggedUser) {
            String userId = ((LoggedUser) principal).getProfileId();
            profileInfo = findPrivateProfileByIdAndUpdateLastLoginDate(userId);
        }
        return profileInfo;
    }


    /**
     * Remove unnecessary fields from profiles for admin use.
     *
     * @param profileList - the list of profiles
     */
    private void removeUnnecessaryFieldsFromProfileForAdminUse(List<Profile> profileList) {

        for (Profile profile : profileList) {
            profile.setPassword(null)
                    .setContact(null)
                    .setContactList(null)
                    .setSocialList(null)
                    .setFinanceInfo(null)
                    .setOrderAddressList(null)
                    .setOfferUserContactInfoList(null)
                    .setFavoriteOffers(null)
                    .setBirthDate(null)
                    .setMainPhone(null)
                    .setLastLoginDate(null)
                    .setProfileRating(null)
                    .setStatus(null);
        }

    }


    /**
     * @param profileList
     * @return
     */
    private List<ProfileDTO> getListOfPublicProfilesWithOptions(List<Profile> profileList) {
        List<ProfileDTO> profileInfoList = new ArrayList<>();
        for (Profile profile : profileList) {
            profileInfoList.add(new PublicProfileDTO(profile));
        }
        return profileInfoList;
    }

    /**
     * @param newProfile
     */
    private void setEmptyFieldsForNewUser(Profile newProfile) {
        newProfile.setFavoriteOffers(new HashSet<>());

        Contact contact = new Contact();
        contact.setContactEmails(new HashSet<>());
        contact.setContactPhones(new HashSet<>());
        contact.setSocNetLink(new HashMap<>());

        newProfile.setPoint(0)
                .setProfileRating(new HashSet<>())
                .setSocialList(new HashSet<>())
                .setFinanceInfo(new FinanceInfo())
                .setContact(contact)
                .setOfferUserContactInfoList(null)
                .setOrderAddressList(null);
    }
    
    @Override
    public void updateChatUID(String profileId, String uid){
        Profile profile = rentOfferProfileRepository.findById(profileId);
        profile.setChatUID(uid);
        rentOfferProfileRepository.save(profile);
    
    }
}
