package ua.com.gup.service.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.security.SecurityUtils;

/**
 * Implementation of the OfferModerationService interface.
 */
@Service
public class OfferModerationServiceImpl implements OfferModerationService {


    @Autowired
    private OfferService offerService;

    @Autowired
    private ProfilesService profilesService;


    @Override
    public HttpStatus editOfferByModerator(Offer inputOffer) {
        String moderatorId = SecurityUtils.getCurrentUserId();
        if (profilesService.findById(moderatorId) == null) {
            return HttpStatus.BAD_REQUEST;
        }
        Offer offerAfterUpdate = offerService.findById(inputOffer.getId());
        if (inputOffer.getLastOfferModerationReport() == null) {
            return HttpStatus.BAD_REQUEST;
        }
        if (offerAfterUpdate == null) {
            return HttpStatus.NOT_FOUND;
        }
        if (inputOffer.getCategories() != null) {
            offerAfterUpdate.setCategories(inputOffer.getCategories());
        }
        offerService.edit(offerAfterUpdate);
        return HttpStatus.OK;
    }
}