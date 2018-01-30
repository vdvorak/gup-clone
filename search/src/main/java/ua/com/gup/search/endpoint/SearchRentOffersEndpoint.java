package ua.com.gup.search.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.search.model.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.filter.rent.RentOfferCalculateRentPriceFilter;
import ua.com.gup.search.model.filter.rent.RentOfferFilter;
import ua.com.gup.search.service.ESSearchRentOfferService;
import ua.com.gup.search.util.Locale;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/rent/offers")
public class SearchRentOffersEndpoint {

    @Autowired
    private ESSearchRentOfferService esSearchRentOfferService;


    @RequestMapping(path = "/index", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity index(@RequestBody Map<String, Object> rentOfferAsMap) throws IOException {
        esSearchRentOfferService.indexRentOffer(rentOfferAsMap);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/index", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete() throws IOException {
        esSearchRentOfferService.clearRentOfferIndex();
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/index/{rentOfferId}/children", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity indexRentOfferChildren(@PathVariable(name = "rentOfferId") String rentOfferId,
                                                 @RequestBody Map<String, Object> rentOfferCalendarMap) throws IOException {
        esSearchRentOfferService.indexRentOfferCalendars(rentOfferId, rentOfferCalendarMap);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getRentOffersIdsByFilter(RentOfferFilter offerFilter, Pageable pageable) throws IOException {
        Page ids = esSearchRentOfferService.findIdsByFilter(offerFilter, pageable);
        return new ResponseEntity(ids, HttpStatus.OK);
    }


    @RequestMapping(value = "/categories/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity countActiveOffersInCategoriesByProfileId(
            @RequestParam("profileId") String profileId) throws IOException {

        if (!StringUtils.isEmpty(profileId)) {
            return new ResponseEntity(esSearchRentOfferService.countOffersInCategoriesByStatusAndProfileId("active", profileId), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/categories/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ESCategoriesOffersStatistic>> countOffersInCategoriesByStatus(@RequestParam(name = "status", defaultValue = "active") String status) throws IOException {
        return new ResponseEntity(esSearchRentOfferService.countOffersInCategoriesByStatus(status), HttpStatus.OK);
    }

    @RequestMapping(value = "/price/calculate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity calculateRentPrice(RentOfferCalculateRentPriceFilter filter) throws IOException {
        Integer rentPrice = esSearchRentOfferService.calculateRentPrice(filter);
        return new ResponseEntity(rentPrice, HttpStatus.OK);
    }


    @RequestMapping(value = "/suggest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<String>> suggest(@RequestParam("q") String query,
                                               @RequestParam(name = "lang", defaultValue = "ua") Locale locale) throws IOException {
        if (!StringUtils.isEmpty(query) && query.length() >= 3) {
            return new ResponseEntity<>(esSearchRentOfferService.suggestByOffersTitlesAndDescriptions(query), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
