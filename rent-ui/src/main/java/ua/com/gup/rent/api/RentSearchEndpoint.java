package ua.com.gup.rent.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.rent.service.dto.search.CategoryOffersStatistic;
import ua.com.gup.rent.service.dto.search.CategoryStatistic;
import ua.com.gup.rent.filter.RentOfferFilter;
import ua.com.gup.rent.service.ElasticSearchService;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortDTO;
import ua.com.gup.rent.service.profile.RentOfferProfilesService;
import ua.com.gup.rent.service.rent.RentOfferService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/search")
public class RentSearchEndpoint {
    private final Logger log = LoggerFactory.getLogger(RentSearchEndpoint.class);

    @Autowired
    private RentOfferProfilesService profilesService;

    @Autowired
    private RentOfferService offerService;

    @Autowired
    private ElasticSearchService searchService;


    @ApiOperation(value = "Get all the offers by filter",
            notes = "List all offer using paging",
            response = RentOfferViewShortDTO.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieval of user detail", response = RentOfferViewShortDTO.class),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported. " +
                            "Not taken into account if the 'query' is specified. Example = 'lastModifiedBy,desc\nprice.amount,desc'")
    })
    @GetMapping(value = "/offers")
    public ResponseEntity<Page> getAllOffersByFilter(RentOfferFilter offerFilter, Pageable pageable) throws JsonProcessingException {
        log.debug("REST request to get a page of Offers");

        if (offerFilter.getAuthorFilter() != null) {
            if (offerFilter.getAuthorFilter().getPublicId() != null && offerFilter.getAuthorFilter().getAuthorId() == null) {
                offerFilter.getAuthorFilter().setAuthorId(profilesService.findByPublicId(offerFilter.getAuthorFilter().getPublicId().trim()).getId());
            }
        }

        Map<String, Object> pageByFilter = searchService.findIdsPageByFilter(offerFilter, pageable);
        List<String> ids = (List<String>) pageByFilter.get("content");
        if (!CollectionUtils.isEmpty(ids)) {
            pageByFilter.put("content", offerService.findByIds(ids, pageable.getSort()));
        }
        return new ResponseEntity(pageByFilter, HttpStatus.OK);
    }


    @RequestMapping(value = "/offers/price/calculate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity calculateRentPrice(RentOfferFilter offerFilter) {
        Integer price = searchService.calculatePrice(offerFilter);
        return new ResponseEntity<>("{\"price\":+" + price + "}", HttpStatus.OK);
    }


    @RequestMapping(value = "/offers/categories/count", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity countProfileActiveOffersByCategory(@RequestParam(name = "profilePublicId") String profilePublicId) {

        List<CategoryStatistic> categoriesCount = Collections.EMPTY_LIST;
        if (profilesService.profileExistsByPublicId(profilePublicId)) {
            String profileId = profilesService.findByPublicId(profilePublicId).getId();
            List<CategoryOffersStatistic> statistics = searchService.countOffersInCategoriesByFilter("active", profileId);
            return new ResponseEntity(statistics, HttpStatus.OK);
        }

        return new ResponseEntity(categoriesCount, HttpStatus.NOT_FOUND);
    }

    //    @CrossOrigin
//    @RequestMapping(value = "offers/categories/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity countOffersInCategoriesByStatus(@RequestParam(name = "status", defaultValue = "active") String status) {
//        UriComponents uriComponents = uriComponentsBuilder.cloneBuilder().path("/offers/categories/status").queryParam("status", status).build();
//        return new ResponseEntity(restTemplate.getForObject(uriComponents.toUri(), CategoryOffersStatistic[].class), HttpStatus.OK);
//    }
//
//


    @RequestMapping(value = "offers/categories/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity countOffersInCategoriesByStatus(@RequestParam(name = "status", defaultValue = "active") String status) {
        List<CategoryOffersStatistic> categoryOffersStatistics = searchService.countOffersInCategoriesByStatus(status);
        return new ResponseEntity(categoryOffersStatistics, HttpStatus.OK);
    }


    @RequestMapping(value = "/offers/suggests", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity suggestWordsByDescription(@RequestParam(name = "q") String query) {

        String[] suggests = searchService.findSuggests(query);
        return new ResponseEntity(suggests, HttpStatus.OK);

    }

    @RequestMapping(value = "/offers/reindex", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity reindexAll() {
        searchService.reIndexRentOffers();
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/offers/{rentOfferId}/calendars/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateRentOfferCalendars(@PathVariable(value = "rentOfferId") String rentOfferId) {
        offerService.demo(rentOfferId);
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/offers/calendars/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAllRentOffersCalendars() {

        offerService.demoAll();
        return new ResponseEntity(HttpStatus.OK);

    }


//
//    @Deprecated
//    @CrossOrigin
//    @RequestMapping(value = "/offers/category", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity countOffersByCategoryAndQuery(@RequestParam(name = "q") String query) {
//
//        SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
//        UriComponents aggregateUriComponents = uriComponentsBuilder.cloneBuilder().path("/offers/count").queryParam("q", query).build();
//
//        CompletableFuture<ResponseEntity<CategoryStatistic[]>> aggregateResponse = CompletableFutureUtil.toCompletableFuture(asyncRestTemplate.getForEntity(aggregateUriComponents.toUri(), CategoryStatistic[].class));
//        aggregateResponse.whenCompleteAsync(((responseEntity, throwable) -> searchResponseDTO.setAggregations(responseEntity.getBody())));
//
//        CompletableFuture.allOf(aggregateResponse).join();
//        return new ResponseEntity(searchResponseDTO, HttpStatus.OK);
//    }


}
