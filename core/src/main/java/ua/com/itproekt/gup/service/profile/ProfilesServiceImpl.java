package ua.com.itproekt.gup.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.profiles.*;
import ua.com.itproekt.gup.server.api.rest.profiles.dto.ProfileInfo;

import java.util.*;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    @Autowired
    PasswordEncoder passwordEncoder;
    BankSession bankSession = new BankSession(); //BankSession bankSession = new BankSession("http://93.73.109.38:8087"); //ToDo need use 'bank_session.properties'

    @Autowired
    private ProfileRepository profileRepository;


    //ToDo  make this work after we will repair oauth
//    @Autowired
//    private SessionRegistry sessionRegistry;

    /**
     * @param profile the profile
     */
    @Override
    public void createProfile(Profile profile) {
        String       hashedPassword = passwordEncoder.encode(profile.getPassword());
        HashSet<UserRole> userRoles = new HashSet<UserRole>() {{
            add(UserRole.ROLE_USER);
        }};

        Profile newProfile = new Profile()
                .setEmail(profile.getEmail())
                .setSocWendor(profile.getSocWendor())
                .setPassword(hashedPassword)
                .setUserRoles(userRoles)
                .setCreatedDateEqualsToCurrentDate();

        setEmptyFieldsForNewUser( newProfile );

        profileRepository.createProfile( newProfile );
        bankSession.createBalanceRecord( newProfile.getId(), 3 );

        profile.setId( newProfile.getId() );
    }

    /**
     * @param profile the profile
     */
    @Override
    public void facebookRegister(Profile profile) {
        HashSet<UserRole> userRoles = new HashSet<UserRole>() {{
            add(UserRole.ROLE_USER);
        }};

        Profile newProfile = new Profile()
                .setSocWendor(profile.getSocWendor())
                .setUid(profile.getUid())
                .setTokenKey(profile.getTokenKey())
                .setUserRoles(userRoles)
                .setCreatedDateEqualsToCurrentDate();

        setEmptyFieldsForNewUser( newProfile );

        profileRepository.createProfile( newProfile );
        bankSession.createBalanceRecord( newProfile.getId(), 3 );

        profile.setId( newProfile.getId() );
    }


    /**
     * @param id
     * @return
     */
    @Override
    public Profile findById(String id) {
        return profileRepository.findById(id);
    }

    /**
     * @param id the id
     * @return
     */
    @Override
    public Profile findWholeProfileById(String id) {
        return profileRepository.findById(id);
    }

    /***
     * @param profile
     * @return
     */
    @Override
    public Profile editProfile(Profile profile) {
        return profileRepository.findProfileAndUpdate(profile);
    }

    /***
     * @param id the id
     */
    @Override
    public void deleteProfileById(String id) {
        profileRepository.deleteProfileById(id);
    }

    /***
     * @param id the id
     * @return
     */
    @Override
    public boolean profileExists(String id) {
        return profileRepository.profileExists(id);
    }

    /**
     * @param email the email
     * @return
     */
    @Override
    public boolean profileExistsWithEmail(String email) {
        return profileRepository.profileExistsWithEmail(email);
    }

    /**
     * @param socWendor the socWendor
     * @return
     */
    @Override
    public boolean profileExistsWithSocWendor(String socWendor) {
        return profileRepository.profileExistsWithSocWendor(socWendor);
    }

    /**
     * @param uid the socWendor
     * @return
     */
    @Override
    public boolean profileExistsWithUid(String uid) {
        return profileRepository.profileExistsWithUid(uid);
    }

    /**
     * @param uid the uid
     * @param socWendor the socWendor
     * @return
     */
    @Override
    public boolean profileExistsWithUidAndWendor(String uid, String socWendor) {
        return profileRepository.profileExistsWithUidAndWendor(uid, socWendor);
    }

    /***
     * @param profileFilterOptions the profile filter options
     * @return
     */
    @Override
    public List<Profile> findAllProfiles(ProfileFilterOptions profileFilterOptions) {
        return profileRepository.findAllProfiles(profileFilterOptions);
    }

    /**
     * @param username the username
     * @return
     */
    @Override
    public Profile findProfileByUsername(String username) {
        return profileRepository.findByUsername(username);
    }

    /**
     * @param email the email
     * @return
     */
    @Override
    public Profile findProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Profile findWholeProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

    /**
     * @param user
     * @return
     */
    @Override
    public boolean isUserModerator(Profile user) {
        Set<UserRole> userRoleSet = user.getUserRoles();
        for (UserRole userRole : userRoleSet) {
            if (userRole == UserRole.ROLE_MODERATOR || userRole == UserRole.ROLE_ADMIN || userRole == UserRole.ROLE_SUPPORT) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public boolean isUserAdminById(String userId) {
        Profile profile = profileRepository.findById(userId);
        return isUserModerator(profile);
    }

    /**
     * @param seoWord
     * @return
     */
    @Override
    public boolean isSeoWordFree(String seoWord) {
        Profile profile = profileRepository.findBySeoWord(seoWord);
        return profile == null;
    }

    /**
     * @param profileId     the profile id
     * @param profileRating the profile rating
     */
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

    /**
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return
     */
    @Override
    public int deleteProfileRating(String profileId, String profileRatingId) {
        return profileRepository.deleteProfileRating(profileId, profileRatingId);
    }

    /**
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return
     */
    @Override
    public Profile findProfileRating(String profileId, String profileRatingId) {
        return profileRepository.findProfileRating(profileId, profileRatingId);
    }

    /**
     * @param profileId       the profile id
     * @param profileRatingId the profile rating id
     * @return
     */
    @Override
    public boolean profileRatingExists(String profileId, String profileRatingId) {
        return profileRepository.profileRatingExists(profileId, profileRatingId);
    }

//    @Override
//    public void addFriend(String profileId, String friendProfileId) {
//        profileRepository.addFriend(profileId, friendProfileId);
//    }

    /**
     * @param term
     * @return
     */
    @Override
    public Set<String> getMatchedNames(String term) {
        return profileRepository.getMatchedNames(term);
    }

    /**
     * @param term
     * @return
     */
    @Override
    public List<Profile> getMatchedNamesWithIds(String term) {
        return profileRepository.getMatchedNamesToFindWithId(term);
    }

    /**
     * @param term
     * @return
     */
    @Override
    public List<Profile> getMatchedCompanies(String term) {
        return profileRepository.getMatchedCompanies(term);
    }

    /**
     * @param profileOwnerContactListId
     * @param contactId
     */
    @Override
    public void addContactToContactList(String profileOwnerContactListId, String contactId) {
        profileRepository.addContactToContactList(profileOwnerContactListId, contactId);
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

    /**
     * @param id
     * @return
     */
    @Override
    public ProfileInfo findPrivateProfileById(String id) {
        return prepareAdditionalFieldForPrivate(findById(id));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProfileInfo findPrivateProfileByIdAndUpdateLastLoginDate(String id) {

        Profile profile = findById(id);
        profile.setLastLoginDateEqualsToCurrentDate();
        profileRepository.findProfileAndUpdate(profile);

        return prepareAdditionalFieldForPrivate(profile);
    }

    /**
     * @param email
     * @return
     */
    @Override
    public ProfileInfo findPrivateProfileByEmailAndUpdateLastLoginDate(String email) {

        Profile profile = findProfileByEmail(email);
        profile.setLastLoginDateEqualsToCurrentDate();
        profileRepository.findProfileAndUpdate(profile);

        return prepareAdditionalFieldForPrivate(findProfileByEmail(email));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProfileInfo findPublicProfileById(String id) {
        return prepareAdditionalFieldForPublic(findById(id));
    }

    /**
     * @param email
     * @return
     */
    @Override
    public ProfileInfo findPublicProfileByEmail(String email) {
        return prepareAdditionalFieldForPublic(findProfileByEmail(email));
    }

    /**
     * @param profileFilterOptions
     * @return
     */
    @Override
    public List<ProfileInfo> findAllPublicProfilesWithOptions(ProfileFilterOptions profileFilterOptions) {
        return getListOfPublicProfilesWithOptions(profileRepository.findAllProfiles(profileFilterOptions));
    }

    /**
     * @param profile
     * @return
     */
    private ProfileInfo prepareAdditionalFieldForPrivate(Profile profile) {
        ProfileInfo profileInfo = new ProfileInfo(profile);


        // ToDo impl all of this!
        profileInfo
                .setUserBalance(0)
                .setUserBonusBalance(0)
                .setUnreadMessages(0)
                .setUnreadMessages(0)
                .setUserAveragePoints(5);

        profileInfo.getProfile().setPassword(null);
        return profileInfo;
    }

    /**
     * @param profile
     * @return
     */
    private ProfileInfo prepareAdditionalFieldForPublic(Profile profile) {
        ProfileInfo profileInfo = new ProfileInfo(profile);

        profileInfo.getProfile()
                .setEmail(null)
                .setPassword(null)
                .setContactList(null)
                .setUserProfile(null)
                .setOrderAddressList(null)
                .setUserRoles(null)
                .setOfferUserContactInfoList(null)
                .setFavoriteOffers(null);

        profileInfo.setUserBalance(null)
                .setUserBonusBalance(null)
                .setUnreadMessages(null)
                .setUnreadMessages(null)
                .setUserAveragePoints(4); // ToDo impl this!

        return profileInfo;
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
                .setContactList(new HashSet<>())
                .setUserProfile(new UserProfile())
                .setContact(contact)
                .setOfferUserContactInfoList(null)
                .setOrderAddressList(null);
    }

}
