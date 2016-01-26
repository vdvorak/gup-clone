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
import java.util.List;
import java.util.Set;

/**
 * The type Profiles service.
 */
@Service
public class ProfilesServiceImpl implements ProfilesService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    BankSession bankSession = new BankSession();

    @Override
    public void createProfile(Profile profile) {
        profile.setCreatedDateEqualsToCurrentDate();

        String hashedPassword = passwordEncoder.encode(profile.getPassword());
        profile.setPassword(hashedPassword);
        HashSet<UserRole> userRoles = new HashSet<>();
        if (profile.getUserRoles() == null || profile.getUserRoles().size() == 0) {
            userRoles.add(UserRole.ROLE_USER);
            profile.setUserRoles(userRoles);
        }
        profileRepository.createProfile(profile);
        bankSession.createBalanceRecord(profile.getId(), 0);
    }

    @Override
    public Profile findById(String id) {
        return profileRepository.findProfileById(id);
    }

    @Override
    public Profile updateProfile(Profile currentProfile) {
        return profileRepository.findProfileAndUpdate(currentProfile);
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
        return profileRepository.findByUsername(username);
    }

    @Override
    public Profile findProfileByEmail(String email) {
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
    public Profile findUserProfile(String profileId) {
        return profileRepository.findUserProfile(profileId);
    }

    @Override
    public List<Profile> findAllWithoutOptions() {
        return profileRepository.findAll();
    }

    @Override
    public Set<String> getMatchedNames(String term) {
        return profileRepository.getMatchedNames(term);
    }

    @Override
    public void addContactToContactList(String profileOwnerContactListId, String contactId) {
        profileRepository.addContactToContactList(profileOwnerContactListId, contactId);
    }
}
