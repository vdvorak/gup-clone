package ua.com.itproekt.gup.server.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.offer.paidservices.PaidServices;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.server.api.rest.dto.OfferInfo;
import ua.com.itproekt.gup.server.api.rest.dto.OfferRegistration;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.SeoUtils;
import ua.com.itproekt.gup.util.Translit;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
@RequestMapping("/api/rest/offersService")
public class OfferRestController {

    @Autowired
    OffersService offersService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    SeoSequenceService seoSequenceService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    VerificationTokenService verificationTokenService;

    //------------------------------------------ Read -----------------------------------------------------------------

    @CrossOrigin
    @RequestMapping(value = "/offer/read/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferInfo> getOfferById(@PathVariable String id) {
        Offer offer = offersService.findOfferAndIncViews(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        //if user is author - he will receive additional fields
        if (offer.getAuthorId().equals(userId)) {
            return new ResponseEntity<>(offersService.getPrivateOfferInfoByOffer(offer), HttpStatus.OK);
        }

        return new ResponseEntity<>(offersService.getPublicOfferInfoByOffer(offer), HttpStatus.OK);
    }


    @CrossOrigin
    @RequestMapping(value = "/offer/read/relevant/{id}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferInfo> getOfferByIdWithRelevant(@PathVariable String id) {
        Offer offer = offersService.findOfferAndIncViews(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        //if user is author - he will receive additional fields
        if (offer.getAuthorId().equals(userId)) {
            return new ResponseEntity<>(offersService.getPrivateOfferInfoByOffer(offer), HttpStatus.OK);
        }

        // receive list of relevant offer
        List<OfferInfo> relevantOffersList = offersService.getListOfMiniPublicOffersWithOptions(offerFilterOptionsPreparatorForRelevantSearch(offer));


        OfferInfo offerInfo = offersService.getPublicOfferInfoByOffer(offer);
        offerInfo.setRelevantOffersList(relevantOffersList);

        return new ResponseEntity<>(offerInfo, HttpStatus.OK);
    }


    @CrossOrigin
    @RequestMapping(value = "/offer/read/all", method = RequestMethod.POST)
    public ResponseEntity<List<OfferInfo>> listOfAllOffers(@RequestBody OfferFilterOptions offerFO, HttpServletRequest request) {
        if (!request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            offerFO.setActive(true);
            offerFO.setModerationStatus(ModerationStatus.COMPLETE);
        }
        return new ResponseEntity<>(offersService.getListOfMiniPublicOffersWithOptions(offerFO), HttpStatus.OK);
    }

    //------------------------------------------ Create ----------------------------------------------------------------

    /**
     * Create new offer with registered or unregistered user
     *
     * @param offerRegistration - must contain Offer and optional email and password
     * @return - status code 201 if Ok and created
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> createOffer(@Valid @RequestBody OfferRegistration offerRegistration) {

        String userId = SecurityOperations.getLoggedUserId();
        // if user is not logged in
        if (userId == null && offerRegistration.getEmail() != null) {

            if (profilesService.profileExistsWithEmail(offerRegistration.getEmail())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            offerSeoUrlAndPaidServicePreparator(offerRegistration);
            offersService.createWithRegistration(offerRegistration);

            return new ResponseEntity<>(new CreatedObjResp(offerRegistration.getOffer().getSeoUrl()), HttpStatus.CREATED);
        } else {
            // if user is logged in

            offerRegistration.getOffer().setAuthorId(userId);

            offerSeoUrlAndPaidServicePreparator(offerRegistration);

            offersService.create(offerRegistration.getOffer());

            return new ResponseEntity<>(new CreatedObjResp(offerRegistration.getOffer().getSeoUrl()), HttpStatus.CREATED);
        }
    }

    //------------------------------------------ Update ----------------------------------------------------------------

    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> editOffer(@Valid @RequestBody Offer offer) {

        if (offer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!offersService.offerExists(offer.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer oldOffer = offersService.findById(offer.getId());

        String userId = SecurityOperations.getLoggedUserId();

        if (!offersService.findById(offer.getId()).getAuthorId().equals(userId)) {
            if (!profilesService.isUserModerator(profilesService.findById(userId))) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }


        if (profilesService.isUserModerator(profilesService.findById(userId))) {
            offer.getModerationMessage().setCreatedDateEqualsToCurrentDate();
            offer.getModerationMessage().setIsRead(false);
            offer.setLastModerationDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        } else {
            offer.getModerationMessage().setMessage(oldOffer.getModerationMessage().getMessage());
            offer.getModerationMessage().setIsRead(true);
        }


        String newTransiltTitle = Translit.makeTransliteration(offer.getTitle());

        String newSeoUrl = newTransiltTitle + "-" + oldOffer.getSeoKey();

        offer.setSeoUrl(newSeoUrl);

        Offer newOffer = offersService.edit(offer);

        return new ResponseEntity<>(new CreatedObjResp(newOffer.getSeoUrl()), HttpStatus.OK);
    }


    /**
     * Change active status of the offer. Allowed only for offer author.
     *
     * @param offerId offer id.
     * @return status 200 is ok, 403 - user is not an author of the offer, 404 - if offer is not found.
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/changeActiveStatus", method = RequestMethod.GET)
    public ResponseEntity<Void> setActive(@PathVariable String offerId) {

        Offer offer = offersService.findById(offerId);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        if (offer.getAuthorId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (offer.getActive()) {
            offer.setActive(false);
        } else {
            offer.setActive(true);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------ Delete ----------------------------------------------------------------

    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/delete/{offerId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOffer(@PathVariable String offerId) {

        Offer offer = offersService.findById(offerId);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        if (offer.getAuthorId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        offersService.delete(offerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //------------------------------------------ Rest for admin --------------------------------------------------------

    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_SUPPORT','ROLE_MODERATOR')")
    @RequestMapping(value = "/offer/moderateStatus/{offerId}", method = RequestMethod.POST)
    public ResponseEntity<Void> makeOfferComplete(@PathVariable String offerId, @RequestBody ModerationStatus moderationStatus) {


        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (moderationStatus != ModerationStatus.FAIL && moderationStatus != ModerationStatus.COMPLETE) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Offer offer = offersService.findById(offerId);

        // if we don't have lastModerationDate => we must check if this offer suite for some of subscriptions

        if (offer.getLastModerationDate() == null && moderationStatus == ModerationStatus.COMPLETE) {
            offer.setLastModerationDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
            offer.setModerationStatus(moderationStatus);
            offersService.edit(offer);
            subscriptionService.checkIfOfferSuiteForSubscriptionAndSendEmail(offer);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        offer
                .setModerationStatus(moderationStatus)
                .setLastModerationDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
        offersService.edit(offer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Add SeoUrl to offer and create new PaidService in offer
     *
     * @param offerRegistration offerRegistration
     */
    private void offerSeoUrlAndPaidServicePreparator(OfferRegistration offerRegistration) {
        long longValueOfSeoKey = seoSequenceService.getNextSequenceId();
        SeoUtils.makeSeoFieldsForOffer(offerRegistration.getOffer(), longValueOfSeoKey);

        PaidServices paidServices = new PaidServices();
        paidServices.setLastUpdateDateToCurrentDate();
        offerRegistration.getOffer().setPaidServices(paidServices);
    }

    private OfferFilterOptions offerFilterOptionsPreparatorForRelevantSearch(Offer offer) {
        OfferFilterOptions offerFilterOptions = new OfferFilterOptions();

        // add categories in filter
        offerFilterOptions.setCategories(offer.getCategories());

        // add adress in filter
        if (offer.getAddress().getCity() != null) {
            offerFilterOptions.getAddress().setCity(offer.getAddress().getCity());
        } else {
            if (offer.getAddress().getArea() != null) {
                offerFilterOptions.getAddress().setArea(offer.getAddress().getArea());
            }
        }
        return offerFilterOptions;
    }


    //ToDo удалить перед продакшеном
//    @RequestMapping(value = "/subscription/test/{offerId}", method = RequestMethod.POST)
//    public ResponseEntity<Void> test(@PathVariable String offerId) {
//
//        Offer offer = offersService.findById(offerId);
////        offer.setLastModerationDate(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
//        subscriptionService.checkIfOfferSuiteForSubscriptionAndSendEmail(offer);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//    }
}
