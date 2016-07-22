package ua.com.itproekt.gup.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.server.api.rest.profiles.dto.ProfileInfo;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.profiles.*;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.*;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    @Autowired
    PasswordEncoder passwordEncoder;
    BankSession bankSession = new BankSession();
    @Autowired
    private ProfileRepository profileRepository;

    //ToDo  make this work after we will repair oauth
//    @Autowired
//    private SessionRegistry sessionRegistry;

    @Override
    public void createProfile(Profile profile) {
        String hashedPassword = passwordEncoder.encode(profile.getPassword());
        HashSet<UserRole> userRoles = new HashSet<UserRole>() {{
            add(UserRole.ROLE_USER);
        }};

        Profile newProfile = new Profile()
                .setEmail(profile.getEmail())
                .setPassword(hashedPassword)
                .setUserRoles(userRoles);

        setEmptyFieldsForNewUser(newProfile);

        profileRepository.createProfile(newProfile);
        bankSession.createBalanceRecord(newProfile.getId(), 3);

        profile.setId(newProfile.getId());
    }

    private void setEmptyFieldsForNewUser(Profile newProfile) {
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
                .setContact(contact);
    }

    @Override
    public Profile findById(String id) {
        Profile profile = profileRepository.findById(id);
        removeAdministrativeFields(profile);
        return profile;
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
    public boolean profileExistsWithEmail(String email) {
        return profileRepository.profileExistsWithEmail(email);
    }

    @Override
    public EntityPage<Profile> findAllProfiles(ProfileFilterOptions profileFilterOptions) {
        return profileRepository.findAllProfiles(profileFilterOptions);
    }

    @Override
    public Profile findProfileByUsername(String username) {
        Profile profile = profileRepository.findByUsername(username);
        removeAdministrativeFields(profile);
        return profile;
    }

    @Override
    public Profile findProfileByEmail(String email) {
        Profile profile = profileRepository.findByEmail(email);
        removeAdministrativeFields(profile);
        return profile;
    }

    @Override
    public Profile findWholeProfileByEmail(String email) {
        return profileRepository.findByEmail(email);
    }

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

//    @Override
//    public void addFriend(String profileId, String friendProfileId) {
//        profileRepository.addFriend(profileId, friendProfileId);
//    }

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
    public void addContactToContactList(String profileOwnerContactListId, String contactId) {
        profileRepository.addContactToContactList(profileOwnerContactListId, contactId);
    }

    private void removeAdministrativeFields(Profile profile) {
        profile.setPassword(null);
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
    public ProfileInfo findExtendedProfileById(String id) {

        Profile profile = findById(id);

        if (profile == null) {
            return null;
        }

        ProfileInfo profileInfo = ProfileInfo.toModel(profile);
        profileInfo.setIsOnline(isUserOnline(id));

        return profileInfo;
    }
}
