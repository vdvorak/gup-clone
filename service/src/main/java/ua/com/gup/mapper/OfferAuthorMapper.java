package ua.com.gup.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.common.service.dto.CommonAuthorDTO;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.service.profile.ProfilesService;

@Component
public class OfferAuthorMapper {

    @Autowired
    private ProfilesService profilesService;

    public CommonAuthorDTO createAuthorDTO(String authorId) {
        Profile offerAuthor = profilesService.findById(authorId);
        CommonAuthorDTO offerAuthorDTO = new CommonAuthorDTO();
        if (offerAuthor != null) {
            offerAuthorDTO.setId(offerAuthor.getPublicId());
            offerAuthorDTO.setFirstName(offerAuthor.getFirstname());
            offerAuthorDTO.setLastName(offerAuthor.getLastname());            
            offerAuthorDTO.setImageUrl(offerAuthor.imageLargeUrl());
        }
        return offerAuthorDTO;
    }
}
