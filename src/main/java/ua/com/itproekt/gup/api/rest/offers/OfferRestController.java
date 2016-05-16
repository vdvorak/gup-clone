package ua.com.itproekt.gup.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/rest/offersService")
public class OfferRestController {

    @Autowired
    OffersService offersService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    SeoSequenceService seoSequenceService;

    //------------------------------------------ Read -----------------------------------------------------------------

    @RequestMapping(value = "/offer/id/{id}/read", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Offer> getOfferById(@PathVariable String id) {
        Offer offer = offersService.findOfferAndIncViews(id);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @RequestMapping(value = "/offer/read/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityPage<Offer>> listOfAllOffers(@RequestBody OfferFilterOptions offerFO,
                                                             HttpServletRequest request) {
        if (!request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            offerFO.setActive(true);
            offerFO.setModerationStatus(ModerationStatus.COMPLETE);
        }

        EntityPage<Offer> offers = offersService.findOffersWihOptions(offerFO);

        if (offers.getEntities().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    //------------------------------------------ Create -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> createOffer(@Valid @RequestBody Offer offer) {

        if (offer.getUserInfo() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String userId;
        if (SecurityOperations.isUserLoggedIn()) {
            userId = SecurityOperations.getLoggedUserId();
        } else {
            if (profilesService.profileExistsWithEmail(offer.getUserInfo().getEmail())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            Profile profile = new Profile();
            profile.setEmail(offer.getUserInfo().getEmail());
            Set<UserRole> offerUserRoleSet = new HashSet<>();
            offerUserRoleSet.add(UserRole.ROLE_OFFERS_USER_UNCONFIRMED);
            profile.setUserRoles(offerUserRoleSet);

            profilesService.createProfile(profile);
            userId = profile.getId();
        }

        offer.setAuthorId(userId);


        long longValueOfSeoKey = seoSequenceService.getNextSequenceId();

        SeoUtils.makeSeoFields(offer, longValueOfSeoKey);

        offersService.create(offer);

        return new ResponseEntity<>(new CreatedObjResp(offer.getSeoUrl()), HttpStatus.CREATED);
    }

    //------------------------------------------ Update -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> editOffer(@Valid @RequestBody Offer offer) {

        if (offer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!offersService.offerExists(offer.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        Offer oldOffer = new Offer();
        oldOffer = offersService.findById(offer.getId());

        String userId = SecurityOperations.getLoggedUserId();
        if (!offersService.findById(offer.getId()).getAuthorId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

//        ModerationStatus для админа
//        if !Admin
//        offer.setModerationStatus(null);

        String newTransiltTitle = Translit.makeTransliteration(offer.getTitle());

        String newSeoUrl = newTransiltTitle + "-" + oldOffer.getSeoKey();

        offer.setSeoUrl(newSeoUrl);

        Offer newOffer = offersService.edit(offer);

        return new ResponseEntity<>(new CreatedObjResp(newOffer.getSeoUrl()), HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/setActive/{isActive}", method = RequestMethod.POST)
    public ResponseEntity<Void> setActive(@PathVariable String offerId, @PathVariable boolean isActive) {

        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        offersService.setActive(offerId, isActive);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------ Delete -----------------------------------------------------------------

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/delete", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteOffer(@PathVariable String offerId) {

        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        offersService.delete(offerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
