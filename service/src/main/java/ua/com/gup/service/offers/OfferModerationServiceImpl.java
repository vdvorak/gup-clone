package ua.com.itproekt.gup.service.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
//import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Implementation of the OfferModerationService interface.
 *
 * @author Kobylyatskyy Alexander
 */
@Service
public class OfferModerationServiceImpl implements OfferModerationService {


    @Autowired
    private OffersService offersService;

    @Autowired
    private ActivityFeedService activityFeedService;

//    @Autowired
//    private SubscriptionService subscriptionService;

    @Autowired
    private ProfilesService profilesService;


    @Override
    public HttpStatus editOfferByModerator(Offer inputOffer) {
        System.err.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

        String moderatorId = SecurityOperations.getLoggedUserId();

        System.err.println("#1");
        if (profilesService.findById(moderatorId) == null) {
            return HttpStatus.BAD_REQUEST;
        }

        System.err.println("#2");
        // because moderator can't change almost anything in the offer
        Offer offerAfterUpdate = offersService.findById(inputOffer.getId());


        System.err.println("#3");
        if (inputOffer.getOfferModerationReports() == null) {
            return HttpStatus.BAD_REQUEST;
        }

        System.err.println("#4");
        if (inputOffer.getOfferModerationReports().getModerationStatus() == null) {
            return HttpStatus.BAD_REQUEST;
        }

        System.err.println("#5");
        if (inputOffer.getOfferModerationReports().getModerationStatus() != ModerationStatus.FAIL && inputOffer.getOfferModerationReports().getModerationStatus() != ModerationStatus.COMPLETE) {
            return HttpStatus.BAD_REQUEST;
        }

        System.err.println("#6");
        if (offerAfterUpdate == null) {
            return HttpStatus.NOT_FOUND;
        }

        System.err.println("#7");
        if (offerAfterUpdate.isDeleted()) {
            return HttpStatus.NOT_FOUND;
        }

        // FAIL - we must have not only status FAIL but also reasons
        if (inputOffer.getOfferModerationReports().getModerationStatus() == ModerationStatus.FAIL
//                && inputOffer.getOfferModerationReports().getOfferRefusalReasonses() != null) // TODO it was getOfferRefusalReasonses..!
                && inputOffer.getOfferModerationReports().getOfferRefusalReasons() != null) // TODO it was getOfferRefusalReasons..!
        {
//            // clear old reasons:
//            offerAfterUpdate.getOfferModerationReports().getOfferRefusalReasons().clear(); //offerAfterUpdate.getOfferModerationReports().getOfferRefusalReasonses().clear();
            // add new reasons:
            offerAfterUpdate.getOfferModerationReports().setOfferRefusalReasons(inputOffer.getOfferModerationReports().getOfferRefusalReasons()); //offerAfterUpdate.getOfferModerationReports().setOfferRefusalReasonses(inputOffer.getOfferModerationReports().getOfferRefusalReasonses());

            offerAfterUpdate.getOfferModerationReports().setModerationStatus(ModerationStatus.FAIL); // TODO & setModerationStatus..?

            activityFeedService.createEvent(eventPreparator(offerAfterUpdate, EventType.OFFER_FAIL));
//            subscriptionService.checkIfOfferSuiteForSubscriptionAndSendEmail(inputOffer); // TODO & subscriptionService..? //TODO ?
        }


        /*
          When offer approved by moderator we send notification to user.
          Then we change offer status to Complete.
          Then we find if this offer suit for subscriptions.
         */
        // COMPLETE
        if (inputOffer.getOfferModerationReports().getModerationStatus() == ModerationStatus.COMPLETE) {
            activityFeedService.createEvent(eventPreparator(offerAfterUpdate, EventType.OFFER_COMPLETE));
            offerAfterUpdate.getOfferModerationReports().setModerationStatus(ModerationStatus.COMPLETE);

//            subscriptionService.checkIfOfferSuiteForSubscriptionAndSendEmail(inputOffer); //TODO ?
        }


        // if categories were changed by moderator - we send notification to user
        if (inputOffer.getCategories() != null) {
            offerAfterUpdate.setCategories(inputOffer.getCategories());
            activityFeedService.createEvent(eventPreparator(offerAfterUpdate, EventType.OFFER_MODERATOR_CHANGE_CATEGORY));
        }


        // FixMe .getOfferModerationReports() - тут могут быть NPE
        offerAfterUpdate.getOfferModerationReports().setLastModifiedDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());

        // set moderator ID who modified this offer
        offerAfterUpdate.getOfferModerationReports().setModeratorId(moderatorId);

        offersService.edit(offerAfterUpdate);
        System.err.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");

        return HttpStatus.OK;
    }


    // ToDo вынести этот трешь возможо в отдельную утилиту либо в слой сервис
    private Event eventPreparator(Offer offer, EventType eventType) {
        return new Event()
                .setTargetUId(offer.getAuthorId()) // to whom
                .setType(eventType) // type of event
                .setContentStoreId(offer.getId()) // about what object event (object ID)
                .setContentStoreTitle(offer.getTitle()) // object title
//                .setContentId(order.getId()) // ToDo подумать, что сюда можно записать (если нужно конечно)
                .setMakerId("Moderator") // who send this event (ID)
                .setImgId(getMainOfferImage(offer)) // object image id
                .setMakerName("Moderator"); // who send this event (name)
    }


    private String getMainOfferImage(Offer offer) {
//        Map<String, String> imagesMap;
//
//        imagesMap = offer.getImagesIds();
//
//        for (String s : imagesMap.keySet()) {
//            if (imagesMap.get(s).equals("1")) {
//                return s;
//            }
//        }
        try {
            return offer.getImages().get(0).getImageId();
        } catch (NullPointerException e){}

        return null;
    }
}
