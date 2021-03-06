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
import ua.com.itproekt.gup.model.offer.filter.OfferFilterOptions;
import ua.com.itproekt.gup.model.profiles.UserRole;
import ua.com.itproekt.gup.service.filestorage.StorageService;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.service.profile.VerificationTokenService;
import ua.com.itproekt.gup.service.seosequence.SeoSequenceService;
import ua.com.itproekt.gup.service.subscription.SubscriptionService;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.SecurityOperations;
import ua.com.itproekt.gup.util.Translit;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private StorageService storageService;

    //------------------------------------------ Read -----------------------------------------------------------------

    /**
     * @param seoUrl
     * @param relevant
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


            if (offer.getModerationStatus() == ModerationStatus.NO || offer.getModerationStatus() == ModerationStatus.FAIL) {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            offerInfo = offersService.getPublicOfferInfoByOffer(offer);
        }


        if (relevant) {
            // receive list of relevant offer
            List<OfferInfo> relevantOffersList = offersService.getListOfMiniPublicOffersWithOptionsAndExclude(OfferRestHelper.offerFilterOptionsPreparatorForRelevantSearchWithCity(offer), offer.getId());
            if (relevantOffersList.size() < 20) {

                //add extra offers from same area
                relevantOffersList.addAll(offersService.getListOfMiniPublicOffersWithOptionsAndExclude(OfferRestHelper.offerFilterOptionsPreparatorForRelevantSearchWithCountry(offer), offer.getId()));
            }

            if (relevantOffersList.size() < 20) {
                // add extra offers from all categories
                relevantOffersList.addAll(offersService.getListOfMiniPublicOffersWithOptionsAndExclude(OfferRestHelper.offerFilterOptionsPreparatorOnlyWithSkipAndLimit(), offer.getId()));
            }

            offerInfo.setRelevantOffersList(relevantOffersList);
        }

        return new ResponseEntity<>(offerInfo, HttpStatus.OK);
    }

    /**
     * @param offerFO
     * @param request
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/read/all", method = RequestMethod.POST)
    public ResponseEntity<List<OfferInfo>> listOfAllOffers(@RequestBody OfferFilterOptions offerFO, HttpServletRequest request) {

        if (!request.isUserInRole(UserRole.ROLE_ADMIN.toString())) {
            offerFO.setActive(true);
            offerFO.setIsDeleted(false);
            offerFO.setModerationStatus(ModerationStatus.COMPLETE);
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
        offerFO.setIsDeleted(true);


        return new ResponseEntity<>(offersService.getListOfPrivateOfferInfoWithOptions(offerFO), HttpStatus.OK);
    }


    //------------------------------------------ Create ----------------------------------------------------------------

    /**
     * @param offerRegistration
     * @param files
     * @return 201 (Created) - created offer, 400 (Bad request) - when user is not authorized,
     * 409 (Conflict) - when email already exist,
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/total/create", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<String> createTotalOffer(
            @RequestPart("offerRegistration") OfferRegistration offerRegistration,
            @RequestPart("files") MultipartFile[] files) {

        System.err.println("This is object: " + offerRegistration.toString());


        //ToDo delete this shit
//        Cookie[] cookies = request.getCookies();
//
//        System.err.println("Before Cookie check");
//        for (Cookie cookie : cookies) {
//            System.err.println("Cookie name: " + cookie.getName() + " || Cookie value: " + cookie.getValue());
//        }


        String userId = SecurityOperations.getLoggedUserId();

        Map<String, String> importImagesMap = new HashMap<>();
        Map<String, String> ownAddedImagesMap = new HashMap<>();
        int firstPositionForImages = 0;


        if (userId == null && (offerRegistration.getEmail() == null || offerRegistration.getPassword() == null)) {
            System.err.println("Not authorize and without date for it");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // if user is not logged in
        if (userId == null && offerRegistration.getEmail() != null && offerRegistration.getPassword() != null) {

            if (profilesService.profileExistsWithEmail(offerRegistration.getEmail())) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            OfferRestHelper.offerSeoUrlAndPaidServicePreparator(seoSequenceService, offerRegistration);

            if (files.length > 0) {
                // Set images id's and their order into offer. When offer is create - images order start with "1"
                offerRegistration.getOffer().setImagesIds(storageService.saveCachedMultiplyImageOffer(files, 1));
            }

            offersService.createWithRegistration(offerRegistration);

            return new ResponseEntity<>(offerRegistration.getOffer().getSeoUrl(), HttpStatus.CREATED);


        } else {
            // if user is logged in

            offerRegistration.getOffer().setAuthorId(userId);

            OfferRestHelper.offerSeoUrlAndPaidServicePreparator(seoSequenceService, offerRegistration);


            if (offerRegistration.getImportImagesUrlList() != null) {
                if (offerRegistration.getImportImagesUrlList().size() > 0) {
                    MultipartFile[] multipartFiles = storageService.imageDownloader(offerRegistration.getImportImagesUrlList());
                    importImagesMap = storageService.saveCachedMultiplyImageOffer(multipartFiles, 1);
                    firstPositionForImages = importImagesMap.size();
                }
            }

            if (files.length > 0) {
                for (MultipartFile file : files) {
                    System.err.println("Filename: " + file.getOriginalFilename() + " ||| " + file.getName());
                }
                // Set images id's and their order into offer. When offer is create - images order start with "1"
                ownAddedImagesMap = storageService.saveCachedMultiplyImageOffer(files, firstPositionForImages + 1);
            }


            Map<String, String> resultImageMap = new HashMap<>();

            resultImageMap.putAll(importImagesMap);
            resultImageMap.putAll(ownAddedImagesMap);

            offerRegistration.getOffer().setImagesIds(resultImageMap);


            offersService.create(offerRegistration.getOffer());

            return new ResponseEntity<>(offerRegistration.getOffer().getSeoUrl(), HttpStatus.CREATED);
        }


    }


    //------------------------------------------ Update ----------------------------------------------------------------

    /**
     * Update offer
     *
     * @param offerRegistration the OfferRegistration object
     * @param files the new files uploaded by client
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

        // Mark message from moderator as read
        if (updatedOffer.getModerationMessage() != null) {
            updatedOffer.getModerationMessage().setMessage(oldOffer.getModerationMessage().getMessage());
            updatedOffer.getModerationMessage().setIsRead(true);
        }


        // update SEO url title for offer
        String newTransiltTitle = Translit.makeTransliteration(updatedOffer.getTitle());
        String newSeoUrl = newTransiltTitle + "-" + oldOffer.getSeoKey();
        updatedOffer.setSeoUrl(newSeoUrl);


        // If false - means that some pictures were
        if (oldOffer.getImagesIds() != null) {
            if (!oldOffer.getImagesIds().equals(updatedOffer.getImagesIds())) {
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


    /**
     * Edit offer by moderator
     *
     * @param offer the offer
     * @return      404 Not Found if offer does not exist or was deleted
     */
    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
    @RequestMapping(value = "/offer/moderator/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreatedObjResp> editOfferByModerator(@Valid @RequestBody Offer offer) {

        if (offer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else if (!offersService.offerExists(offer.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer oldOffer = offersService.findById(offer.getId());

        if (oldOffer.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // prepare new offer before update it
        Offer newOffer = offersService.edit(OfferRestHelper.offerPreparatorForEditOffer(offer, oldOffer));

        return new ResponseEntity<>(new CreatedObjResp(newOffer.getSeoUrl()), HttpStatus.OK);
    }

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

        offer.setIsDeleted(true);

        offersService.edit(offer);

//        offersService.delete(offerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //------------------------------------------ Rest for admin --------------------------------------------------------

    /**
     * @param offerId          - offer id
     * @param moderationStatus - moderation status
     * @return
     */
    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(value = "/offer/moderateStatus/{offerId}", method = RequestMethod.POST)
    public ResponseEntity<Void> makeOfferComplete(@PathVariable String offerId, @RequestBody ModerationStatus moderationStatus) {

        if (moderationStatus != ModerationStatus.FAIL && moderationStatus != ModerationStatus.COMPLETE) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Offer offer = offersService.findById(offerId);

        if (offer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (offer.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

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
}
