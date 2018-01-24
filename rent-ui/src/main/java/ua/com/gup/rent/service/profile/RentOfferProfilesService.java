package ua.com.gup.rent.service.profile;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.ManagerContactInfoEditDto;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.RentOfferManagerPrivateProfileDto;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.UserProfileManagerDto;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.UserProfileShortManagerDto;

import java.util.List;

/**
 * The interface Profiles service.
 */
public interface RentOfferProfilesService extends CommonProfileService<RentOfferProfile> {

    ProfileDTO findPrivateProfileDTOForAdminByPublicId(String publicId);

    Page<ProfileShortAdminDTO> findByRole(String role, Pageable pageable);

    void linkProfile(String managerPublicId, String profilePublicId);

    void unlinkProfile(String managerPublicId, String profilePublicId);

    void updateUserContactInfoManager(String profilePublicId, ManagerContactInfoEditDto dto);

    boolean hasManager(String profilePublicId);

    List<UserProfileShortManagerDto> getManagerUsers(String managerPublicId);


    RentOfferManagerPrivateProfileDto findManagerPrivateProfileDTOForAdminByPublicId(String publicId);

    Page<UserProfileShortManagerDto> findUserProfiles(ProfileFilter filter, Pageable pageable);

    Page<UserProfileShortManagerDto> findUserProfiles(String managerPublicId, ProfileFilter filter, Pageable pageable);


    boolean profileExistsByPublicId(String id);

    UserProfileManagerDto findUserProfile(String profilePublicId);
}