package ua.com.gup.service.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.dto.profile.*;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.profiles.Contact;
import ua.com.gup.mongo.model.profiles.FinanceInfo;
import ua.com.gup.mongo.model.profiles.ProfileContactList;
import ua.com.gup.mongo.model.profiles.ProfileRating;
import ua.com.gup.repository.profile.ProfileRepository;
import ua.com.gup.repository.sequence.PublicProfileSequenceRepository;
import ua.com.gup.util.security.SecurityUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProfilesServiceImpl implements ProfilesService {
    private final Logger log = LoggerFactory.getLogger(ProfilesServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PublicProfileSequenceRepository profileSequenceService;

    @Override
    public void createProfile(CreateProfileDTO profile) {
        Profile newProfile = new Profile()
                .setPublicId("id" + profileSequenceService.getNextSequenceId(ObjectType.USER))
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
    public Profile findById(String id) {
        return profileRepository.findById(id);
    }

    @Override
    public Profile findByPublicId(String id) {
        return profileRepository.findByPublicId(id);
    }


    @Override
    @Deprecated
    public Profile editProfile(Profile profile) {
        return profileRepository.findProfileAndUpdate(profile);
    }

    @Override
    public void updateProfile(Profile profile) {
        profileRepository.updateProfile(profile);
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
    public boolean profileExistsWithMainPhoneNumber(String mainPhoneNumber) {
        return profileRepository.profileExistsWithMainPhoneNumber(mainPhoneNumber);
    }


    @Override
    public boolean profileExistsWithUidAndWendor(String uid, String socWendor) {
        return profileRepository.profileExistsWithUidAndWendor(uid, socWendor);
    }


    @Override
    public Page<ProfileShortAdminDTO> findAllProfilesForAdminShort(Profile profileFilter, Pageable pageable) {

        long count = profileRepository.countByFilter(profileFilter);
        List<Profile> fullProfiles = Collections.EMPTY_LIST;
        if (count > 0) {
            fullProfiles = profileRepository.findByFilterForAdmins(profileFilter, pageable);
        }
        List<ProfileShortAdminDTO> list = fullProfiles.stream().map(profile -> new ProfileShortAdminDTO(profile)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, count);
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
    public ProfileDTO findPrivateProfileDTOByPublicId(String publicId) {
        return new PrivateProfileDTO(profileRepository.findByPublicId(publicId));
    }

    @Override
    public ProfileDTO findPrivateProfileDTOForAdminByPublicId(String publicId) {
        return new AdminPrivateProfileDTO(profileRepository.findByPublicId(publicId));
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


    @Override
    public void deleteFromMyContactList(String profileId) {
        String userId = SecurityUtils.getCurrentUserId();

        Profile profile = findById(userId);

        Set<ProfileContactList> contactList = profile.getContactList(); //Map<String, String> contactList = profile.getContactList(); //Set<String> contactList = profile.getContactList();
        contactList.remove(profileId);
        profile.setContactList(contactList);

        editProfile(profile);
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
}
