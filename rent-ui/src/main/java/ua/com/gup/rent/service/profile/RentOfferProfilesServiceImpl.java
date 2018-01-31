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
import ua.com.gup.common.dto.profile.manager.ManagerPrivateProfileDto;
import ua.com.gup.common.dto.profile.manager.client.ManagerContactInfoEditDto;
import ua.com.gup.common.model.mongo.manager.ContactInfo;
import ua.com.gup.common.model.mongo.manager.RelevancePhone;
import ua.com.gup.common.service.OperationService;
import ua.com.gup.common.service.impl.CommonProfileServiceImpl;
import ua.com.gup.rent.model.mongo.user.RentManagerClientInfo;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.repository.profile.RentOfferProfileRepository;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.*;

import java.util.Collections;
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
    public void updateUserContactInfoManager(String profilePublicId, ManagerContactInfoEditDto dto) {
        RentOfferProfile user = rentOfferProfileRepository.findByPublicId(profilePublicId, RentOfferProfile.class);
        if (user.getManagerClientInfo() == null) {
            user.setManagerClientInfo(new RentManagerClientInfo());
        }

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setRealAddress(dto.getRealAddress());
        contactInfo.setSkypeUserName(dto.getSkypeUserName());
        contactInfo.setViberUserName(dto.getViberUserName());
        if (dto.getContactPhone() != null) {
            RelevancePhone phone = new RelevancePhone(dto.getContactPhone().number, dto.getContactPhone().relevance);
            contactInfo.setContactPhone(phone);
        }

        user.getManagerClientInfo().setContactInfo(contactInfo);
        user.getManagerClientInfo().setAdditionalInfo(dto.getAdditionalInfo());
        user.getManagerClientInfo().setInterestingStatus(dto.getInterestingStatus());
        rentOfferProfileRepository.save(user);
    }



//    @Override
//    public List<UserProfileShortManagerDto> getManagerUsers(String managerPublicId) {
//
//        RentOfferProfile manager = rentOfferProfileRepository.findByPublicId(managerPublicId, RentOfferProfile.class);
//        List<RentOfferProfile> users = rentOfferProfileRepository.findUsersByManager(manager.getId());
//        if (users == null) {
//            return Collections.EMPTY_LIST;
//        }
//        return users.stream().
//                map(profile -> new UserProfileShortManagerDto(profile, manager)).collect(Collectors.toList());
//    }



    @Override
    public ManagerPrivateProfileDto findManagerPrivateProfileDTOForAdminByPublicId(String publicId) {
        RentOfferProfile managerProfile = rentOfferProfileRepository.findByPublicId(publicId, RentOfferProfile.class);
        Set<String> usersPulblicId = rentOfferProfileRepository.getPulblicIdsByIds(managerProfile.getManagerInfo().getUsers());
        if (managerProfile != null) {
            return new ManagerPrivateProfileDto(managerProfile, usersPulblicId);
        }
        return null;
    }

//    @Override
//    public Page<UserProfileShortManagerDto> findUserProfiles(ManagerProfileFilter filter, Pageable pageable) {
//        return findUserProfiles(null,filter,pageable);
//    }

    private OperationService operationService;


    @Override
    public boolean profileExistsByPublicId(String profilePublicId) {
        return rentOfferProfileRepository.profileExistsByPublicId(profilePublicId);
    }

}
