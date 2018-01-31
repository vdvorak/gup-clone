package ua.com.gup.service.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.gup.common.dto.profile.PrivateProfileDTO;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.common.dto.profile.PublicProfileDTO;
import ua.com.gup.common.mapper.profile.admin.AdminPrivateProfileMapper;
import ua.com.gup.common.model.enumeration.CommonUserType;
import ua.com.gup.common.model.mongo.BanInfo;
import ua.com.gup.common.model.mongo.profile.Contact;
import ua.com.gup.common.model.mongo.profile.FinanceInfo;
import ua.com.gup.common.model.mongo.profile.ProfileContactList;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.common.service.OperationService;
import ua.com.gup.common.service.impl.CommonProfileServiceImpl;
import ua.com.gup.dto.profile.CreateProfileDTO;
import ua.com.gup.dto.profile.manager.ManagerPrivateProfileDto;
import ua.com.gup.dto.profile.manager.UserPrivateProfileDto;
import ua.com.gup.dto.profile.manager.UserProfileShortAdminDto;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.profiles.ProfileRating;
import ua.com.gup.repository.profile.ProfileFilter;
import ua.com.gup.repository.profile.ProfileRepository;
import ua.com.gup.repository.sequence.PublicProfileSequenceRepository;
import ua.com.gup.util.security.SecurityUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ProfilesServiceImpl extends CommonProfileServiceImpl<Profile> implements ProfilesService {
    private final Logger log = LoggerFactory.getLogger(ProfilesServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private PublicProfileSequenceRepository profileSequenceService;
    @Autowired
    private OperationService operationService;

    @Override
    public void createProfile(CreateProfileDTO profile) {
        Profile newProfile = new Profile();
        newProfile.setPublicId("id" + profileSequenceService.getNextSequenceId(ObjectType.USER))
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
    public Page<ProfileShortAdminDTO> findAllProfilesForAdminShort(ProfileFilter profileFilter, Pageable pageable) {

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

    @Autowired
    private AdminPrivateProfileMapper adminPrivateProfileMapper;
    @Override
    public ProfileDTO findPrivateProfileDTOForAdminByPublicId(String publicId) {
        Profile profile = profileRepository.findByPublicId(publicId);
        return adminPrivateProfileMapper.convert(profile);

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

    @Override
    public void banProfile(Profile profile, String privateExp, String publicExp) {
        profile.setBan(true);

        BanInfo banInfo = new BanInfo();
        banInfo.setBanDate(new Date());
        banInfo.setPrivateExplanation(privateExp);
        banInfo.setPublicExplanation(publicExp);
        profile.setBanInfo(banInfo);
        updateProfile(profile);
    }

    @Override
    public void unbanProfile(Profile profile) {
        profile.setBan(false);
        profile.setBanInfo(null);
        updateProfile(profile);
    }

    @Override
    public Page<ProfileShortAdminDTO> findByRole(String role, Pageable pageable) {
        long count = profileRepository.countByRole(role);
        List<Profile> fullProfiles = Collections.EMPTY_LIST;
        if (count > 0) {
            fullProfiles = profileRepository.findByRole(role, pageable);
        }
        List<ProfileShortAdminDTO> list = fullProfiles.stream().map(profile -> new ProfileShortAdminDTO(profile)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public void linkProfile(String managerPublicId, String profilePublicId) {
        Profile user = profileRepository.findByPublicId(profilePublicId, Profile.class);
        Profile manager = profileRepository.findByPublicId(managerPublicId, Profile.class);

        manager.getManagerInfo().getUsers().add(user.getId());
        user.getManagerClientInfo().setManager(manager.getId());

        profileRepository.updateProfile(manager);
        profileRepository.updateProfile(user);
    }

    @Override
    public void unlinkProfile(String managerPublicId, String profilePublicId) {
        Profile user = profileRepository.findByPublicId(profilePublicId, Profile.class);
        Profile manager = profileRepository.findById(managerPublicId, Profile.class);

        manager.getManagerInfo().getUsers().remove(user.getId());
        user.getManagerClientInfo().setManager(null);

        profileRepository.updateProfile(manager);
        profileRepository.updateProfile(user);
    }

    @Override
    public boolean hasManager(String profilePublicId) {
        return profileRepository.hasManager(profilePublicId);
    }

    @Override
    public List<UserProfileShortAdminDto> getManagerUsers(String managerPublicId) {

        String managerId = profileRepository.getIdByPulblicId(managerPublicId);
        List<Profile> users = profileRepository.findUsersByManager(managerId);
        if (users == null) {
            return Collections.EMPTY_LIST;
        }
        return users.stream().
                map(profile -> new UserProfileShortAdminDto(profile, managerPublicId)).
                collect(Collectors.toList());
    }

    @Override
    public UserPrivateProfileDto getManagerUser(String managerPublicId, String publicId) {
        Profile profile = profileRepository.getManagerUser(managerPublicId, publicId);
        if (profile == null) {
            return null;
        }
        return new UserPrivateProfileDto(profile, managerPublicId);

    }

    @Override
    public ManagerPrivateProfileDto findManagerPrivateProfileDTOForAdminByPublicId(String publicId) {
        Profile managerProfile = profileRepository.findByPublicId(publicId, Profile.class);
        Set<String> usersPulblicId = profileRepository.getPulblicIdsByIds(managerProfile.getManagerInfo().getUsers());
        if (managerProfile != null) {
            return new ManagerPrivateProfileDto(managerProfile, usersPulblicId);
        }
        return null;
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
    public boolean hasRole(String profilePublicId, String roleUser) {
        return profileRepository.hasRole(profilePublicId, roleUser);
    }
}
