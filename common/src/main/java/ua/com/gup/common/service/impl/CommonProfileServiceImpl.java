package ua.com.gup.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.repository.CommonProfileRepository;
import ua.com.gup.common.service.CommonProfileService;

public abstract class CommonProfileServiceImpl<T extends CommonProfile> implements CommonProfileService<T> {
    @Autowired
    private CommonProfileRepository profileRepository;

    @Override
    public String getIdByPulblicId(String publicId) {
        return profileRepository.getIdByPulblicId(publicId);
    }
}
