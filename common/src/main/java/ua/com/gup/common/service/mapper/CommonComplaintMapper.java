package ua.com.gup.common.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.gup.common.model.complaint.ComplaintInitiator;
import ua.com.gup.common.model.mongo.CommonProfile;
import ua.com.gup.common.service.CommonProfileService;

public class CommonComplaintMapper {
    @Autowired
    private CommonProfileService profilesService;

    public ComplaintInitiator getInitiatorProfile(String authorId) {


        CommonProfile initiatorProfile = profilesService.findById(authorId);
        ComplaintInitiator complaintInitiator = new ComplaintInitiator();
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
