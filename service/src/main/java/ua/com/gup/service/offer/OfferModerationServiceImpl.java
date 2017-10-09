package ua.com.gup.service.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.com.gup.domain.offer.Offer;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.util.SecurityOperations;

/**
 * Implementation of the OfferModerationService interface.
 */
@Service
public class OfferModerationServiceImpl implements OfferModerationService {


    @Autowired
    private OffersService offersService;

    @Autowired
    private ProfilesService profilesService;


    @Override
    public HttpStatus editOfferByModerator(Offer inputOffer) {
        String moderatorId = SecurityOperations.getLoggedUserId();
        System.err.println("#1");
        if (profilesService.findById(moderatorId) == null) {
            return HttpStatus.BAD_REQUEST;
        }
        System.err.println("#2");
        Offer offerAfterUpdate = offersService.findById(inputOffer.getId());
        System.err.println("#3");
        if (inputOffer.getLastOfferModerationReport() == null) {
            return HttpStatus.BAD_REQUEST;
        }
        System.err.println("#6");
        if (offerAfterUpdate == null) {
            return HttpStatus.NOT_FOUND;
        }
        if (inputOffer.getCategories() != null) {
            offerAfterUpdate.setCategories(inputOffer.getCategories());
        }
        offersService.edit(offerAfterUpdate);
        return HttpStatus.OK;
    }
}