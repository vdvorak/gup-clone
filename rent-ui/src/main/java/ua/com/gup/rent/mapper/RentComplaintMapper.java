package ua.com.gup.rent.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.rent.model.mongo.user.RentOfferProfile;
import ua.com.gup.rent.model.rent.complaint.RentComplaintInitiator;
import ua.com.gup.rent.service.profile.RentOfferProfilesService;

/**
 * @author Victor Dvorak
 **/
@Component
public class RentComplaintMapper {
    @Autowired
    private RentOfferProfilesService profilesService;

    public RentComplaintInitiator getInitiatorProfile(String authorId) {
        RentOfferProfile initiatorProfile = profilesService.findById(authorId);
        RentComplaintInitiator complaintInitiator = new RentComplaintInitiator();
        if (initiatorProfile != null) {
            complaintInitiator.setId(initiatorProfile.getPublicId());
            complaintInitiator.setFirstName(initiatorProfile.getFirstname());
            complaintInitiator.setLastName(initiatorProfile.getLastname());
            complaintInitiator.setImage(initiatorProfile.getImageLarge());            
            complaintInitiator.setImageUrl(initiatorProfile.getImgUrl());
            complaintInitiator.setEmail(initiatorProfile.getEmail());
        }
        return complaintInitiator;
    }
}
