package ua.com.itproekt.gup.server.api.rest.offers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.model.activityfeed.Event;
import ua.com.itproekt.gup.model.activityfeed.EventType;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.RentedOfferPeriodInfo;
import ua.com.itproekt.gup.service.activityfeed.ActivityFeedService;
import ua.com.itproekt.gup.service.offers.OfferPricesServiceImpl;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.offers.PriceOfRentsRestore;
import ua.com.itproekt.gup.service.offers.RentsRestore;
import ua.com.itproekt.gup.util.CreatedObjResp;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/offersService")
public class OfferRentsRestControllerTest {

    @Autowired
    private OffersService offersService;
    @Autowired
    private OfferPricesServiceImpl monthOfPricesService;

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

}
