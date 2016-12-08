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
import ua.com.itproekt.gup.service.siteMap.SiteMapGeneratorService;
import ua.com.itproekt.gup.util.SecurityOperations;

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

        if (offer.isDeleted()) {
            return new ResponseEntity<>("Offer with this seoUrl was deleted", HttpStatus.NOT_FOUND);
        }

        String userId = SecurityOperations.getLoggedUserId();

        OfferInfo offerInfo;

        //if user is author - he will receive additional fields
        if (offer.getAuthorId().equals(userId)) {
            offerInfo = offersService.getPrivateOfferInfoByOffer(offer);
        } else {

            if (offer.getOfferModerationReports().getModerationStatus() == ModerationStatus.NO || offer.getOfferModerationReports().getModerationStatus() == ModerationStatus.FAIL) {
                return new ResponseEntity<>("Moderation status is NO or FAIL", HttpStatus.FORBIDDEN);
            }

            offerInfo = offersService.getPublicOfferInfoByOffer(offer);
        }


         //if user is moderator or administrator
        if (request.isUserInRole(UserRole.ROLE_ADMIN.toString()) || request.isUserInRole(UserRole.ROLE_MODERATOR.toString())){
            offerInfo.setIsForAdmin(true);
            return new ResponseEntity<>(offerInfo, HttpStatus.OK);
        }


        if (relevant) {
            offerInfo.setRelevantOffersList(offersService.getListOfRelevantPublicOffersForSpecificOffer(offerInfo.getOffer()));
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

        // we can show only offers which have Complete status (approve by moderators)
        OfferModerationReports offerModerationReports = new OfferModerationReports();
        offerModerationReports.setModerationStatus(ModerationStatus.COMPLETE);

            offerFO.setActive(true);
            offerFO.setDeleted(false);
            offerFO.setOfferModerationReports(offerModerationReports);


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
     * @param offerRegistration - the OfferRegistration object with information about offer
     *                          and with registration information.
     * @param files             - the array of the multipart files.
     * @return 201 (Created) - created offer, 400 (Bad request) - when user is not authorized,
     * 409 (Conflict) - when email already exist.
     */
    @CrossOrigin
    @RequestMapping(value = "/offer/total/OLD", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<String> oldVersion(
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
//        if (!(request.isUserInRole(UserRole.ROLE_ADMIN.toString()) || request.isUserInRole(UserRole.ROLE_MODERATOR.toString()))){
//            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
//        }

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
    @PreAuthorize("hasRole('ROLE_ADMIN','ROLE_MODERATOR')")
    @RequestMapping(value = "/offer/moderateStatus/{offerId}", method = RequestMethod.POST)
    public ResponseEntity<Void> makeOfferComplete(@RequestBody Offer inputOffer) {

        return new ResponseEntity<>(offerModerationService.editOfferByModerator(inputOffer));
    }




    // ---------------------------------------- Test controller for generating siteMap.xml

    @CrossOrigin
    @RequestMapping(value = "/offer/xmltest", method = RequestMethod.GET)
    public ResponseEntity<Void> xmlTest() {

        siteMapGeneratorService.generateSiteMap();

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // ---------------------------------------- Test controller for new Offer createion ------------------------




    // ToDo Добавить Preauthorize
    @CrossOrigin
    @RequestMapping(value = "/offer/total/create", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<String> createTotalOffer(@RequestPart("offerRegistration") OfferRegistration offerRegistration,
                                                   @RequestPart("files") MultipartFile[] files) {


        return offersService.createWithRegistration(offerRegistration, files);
    }

}
