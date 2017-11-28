package ua.com.gup.server.api.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.gup.server.api.search.dto.CategoryOffersStatistic;
import ua.com.gup.server.api.search.dto.CategoryStatistic;
import ua.com.gup.server.api.search.dto.SearchResponseDTO;
import ua.com.gup.server.util.CompletableFutureUtil;
import ua.com.gup.service.profile.ProfilesService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path = "/api/search")
public class SearchEndpoint {
    private final Logger log = LoggerFactory.getLogger(SearchEndpoint.class);

    @Autowired
    private Environment e;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProfilesService profilesService;

    private UriComponentsBuilder uriComponentsBuilder;

    @PostConstruct
    public void initialize() {
        uriComponentsBuilder = UriComponentsBuilder.newInstance().scheme(e.getProperty("search.host.scheme"))
                .host(e.getProperty("search.host.address")).port(e.getProperty("search.host.port")).path("/search/api");

    }

    @CrossOrigin
    @RequestMapping(value = "/offers/count/categories", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity countProfileActiveOffersByCategory(@RequestParam(name = "profilePublicId") String profilePublicId) {

        List<CategoryStatistic> categoriesCount = Collections.EMPTY_LIST;
        if (profilesService.profileExistsByPublicId(profilePublicId)) {
            String profileId = profilesService.findByPublicId(profilePublicId).getId();
            UriComponents uriComponents = uriComponentsBuilder.cloneBuilder().path("/offers/count/categories").queryParam("profileId", profileId).build();
            CategoryStatistic[] categoryStatistics = restTemplate.getForObject(uriComponents.toUri(), CategoryStatistic[].class);
            return new ResponseEntity(categoryStatistics, HttpStatus.OK);
        }

        return new ResponseEntity(categoriesCount, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "offers/categories/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity countOffersInCategoriesByStatus(@RequestParam(name = "status", defaultValue = "active") String status) {
        UriComponents uriComponents = uriComponentsBuilder.cloneBuilder().path("/offers/categories/status").queryParam("status", status).build();
        return new ResponseEntity(restTemplate.getForObject(uriComponents.toUri(), CategoryOffersStatistic[].class), HttpStatus.OK);
    }


    @CrossOrigin
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

    @Deprecated
    @CrossOrigin
    @RequestMapping(value = "/offers/category", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity countOffersByCategoryAndQuery(@RequestParam(name = "q") String query) {

        SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
        UriComponents aggregateUriComponents = uriComponentsBuilder.cloneBuilder().path("/offers/count").queryParam("q", query).build();

        CompletableFuture<ResponseEntity<CategoryStatistic[]>> aggregateResponse = CompletableFutureUtil.toCompletableFuture(asyncRestTemplate.getForEntity(aggregateUriComponents.toUri(), CategoryStatistic[].class));
        aggregateResponse.whenCompleteAsync(((responseEntity, throwable) -> searchResponseDTO.setAggregations(responseEntity.getBody())));

        CompletableFuture.allOf(aggregateResponse).join();
        return new ResponseEntity(searchResponseDTO, HttpStatus.OK);
    }


}
