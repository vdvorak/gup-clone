package ua.com.gup.rent.service.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.rent.profiles.RentOfferProfileFilterOptions;
import ua.com.gup.rent.model.rent.profiles.RentOfferProfileRating;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferPrivateProfileDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferProfileDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferPublicProfileDTO;
import ua.com.gup.rent.util.security.RentSecurityUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class RentOfferProfilesServiceImpl implements RentOfferProfilesService {
    private final Logger log = LoggerFactory.getLogger(RentOfferProfilesServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RentOfferProfileRepository rentOfferProfileRepository;


    /**
     * Create profile.
     *
     * @param profile - the profile.
     */
    @Override
    public void createProfile(RentOfferProfile profile) {

    }

    /**
     * Create profile.
     *
     * @param profile - the Profile object.
     */
    @Override
    public void facebookRegister(RentOfferProfile profile) {

    }

    @Override
    public RentOfferProfile findById(String id) {
        return rentOfferProfileRepository.findById(id);
    }

    @Override
    public RentOfferProfile findByPublicId(String id) {
        return rentOfferProfileRepository.findByPublicId(id);
    }


    @Override
    public RentOfferProfile findWholeProfileById(String id) {
        return rentOfferProfileRepository.findById(id);
    }


    @Override
    public RentOfferProfile editProfile(RentOfferProfile profile) {
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
    public RentOfferProfile findProfileByUsername(String username) {
        return rentOfferProfileRepository.findByUsername(username);
    }


    @Override
    public RentOfferProfile findProfileByEmail(String email) {
        return rentOfferProfileRepository.findByEmail(email);
    }

    @Override
    public RentOfferProfile findProfileByMainPhone(String mainPhone) {
        return rentOfferProfileRepository.findProfileByMainPhone(mainPhone);
    }

    @Override
    public RentOfferProfile findWholeProfileByEmail(String email) {
        return rentOfferProfileRepository.findByEmail(email);
    }


    @Override
    public boolean isSeoWordFree(String seoWord) {
        RentOfferProfile profile = rentOfferProfileRepository.findBySeoWord(seoWord);
        return profile == null;
    }


    @Override
    public void createProfileRating(String profileId, RentOfferProfileRating profileRating) {
        RentOfferProfileRating newProfileRating = new RentOfferProfileRating()
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
    public RentOfferProfile findProfileRating(String profileId, String profileRatingId) {
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
    public List<RentOfferProfile> getMatchedNamesWithIds(String term) {
        return rentOfferProfileRepository.getMatchedNamesToFindWithId(term);
    }


    @Override
    public List<RentOfferProfile> getMatchedCompanies(String term) {
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
    public RentOfferProfileDTO findPrivateProfileByIdAndUpdateLastLoginDate(String id) {
        RentOfferProfile profile = findById(id);
        profile.setLastLoginDateEqualsToCurrentDate();
        rentOfferProfileRepository.findProfileAndUpdate(profile);
        return new RentOfferPrivateProfileDTO(profile);
    }

    @Override
    public RentOfferProfileDTO incMainPhoneViewsAtOne(String id) {
        RentOfferProfile profile = rentOfferProfileRepository.incMainPhoneViewsAtOne(id);
        if (profile != null) {
            return new RentOfferPublicProfileDTO(profile);
        } else {
            return null;
        }
    }


    @Override
    public RentOfferProfileDTO findPrivateProfileByEmailAndUpdateLastLoginDate(String email) {
        RentOfferProfile profile = findProfileByEmail(email);
        profile.setLastLoginDateEqualsToCurrentDate();
        rentOfferProfileRepository.findProfileAndUpdate(profile);
        RentOfferProfileDTO profileInfo = new RentOfferPrivateProfileDTO(findProfileByEmail(email));
        return profileInfo;
    }


    @Override
    public RentOfferProfileDTO findPublicProfileById(String id) {

        RentOfferProfile profile = findById(id);
        if (profile != null) {
            return new RentOfferPublicProfileDTO(profile);
        } else {
            return null;
        }
    }


    @Override
    public RentOfferProfileDTO findPublicProfileByPublicId(String id) {
        RentOfferProfile profile = findByPublicId(id);
        if (profile != null) {
            return new RentOfferPublicProfileDTO(profile);
        } else {
            return null;
        }
    }


    @Override
    public List<RentOfferProfileDTO> findAllPublicProfilesWithOptions(RentOfferProfileFilterOptions profileFilterOptions) {
        return getListOfPublicProfilesWithOptions(rentOfferProfileRepository.findAllProfiles(profileFilterOptions));
    }


    @Override
    public RentOfferProfileDTO findPrivateProfileDTOByUid(String uid, String socWendor) {
        return new RentOfferPrivateProfileDTO(rentOfferProfileRepository.findProfileByUidAndWendor(uid, socWendor));
    }

    @Override
    public RentOfferProfileDTO findPrivateProfileDTOByPhoneNumberd(String phoneNumber, String socWendor) {
        return new RentOfferPrivateProfileDTO(rentOfferProfileRepository.findProfileByPhoneNumberAndWendor(phoneNumber, socWendor));
    }

    @Override
    public RentOfferProfile findProfileByUidAndWendor(String uid, String socWendor) {
        return rentOfferProfileRepository.findProfileByUidAndWendor(uid, socWendor);
    }

    @Override
    public RentOfferProfile findProfileByPhoneNumberAndWendor(String phoneNumber, String socWendor) {
        return rentOfferProfileRepository.findProfileByPhoneNumberAndWendor(phoneNumber, socWendor);
    }

    /**
     * This method provides additional information for admin.
     *
     * @param profileFilterOptions - the profile filter options.
     * @return - the list of profiles.
     */
    @Override
    public List<RentOfferProfile> findAllProfilesForAdmin(RentOfferProfileFilterOptions profileFilterOptions) {
        return null;
    }

    /**
     * Return list of profiles for admin-panel in short and light version without unnecessary fields.
     *
     * @param profileFilterOptions - the profile filter options
     * @return - the list of profiles
     */
    @Override
    public List<RentOfferProfile> findAllProfilesForAdminShort(RentOfferProfileFilterOptions profileFilterOptions) {
        return null;
    }

   /* *//**
     * If User is logged in - return Profile Info, if not - return null;
     *
     * @param request - the HttpServletRequest object.
     * @return - the RentOfferProfileDTO object if user is loggedIn, or null if not.
     *//*
    @Override
    public RentOfferProfileDTO getLoggedUser(HttpServletRequest request) throws Exception {
        RentOfferProfileDTO profileInfo = null;
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
    }*/


    @Override
    public void deleteFromMyContactList(String profileId) {
        String userId = RentSecurityUtils.getCurrentUserId();

        RentOfferProfile profile = findById(userId);

       /* Set<ProfileContactList> contactList = profile.getContactList(); //Map<String, String> contactList = profile.getContactList(); //Set<String> contactList = profile.getContactList();
        contactList.remove(profileId);
        profile.setContactList(contactList);*/

        editProfile(profile);
    }


    @Override
    public void updateFavoriteOffers(String offerId) {
        RentOfferProfile profile = findById(RentSecurityUtils.getCurrentUserId());
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
     * Create and prepare RentOfferProfileDTO object from Principal object.
     *
     * @param principal - the principal object.
     * @return - the Profile info object.
     */
    private RentOfferProfileDTO profileInfoPreparatorFromPrincipal(Object principal) {

        RentOfferProfileDTO profileInfo = new RentOfferPrivateProfileDTO();

        /*if (principal instanceof LoggedUser) {
            String userId = ((LoggedUser) principal).getProfileId();
            profileInfo = findPrivateProfileByIdAndUpdateLastLoginDate(userId);
        }*/
        return profileInfo;
    }


    /**
     * Remove unnecessary fields from profiles for admin use.
     *
     * @param profileList - the list of profiles
     */
    private void removeUnnecessaryFieldsFromProfileForAdminUse(List<RentOfferProfile> profileList) {

        for (RentOfferProfile profile : profileList) {
            profile //.setPassword(null)
                    //.setContact(null)
                    //.setContactList(null)
                    .setSocialList(null)
                   // .setFinanceInfo(null)
                   // .setOrderAddressList(null)
                   // .setOfferUserContactInfoList(null)
                    .setFavoriteOffers(null)
                    .setBirthDate(null)
                    .setMainPhone(null)
                    .setLastLoginDate(null)
                   // .setProfileRating(null)
                    .setStatus(null);
        }

    }


    /**
     * @param profileList
     * @return
     */
    private List<RentOfferProfileDTO> getListOfPublicProfilesWithOptions(List<RentOfferProfile> profileList) {
        List<RentOfferProfileDTO> profileInfoList = new ArrayList<>();
        for (RentOfferProfile profile : profileList) {
            profileInfoList.add(new RentOfferPublicProfileDTO(profile));
        }
        return profileInfoList;
    }

    /**
     * @param newProfile
     */
    private void setEmptyFieldsForNewUser(RentOfferProfile newProfile) {
        newProfile.setFavoriteOffers(new HashSet<>());

     /*   Contact contact = new Contact();
        contact.setContactEmails(new HashSet<>());
        contact.setContactPhones(new HashSet<>());
        contact.setSocNetLink(new HashMap<>());*/

        newProfile.setPoint(0)
               // .setProfileRating(new HashSet<>())
                .setSocialList(new HashSet<>());
               // .setFinanceInfo(new FinanceInfo())
               // .setContact(contact)
               // .setOfferUserContactInfoList(null)
               // .setOrderAddressList(null);
    }
    
    @Override
    public void updateChatUID(String profileId, String uid){
        RentOfferProfile profile = rentOfferProfileRepository.findById(profileId);
        profile.setChatUID(uid);
        rentOfferProfileRepository.save(profile);
    
    }
}
