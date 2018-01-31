package ua.com.gup.rent.service.profile;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.common.dto.profile.manager.ManagerPrivateProfileDto;
import ua.com.gup.common.dto.profile.manager.client.ManagerContactInfoEditDto;
import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;

/**
 * The interface Profiles service.
 */
public interface RentOfferProfilesService extends CommonProfileService<RentOfferProfile> {

    ProfileDTO findPrivateProfileDTOForAdminByPublicId(String publicId);

    Page<ProfileShortAdminDTO> findByRole(String role, Pageable pageable);

    void updateUserContactInfoManager(String profilePublicId, ManagerContactInfoEditDto dto);

    //List<UserProfileShortManagerDto> getManagerUsers(String managerPublicId);

    //Page<UserProfileShortManagerDto> findUserProfiles(ManagerProfileFilter filter, Pageable pageable);

    //Page<UserProfileShortManagerDto> findUserProfiles(String managerPublicId, ManagerProfileFilter filter, Pageable pageable);

    ManagerPrivateProfileDto findManagerPrivateProfileDTOForAdminByPublicId(String publicId);

    boolean profileExistsByPublicId(String id);


}