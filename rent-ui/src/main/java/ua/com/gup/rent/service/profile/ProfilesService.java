package ua.com.gup.rent.service.profile;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.ProfileDTO;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.rent.model.mongo.user.Profile;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.ManagerContactInfoEditDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.ManagerPrivateProfileDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.UserProfileManagerDTO;
import ua.com.gup.rent.service.dto.rent.offer.profile.manager.UserProfileShortManagerDTO;

import java.util.List;

/**
 * The interface Profiles service.
 */
public interface ProfilesService extends CommonProfileService<Profile> {

    ProfileDTO findPrivateProfileDTOForAdminByPublicId(String publicId);

    Page<ProfileShortAdminDTO> findByRole(String role, Pageable pageable);

    void linkProfile(String managerPublicId, String profilePublicId);

    void unlinkProfile(String managerPublicId, String profilePublicId);

    void updateUserContactInfoManager(String profilePublicId, ManagerContactInfoEditDTO dto);

    boolean hasManager(String profilePublicId);

    List<UserProfileShortManagerDTO> getManagerUsers(String managerPublicId);


    ManagerPrivateProfileDTO findManagerPrivateProfileDTOForAdminByPublicId(String publicId);

    Page<UserProfileShortManagerDTO> findUserProfiles(ProfileFilter filter, Pageable pageable);

    Page<UserProfileShortManagerDTO> findUserProfiles(String managerPublicId, ProfileFilter filter, Pageable pageable);


    boolean profileExistsByPublicId(String id);

    UserProfileManagerDTO findUserProfile(String profilePublicId);
}