package ua.com.itproekt.gup.server.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.itproekt.gup.dto.OfferInfo;
import ua.com.itproekt.gup.dto.OfferRegistration;
import ua.com.itproekt.gup.model.offer.ModerationStatus;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.OfferModerationReports;
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.offers.OfferModerationService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.Translit;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rest/offersService")
public class OfferRestController {

    @Autowired
    OffersService offersService;

    @Autowired
    OfferModerationService offerModerationService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    SeoSequenceService seoSequenceService;


    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    private StorageService storageService;

    //------------------------------------------ Read -----------------------------------------------------------------

    /**
     * Return one offer and some relevant offers to this one.
     *
     * @param seoUrl - the seo url of the specific offer.
     * @param relevant - the boolean flag - to show or not to show relevant offers.
     * @return forbidden (403) if offer is not premoderated
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/read/{seoUrl}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OfferInfo> getOfferByIdWithRelevant(@PathVariable String seoUrl,
                                                              @RequestParam(required = false, defaultValue = "false") boolean relevant) {
        Offer offer = offersService.findBySeoUrlAndIncViews(seoUrl);

        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (offer.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        OfferInfo offerInfo;

        //if user is author - he will receive additional fields
        if (offer.getAuthorId().equals(userId)) {
            offerInfo = offersService.getPrivateOfferInfoByOffer(offer);
        } else {

            if (offer.getOfferModerationReports().getModerationStatus() == ModerationStatus.NO || offer.getOfferModerationReports().getModerationStatus() == ModerationStatus.FAIL) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            offerInfo = offersService.getPublicOfferInfoByOffer(offer);
        }


        if (relevant) {

            // FixMe delete this after testing
//            // receive list of relevant offer
//            List<OfferInfo> relevantOffersList = offersService.getListOfMiniPublicOffersWithOptionsAndExclude(OfferRestHelper.offerFilterOptionsPreparatorForRelevantSearchWithCity(offer), offer.getId());
//            if (relevantOffersList.size() < 20) {
//
//                //add extra offers from same area
//                relevantOffersList.addAll(offersService.getListOfMiniPublicOffersWithOptionsAndExclude(OfferRestHelper.offerFilterOptionsPreparatorForRelevantSearchWithCountry(offer), offer.getId()));
//            }
//
//            if (relevantOffersList.size() < 20) {
//                // add extra offers from all categories
//                relevantOffersList.addAll(offersService.getListOfMiniPublicOffersWithOptionsAndExclude(OfferRestHelper.offerFilterOptionsPreparatorOnlyWithSkipAndLimit(), offer.getId()));
//            }

            offerInfo.setRelevantOffersList(offersService.getListOfRelevantPublicOffersForSpecificOffer(offerInfo.getOffer()));
        }

        return new ResponseEntity<>(offerInfo, HttpStatus.OK);
    }

    /**
     * @param offerFO - the offer filter option
     * @param request - the HttpServletRequest object for detecting user role
     * @return - the OK status (200) if all is ok )
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/read/all", method = RequestMethod.POST)
    public ResponseEntity<List<OfferInfo>> listOfAllOffers(@RequestBody OfferFilterOptions offerFO, HttpServletRequest request) {

        // we can show only offers which have Complete status (approve by moderators)
        OfferModerationReports offerModerationReports = new OfferModerationReports();
        offerModerationReports.setModerationStatus(ModerationStatus.COMPLETE);


        if (!request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            offerFO.setActive(true);
            offerFO.setDeleted(false);
            offerFO.setOfferModerationReports(offerModerationReports);
        }

        if (offerFO.isMain()) {
            offerFO.setCreatedDateSortDirection("DESC");
            offerFO.setSkip(0);
            offerFO.setLimit(18);
        }


        List<OfferInfo> offerInfoList = offersService.getListOfMiniPublicOffersWithOptions(offerFO);

        return new ResponseEntity<>(offerInfoList, HttpStatus.OK);
    }


    /**
     * Return user's offers with options
     *
     * @param offerFO filter options.
     * @return status 200 if Ok, 403 - for non authorize
     */
    @PreAuthorize("isAuthenticated()")
    @CrossOrigin
    @RequestMapping(value = "/offer/read/all/my", method = RequestMethod.POST)
    public ResponseEntity<List<OfferInfo>> listOfAllOffersForAuthor(@RequestBody OfferFilterOptions offerFO) {

        String userId = SecurityOperations.getLoggedUserId();
        offerFO.setAuthorId(userId);
        offerFO.setDeleted(true);


        return new ResponseEntity<>(offersService.getListOfPrivateOfferInfoWithOptions(offerFO), HttpStatus.OK);
    }


    //------------------------------------------ Create ----------------------------------------------------------------

