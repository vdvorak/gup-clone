package ua.com.gup.rent.service.profile;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.model.enumeration.CommonUserRole;
import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferProfileDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.RentOfferProfileShortAdminDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.RentOfferManagerPrivateProfileDto;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.RentOfferUserPrivateProfileDto;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.RentOfferUserProfileShortAdminDto;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.UserProfileShortManagrDto;

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

    RentOfferProfileDTO findPrivateProfileDTOForAdminByPublicId(String publicId);

    Page<RentOfferProfileShortAdminDTO> findByRole(ProfileFilter filter,CommonUserRole role, Pageable pageable);

    void linkProfile(String managerPublicId, String profilePublicId);

    void unlinkProfile(String managerPublicId, String profilePublicId);

    boolean hasManager(String profilePublicId);

    List<UserProfileShortManagrDto> getManagerUsers(String managerPublicId);

    RentOfferUserPrivateProfileDto getManagerUser(String managerPublicId, String publicId);

    RentOfferManagerPrivateProfileDto findManagerPrivateProfileDTOForAdminByPublicId(String publicId);

    Page<UserProfileShortManagrDto> findUserProfiles(ProfileFilter filter, Pageable pageable);

    Page<UserProfileShortManagrDto> findUserProfiles(String managerPublicId, ProfileFilter filter, Pageable pageable);

}