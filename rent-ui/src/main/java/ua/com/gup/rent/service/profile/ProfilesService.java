package ua.com.gup.rent.service.profile;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.ProfileShortAdminDTO;
import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.rent.model.mongo.user.Profile;

/**
 * The interface Profiles service.
 */
public interface ProfilesService extends CommonProfileService<Profile> {

    Page<ProfileShortAdminDTO> findByRole(String role, Pageable pageable);

}