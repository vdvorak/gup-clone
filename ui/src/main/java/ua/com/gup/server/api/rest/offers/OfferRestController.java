package ua.com.gup.server.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.gup.domain.Offer;
import ua.com.gup.domain.offer.OfferModerationReport;
import ua.com.gup.dto.OfferInfo;
import ua.com.gup.dto.OfferRegistration;
import ua.com.gup.model.offer.filter.OfferFilterOptions;
import ua.com.gup.model.profiles.UserRole;
import ua.com.gup.service.offers.OfferModerationService;
import ua.com.gup.service.offers.OffersService;
import ua.com.gup.service.profile.ProfilesService;
import ua.com.gup.service.profile.VerificationTokenService;
import ua.com.gup.service.sequence.SeoSequenceService;
import ua.com.gup.service.siteMap.SiteMapGeneratorService;
import ua.com.gup.util.SecurityOperations;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    SiteMapGeneratorService siteMapGeneratorService;


    @Autowired
    VerificationTokenService verificationTokenService;

    //------------------------------------------ Read -----------------------------------------------------------------

    /**
     * Return one offer and some relevant offers to this one.
     *
     * @param seoUrl   - the seo url of the specific offer.
     * @param relevant - the boolean flag - to show or not to show relevant offers.
     * @return forbidden (403) if offer is not premoderated
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/read/{seoUrl}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOfferByIdWithRelevant(@PathVariable String seoUrl,
                                                           @RequestParam(required = false, defaultValue = "false") boolean relevant, HttpServletRequest request) {
        Offer offer = offersService.findBySeoUrlAndIncViews(seoUrl);

        if (offer == null) {
            return new ResponseEntity<>("Offer with this seoUrl is not exist", HttpStatus.NOT_FOUND);
        }

        //todo vdvorak
        /*if (offer.isDeleted()) {
            return new ResponseEntity<>("Offer with this seoUrl was deleted", HttpStatus.NOT_FOUND);
        }*/

        String userId = SecurityOperations.getLoggedUserId();

        OfferInfo offerInfo;

        // if user not admin nor the moderator
        if (!(request.isUserInRole(UserRole.ROLE_ADMIN.toString()) || request.isUserInRole(UserRole.ROLE_MODERATOR.toString()))){
            //if user is author - he will receive additional fields
            if (offer.getAuthorId().equals(userId)) {
                offerInfo = offersService.getPrivateOfferInfoByOffer(offer);
            } else {
                //todo vdvorak
               /* if (offer.getOfferModerationReports().getModerationStatus() == ModerationStatus.NO || offer.getOfferModerationReports().getModerationStatus() == ModerationStatus.FAIL) {
                    return new ResponseEntity<>("{'success':false,'error':{'code':407,'info':'Moderation status is NO or FAIL'}}", HttpStatus.BAD_REQUEST);
                }*/
                offerInfo = offersService.getPublicOfferInfoByOffer(offer);
            }
        } else {
            offerInfo = offersService.getPrivateOfferInfoByOffer(offer);
        }

        //if user is moderator or administrator
        if (request.isUserInRole(UserRole.ROLE_ADMIN.toString()) || request.isUserInRole(UserRole.ROLE_MODERATOR.toString())) {
            offerInfo.setForAdmin(true);
            return new ResponseEntity<>(offerInfo, HttpStatus.OK);
        }


        if (relevant) {
            offerInfo.setRelevantOffers(offersService.getListOfRelevantPublicOffersForSpecificOffer(offerInfo.getOffer()));
        }

        return new ResponseEntity<>(offerInfo, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/offer/phoneViews/{seoUrl}", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOfferByIdWithRelevant(@PathVariable String seoUrl, HttpServletRequest request) {
        Offer offer = offersService.findBySeoUrlAndIncPhoneViews(seoUrl);

        if (offer == null) {
            return new ResponseEntity<>("Offer with this seoUrl is not exist", HttpStatus.NOT_FOUND);
        }

        //todo vdvorak
      /*  if (offer.isDeleted()) {
            return new ResponseEntity<>("Offer with this seoUrl was deleted", HttpStatus.NOT_FOUND);
        }*/

        String userId = SecurityOperations.getLoggedUserId();

        OfferInfo offerInfo;

        // if user not admin nor the moderator
        if (!(request.isUserInRole(UserRole.ROLE_ADMIN.toString()) || request.isUserInRole(UserRole.ROLE_MODERATOR.toString()))){
            //if user is author - he will receive additional fields
            if (offer.getAuthorId().equals(userId)) {
                offerInfo = offersService.getPrivateOfferInfoByOffer(offer);
            } else {
                //todo vdvorak future
               /* if (offer.getOfferModerationReports().getModerationStatus() == ModerationStatus.NO || offer.getOfferModerationReports().getModerationStatus() == ModerationStatus.FAIL) {
                    return new ResponseEntity<>("{'success':false,'error':{'code':407,'info':'Moderation status is NO or FAIL'}}", HttpStatus.BAD_REQUEST);
                }*/
                offerInfo = offersService.getPublicOfferInfoByOffer(offer);
            }
        } else {
            return new ResponseEntity<>("{'success':false,'error':{'code':405,'info':'Method Not Allowed to Moderation'}}", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(offerInfo, HttpStatus.OK);
    }

    /**
     * Return list of the offers which relevant to offer filter option.
     *
     * @param offerFO - the offer filter option
     * @return - the OK status (200) if all is ok )
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/read/all", method = RequestMethod.POST)
    public ResponseEntity<List<OfferInfo>> listOfAllOffers(@RequestBody OfferFilterOptions offerFO) {
        /* we can show only offers which have Complete status (approve by moderators) */
        OfferModerationReport offerModerationReport = new OfferModerationReport();

        //todo vdvorak future
        //offerModerationReport.setModerationStatus(ModerationStatus.COMPLETE);
        /*offerFO.setActive(true);
        offerFO.setDeleted(false);*/
        offerFO.setLastOfferModerationReport(offerModerationReport);

        if (offerFO.isMain()) {
            offerFO.setCreatedDateSortDirection("DESC");
            offerFO.setSkip(0);
            offerFO.setLimit(18);
        }

        List<OfferInfo> infoOffers = offersService.getListOfMiniPublicOffersWithOptions(offerFO);

        return new ResponseEntity<>(infoOffers, HttpStatus.OK);
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
        //todo vdvorak
        //offerFO.setDeleted(true);


        return new ResponseEntity<>(offersService.getListOfPrivateOfferInfoWithOptions(offerFO), HttpStatus.OK);
    }


    //------------------------------------------ Create ----------------------------------------------------------------

    /**
     * This controller allow to create new offer.
     *
     * @param offerRegistration - the OfferRegistration object with information about offer.
     * @param files             - the array of the multipart files.
     * @return - the status 200 (OK), the status 401 if user is unauthorized.
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/total/create", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<String> createTotalOffer(@RequestPart("offerRegistration") OfferRegistration offerRegistration,
                                                   @RequestPart("files") MultipartFile[] files)
    {
        String userId = SecurityOperations.getLoggedUserId();

        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // set userId to the offer
        offerRegistration.getOffer().setAuthorId(userId);

        return offersService.createFullOffer(offerRegistration, files);
    }

//    /**
//     * TODO: This controller allow vs confirm to create new offer.
//     *
//     * @param offerRegistration
//     * @param files
//     * @return
//     */
//    @CrossOrigin
//    @PreAuthorize("isAuthenticated()")
//    @RequestMapping(value = "/offer/total/create-accept", method = RequestMethod.POST, consumes = {"multipart/form-data"})
//    public ResponseEntity<String> createTotalOfferConfirm(@RequestPart("offerRegistration") OfferRegistration offerRegistration,
//                                                          @RequestPart("files") MultipartFile[] files)
//    {
//        String userId = SecurityOperations.getLoggedUserId();
//
//        Offer offer = offerRegistration.getOffer();
//
//        if(Validator2Util.UPD_URL.check(offer.getSeoUrl())
//                && Validator2Util.UPD_URL.check(offer.getSeoUrl())
//                && Validator2Util.NUMBER.check(String.valueOf(offer.getPrice()))
//                && Validator2Util.LATIN_CYRILLIC.check(offer.getDescription())
//                && Validator2Util.LATIN.check(offer.getSeoCategory())
//                && Validator2Util.LATIN.check(offer.getTitle())
//                && Validator2Util.YYYY_MM_DD.check(String.valueOf(offer.getCreatedDate())))
//        {
//            // set userId to the offer
//            offerRegistration.getOffer().setAuthorId(userId);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//        }
//
//        return offersService.createFullOffer(offerRegistration, files);
//    }

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

        String userId = SecurityOperations.getLoggedUserId();

        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return offersService.editByUser(offerRegistration, files);
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
        //todo vdvorak
        /*if (offer.isDeleted()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/

        String userId = SecurityOperations.getLoggedUserId();

        if (!offer.getAuthorId().equals(userId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        //todo vdvorak
        /*if (offer.getActive()) {
            offer.setActive(false);
        } else {
            offer.setActive(true);
        }*/

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

        //todo vdvorak
        //offer.setDeleted(true);

        offersService.edit(offer);

//        offersService.delete(offerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    //------------------------------------------ Rest for admin --------------------------------------------------------


    /**
     * Return the list of the offers which are relevant to offer filter options. Only for admin.
     *
     * @param offerFO - the offer filter option
     * @param request - the HttpServletRequest object for detecting user role
     * @return - the OK status (200) if all is ok )
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/read/admin/all", method = RequestMethod.POST)
    public ResponseEntity<List<Offer>> listOfAllOffersForAdmin(@RequestBody OfferFilterOptions offerFO, HttpServletRequest request) {

        // ToDo Turn this ON in release!!!!!!!
        // if user not admin nor the moderator
        if (!(request.isUserInRole(UserRole.ROLE_ADMIN.toString()) || request.isUserInRole(UserRole.ROLE_MODERATOR.toString()))){
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }

        List<Offer> offerList = offersService.findOffersWihOptions(offerFO).getEntities();

        return new ResponseEntity<>(offerList, HttpStatus.OK);
    }


    /**
     * This controller allow administrator or moderator edit offer (change categories), change moderation status
     * and leave comments.
     *
     * @param inputOffer - the offer from moderator with.
     * @return - the status.
     */
    @CrossOrigin
    @PreAuthorize("hasRole({'ROLE_ADMIN','ROLE_MODERATOR'})")
    @RequestMapping(value = "/offer/moderateStatus/{offerId}", method = RequestMethod.POST)
    public ResponseEntity<Void> makeOfferComplete(@RequestBody Offer inputOffer) {
        System.err.println("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
        return new ResponseEntity<>(offerModerationService.editOfferByModerator(inputOffer));
    }

//    /**
//     * TODO: This controller allow administrator or moderator edit offer (change categories), change moderation status and leave comments.
//     *
//     * @param offer
//     * @param offerId
//     * @return offerId
//     */
//    @CrossOrigin
//    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MODERATOR')")
//    @RequestMapping(value = "/offer/moderate/{offerId}", method = RequestMethod.POST)
//    public ResponseEntity<String> makeOfferComplete(@RequestBody Offer offer, @PathVariable String offerId) {
//        return new ResponseEntity<>(offerId, offerModerationService.editOfferByModerator(offer));
//    }


    // ---------------------------------------- Test controller for generating siteMap.xml

    @CrossOrigin
    @RequestMapping(value = "/offer/xmltest", method = RequestMethod.GET)
    public ResponseEntity<Void> xmlTest() {
        siteMapGeneratorService.generateSiteMap();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
