package ua.com.gup.rent.service.profile;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferProfileDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.*;

import java.util.List;

/**
 * The interface Profiles service.
 */
public interface RentOfferProfilesService extends CommonProfileService<RentOfferProfile> {

    /**
     * Find profile by it's ID.
     *
     * @param id - the profile ID.
     * @return - the profile.
     */


    RentOfferProfile findByPublicId(String id);

    ProfileDTO findPrivateProfileDTOForAdminByPublicId(String publicId);

    Page<ProfileShortAdminDTO> findByRole(ProfileFilter filter, CommonUserRole role, Pageable pageable);

    void linkProfile(String managerPublicId, String profilePublicId);

    void unlinkProfile(String managerPublicId, String profilePublicId);

    void updateUserContactInfoManager(String profilePublicId, ManagerContactInfoEditDto dto);

    boolean hasManager(String profilePublicId);

    List<UserProfileShortManagerDto> getManagerUsers(String managerPublicId);



    RentOfferManagerPrivateProfileDto findManagerPrivateProfileDTOForAdminByPublicId(String publicId);

    Page<UserProfileShortManagerDto> findUserProfiles(ProfileFilter filter, Pageable pageable);

    Page<UserProfileShortManagerDto> findUserProfiles(String managerPublicId, ProfileFilter filter, Pageable pageable);

    UserProfileManagerDto findUserProfile(String profilePublicId);
}