    /**
     * This controller allow to create new offer and register new profile at the same time.
     *
     * @param offerRegistration - the OfferRegistration object with informmation about offer
     *                          and with registration information.
     * @param files             - the array of the multipart files.
     * @return 201 (Created) - created offer, 400 (Bad request) - when user is not authorized,
     * 409 (Conflict) - when email already exist.
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/total/create", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<String> createTotalOffer(
            @RequestPart("offerRegistration") OfferRegistration offerRegistration,
            @RequestPart("files") MultipartFile[] files) {
        return offersService.createWithRegistration(offerRegistration, files);
    }


    //------------------------------------------ Update ----------------------------------------------------------------

    /**
     * Update offer
     *
     * @param offerRegistration the OfferRegistration object
     * @param files             the new files uploaded by client
     * @return the status 400 (Bad Request) if offer ID in request object is null; the status 404 (Not Found)
     * if offer with specific ID cannot be found; the status 403 (Forbidden) if user which try to update offer is not
     * it's author; the status 200 (Ok) if offer update was successful
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/edit", method = RequestMethod.POST,
            consumes = {"multipart/form-data"})
    public ResponseEntity<String> editOffer(
            @RequestPart("offerRegistration") OfferRegistration offerRegistration,
            @RequestPart("files") MultipartFile[] files) {

        Offer updatedOffer = offerRegistration.getOffer();

//        System.err.println("Incoming offer Id for update: " + updatedOffer.getId());


        // check is offer not null and exist
        if (updatedOffer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!offersService.offerExists(updatedOffer.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer oldOffer = offersService.findById(updatedOffer.getId());

        String userId = SecurityOperations.getLoggedUserId();


        // Check if current user is not an author
        if (!offersService.findById(updatedOffer.getId()).getAuthorId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // update SEO url title for offer
        String newTranslitTitle = Translit.makeTransliteration(updatedOffer.getTitle());
        String newSeoUrl = newTranslitTitle + "-" + oldOffer.getSeoKey();
        updatedOffer.setSeoUrl(newSeoUrl);


        // If false - means that some pictures were added
        if (oldOffer.getImagesIds() != null) {

            /**
             * If old images list is NOT equal with new - it means that some of the old pictures must be deleted.
             */
            if (!oldOffer.getImagesIds().equals(updatedOffer.getImagesIds())) {
                //Find images in old offer version that were deleted in new
                // and delete them from base in all resized variants.
                storageService.deleteDiffImagesAfterOfferUpdate(oldOffer.getImagesIds(), updatedOffer.getImagesIds());
            }
        }

        if (files.length > 0) {
            updatedOffer.setImagesIds(OfferRestHelper.updaterOfferImages(storageService, updatedOffer.getImagesIds(), files));
        }

        Offer newOffer = offersService.edit(updatedOffer);

//        System.err.println("Updated offer id: " + newOffer.getId());

        return new ResponseEntity<>(newOffer.getSeoUrl(), HttpStatus.OK);
    }


//    /**
//     * Edit offer by moderator
//     *
//     * @param offer the offer
//     * @return 404 Not Found if offer does not exist or was deleted
//     */
//    @CrossOrigin
//    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
//    @RequestMapping(value = "/offer/moderator/edit", method = RequestMethod.POST,
//            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<CreatedObjResp> editOfferByModerator(@Valid @RequestBody Offer offer) {
//
//        if (offer.getId() == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        } else if (!offersService.offerExists(offer.getId())) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Offer oldOffer = offersService.findById(offer.getId());
//
//        if (oldOffer.isDeleted()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        // prepare new offer before update it
//        Offer newOffer = offersService.edit(OfferRestHelper.offerPreparatorForEditOffer(offer, oldOffer));
//
//        return new ResponseEntity<>(new CreatedObjResp(newOffer.getSeoUrl()), HttpStatus.OK);
//    }

    /**
     * Change active status of the offer. Allowed only for offer author.
     *
     * @param offerId offer id.
     * @return status 200 is ok, 403 - user is not an author of the offer, 404 - if offer is not found or was deleted.
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/changeActiveStatus", method = RequestMethod.GET)
    public ResponseEntity<Void> setActive(@PathVariable String offerId) {

        Offer offer = offersService.findById(offerId);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (offer.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        if (!offer.getAuthorId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (offer.getActive()) {
            offer.setActive(false);
        } else {
            offer.setActive(true);
        }

        offersService.edit(offer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //------------------------------------------ Delete ----------------------------------------------------------------

    /**
     * Delete offer by user.
     *
     * @param offerId - offer id.
     * @return
     */
    @CrossOrigin
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/delete/{offerId}", method = RequestMethod.GET)
    public ResponseEntity<Void> deleteOffer(@PathVariable String offerId) {

        Offer offer = offersService.findById(offerId);
        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        if (!offer.getAuthorId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        offer.setDeleted(true);

        offersService.edit(offer);

//        offersService.delete(offerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //------------------------------------------ Rest for admin --------------------------------------------------------

    /**
     * This controller allow administrator or moderator edit offer (change categories), change moderation status
     * and leave comments.
     *
     * @param inputOffer - the offer from moderator with.
     * @return - the status.
     */
    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(value = "/offer/moderateStatus/{offerId}", method = RequestMethod.POST)
    public ResponseEntity<Void> makeOfferComplete(@RequestBody Offer inputOffer) {

        return new ResponseEntity<>(offerModerationService.editOfferByModerator(inputOffer));
    }


}
