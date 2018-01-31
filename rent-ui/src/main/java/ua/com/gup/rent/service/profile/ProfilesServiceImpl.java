package ua.com.gup.rent.service.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.com.gup.common.dto.profile.AdminPrivateProfileDTO;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.common.model.mongo.manager.ContactInfo;
import ua.com.gup.common.model.mongo.manager.RelevancePhone;
import ua.com.gup.common.service.impl.CommonProfileServiceImpl;
import ua.com.gup.rent.model.mongo.user.ExtendManagerUserInfo;
import ua.com.gup.rent.model.mongo.user.ManagerProfile;
import ua.com.gup.rent.model.mongo.user.Profile;
import ua.com.gup.rent.model.mongo.user.UserProfile;
import ua.com.gup.rent.repository.profile.ProfileRepositoryFilter;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProfilesServiceImpl extends CommonProfileServiceImpl<Profile> implements ProfilesService {
    private final Logger log = LoggerFactory.getLogger(ProfilesServiceImpl.class);

    @Autowired
    private RentOfferProfileRepository rentOfferProfileRepository;


    @Override
    public Profile findById(String id) {
        return rentOfferProfileRepository.findById(id);
    }

    @Override
    public boolean hasRole(String profilePublicId, String roleUser) {
        return rentOfferProfileRepository.hasRole(profilePublicId, roleUser);
    }

    @Override
    public Profile findByPublicId(String id) {
        return rentOfferProfileRepository.findByPublicId(id);
    }

    @Override
    public ProfileDTO findPrivateProfileDTOForAdminByPublicId(String publicId) {
        return new AdminPrivateProfileDTO(rentOfferProfileRepository.findByPublicId(publicId));
    }

    @Override
    public Page<ProfileShortAdminDTO> findByRole(String role, Pageable pageable) {
        long count = rentOfferProfileRepository.countByRole(role);
        List<Profile> fullProfiles = Collections.EMPTY_LIST;
        if (count > 0) {
            fullProfiles = rentOfferProfileRepository.findByRole(role, pageable);
        }
        List<ProfileShortAdminDTO> list = fullProfiles.stream().
                map(profile -> new ProfileShortAdminDTO(profile)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public void linkProfile(String managerPublicId, String profilePublicId) {
        UserProfile user = rentOfferProfileRepository.findByPublicId(profilePublicId, UserProfile.class);
        ManagerProfile manager = rentOfferProfileRepository.findByPublicId(managerPublicId, ManagerProfile.class);

        manager.getUsers().add(user.getId());
        user.setManager(manager.getId());

        rentOfferProfileRepository.updateProfile(manager);
        rentOfferProfileRepository.updateProfile(user);
    }

    @Override
    public void unlinkProfile(String managerPublicId, String profilePublicId) {
        UserProfile user = rentOfferProfileRepository.findByPublicId(profilePublicId, UserProfile.class);
        ManagerProfile manager = rentOfferProfileRepository.findByPublicId(managerPublicId, ManagerProfile.class);

        manager.getUsers().remove(user.getId());
        user.setManager(null);

        rentOfferProfileRepository.updateProfile(manager);
        rentOfferProfileRepository.updateProfile(user);
    }

    @Override
    public void updateUserContactInfoManager(String profilePublicId, ManagerContactInfoEditDTO dto) {
        UserProfile user = rentOfferProfileRepository.findByPublicId(profilePublicId, UserProfile.class);
        if (user.getManagerInfo() == null) {
            user.setManagerInfo(new ExtendManagerUserInfo());
        }

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setRealAddress(dto.getRealAddress());
        contactInfo.setSkypeUserName(dto.getSkypeUserName());
        contactInfo.setViberUserName(dto.getViberUserName());
        if (dto.getContactPhone() != null) {
            RelevancePhone phone = new RelevancePhone(dto.getContactPhone().number, dto.getContactPhone().relevance);
            contactInfo.setContactPhone(phone);
        }

        user.getManagerInfo().setContactInfo(contactInfo);
        user.getManagerInfo().setAdditionalInfo(dto.getAdditionalInfo());
        user.getManagerInfo().setInterestingStatus(dto.getInterestingStatus());
        rentOfferProfileRepository.save(user);
    }

    @Override
    public boolean hasManager(String profilePublicId) {
        return rentOfferProfileRepository.hasManager(profilePublicId);
    }

    @Override
    public List<UserProfileShortManagerDTO> getManagerUsers(String managerPublicId) {

        ManagerProfile manager = rentOfferProfileRepository.findByPublicId(managerPublicId, ManagerProfile.class);
        List<UserProfile> users = rentOfferProfileRepository.findUsersByManager(manager.getId());
        if (users == null) {
            return Collections.EMPTY_LIST;
        }
        return users.stream().
                map(profile -> new UserProfileShortManagerDTO(profile, manager)).collect(Collectors.toList());
    }



    @Override
    public ManagerPrivateProfileDTO findManagerPrivateProfileDTOForAdminByPublicId(String publicId) {
        ManagerProfile managerProfile = rentOfferProfileRepository.findByPublicId(publicId, ManagerProfile.class);
        Set<String> usersPulblicId = rentOfferProfileRepository.getPulblicIdsByIds(managerProfile.getUsers());
        if (managerProfile != null) {
            return new ManagerPrivateProfileDTO(managerProfile, usersPulblicId);
        }
        return null;
    }

    @Override
    public Page<UserProfileShortManagerDTO> findUserProfiles(ProfileFilter filter, Pageable pageable) {
        return findUserProfiles(null,filter,pageable);
    }

    public Page<UserProfileShortManagerDTO> findUserProfiles(String managerPublicId, ProfileFilter filter, Pageable pageable){
        ProfileRepositoryFilter repositoryFilter = new ProfileRepositoryFilter(filter);
        repositoryFilter.setManagerPublicId(managerPublicId);
        long count = rentOfferProfileRepository.countByFilter(repositoryFilter);
        List<UserProfile> fullProfiles = Collections.EMPTY_LIST;
        if (count > 0) {
            fullProfiles = rentOfferProfileRepository.findByFilter(repositoryFilter, pageable, UserProfile.class );

        }
        List<UserProfileShortManagerDTO> result = new LinkedList();
        for (UserProfile user: fullProfiles) {
            UserProfileShortManagerDTO dto = new UserProfileShortManagerDTO(user, null);
            if (user.getManager() != null) {
                ManagerProfile manager = rentOfferProfileRepository.findById(user.getManager(), ManagerProfile.class);
                ManagerInfoUserProfileShortDTO managerDto = new ManagerInfoUserProfileShortDTO(user.getManagerInfo(),manager);
                dto.setManagerInfo(managerDto);
            }
            result.add(dto);
        }
        return new PageImpl<>(result, pageable, count);
    }


    @Override
    public UserProfileManagerDTO findUserProfile(String profilePublicId) {
        UserProfile user = rentOfferProfileRepository.findByPublicId(profilePublicId, UserProfile.class);
        ManagerProfile manager = rentOfferProfileRepository.findById(user.getManager(), ManagerProfile.class);
        UserProfileManagerDTO userDto = new UserProfileManagerDTO(user, manager);


        return userDto;

    }

    @Override
    public boolean profileExistsByPublicId(String profilePublicId) {
        return rentOfferProfileRepository.profileExistsByPublicId(profilePublicId);
    }

}
