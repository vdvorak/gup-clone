package ua.com.gup.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.manager.ManagerClientProfileDto;
import ua.com.gup.common.dto.profile.manager.ManagerPrivateProfileDto;
import ua.com.gup.common.dto.profile.manager.ManagerProfileFilter;
import ua.com.gup.common.dto.profile.manager.client.ManagerContactInfoEditDto;

public interface CommonManagerService {

    ManagerPrivateProfileDto findManagerByPublicId(String publicId);

    void linkProfile(String managerPublicId, String profilePublicId);

    void unlinkProfile(String managerPublicId, String profilePublicId);

    boolean hasManager(String profilePublicId);

    Page<ManagerClientProfileDto> findUserProfiles(String managerPublicId, ManagerProfileFilter filter, Pageable pageable);

    Page<ManagerClientProfileDto> findUserProfiles(ManagerProfileFilter filter, Pageable pageable);

    ManagerClientProfileDto findUserProfile(String profilePublicId);

    void updateUserContactInfoManager(String profilePublicId, ManagerContactInfoEditDto dto);
}
