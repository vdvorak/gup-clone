package ua.com.gup.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.domain.profile.Profile;
import ua.com.gup.dto.offer.OfferAuthorDTO;
import ua.com.gup.service.profile.ProfilesService;

@Component
public class OfferAuthorMapper {

    @Autowired
    private ProfilesService profilesService;

    public OfferAuthorDTO createAuthorDTO(String authorId) {
        Profile offerAuthor = profilesService.findById(authorId);
        OfferAuthorDTO offerAuthorDTO = new OfferAuthorDTO();
        if (offerAuthor != null) {
            offerAuthorDTO.setId(offerAuthor.getPublicId());
            offerAuthorDTO.setFirstName(offerAuthor.getFirstname());
            offerAuthorDTO.setLastName(offerAuthor.getLastname());
            offerAuthorDTO.setImageId(offerAuthor.getImgId());
            offerAuthorDTO.setImageUrl(offerAuthor.getImgUrl());
        }
        return offerAuthorDTO;
    }
}
