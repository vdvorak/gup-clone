package ua.com.itproekt.gup.server.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.offers.*;
import ua.com.itproekt.gup.service.offers.price.RentUser;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.ConvertUtil;
import ua.com.itproekt.gup.util.SecurityOperations;

/**
 * GET
 * http://localhost:8184/api/rest/offersService/offer/57f37a5e6032233325b9f8c9/rents
 * {
 *   "days": ["18.11.2016","19.11.2016","20.11.2016"]
 * }
 */
@RestController
@RequestMapping("/api/rest/offersService")
public class OfferRentsRestController {

    @Autowired
    private OffersService offersService;
    @Autowired
    private OfferPricesServiceImpl monthOfPricesService;
    @Autowired
    ProfilesService profilesService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/rents", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> viewOfferRents(@PathVariable String offerId){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer viewOffer = offersService.findById(offerId);
        monthOfPricesService = new OfferPricesServiceImpl(viewOffer.getMonthOfPrices(), viewOffer.getRents());

        return new ResponseEntity<>(monthOfPricesService.jsonRent(), HttpStatus.OK); //return new ResponseEntity<>(monthOfPricesService.toJsonFull(), HttpStatus.OK);
    }

    /**
     * POST
     * http://localhost:8184/api/rest/offersService/offer/57f37a5e6032233325b9f8c9/rents
     * {
     *   "days": ["18.11.2016","19.11.2016","20.11.2016"]
     * }
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/rents", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createOfferrRents(@PathVariable String offerId,
                                                    @RequestBody RentNew rentNew){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer editOffer = offersService.findById(offerId);
        String userId = SecurityOperations.getLoggedUser().getProfileId();
        monthOfPricesService = new OfferPricesServiceImpl(editOffer.getMonthOfPrices(), editOffer.getRents());

//        monthOfPricesService.addRent(ConvertUtil.toDate(rentNew.getDays()), userId);
        Profile profile = profilesService.findWholeProfileById(userId);
        RentUser user = new RentUser();
        user.setId(userId);
        user.setFullName(profile.getUsername());
        user.setImgId(profile.getImgId());
        user.setRating(11); //TODO: ...
        monthOfPricesService.addRent(ConvertUtil.toDate(rentNew.getDays()), user);
        editOffer.setRents(monthOfPricesService.toRestore2());
        offersService.edit(editOffer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * POST
     * http://localhost:8184/api/rest/offersService/offer/57f37a5e6032233325b9f8c9/delRents
     * {
     *   "days": ["18.11.2016","19.11.2016","20.11.2016"]
     * }
     */
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/{offerId}/delRents", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteOfferrRents(@PathVariable String offerId,
                                                    @RequestBody RentNew rentDel){
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer editOffer = offersService.findById(offerId);
        monthOfPricesService = new OfferPricesServiceImpl(editOffer.getMonthOfPrices(), editOffer.getRents());

        monthOfPricesService.delRent(ConvertUtil.toDate(rentDel.getDays()));
        editOffer.setRents(monthOfPricesService.toRestore2());
        offersService.edit(editOffer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
