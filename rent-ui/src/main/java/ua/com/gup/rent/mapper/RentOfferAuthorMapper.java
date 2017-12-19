package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.common.service.dto.CommonAuthorDTO;
import ua.com.gup.rent.service.profile.RentOfferProfilesService;

@Component
public class RentOfferAuthorMapper {

    @Autowired
    private RentOfferProfilesService rentOfferProfilesService;

    public CommonAuthorDTO createAuthorDTO(String authorId) {
        RentOfferProfile rentOfferAuthor  = rentOfferProfilesService.findById(authorId);
        CommonAuthorDTO rentOfferAuthorDTO = new CommonAuthorDTO();
        if (rentOfferAuthor != null) {
            rentOfferAuthorDTO.setId(rentOfferAuthor.getPublicId());
            rentOfferAuthorDTO.setFirstName(rentOfferAuthor.getFirstname());
            rentOfferAuthorDTO.setLastName(rentOfferAuthor.getLastname());
            rentOfferAuthorDTO.setImageId(rentOfferAuthor.getImgId());
            rentOfferAuthorDTO.setImageUrl(rentOfferAuthor.getImgUrl());
        }
        return rentOfferAuthorDTO;
    }
}
