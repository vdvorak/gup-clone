package ua.com.gup.search.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.search.model.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.filter.rent.RentOfferFilter;
import ua.com.gup.search.service.ESSearchRentOfferService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/rent/offers")
public class SearchRentOffersEndpoint {

    @Autowired
    private ESSearchRentOfferService esSearchRentOfferService;


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
}
