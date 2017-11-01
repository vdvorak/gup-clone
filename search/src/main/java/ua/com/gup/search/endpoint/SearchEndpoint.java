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
import ua.com.gup.search.model.ESCategoriesCount;
import ua.com.gup.search.model.ESOffer;
import ua.com.gup.search.service.ESSearchService;
import ua.com.gup.search.util.Locale;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/search/")
public class SearchEndpoint {

    @Autowired
    private ESSearchService esSearchService;

    @RequestMapping(value = "offers/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ESCategoriesCount>> countOffersInCategories(@RequestParam("q") String query,
                                                                           @RequestParam(name = "lang", defaultValue = "ua") Locale locale) throws IOException {
        if (!StringUtils.isEmpty(query) && query.length() >= 2) {
            return new ResponseEntity(esSearchService.countMatchesInCategories(query, locale), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "offers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ESOffer> searchOffers(@RequestParam("q") String query,
                                                @RequestParam(name = "categories", required = false) Integer[] categoriesIds) throws IOException {
        if (!StringUtils.isEmpty(query) && query.length() >= 2) {
            Iterable<ESOffer> esOfferIterable = esSearchService.findByQueryAndCategoriesIds(query, categoriesIds);


        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
