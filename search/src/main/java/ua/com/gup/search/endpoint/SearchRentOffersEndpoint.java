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
import ua.com.gup.search.model.filter.rent.RentOfferFilter;
import ua.com.gup.search.service.ESSearchRentOfferService;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Map;

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
        LocalDateTime time = LocalDateTime.now();
        Page ids = esSearchRentOfferService.findIdsByFilter(offerFilter, pageable);
        System.out.println(Duration.between(time, LocalDateTime.now()).getSeconds());
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
}
