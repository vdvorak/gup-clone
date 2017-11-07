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
import ua.com.gup.search.model.ESCategoriesStatistic;
import ua.com.gup.search.service.ESSearchService;
import ua.com.gup.search.util.Locale;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class SearchEndpoint {

    @Autowired
    private ESSearchService esSearchService;

    @RequestMapping(value = "offers/count/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ESCategoriesStatistic>> countActiveOffersInCategoriesByProfileId(
            @RequestParam("profileId") String profileId) throws IOException {

        if (!StringUtils.isEmpty(profileId)) {
            return new ResponseEntity(esSearchService.countOffersInCategoriesByStatusAndProfileId("active", profileId), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);


    }


    @RequestMapping(value = "offers/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ESCategoriesStatistic>> countOffersInCategories(@RequestParam("q") String query,
                                                                               @RequestParam(name = "lang", defaultValue = "ua") Locale locale) throws IOException {
        if (!StringUtils.isEmpty(query) && query.length() >= 3) {
            return new ResponseEntity(esSearchService.countOffersInCategoriesByQuery(query, locale), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "offers/suggest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> suggest(@RequestParam("q") String query,
                                                @RequestParam(name = "lang", defaultValue = "ua") Locale locale) throws IOException {
        if (!StringUtils.isEmpty(query) && query.length() >= 3) {
            return new ResponseEntity<>(esSearchService.suggestByOffersTitlesAndDescriptions(query), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
