package ua.com.gup.search.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.gup.search.model.search.ESCategoriesStatistic;
import ua.com.gup.search.service.ESSearchOfferService;
import ua.com.gup.search.util.Locale;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/offers")
public class SearchOffersEndpoint {

    @Autowired
    private ESSearchOfferService esSearchOfferService;

    @RequestMapping(value = "/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ESCategoriesStatistic>> countOffersInCategories(@RequestParam("q") String query,
                                                                               @RequestParam(name = "lang", defaultValue = "ua") Locale locale) throws IOException {
        if (!StringUtils.isEmpty(query) && query.length() >= 3) {
            return new ResponseEntity(esSearchOfferService.countOffersInCategoriesByQuery(query, locale), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/count/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ESCategoriesStatistic>> countActiveOffersInCategoriesByProfileId(
            @RequestParam("profileId") String profileId) throws IOException {

        if (!StringUtils.isEmpty(profileId)) {
            return new ResponseEntity(esSearchOfferService.countOffersInCategoriesByStatusAndProfileId("active", profileId), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/categories/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ESCategoriesStatistic>> countOffersInCategoriesByStatus(@RequestParam(name = "status", defaultValue = "active") String status) throws IOException {
        return new ResponseEntity(esSearchOfferService.countOffersInCategoriesByStatus(status), HttpStatus.OK);
    }


    @RequestMapping(value = "/suggest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<String>> suggest(@RequestParam("q") String query,
                                               @RequestParam(name = "lang", defaultValue = "ua") Locale locale) throws IOException {
        if (!StringUtils.isEmpty(query) && query.length() >= 3) {
            return new ResponseEntity<>(esSearchOfferService.suggestByOffersTitlesAndDescriptions(query), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



}
