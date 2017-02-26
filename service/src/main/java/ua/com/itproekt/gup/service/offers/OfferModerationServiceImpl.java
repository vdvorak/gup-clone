package ua.com.itproekt.gup.service.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.Image;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.OfferRefusalReason;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.SecurityOperations;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ProfilesService profilesService;


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


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println( inputOffer );
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Offer{id='58b19cb64c8e83fbe9581764', authorId='588b6f964c8e9af4b7e2081a', userInfo=OfferUserContactInfo{contactName='Назаренко Дмитрий', email='diimonss@gmail.com', phoneNumbers=[(234) 234-2342]}, active=true, createdDate=1488034998356, reservation=null, views=3, monthOfPrices=null, rents=null, rent=null, seoUrl='2-k-kvartira-m-chernihovskaia-ej', seoKey='ej', categories=[1, 15, 1147], seoCategory='Долгосрочная аренда квартир', properties=[ua.com.itproekt.gup.model.offer.Property@50ee06d8, ua.com.itproekt.gup.model.offer.Property@3a748cc6], imagesIds=null, images=[Image{index=null, url='null', id='58b19cb54c8e83fbe9581734'}, Image{index=null, url='null', id='58b19cb54c8e83fbe958173a'}, Image{index=null, url='null', id='58b19cb54c8e83fbe9581740'}, Image{index=null, url='null', id='58b19cb54c8e83fbe9581746'}, Image{index=null, url='null', id='58b19cb64c8e83fbe958174c'}, Image{index=null, url='null', id='58b19cb64c8e83fbe9581752'}, Image{index=null, url='null', id='58b19cb64c8e83fbe9581758'}, Image{index=null, url='null', id='58b19cb64c8e83fbe958175e'}], videoUrl='null', title='2-к квартира м.Черниговская', description='Сдам 2х комнатную квартиру в нормальном жилом состоянии, ул. Города Шалетт 14, 10мин пешком м. Черниговская.
        // В квартире три спальных места, мебель 2000х годов, стиралка автомат, телевизор, холодильник.
        // Хорошая адекватная хозяйка
        // Все вопросы по телефону
        // Риелтор комиссия 50%', price=500000, priceWithVat=false, currency=UAH, address=Address{coordinates='ChIJ94YCHk7E1EAR1dZw1w37AHA', country='1', area='9', city='189', district='null', street='null', lat=null, lng=null}, priceCanBeNegotiated=null, used=null, canBeReserved=null, canBeRented=null, showOrdersCount=true, maximumReservedPeriod=null, availableShippingMethods=[], availablePaymentMethods=[CASH_PAYMENT], paidServices=PaidServices{isMarked=null, isUrgent=null, isCheaper=null, lastPaidUpdateDate=1488034997670}, productReturnsTerms=null, offerModerationReports=OfferModerationReports{moderatorId='null', lastModifiedDate=null, moderationStatus=FAIL, offerRefusalReasons=null, offerModifiedFieldLIst=null}, deleted=false}
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println( "getModerationStatus="+inputOffer.getOfferModerationReports().getModerationStatus() );
        System.out.println( "getOfferRefusalReasons="+inputOffer.getOfferModerationReports().getOfferRefusalReasons() ); //System.err.println( "getOfferRefusalReasonses="+inputOffer.getOfferModerationReports().getOfferRefusalReasonses() );
        System.out.println("======================================================================================");
        System.out.println( inputOffer.getOfferModerationReports() );
        // OfferModerationReports{moderatorId='null', lastModifiedDate=null, moderationStatus=FAIL, offerRefusalReasons=null, offerModifiedFieldLIst=null}
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // FAIL - we must have not only status FAIL but also reasons
        if (inputOffer.getOfferModerationReports().getModerationStatus() == ModerationStatus.FAIL
//                && inputOffer.getOfferModerationReports().getOfferRefusalReasonses() != null) // TODO it was getOfferRefusalReasonses..!
                && inputOffer.getOfferModerationReports().getOfferRefusalReasons() != null) // TODO it was getOfferRefusalReasons..!
        {
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println( inputOffer );
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////

//            // clear old reasons:
//            offerAfterUpdate.getOfferModerationReports().getOfferRefusalReasons().clear(); //offerAfterUpdate.getOfferModerationReports().getOfferRefusalReasonses().clear();
            // add new reasons:
            offerAfterUpdate.getOfferModerationReports().setOfferRefusalReasons(inputOffer.getOfferModerationReports().getOfferRefusalReasons()); //offerAfterUpdate.getOfferModerationReports().setOfferRefusalReasonses(inputOffer.getOfferModerationReports().getOfferRefusalReasonses());

            offerAfterUpdate.getOfferModerationReports().setModerationStatus(ModerationStatus.FAIL); // TODO & setModerationStatus..?

            activityFeedService.createEvent(eventPreparator(offerAfterUpdate, EventType.OFFER_FAIL));
            subscriptionService.checkIfOfferSuiteForSubscriptionAndSendEmail(inputOffer); // TODO & subscriptionService..?
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
