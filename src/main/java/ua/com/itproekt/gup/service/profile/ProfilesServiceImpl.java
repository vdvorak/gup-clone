package ua.com.itproekt.gup.service.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.dao.profile.ProfileRepository;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.ProfileFilterOptions;
import ua.com.itproekt.gup.model.profiles.ProfileRating;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.util.EntityPage;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProfilesServiceImpl implements ProfilesService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    BankSession bankSession = new BankSession();

    @Override
    public void createProfile(Profile profile) {
        String hashedPassword = passwordEncoder.encode(profile.getPassword());
        HashSet<UserRole> userRoles = new HashSet<UserRole>(){{
                add(UserRole.ROLE_USER);
        }};

        Profile newProfile = new Profile()
                .setEmail(profile.getEmail())
                .setPassword(hashedPassword)
                .setUserRoles(userRoles)
                .setCreatedDateEqualsToCurrentDate();

        profileRepository.createProfile(newProfile);
        bankSession.createBalanceRecord(newProfile.getId(), 0);

        profile.setId(newProfile.getId());
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
        removeAdministrativeFieldsForEdit(profile);

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
    public void addFriend(String profileId, String friendProfileId) {
        profileRepository.addFriend(profileId, friendProfileId);
    }

    @Override
    public Set<String> getMatchedNames(String term) {
        return profileRepository.getMatchedNames(term);
    }

    @Override
    public void addContactToContactList(String profileOwnerContactListId, String contactId) {
        profileRepository.addContactToContactList(profileOwnerContactListId, contactId);
    }

    private void removeAdministrativeFields(Profile profile){
        profile.setEmail(null)
                .setPassword(null)
                .setMainPhoneNumber(null);
    }

    private void removeAdministrativeFieldsForEdit(Profile profile) {
        profile.setPoint(null)
                .setPassword(null)
                .setUnreadMessages(null)
                .setProfileRating(null)
                .setConfirmModerator(null)
                .setUserRoles(null)
                .setCreatedDate(null)
                .setLastLoginDate(null);
    }
}
