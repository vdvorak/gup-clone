package ua.com.gup.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.gup.mongo.composition.domain.profile.Profile;
import ua.com.gup.mongo.model.complaint.ComplaintInitiator;
import ua.com.gup.service.profile.ProfilesService;

/**
 * @author Victor Dvorak
 **/
@Component
public class ComplaintMapper {
    @Autowired
    private ProfilesService profilesService;

    public ComplaintInitiator getInitiatorProfile(String authorId) {
        Profile initiatorProfile = profilesService.findById(authorId);
        ComplaintInitiator complaintInitiator = new ComplaintInitiator();
        if (initiatorProfile != null) {
            complaintInitiator.setId(initiatorProfile.getPublicId());
            complaintInitiator.setFirstName(initiatorProfile.getFirstname());
            complaintInitiator.setLastName(initiatorProfile.getLastname());
            complaintInitiator.setImageId(initiatorProfile.getImgId());
            complaintInitiator.setImageUrl(initiatorProfile.getImgUrl());
            complaintInitiator.setEmail(initiatorProfile.getEmail());
        }
        return complaintInitiator;
    }
}
