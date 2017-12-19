package ua.com.gup.common.mapper;

import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.service.CommonProfileService;
import ua.com.gup.common.service.dto.CommonAuthorDTO;

public abstract class CommonAuthorMapper {

    public CommonAuthorDTO createAuthorDTO(String authorId) {
        CommonProfile profile = getProfileService().findById(authorId);
        CommonAuthorDTO authorDTO = new CommonAuthorDTO();
        if (profile != null) {
            authorDTO.setId(profile.getPublicId());
            authorDTO.setFirstName(profile.getFirstname());
            authorDTO.setLastName(profile.getLastname());
            authorDTO.setImageUrl(profile.imageLargeUrl());
        }
        return authorDTO;
    }

    protected abstract CommonProfileService getProfileService();
}
