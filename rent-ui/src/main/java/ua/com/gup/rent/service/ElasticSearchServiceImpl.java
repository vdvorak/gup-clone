package ua.com.gup.rent.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.gup.common.model.filter.*;
import ua.com.gup.rent.filter.RentOfferFilter;
import ua.com.gup.rent.model.mongo.rent.RentOffer;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendarChild;
import ua.com.gup.rent.repository.calendar.RentOfferCalendarChildRepository;
import ua.com.gup.rent.repository.rent.impl.RentOfferMongoRepository;
import ua.com.gup.rent.service.dto.search.CategoryOffersStatistic;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private final Logger log = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);

    @Autowired
    private Environment e;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestTemplate restAsyncTemplate;
    @Autowired
    private RentOfferMongoRepository rentOfferMongoRepository;
    @Autowired
    private RentOfferCalendarChildRepository rentOfferCalendarChildRepository;


    private UriComponentsBuilder uriComponentsBuilder;
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void initialize() {
        restTemplate.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                log.info("request: " + request.getURI().toString());
                log.info("request body: " + new String(body));
                return execution.execute(request, body);
            }
        }));
        restAsyncTemplate.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                log.info("request: " + request.getURI().toString());
                log.info("request body: " + new String(body));
                return execution.execute(request, body);
            }
        }));
        uriComponentsBuilder = UriComponentsBuilder.newInstance().scheme(e.getProperty("search.host.scheme"))
                .host(e.getProperty("search.host.address")).port(e.getProperty("search.host.port")).path("/search/api/rent");
    }

    @Override
    public void indexRentOffer(RentOffer rentOffer) {
        UriComponentsBuilder builder = this.uriComponentsBuilder.cloneBuilder().path("/offers/index");
        Map<String, Object> rentOfferAsMap = objectMapper.convertValue(rentOffer, Map.class);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        rentOfferAsMap.put("createdDate", rentOffer.getCreatedDate().format(pattern));
        if (rentOffer.getLastModifiedDate() != null) {
            rentOfferAsMap.put("lastModifiedDate", rentOffer.getLastModifiedDate().format(pattern));
        }
        restTemplate.postForEntity(builder.toUriString(), rentOfferAsMap, Object.class);

    }

    @Override
    public void reIndexRentOffers() {
        UriComponents components = this.uriComponentsBuilder.cloneBuilder().path("/offers/index").build();
        ResponseEntity<Object> exchange = restTemplate.exchange(components.toUri(), HttpMethod.DELETE, null, Object.class);//delete();
        if (exchange.getStatusCode().is2xxSuccessful()) {
            //reindex all
            final int pageLimit = 100;
            int pageNumber = 0;
            Page<RentOffer> page = rentOfferMongoRepository.findAll(new PageRequest(pageNumber, pageLimit));
            while (page.hasNext()) {
                processPageContent(page.getContent());
                page = rentOfferMongoRepository.findAll(new PageRequest(++pageNumber, pageLimit));
            }
            processPageContent(page.getContent());
        }
    }

    @Override
    public void indexRentOfferCalendars(RentOffer rentOffer) {
        indexRentOfferCalendars(rentOffer, rentOfferCalendarChildRepository.findAllByOffer(rentOffer).toArray(new RentOfferCalendarChild[]{}));
    }

    private void processPageContent(List<RentOffer> rentOffers) {
        for (RentOffer rentOffer : rentOffers) {
            indexRentOffer(rentOffer);
            indexRentOfferCalendars(rentOffer, rentOfferCalendarChildRepository.findAllByOffer(rentOffer).toArray(new RentOfferCalendarChild[]{}));
        }
    }


    @Override
    public void indexRentOfferCalendars(RentOffer rentOffer, RentOfferCalendarChild... calendars) {
        UriComponentsBuilder builder = this.uriComponentsBuilder.cloneBuilder().path("/offers/index/{rentOfferId}/children");
        UriComponents uriComponents = builder.buildAndExpand(rentOffer.getId());

        for (RentOfferCalendarChild calendar : calendars) {
            Map convertValue = objectMapper.convertValue(calendar, Map.class);
            restAsyncTemplate.postForEntity(uriComponents.toUriString(), convertValue, Object.class);
        }


    }


    @Override
    public Map<String, Object> findIdsPageByFilter(RentOfferFilter offerFilter, Pageable pageable) {
        UriComponentsBuilder builder = this.uriComponentsBuilder.cloneBuilder().path("/offers");

        if (pageable != null) {
            Sort sort = pageable.getSort();
            if (sort != null) {
                sort.forEach(s -> builder.queryParam("sort", (s.getProperty() + "," + s.getDirection().name())));
            }
            builder.queryParam("size", pageable.getPageSize());
            builder.queryParam("page", pageable.getPageNumber());
        }

        if (!StringUtils.isEmpty(offerFilter.getQuery())) {
            builder.queryParam("query", offerFilter.getQuery());
        }
        if (offerFilter.getCategory() != null) {
            builder.queryParam("category", offerFilter.getCategory());
        }
        if (!StringUtils.isEmpty(offerFilter.getCreatedDate())) {
            builder.queryParam("createdDate", offerFilter.getCreatedDate());
        }
        if (!StringUtils.isEmpty(offerFilter.getDtRentStart())) {
            builder.queryParam("dtRentStart", offerFilter.getDtRentStart());
        }
        if (!StringUtils.isEmpty(offerFilter.getDtRentEnd())) {
            builder.queryParam("dtRentEnd", offerFilter.getDtRentEnd());
        }
        if (offerFilter.getSeoUrls() != null) {
            builder.queryParam("seoUrls", String.join(",", offerFilter.getSeoUrls()));
        }
        CommonAddressFilter address = offerFilter.getAddress();
        if (address != null) {
            if (!StringUtils.isEmpty(address.getCountries())) {
                builder.queryParam("address.countries", address.getCountries());
            }
            if (!StringUtils.isEmpty(address.getRegions())) {
                builder.queryParam("address.regions", address.getRegions());
            }
            if (!StringUtils.isEmpty(address.getDistricts())) {
                builder.queryParam("address.districts", address.getDistricts());
            }
            if (!StringUtils.isEmpty(address.getCities())) {
                builder.queryParam("address.cities", address.getCities());
            }

        }
        CommonAuthorFilter authorFilter = offerFilter.getAuthorFilter();
        if (authorFilter != null) {
            if (!StringUtils.isEmpty(authorFilter.getAuthorId() != null)) {
                builder.queryParam("authorFilter.authorId", authorFilter.getAuthorId());
            }
        }

        List<CommonAttributeFilter> attrs = offerFilter.getAttrs();
        for (int i = 0; i < attrs.size(); i++) {
            CommonAttributeFilter caf = attrs.get(i);
            builder.queryParam("attrs[" + i + "].key", caf.getKey());
            builder.queryParam("attrs[" + i + "].vals", caf.getVals());
        }

        List<CommonAttributeFilter> multiAttrs = offerFilter.getMultiAttrs();
        for (int i = 0; i < multiAttrs.size(); i++) {
            CommonAttributeFilter caf = multiAttrs.get(i);
            builder.queryParam("multiAttrs[" + i + "].key", caf.getKey());
            builder.queryParam("multiAttrs[" + i + "].vals", caf.getVals());
        }

        List<BooleanAttributeFilter> boolAttrs = offerFilter.getBoolAttrs();
        for (int i = 0; i < boolAttrs.size(); i++) {
            BooleanAttributeFilter baf = boolAttrs.get(i);
            builder.queryParam("boolAttrs[" + i + "].key", baf.getKey());
            builder.queryParam("boolAttrs[" + i + "].val", baf.getVal());
        }

        List<NumericAttributeFilter> numAttrs = offerFilter.getNumAttrs();
        for (int i = 0; i < numAttrs.size(); i++) {
            NumericAttributeFilter naf = numAttrs.get(i);
            builder.queryParam("numAttrs[" + i + "].key", naf.getKey());
            builder.queryParam("numAttrs[" + i + "].from", naf.getFrom());
            builder.queryParam("numAttrs[" + i + "].to", naf.getTo());
        }


        MoneyFilter price = offerFilter.getPrice();
        if (price != null) {
            builder.queryParam("price.from", price.getFrom());
            builder.queryParam("price.to", price.getTo());
        }

        CommonCoordinatesFilter coordinates = offerFilter.getCoordinates();
        if (coordinates != null) {
            builder.queryParam("coordinates.minYX", convertToString(coordinates.getMinYX()));
            builder.queryParam("coordinates.maxYX", convertToString(coordinates.getMaxYX()));
        }

        if ( offerFilter.getCount() == null || ( offerFilter.getCount() > 0 && offerFilter.getCount() < 100) ) {
            builder.queryParam("count", offerFilter.getCount());
        }

        return restTemplate.getForObject(builder.toUriString(), Map.class);
    }

    @Override
    public List<CategoryOffersStatistic> countOffersInCategoriesByStatus(String status) {
        UriComponents uriComponents = uriComponentsBuilder.cloneBuilder().path("/offers/categories/status").queryParam("status", status).build();
        return restTemplate.getForObject(uriComponents.toUri(), List.class);
    }

    @Override
    public List<CategoryOffersStatistic> countOffersInCategoriesByFilter(String status, String profileId) {
        UriComponents uriComponents = uriComponentsBuilder.cloneBuilder().path("/offers/categories/count").queryParam("profileId", profileId).build();
        return restTemplate.getForObject(uriComponents.toUri(), List.class);
    }

    @Override
    public Map calculatePrice(RentOfferFilter offerFilter) {
        UriComponentsBuilder builder = this.uriComponentsBuilder.cloneBuilder().path("/offers/price/calculate");
        if (!StringUtils.isEmpty(offerFilter.getDtRentStart())) {
            builder.queryParam("dtRentStart", offerFilter.getDtRentStart());
        }
        if (!StringUtils.isEmpty(offerFilter.getDtRentEnd())) {
            builder.queryParam("dtRentEnd", offerFilter.getDtRentEnd());
        }
        if (offerFilter.getSeoUrls() != null) {
            builder.queryParam("seoUrls", String.join(",", offerFilter.getSeoUrls()));
        }
        if ( offerFilter.getCount() == null || ( offerFilter.getCount() > 0 && offerFilter.getCount() < 100) ) {
            builder.queryParam("count", offerFilter.getCount());
        }
        return restTemplate.getForObject(builder.toUriString(), Map.class);
    }


    @Override
    public String[] findSuggests(String query) {
        UriComponents uriComponents = uriComponentsBuilder.cloneBuilder()
                .path("/offers/suggest")
                .queryParam("q", query)
                .build();
        return restTemplate.getForObject(uriComponents.toUri(), String[].class);
    }

    private String convertToString(BigDecimal[] decimals) {
        StringBuilder sb = new StringBuilder();

        Arrays.asList(decimals).stream().forEach(c -> {
                    if (sb.length() != 0) {
                        sb.append(",");
                    }
                    sb.append(String.valueOf(c));
                }
        );
        return sb.toString();
    }
}
