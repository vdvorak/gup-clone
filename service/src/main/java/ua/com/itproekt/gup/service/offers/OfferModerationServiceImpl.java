package ua.com.itproekt.gup.service.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.OfferRefusalReasons;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * Implementation of the OfferModerationService interface.
 *
 * @author Kobylyatskyy Alexander
 */
@Service
public class OfferModerationServiceImpl implements OfferModerationService {


    @Autowired
    OffersService offersService;

    @Autowired
    ActivityFeedService activityFeedService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    ProfilesService profilesService;


    @Override
    public HttpStatus editOfferByModerator(Offer inputOffer) {

        String moderatorId = SecurityOperations.getLoggedUserId();

        if (profilesService.findById(moderatorId) == null) {
            return HttpStatus.BAD_REQUEST;
        }

        // because moderator can't change almost anything in the offer
        Offer offerAfterUpdate = offersService.findById(inputOffer.getId());


        if (inputOffer.getOfferModerationReports() == null) {
            return HttpStatus.BAD_REQUEST;
        }

        if (inputOffer.getOfferModerationReports().getModerationStatus() == null) {
            return HttpStatus.BAD_REQUEST;
        }

        if (inputOffer.getOfferModerationReports().getModerationStatus() != ModerationStatus.FAIL && inputOffer.getOfferModerationReports().getModerationStatus() != ModerationStatus.COMPLETE) {
            return HttpStatus.BAD_REQUEST;
        }

        if (offerAfterUpdate == null) {
            return HttpStatus.NOT_FOUND;
        }

        if (offerAfterUpdate.isDeleted()) {
            return HttpStatus.NOT_FOUND;
        }


        // if status is Fail but reason is not valid - this method can not ba complete.
        if (inputOffer.getOfferModerationReports().getModerationStatus() == ModerationStatus.FAIL && inputOffer.getOfferModerationReports().getOfferRefusalReasons() != null) {

            if (inputOffer.getOfferModerationReports().getOfferRefusalReasons() == OfferRefusalReasons.ADULT_CONTENT) {
                activityFeedService.createEvent(eventPreparator(offerAfterUpdate, EventType.OFFER_FAIL_ADULT_CONTENT));
            }

            if (inputOffer.getOfferModerationReports().getOfferRefusalReasons() == OfferRefusalReasons.MISMATCH_DESCRIBE) {
                activityFeedService.createEvent(eventPreparator(offerAfterUpdate, EventType.OFFER_FAIL_MISMATCH_DESCRIBE));
            }

            if (inputOffer.getOfferModerationReports().getOfferRefusalReasons() == OfferRefusalReasons.PROFANITY) {
                activityFeedService.createEvent(eventPreparator(offerAfterUpdate, EventType.OFFER_FAIL_PROFANITY));
            }

            if (inputOffer.getOfferModerationReports().getOfferRefusalReasons() == OfferRefusalReasons.PROHIBITED_CONTENT) {
                activityFeedService.createEvent(eventPreparator(offerAfterUpdate, EventType.OFFER_FAIL_PROHIBITED_CONTENT));
            }

        }


        /**
         * When offer approved by moderator we send notification to user.
         * Then we change offer status to Complete.
         * Then we find if this offer suit for subscriptions.
         */
        if (inputOffer.getOfferModerationReports().getModerationStatus() == ModerationStatus.COMPLETE) {
            activityFeedService.createEvent(eventPreparator(offerAfterUpdate, EventType.OFFER_COMPLETE));
            offerAfterUpdate.getOfferModerationReports().setModerationStatus(ModerationStatus.COMPLETE);

            subscriptionService.checkIfOfferSuiteForSubscriptionAndSendEmail(inputOffer);
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

        Map<String, String> imagesMap;

        imagesMap = offer.getImagesIds();

        for (String s : imagesMap.keySet()) {
            if (imagesMap.get(s).equals("1")) {
                return s;
            }
        }

        return null;
    }
}
