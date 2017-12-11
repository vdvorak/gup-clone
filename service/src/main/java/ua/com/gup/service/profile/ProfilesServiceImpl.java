package ua.com.gup.service.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.dto.profile.CreateProfileDTO;
import ua.com.gup.dto.profile.PrivateProfileDTO;
import ua.com.gup.dto.profile.ProfileDTO;
import ua.com.gup.dto.profile.PublicProfileDTO;
import ua.com.gup.mongo.composition.domain.oauth2.OAuth2AuthenticationAccessToken;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.login.LoggedUser;
import ua.com.gup.mongo.model.profiles.Contact;
import ua.com.gup.mongo.model.profiles.FinanceInfo;
import ua.com.gup.mongo.model.profiles.ProfileContactList;
import ua.com.gup.mongo.model.profiles.ProfileRating;
import ua.com.gup.repository.oauth2.OAuth2AccessTokenRepository;
import ua.com.gup.repository.profile.ProfileRepository;
import ua.com.gup.service.sequence.PublicProfileSequenceService;
import ua.com.gup.util.LogUtil;
import ua.com.gup.util.security.SecurityUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class ProfilesServiceImpl implements ProfilesService {
    private final Logger log = LoggerFactory.getLogger(ProfilesServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OAuth2AccessTokenRepository oAuth2AccessTokenRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PublicProfileSequenceService profileSequenceService;

    @Override
    public void createProfile(CreateProfileDTO profile) {
        Profile newProfile = new Profile()
                .setPublicId("id" + profileSequenceService.getNextSequenceId(Profile.COLLECTION_NAME))
                .setExecutive(profile.getExecutive())
                .setContactPerson(profile.getContactPerson())
                .setAddress(profile.getAddress())
                .setActive(Boolean.TRUE)
                .setEmail(profile.getEmail())
                .setMainPhone(profile.getMainPhone())
                .setSocWendor("GUP")
                .setPassword(passwordEncoder.encode(profile.getPassword()))
                .setUserRoles(profile.getUserRoles())
                .setUserType(CommonUserType.LEGAL_ENTITY)
                .setCreatedDateEqualsToCurrentDate();

        profileRepository.createProfile(newProfile);
    }

    @Override
    public void createProfile(Profile profile) {
        String hashedPassword = passwordEncoder.encode(profile.getPassword());
        Profile newProfile = new Profile()
                .setPublicId("id" + profileSequenceService.getNextSequenceId(Profile.COLLECTION_NAME))
                .setExecutive(profile.getExecutive())
                .setContactPerson(profile.getContactPerson())
                .setAddress(profile.getAddress())
                .setActive(profile.getActive())
                .setEmail(profile.getEmail())
                .setMainPhone(profile.getMainPhone())
                .setSocWendor(profile.getSocWendor())
                .setPassword(hashedPassword)
                .setUserRoles(profile.getUserRoles()) //TODO ?
                .setUserType(profile.getUserType())
                .setCreatedDateEqualsToCurrentDate()
                .setBankCard(profile.getBankCard()); // strange and magic number. Actually it is total number of fields, that you can manually filled.
        setEmptyFieldsForNewUser(newProfile);

        profileRepository.createProfile(newProfile);

        // create new balance for user in the bank
        //bankSession.createBalanceRecord(newProfile.getId(), 3); //TODO for banking

        profile.setId(newProfile.getId());
    }


    @Override
    public void facebookRegister(Profile profile) {
        HashSet<CommonUserRole> userRoles = new HashSet<CommonUserRole>() {{
            add(CommonUserRole.ROLE_USER);
        }};

        Profile newProfile = new Profile()
                .setPublicId("id" + profileSequenceService.getNextSequenceId(Profile.COLLECTION_NAME))
                .setExecutive(profile.getExecutive())
                .setContactPerson(profile.getContactPerson())
                .setAddress(profile.getAddress())
                .setActive(profile.getActive())
                .setSocWendor(profile.getSocWendor())
                .setUid(profile.getUid())
                .setTokenKey(profile.getTokenKey())
                .setUserRoles(userRoles)
                .setUserType(profile.getUserType())
                .setCreatedDateEqualsToCurrentDate()
                .setBankCard(profile.getBankCard());

        setEmptyFieldsForNewUser(newProfile);

        profileRepository.createProfile(newProfile);

        /*bankSession.createBalanceRecord(newProfile.getId(), 3);*///TODO for banking

        profile.setId(newProfile.getId());
    }


    @Override
    public Profile findById(String id) {
        return profileRepository.findById(id);
    }

    @Override
    public Profile findByPublicId(String id) {
        return profileRepository.findByPublicId(id);
    }


    @Override
    public Profile findWholeProfileById(String id) {
        return profileRepository.findById(id);
    }


    @Override
    public Profile editProfile(Profile profile) {
        return profileRepository.findProfileAndUpdate(profile);
    }


    @Override
    public void deleteProfileById(String id) {
        profileRepository.deleteProfileById(id);
    }


    @Override
    public boolean profileExists(String id) {
        return profileRepository.profileExists(id);
    }

    @Override
    public boolean profileExistsByPublicId(String id) {
        return profileRepository.profileExistsByPublicId(id);
    }

    @Override
    public boolean profileExistsWithEmail(String email) {
        return profileRepository.profileExistsWithEmail(email);
    }

    @Override
    public boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber) {
        return profileRepository.profileExistsWithMainPhoneNumber(mainPhoneNumber);
    }


    @Override
    public boolean profileExistsWithUidAndWendor(String uid, String socWendor) {
        return profileRepository.profileExistsWithUidAndWendor(uid, socWendor);
    }


    @Override
    public Page<Profile> findAllProfilesForAdminShort(Profile profileFilter, Pageable pageable) {

        long count = profileRepository.countByFilter(profileFilter);
        List<Profile> fullProfiles = Collections.EMPTY_LIST;
        if (count > 0) {
            fullProfiles = profileRepository.findByFilterForAdmins(profileFilter, pageable);
        }
        removeUnnecessaryFieldsFromProfileForAdminUse(fullProfiles);
        Page<Profile> result = new PageImpl<>(fullProfiles, pageable, fullProfiles.size());
        return result;
    }


    @Override
    public Profile findProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    @Override
    public Profile findProfileByMainPhone(String mainPhone) {
        return profileRepository.findProfileByMainPhone(mainPhone);
    }


    @Override
    public Profile findWholeProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }


    @Override
    public boolean isSeoWordFree(String seoWord) {
        Profile profile = profileRepository.findBySeoWord(seoWord);
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
        profileRepository.createProfileRating(profileId, newProfileRating);
        profileRating.setProfileRatingId(newProfileRating.getProfileRatingId());
    }


    @Override
    public int deleteProfileRating(String profileId, String profileRatingId) {
        return profileRepository.deleteProfileRating(profileId, profileRatingId);
    }


    @Override
    public Profile findProfileRating(String profileId, String profileRatingId) {
        return profileRepository.findProfileRating(profileId, profileRatingId);
    }


    @Override
    public boolean profileRatingExists(String profileId, String profileRatingId) {
        return profileRepository.profileRatingExists(profileId, profileRatingId);
    }


    @Override
    public Set<String> getMatchedNames(String term) {
        return profileRepository.getMatchedNames(term);
    }


    @Override
    public List<Profile> getMatchedNamesWithIds(String term) {
        return profileRepository.getMatchedNamesToFindWithId(term);
    }


    @Override
    public List<Profile> getMatchedCompanies(String term) {
        return profileRepository.getMatchedCompanies(term);
    }


    @Override
    public void toggleProfileInUserSocialList(String userId, String publicProfileId) {
        if (profileRepository.profileExistsInUserSocialList(userId, publicProfileId)) {
            profileRepository.deleteProfileFromUserSocialList(userId, publicProfileId);
            profileRepository.decrementProfileStatistic(publicProfileId, "inContactsCounter");
        } else {
            profileRepository.addProfileToUserSocialList(userId, publicProfileId);
            profileRepository.incrementProfileStatistic(publicProfileId, "inContactsCounter");
        }
    }


    @Override
    public ProfileDTO findPrivateProfileByIdAndUpdateLastLoginDate(String id) {
        Profile profile = findById(id);
        profile.setLastLoginDateEqualsToCurrentDate();
        profileRepository.findProfileAndUpdate(profile);
        return new PrivateProfileDTO(profile);
    }

    @Override
    public ProfileDTO incMainPhoneViewsAtOne(String id) {
        Profile profile = profileRepository.incMainPhoneViewsAtOne(id);
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
        profileRepository.findProfileAndUpdate(profile);
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
    public ProfileDTO findPrivateProfileDTOByUid(String uid, String socWendor) {
        return new PrivateProfileDTO(profileRepository.findProfileByUidAndWendor(uid, socWendor));
    }

    @Override
    public ProfileDTO findPrivateProfileDTOByPhoneNumberd(String phoneNumber, String socWendor) {
        return new PrivateProfileDTO(profileRepository.findProfileByPhoneNumberAndWendor(phoneNumber, socWendor));
    }


    @Override
    public Profile findProfileByUidAndWendor(String uid, String socWendor) {
        return profileRepository.findProfileByUidAndWendor(uid, socWendor);
    }

    @Override
    public Profile findProfileByPhoneNumberAndWendor(String phoneNumber, String socWendor) {
        return profileRepository.findProfileByPhoneNumberAndWendor(phoneNumber, socWendor);
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
    public void updateChatUID(String profileId, String uid) {
        Profile profile = profileRepository.findById(profileId);
        profile.setChatUID(uid);
        profileRepository.save(profile);

    }
}
