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
import ua.com.gup.common.model.security.Role;
import ua.com.gup.common.service.OperationService;
import ua.com.gup.common.service.impl.CommonProfileServiceImpl;
import ua.com.gup.rent.model.mongo.user.RentManagerUserInfo;
import ua.com.gup.rent.model.mongo.user.RentOfferManagerProfile;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.mongo.user.RentOfferUserProfile;
import ua.com.gup.rent.repository.profile.ProfileRepositoryFilter;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class RentOfferProfilesServiceImpl extends CommonProfileServiceImpl<RentOfferProfile> implements RentOfferProfilesService {
    private final Logger log = LoggerFactory.getLogger(RentOfferProfilesServiceImpl.class);

    @Autowired
    private RentOfferProfileRepository rentOfferProfileRepository;


    @Override
    public RentOfferProfile findById(String id) {
        return rentOfferProfileRepository.findById(id);
    }

    @Override
    public boolean hasRole(String profilePublicId, String roleUser) {
        return rentOfferProfileRepository.hasRole(profilePublicId, roleUser);
    }

    @Override
    public RentOfferProfile findByPublicId(String id) {
        return rentOfferProfileRepository.findByPublicId(id);
    }

    @Override
    public ProfileDTO findPrivateProfileDTOForAdminByPublicId(String publicId) {
        return new AdminPrivateProfileDTO(rentOfferProfileRepository.findByPublicId(publicId));
    }

    @Override
    public Page<ProfileShortAdminDTO> findByRole(String role, Pageable pageable) {
        long count = rentOfferProfileRepository.countByRole(role);
        List<RentOfferProfile> fullProfiles = Collections.EMPTY_LIST;
        if (count > 0) {
            fullProfiles = rentOfferProfileRepository.findByRole(role, pageable);
        }
        List<ProfileShortAdminDTO> list = fullProfiles.stream().
                map(profile -> new ProfileShortAdminDTO(profile)).collect(Collectors.toList());
        return new PageImpl<>(list, pageable, count);
    }

    @Override
    public void linkProfile(String managerPublicId, String profilePublicId) {
        RentOfferUserProfile user = rentOfferProfileRepository.findByPublicId(profilePublicId, RentOfferUserProfile.class);
        RentOfferManagerProfile manager = rentOfferProfileRepository.findByPublicId(managerPublicId, RentOfferManagerProfile.class);

        manager.getUsers().add(user.getId());
        user.setManager(manager.getId());

        rentOfferProfileRepository.updateProfile(manager);
        rentOfferProfileRepository.updateProfile(user);
    }

    @Override
    public void unlinkProfile(String managerPublicId, String profilePublicId) {
        RentOfferUserProfile user = rentOfferProfileRepository.findByPublicId(profilePublicId, RentOfferUserProfile.class);
        RentOfferManagerProfile manager = rentOfferProfileRepository.findByPublicId(managerPublicId, RentOfferManagerProfile.class);

        manager.getUsers().remove(user.getId());
        user.setManager(null);

        rentOfferProfileRepository.updateProfile(manager);
        rentOfferProfileRepository.updateProfile(user);
    }

    @Override
    public void updateUserContactInfoManager(String profilePublicId, ManagerContactInfoEditDto dto) {
        RentOfferUserProfile user = rentOfferProfileRepository.findByPublicId(profilePublicId, RentOfferUserProfile.class);
        if (user.getManagerInfo() == null) {
            user.setManagerInfo(new RentManagerUserInfo());
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
    public List<UserProfileShortManagerDto> getManagerUsers(String managerPublicId) {

        RentOfferManagerProfile manager = rentOfferProfileRepository.findByPublicId(managerPublicId, RentOfferManagerProfile.class);
        List<RentOfferUserProfile> users = rentOfferProfileRepository.findUsersByManager(manager.getId());
        if (users == null) {
            return Collections.EMPTY_LIST;
        }
        return users.stream().
                map(profile -> new UserProfileShortManagerDto(profile, manager)).collect(Collectors.toList());
    }



    @Override
    public RentOfferManagerPrivateProfileDto findManagerPrivateProfileDTOForAdminByPublicId(String publicId) {
        RentOfferManagerProfile managerProfile = rentOfferProfileRepository.findByPublicId(publicId, RentOfferManagerProfile.class);
        Set<String> usersPulblicId = rentOfferProfileRepository.getPulblicIdsByIds(managerProfile.getUsers());
        if (managerProfile != null) {
            return new RentOfferManagerPrivateProfileDto(managerProfile, usersPulblicId);
        }
        return null;
    }

    @Override
    public Page<UserProfileShortManagerDto> findUserProfiles(ProfileFilter filter, Pageable pageable) {
        return findUserProfiles(null,filter,pageable);
    }

    public Page<UserProfileShortManagerDto> findUserProfiles(String managerPublicId, ProfileFilter filter, Pageable pageable){
        ProfileRepositoryFilter repositoryFilter = new ProfileRepositoryFilter(filter);
        repositoryFilter.setManagerPublicId(managerPublicId);
        long count = rentOfferProfileRepository.countByFilter(repositoryFilter);
        List<RentOfferUserProfile> fullProfiles = Collections.EMPTY_LIST;
        if (count > 0) {
            fullProfiles = rentOfferProfileRepository.findByFilter(repositoryFilter, pageable, RentOfferUserProfile.class );

        }
        List<UserProfileShortManagerDto> result = new LinkedList();
        for (RentOfferUserProfile user: fullProfiles) {
            UserProfileShortManagerDto dto = new UserProfileShortManagerDto(user, null);
            if (user.getManager() != null) {
                RentOfferManagerProfile manager = rentOfferProfileRepository.findById(user.getManager(), RentOfferManagerProfile.class);
                ManagerInfoUserProfileShortDto managerDto = new ManagerInfoUserProfileShortDto(user.getManagerInfo(),manager);
                dto.setManagerInfo(managerDto);
            }
            result.add(dto);
        }
        return new PageImpl<>(result, pageable, count);
    }


    @Override
    public UserProfileManagerDto findUserProfile(String profilePublicId) {
        RentOfferUserProfile user = rentOfferProfileRepository.findByPublicId(profilePublicId, RentOfferUserProfile.class);
        RentOfferManagerProfile manager = rentOfferProfileRepository.findById(user.getManager(), RentOfferManagerProfile.class);
        UserProfileManagerDto userDto = new UserProfileManagerDto(user, manager);


        return userDto;

    }

    @Override
    public boolean profileExistsByPublicId(String profilePublicId) {
        return rentOfferProfileRepository.profileExistsByPublicId(profilePublicId);
    }

}
