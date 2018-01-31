package ua.com.gup.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ua.com.gup.common.dto.profile.manager.ManagerClientProfileDto;
import ua.com.gup.common.dto.profile.manager.ManagerPrivateProfileDto;
import ua.com.gup.common.dto.profile.manager.ManagerProfileFilter;
import ua.com.gup.common.dto.profile.manager.client.CommonClientInfoProfileDto;
import ua.com.gup.common.dto.profile.manager.client.ManagerContactInfoEditDto;
import ua.com.gup.common.mapper.manager.CommonManagerClientMapper;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.model.mongo.manager.ContactInfo;
import ua.com.gup.common.model.mongo.manager.RelevancePhone;
import ua.com.gup.common.repository.CommonProfileRepository;
import ua.com.gup.common.repository.filter.ProfileRepositoryFilter;
import ua.com.gup.common.service.CommonManagerService;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class CommonManagerServiceImpl implements CommonManagerService {
    @Autowired
    private CommonProfileRepository profileRepository;

    @Override
    public ManagerPrivateProfileDto findManagerByPublicId(String publicId) {
        CommonProfile managerProfile = profileRepository.findByPublicId(publicId);
        Set<String> usersPulblicId = profileRepository.getPulblicIdsByIds(managerProfile.getManagerInfo().getUsers());
        if (managerProfile != null) {
            return new ManagerPrivateProfileDto(managerProfile, usersPulblicId);
        }
        return null;
    }

    @Override
    public void linkProfile(String managerPublicId, String profilePublicId) {
        CommonProfile user = profileRepository.findByPublicId(profilePublicId);
        CommonProfile manager = profileRepository.findByPublicId(managerPublicId);

        manager.getManagerInfo().getUsers().add(user.getId());
        user.getManagerClientInfo().setManager(manager.getId());

        profileRepository.updateProfile(manager);
        profileRepository.updateProfile(user);
    }

    @Override
    public void unlinkProfile(String managerPublicId, String profilePublicId) {
        CommonProfile user = profileRepository.findByPublicId(profilePublicId);
        CommonProfile manager = profileRepository.findByPublicId(managerPublicId);

        manager.getManagerInfo().getUsers().remove(user.getId());
        user.getManagerClientInfo().setManager(null);

        profileRepository.updateProfile(manager);
        profileRepository.updateProfile(user);
    }

    @Override
    public boolean hasManager(String profilePublicId) {
        return profileRepository.hasManager(profilePublicId);
    }

    @Autowired
    private CommonManagerClientMapper managerClientMapper;

    public Page<ManagerClientProfileDto> findUserProfiles(String managerPublicId, ManagerProfileFilter filter, Pageable pageable){
        ProfileRepositoryFilter repositoryFilter = new ProfileRepositoryFilter(filter);
        repositoryFilter.setManagerPublicId(managerPublicId);
        long count = profileRepository.countByFilter(repositoryFilter);
        List<CommonProfile> fullProfiles = Collections.EMPTY_LIST;
        if (count > 0) {
            fullProfiles = profileRepository.findByFilter(repositoryFilter, pageable);
        }

        List<ManagerClientProfileDto> result = new LinkedList();
        for (CommonProfile user: fullProfiles) {
            ManagerClientProfileDto dto = managerClientMapper.convert(user);
            result.add(dto);
        }
        return new PageImpl<>(result, pageable, count);
    }

    @Override
    public Page<ManagerClientProfileDto> findUserProfiles(ManagerProfileFilter filter, Pageable pageable) {
        return findUserProfiles(null,filter,pageable);
    }

    @Override
    public ManagerClientProfileDto findUserProfile(String profilePublicId) {
        CommonProfile user = profileRepository.findByPublicId(profilePublicId);
        return managerClientMapper.convert(user);

    }


    @Override
    public void updateUserContactInfoManager(String profilePublicId, ManagerContactInfoEditDto dto) {
        CommonProfile user = profileRepository.findByPublicId(profilePublicId);

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
        profileRepository.updateProfile(user);
    }

}
