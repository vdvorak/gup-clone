package ua.com.gup.service.profile;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.offer.Offer;
import ua.com.gup.dto.FavoriteOfferInfo;
import ua.com.gup.dto.OfferRegistration;
import ua.com.gup.dto.ProfileInfo;
import ua.com.gup.model.login.LoggedUser;
import ua.com.gup.model.offer.filter.OfferFilterOptions;
import ua.com.gup.model.profiles.*;
import ua.com.gup.repository.oauth2.OAuth2AccessTokenRepository;
import ua.com.gup.repository.profile.ProfileRepository;
import ua.com.gup.service.offers.OffersService;
import ua.com.gup.service.sequence.PublicProfileSequenceService;
import ua.com.gup.util.SecurityOperations;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Service
public class ProfilesServiceImpl implements ProfilesService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OffersService offersService;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private OAuth2AccessTokenRepository oAuth2AccessTokenRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PublicProfileSequenceService profileSequenceService;

    //ToDo need use 'bank_session.properties'
    //BankSession bankSession = new BankSession("http://93.73.109.38:8087");
    /*@Autowired
    private BankSession bankSession;*/

    @Override
    public void createProfile(Profile profile) {
        String hashedPassword = passwordEncoder.encode(profile.getPassword());
        HashSet<UserRole> userRoles = new HashSet<UserRole>() {{
            add(UserRole.ROLE_USER);
        }};

        Profile newProfile = new Profile()
                .setPublicId("id" + profileSequenceService.getNextSequenceId(Profile.COLLECTION_NAME))
                .setExecutive(profile.getExecutive())
                .setContactPerson(profile.getContactPerson())
                .setAddress(profile.getAddress())
                .setActive(profile.getActive())
                .setEmail(profile.getEmail())
                .setMainPhoneNumber(profile.getMainPhoneNumber())
                .setSocWendor(profile.getSocWendor())
                .setPassword(hashedPassword)
                .setUserRoles(userRoles) //TODO ?
                .setUserType(profile.getUserType())
                .setCreatedDateEqualsToCurrentDate()
                .setNotCompletedFields(11)
                //.setPublicKey(profile.getPublicKey())
                //.setPrivateKey(profile.getPrivateKey())
                //.setPublicHash(profile.getPublicHash())
                .setBankCard(profile.getBankCard()); // strange and magic number. Actually it is total number of fields, that you can manually filled.
        setEmptyFieldsForNewUser(newProfile);

        profileRepository.createProfile(newProfile);

        // create new balance for user in the bank
        //bankSession.createBalanceRecord(newProfile.getId(), 3); //TODO for banking

        profile.setId(newProfile.getId());
    }


    @Override
    public void createProfileWithRoles(Profile profile) {
        String hashedPassword = passwordEncoder.encode(profile.getPassword());

        Profile newProfile = new Profile()
                .setPublicId("id" + profileSequenceService.getNextSequenceId(Profile.COLLECTION_NAME))
                .setExecutive(profile.getExecutive())
                .setContactPerson(profile.getContactPerson())
                .setAddress(profile.getAddress())
                .setActive(profile.getActive())
                .setEmail(profile.getEmail())
                .setMainPhoneNumber(profile.getMainPhoneNumber())
                .setSocWendor(profile.getSocWendor())
                .setPassword(hashedPassword)
                .setUserRoles(profile.getUserRoles())
                .setUserType(profile.getUserType())
                .setCreatedDateEqualsToCurrentDate()
                .setNotCompletedFields(11)
                // .setPublicKey(profile.getPublicKey())
                // .setPrivateKey(profile.getPrivateKey())
                // .setPublicHash(profile.getPublicHash())  // strange and magic number. Actually it is total number of fields, that you can manually filled.
                .setBankCard(profile.getBankCard());

        setEmptyFieldsForNewUser(newProfile);

        profileRepository.createProfile(newProfile);

        // create new balance for user in the bank
        //bankSession.createBalanceRecord(newProfile.getId(), 3);//TODO for banking

        profile.setId(newProfile.getId());
    }


    @Override
    public Profile createProfileFromOfferRegistration(OfferRegistration offerRegistration) {
        Profile profile = new Profile();

        Set<UserRole> offerUserRoleSet = new HashSet<>();
        offerUserRoleSet.add(UserRole.ROLE_USER);

        profile
                .setPublicId("id" + profileSequenceService.getNextSequenceId(Profile.COLLECTION_NAME))
                .setExecutive(profile.getExecutive())
                .setContactPerson(profile.getContactPerson())
                .setAddress(profile.getAddress())
                .setActive(profile.getActive())
                .setEmail(offerRegistration.getEmail())
                .setPassword(offerRegistration.getPassword())
                .setUserRoles(offerUserRoleSet)
                .setUserType(profile.getUserType())
                .setBankCard(profile.getBankCard());

        if (!StringUtils.isNotBlank(offerRegistration.getUsername())) {
            profile.setUsername(offerRegistration.getUsername());
        }
        if (!StringUtils.isNotBlank(offerRegistration.getLastname())) {
            profile.setLastname(offerRegistration.getLastname());
        }
        if (!StringUtils.isNotBlank(offerRegistration.getFirstname())) {
            profile.setFirstname(offerRegistration.getFirstname());
        }

        if (0 < offerRegistration.getContactPhones().size()) {
            Contact contact = new Contact();
            contact.setContactPhones(offerRegistration.getContactPhones());
            profile.setContact(contact);
        }

        createProfile(profile);
        verificationTokenService.generateEmailRegistrationToken(profile.getId());
        return profile;
    }

    @Override
    public void facebookRegister(Profile profile) {
        HashSet<UserRole> userRoles = new HashSet<UserRole>() {{
            add(UserRole.ROLE_USER);
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
        profile.setNotCompletedFields(countEmptyFields(profile));
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
    public boolean profilePublicExists(String id) {
        return profileRepository.profilePublicExists(id);
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
    public boolean profileExistsWithSocWendor(String socWendor) {
        return profileRepository.profileExistsWithSocWendor(socWendor);
    }


    @Override
    public boolean profileExistsWithUid(String uid) {
        return profileRepository.profileExistsWithUid(uid);
    }


    @Override
    public boolean profileExistsWithUidAndWendor(String uid, String socWendor) {
        return profileRepository.profileExistsWithUidAndWendor(uid, socWendor);
    }


    @Override
    public List<Profile> findAllProfiles(ProfileFilterOptions profileFilterOptions) {
        return profileRepository.findAllProfiles(profileFilterOptions);
    }


    @Override
    public List<Profile> findAllProfilesForAdmin(ProfileFilterOptions profileFilterOptions) {
        return profileRepository.findAllProfilesForAdmin(profileFilterOptions);
    }


    @Override
    public List<Profile> findAllProfilesForAdminShort(ProfileFilterOptions profileFilterOptions) {
        List<Profile> fullProfiles = profileRepository.findAllProfilesForAdmin(profileFilterOptions);
        removeUnnecessaryFieldsFromProfileForAdminUse(fullProfiles);

        return fullProfiles;
    }

    @Override
    public Profile findProfileByUsername(String username) {
        return profileRepository.findByUsername(username);
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
    public boolean isUserModerator(Profile user) {
        Set<UserRole> userRoleSet = user.getUserRoles();
        for (UserRole userRole : userRoleSet) {
            if (userRole == UserRole.ROLE_MODERATOR || userRole == UserRole.ROLE_ADMIN) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean isUserAdminById(String userId) {
        Profile profile = profileRepository.findById(userId);
        return isUserModerator(profile);
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
    public Set<String> getAdminIdAll() {
        return profileRepository.getAdminIdAll();
    }

    @Override
    public Set<String> getAdminIdAllByOnline() {
        return profileRepository.getAdminIdAllByOnline();
    }

    @Override
    public String getAdminId() {
        return profileRepository.getAdminId();
    }

    @Override
    public String getAdminIdByOnline() {
        return profileRepository.getAdminIdByOnline();
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
    public void addContactToContactList(String profileOwnerContactListId, String contactId) {
        profileRepository.addContactToContactList(profileOwnerContactListId, contactId);
    }

    @Override
    public void addSocialToSocialList(String userId, String profileId) {
        profileRepository.addSocialToSocialList(userId, profileId);
    }


    //ToDo  make this work after we will repair oauth
    @Override
    public boolean isUserOnline(String userId) {
//        Profile profile = findWholeProfileById(userId);
//
//        List<Object> principals = sessionRegistry.getAllPrincipals();
//        User user;
//        for (Object principal : principals) {
//            user = (User) principal;
//            if (user.getUsername().equals(profile.getEmail()))
//                return true;
//        }
        return false;
    }


    @Override
    public ProfileInfo findPrivateProfileByIdAndUpdateLastLoginDate(String id) {
        Profile profile = findById(id);
        profile.setLastLoginDateEqualsToCurrentDate();
        profileRepository.findProfileAndUpdate(profile);
        return prepareAdditionalFieldForPrivate(profile);
    }

    @Override
    public ProfileInfo incMainPhoneViewsAtOne(String id) {
//        Profile profile = profileRepository.incMainPhoneViewsAtOne(id);
//        profile.setLastLoginDateEqualsToCurrentDate();
//        profileRepository.findProfileAndUpdate(profile);
//        return prepareAdditionalFieldForPrivate(profile);
        Profile profile = profileRepository.incMainPhoneViewsAtOne(id);
        if (profile != null) {
            return prepareAdditionalFieldForPublic(profile);
        } else {
            return null;
        }
    }


    @Override
    public ProfileInfo findPrivateProfileByEmailAndUpdateLastLoginDate(String email) {
        Profile profile = findProfileByEmail(email);
        profile.setLastLoginDateEqualsToCurrentDate();
        profileRepository.findProfileAndUpdate(profile);
        ProfileInfo profileInfo = prepareAdditionalFieldForPrivate(findProfileByEmail(email));

        return profileInfo;
    }


    @Override
    public ProfileInfo findPrivateProfileByEmail(String email) {
        ProfileInfo profileInfo = prepareAdditionalFieldForPrivate(findProfileByEmail(email));

        return profileInfo;
    }


    @Override
    public ProfileInfo findPublicProfileById(String id) {

        Profile profile = findById(id);
        if (profile != null) {
            return prepareAdditionalFieldForPublic(profile);
        } else {
            return null;
        }
    }


    @Override
    public ProfileInfo findPublicProfileByPublicId(String id) {

        Profile profile = findByPublicId(id);
        if (profile != null) {
            return prepareAdditionalFieldForPublic(profile);
        } else {
            return null;
        }
    }


    @Override
    public List<ProfileInfo> findAllPublicProfilesWithOptions(ProfileFilterOptions profileFilterOptions) {
        return getListOfPublicProfilesWithOptions(profileRepository.findAllProfiles(profileFilterOptions));
    }


    @Override
    public ProfileInfo findPrivateProfileByUidAndUpdateLastLoginDate(String uid, String socWendor) {
        return prepareAdditionalFieldForPrivate(profileRepository.findProfileByUidAndWendor(uid, socWendor));
    }

    @Override
    public ProfileInfo findPrivateProfileByPhoneNumberdAndUpdateLastLoginDate(String PhoneNumberd, String socWendor) {
        return prepareAdditionalFieldForPrivate(profileRepository.findProfileByPhoneNumberAndWendor(PhoneNumberd, socWendor));
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
     * @return - the ProfileInfo object if user is loggedIn, or null if not.
     */
    @Override
    public ProfileInfo getLoggedUser(HttpServletRequest request) {

        ProfileInfo profileInfo = null;

        if (request.getCookies() != null) {

            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {

                Object principal = null;

                if (cookie.getName().equals("authToken")) {
                    principal = oAuth2AccessTokenRepository.findByTokenId(cookie.getValue()).getAuthentication().getUserAuthentication().getPrincipal();
                } else if (cookie.getName().equals("refreshToken")) {
                    principal = oAuth2AccessTokenRepository.findByRefreshToken(cookie.getValue()).getAuthentication().getUserAuthentication().getPrincipal();
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
        String userId = SecurityOperations.getLoggedUserId();

        Profile profile = findById(userId);

        Set<ProfileContactList> contactList = profile.getContactList(); //Map<String, String> contactList = profile.getContactList(); //Set<String> contactList = profile.getContactList();
        contactList.remove(profileId);
        profile.setContactList(contactList);

        editProfile(profile);
    }

    @Override
    public void deleteFromMySocialList(String profileId) {
        String userId = SecurityOperations.getLoggedUserId();

        Profile profile = findById(userId);

        Set<String> socialList = profile.getSocialList();

        socialList.remove(profileId);
        profile.setSocialList(socialList);

        editProfile(profile);
    }


    @Override
    public void updateFavoriteOffers(String offerId) {
        Profile profile = findById(SecurityOperations.getLoggedUserId());
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
     * Create and prepare ProfileInfo object from Principal object.
     *
     * @param principal - the principal object.
     * @return - the Profile info object.
     */
    private ProfileInfo profileInfoPreparatorFromPrincipal(Object principal) {

        ProfileInfo profileInfo = new ProfileInfo();

        if (principal instanceof LoggedUser) {
            String userId = ((LoggedUser) principal).getProfileId();
            profileInfo = findPrivateProfileByIdAndUpdateLastLoginDate(userId);
        }
        return profileInfo;
    }


    /**
     * Add additional field to ProfileInfo object.
     *
     * @param profile - the profile.
     * @return - the ProfileInfo object.
     */
    private ProfileInfo prepareAdditionalFieldForPrivate(Profile profile) {
        ProfileInfo profileInfo = new ProfileInfo(profile);
        OfferFilterOptions offerFilterOptionsForAuthor = new OfferFilterOptions();
        offerFilterOptionsForAuthor.setAuthorId(profile.getId());
        profileInfo.getProfile().setFavoriteOffers(null);
        profileInfo.getProfile().setPassword(null);
        return profileInfo;
    }

    /**
     * @param profile - the profile.
     * @return - the ProfileInfo object.
     */
    private ProfileInfo prepareAdditionalFieldForPublic(Profile profile) {
        ProfileInfo profileInfo = new ProfileInfo(profile);

        profileInfo.getProfile()
                .setEmail(null)
                .setPassword(null)
                .setContactList(null)
                .setSocialList(null)
                .setFinanceInfo(null)
                .setOrderAddressList(null)
                .setUserRoles(null)
                .setOfferUserContactInfoList(null)
                .setFavoriteOffers(null);


        profileInfo.setUserBalance(null)
                .setUserBonusBalance(null)
                .setUnreadMessages(null)
                .setUnreadEventsCount(null);
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
                    .setMainPhoneNumber(null)
                    .setLastLoginDate(null)
                    .setProfileRating(null)
                    .setStatus(null);
        }

    }


    /**
     * @param profileList
     * @return
     */
    private List<ProfileInfo> getListOfPublicProfilesWithOptions(List<Profile> profileList) {
        List<ProfileInfo> profileInfoList = new ArrayList<>();
        for (Profile profile : profileList) {
            profileInfoList.add(prepareAdditionalFieldForPublic(profile));
        }
        return profileInfoList;
    }

    /**
     * @param newProfile
     */
    private void setEmptyFieldsForNewUser(Profile newProfile) {
        newProfile.setFavoriteOffers(new HashSet<>());

        Contact contact = new Contact();
        contact.setType(UserType.INDIVIDUAL);
        contact.setContactEmails(new HashSet<>());
        contact.setContactPhones(new HashSet<>());
        contact.setNaceId(new ArrayList<>());
        contact.setSocNetLink(new HashMap<>());

        newProfile.setPoint(0)
                .setProfileRating(new HashSet<>())
                .setSocialList(new HashSet<>())
                .setFinanceInfo(new FinanceInfo())
                .setContact(contact)
                .setOfferUserContactInfoList(null)
                .setOrderAddressList(null);
    }

    /**
     * @param profile
     * @return
     */
    private List<FavoriteOfferInfo> favoriteOfferInfoListPreparator(Profile profile) {
        List<FavoriteOfferInfo> favoriteOfferInfoList = new ArrayList<>();

        Set<String> favoriteOffers = profile.getFavoriteOffers();

        if (favoriteOffers == null) {
            return null;
        }

        for (String favoriteOfferId : favoriteOffers) {
            favoriteOfferInfoList.add(favoriteOfferInfoPreparator(favoriteOfferId));
        }
        return favoriteOfferInfoList;
    }

    /**
     * @param favoriteOfferId
     * @return
     */
    private FavoriteOfferInfo favoriteOfferInfoPreparator(String favoriteOfferId) {
        FavoriteOfferInfo favoriteOfferInfo = new FavoriteOfferInfo();

        Offer offer = offersService.findById(favoriteOfferId);

        favoriteOfferInfo.setFavoriteOfferId(favoriteOfferId);
        favoriteOfferInfo.setFavoriteOfferSeoUrl(offer.getSeoUrl());
        favoriteOfferInfo.setFavoriteOfferTitle(offer.getTitle());
        favoriteOfferInfo.setFavoriteOfferImage(offersService.getMainOfferImage(offer));

        return favoriteOfferInfo;
    }

    /**
     * Count empty field for profile
     *
     * @param profile
     * @return
     */
    private int countEmptyFields(Profile profile) {
        int result = 0;

        // 1
        if (StringUtils.isBlank(profile.getMainPhoneNumber())) {
            result++;
        }

        // 1
        if (StringUtils.isBlank(profile.getUsername())) {
            result++;
        }

        // 1
        if (StringUtils.isBlank(profile.getImgId()) && StringUtils.isBlank(profile.getImgUrl())) {
            result++;
        }

        // 1
        if (profile.getBirthDate() == null) {
            result++;
        }

        // 8 maximum
        if (profile.getContact() == null) {
            result = result + 8;
        } else {
            if (StringUtils.isBlank(profile.getContact().getPosition())) {
                result++;
            }
            if (StringUtils.isBlank(profile.getContact().getCompanyName())) {
                result++;
            }
            if (StringUtils.isBlank(profile.getContact().getAboutUs())) {
                result++;
            }
            if (StringUtils.isBlank(profile.getContact().getSkypeUserName())) {
                result++;
            }
            if (StringUtils.isBlank(profile.getContact().getLinkToWebSite())) {
                result++;
            }
            if (CollectionUtils.isEmpty(profile.getContact().getContactEmails())) {
                result++;
            }
            if (CollectionUtils.isEmpty(profile.getContact().getContactPhones())) {
                result++;
            }
            if (MapUtils.isEmpty(profile.getContact().getSocNetLink())) {
                result++;
            }
        }
        return result;
    }


}