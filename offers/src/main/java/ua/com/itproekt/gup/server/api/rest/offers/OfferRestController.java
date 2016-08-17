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
import ua.com.itproekt.gup.server.api.rest.dto.OfferRegistration;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
    public ResponseEntity<Offer> getOfferById(@PathVariable String id) {
        Offer offer = offersService.findOfferAndIncViews(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/offer/read/all", method = RequestMethod.POST)
    public ResponseEntity<EntityPage<Offer>> listOfAllOffers(@RequestBody OfferFilterOptions offerFO, HttpServletRequest request) {
        if (!request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            offerFO.setActive(true);
            offerFO.setModerationStatus(ModerationStatus.COMPLETE);
        }
//        return ModelUtil.toModel(offersService.findOffersWihOptions(offerFO));
        return new ResponseEntity<>(offersService.findOffersWihOptions(offerFO), HttpStatus.OK);
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

    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/setActive/{isActive}", method = RequestMethod.POST)
    public ResponseEntity<Void> setActive(@PathVariable String offerId, @PathVariable boolean isActive) {

        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        offersService.setActive(offerId, isActive);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------ Delete ----------------------------------------------------------------

    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/delete/{offerId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOffer(@PathVariable String offerId) {

        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
     * @param offerRegistration
     */
    private void offerSeoUrlAndPaidServicePreparator(OfferRegistration offerRegistration) {
        long longValueOfSeoKey = seoSequenceService.getNextSequenceId();
        SeoUtils.makeSeoFieldsForOffer(offerRegistration.getOffer(), longValueOfSeoKey);

        PaidServices paidServices = new PaidServices();
        paidServices.setLastUpdateDateToCurrentDate();
        offerRegistration.getOffer().setPaidServices(paidServices);
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
