package ua.com.gup.rent.api.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.gup.rent.api.search.dto.CategoryOffersStatistic;
import ua.com.gup.rent.api.search.dto.CategoryStatistic;
import ua.com.gup.rent.filter.RentOfferFilter;
import ua.com.gup.rent.service.dto.rent.offer.view.RentOfferViewShortDTO;
import ua.com.gup.rent.service.profile.RentOfferProfilesService;
import ua.com.gup.rent.service.rent.RentOfferService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/search")
public class RentSearchEndpoint {
    private final Logger log = LoggerFactory.getLogger(RentSearchEndpoint.class);

    @Autowired
    private Environment e;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RentOfferProfilesService profilesService;

    @Autowired
    private RentOfferService offerService;

    private UriComponentsBuilder uriComponentsBuilder;
    private final ObjectMapper oMapper = new ObjectMapper();

    @PostConstruct
    public void initialize() {
        restTemplate.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                System.out.println("request: " + request.getURI().toString());
                System.out.println("request body: " + new String(body));
                return execution.execute(request, body);
            }
        }));
        uriComponentsBuilder = UriComponentsBuilder.newInstance().scheme(e.getProperty("search.host.scheme"))
                .host(e.getProperty("search.host.address")).port(e.getProperty("search.host.port")).path("/search/api/rent");

    }

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
    public ResponseEntity<Page> getAllOffersByFilter(RentOfferFilter offerFilter, Pageable pageable) {
        log.debug("REST request to get a page of Offers");

        Map<String, Object> offerFilterMap = oMapper.convertValue(offerFilter, Map.class);
        UriComponentsBuilder builder = this.uriComponentsBuilder.cloneBuilder();
        if (pageable != null) {
            Sort sort = pageable.getSort();
            if (sort != null) {
                sort.forEach(s -> builder.queryParam("sort", (s.getProperty() + "," + s.getDirection().name())));
            }
            builder.queryParam("size", pageable.getPageSize());
            builder.queryParam("page", pageable.getPageNumber());
        }

        UriComponentsBuilder uriComponentsBuilder = builder.path("/offers");

        offerFilterMap.forEach((key, value) -> uriComponentsBuilder.queryParam(key, value));

        UriComponents uriComponents = uriComponentsBuilder.build();
        Map elasticPage = restTemplate.getForObject(uriComponents.toUriString(), Map.class);
        List<String> ids = (List<String>) elasticPage.get("content");
        if (!CollectionUtils.isEmpty(ids)) {
            elasticPage.put("content", offerService.findByIds(ids, pageable.getSort()));
        }
        return new ResponseEntity(elasticPage, HttpStatus.OK);
    }

    @RequestMapping(value = "/offers/categories/count", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity countProfileActiveOffersByCategory(@RequestParam(name = "profilePublicId") String profilePublicId) {

        List<CategoryStatistic> categoriesCount = Collections.EMPTY_LIST;
        if (profilesService.profileExistsByPublicId(profilePublicId)) {
            String profileId = profilesService.findByPublicId(profilePublicId).getId();
            UriComponents uriComponents = uriComponentsBuilder.cloneBuilder().path("/offers/categories/count").queryParam("profileId", profileId).build();
            CategoryStatistic[] categoryStatistics = restTemplate.getForObject(uriComponents.toUri(), CategoryStatistic[].class);
            return new ResponseEntity(categoryStatistics, HttpStatus.OK);
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
        UriComponents uriComponents = uriComponentsBuilder.cloneBuilder().path("/offers/categories/status").queryParam("status", status).build();
        return new ResponseEntity(restTemplate.getForObject(uriComponents.toUri(), CategoryOffersStatistic[].class), HttpStatus.OK);
    }


    @RequestMapping(value = "/offers/suggest", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity suggestWordsByDescription(@RequestParam(name = "q") String query) {

        UriComponents uriComponents = uriComponentsBuilder.cloneBuilder()
                .path("/offers/suggest")
                .queryParam("q", query)
                .build();
        String[] suggests = restTemplate.getForObject(uriComponents.toUri(), String[].class);
        return new ResponseEntity(suggests, HttpStatus.OK);

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
