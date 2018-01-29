package ua.com.gup.search.repository.impl;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.join.query.HasChildQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import ua.com.gup.search.model.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.ESCategoriesStatistic;
import ua.com.gup.search.model.filter.rent.*;
import ua.com.gup.search.repository.ESRentOfferRepository;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Repository
public class ESRentOfferRepositoryImpl implements ESRentOfferRepository {

    @Autowired
    private Environment e;
    private static final String RENT_INDEX = "heroku_lktlmxlq_rent";
    private static final String OFFER_TYPE = "offer";

    private static final List<String> rentOfferIndexFields = Arrays.asList("seoUrl", "title", "description",
            "createdDate",
            "lastModifiedDate",
            "address",
            "status", "categories", "authorId",
            "attrs", "multiAttrs", "numAttrs", "boolAttrs",
            "user",
            "settings",
            "lastModifiedUser", "price");

    private final TermSuggestionBuilder descriptionUaSuggestion
            = new TermSuggestionBuilder("description_ua")
            .stringDistance(TermSuggestionBuilder.StringDistanceImpl.NGRAM)
            .minWordLength(3)
            .size(10);

    private final TermSuggestionBuilder descriptionRuSuggestion
            = new TermSuggestionBuilder("description_ru")
            .stringDistance(TermSuggestionBuilder.StringDistanceImpl.NGRAM)
            .minWordLength(3)
            .size(10);

    @Autowired
    private RestHighLevelClient esClient;

    @Override
    public List<ESCategoriesStatistic> countOffersInCategoriesByStatusAndProfileId(String offerStatus, String profileId) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(RENT_INDEX);
        searchRequest.types(OFFER_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(false);
        searchSourceBuilder.size(0);

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();

        boolQueryBuilder.must(new TermQueryBuilder("status", offerStatus));
        boolQueryBuilder.must(new TermQueryBuilder("authorId", profileId));

        searchSourceBuilder.query(boolQueryBuilder);

        TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_category")
                .field("categories").size(Integer.MAX_VALUE);
        searchSourceBuilder.aggregation(aggregation);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = esClient.search(searchRequest);
        Aggregations aggregations = searchResponse.getAggregations();

        if (aggregations != null) {
            Terms byCategoryAgg = aggregations.get("by_category");
            List<ESCategoriesStatistic> categoriesCounts = new ArrayList<>(byCategoryAgg.getBuckets().size());
            byCategoryAgg.getBuckets().forEach(b -> categoriesCounts.add(new ESCategoriesStatistic(b.getKeyAsNumber().longValue(), b.getDocCount())));
            return categoriesCounts;
        }
        return Collections.EMPTY_LIST;
    }


    private QueryBuilder boolQueryBuilder(RentOfferFilter offerFilter) {
        return boolQueryBuilder(offerFilter, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
    }

    private QueryBuilder boolQueryBuilder(RentOfferFilter offerFilter, Collection<String> excludedIds, Collection<String> statusList) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (offerFilter.getCategory() != null) {
            boolQueryBuilder.must(new TermsQueryBuilder("categories", new int[]{offerFilter.getCategory()}));
        }

        if (!StringUtils.isEmpty(offerFilter.getSeoUrls())) {
            boolQueryBuilder.must(new TermQueryBuilder("seoUrl", offerFilter.getSeoUrls()));
        }

        if (offerFilter.getAuthorFilter() != null && !StringUtils.isEmpty(offerFilter.getAuthorFilter().getAuthorId())) {
            boolQueryBuilder.must(new TermQueryBuilder("authorId", offerFilter.getAuthorFilter().getAuthorId()));
        }

        if (!CollectionUtils.isEmpty(excludedIds)) {
            boolQueryBuilder.mustNot(new TermQueryBuilder("_id", excludedIds));
        }

        if (!CollectionUtils.isEmpty(statusList)) {
            boolQueryBuilder.must(new TermsQueryBuilder("status", statusList));
        } else {
            boolQueryBuilder.must(new TermQueryBuilder("status", "active"));
        }

        CoordinatesFilter coordinatesFilter = offerFilter.getCoordinates();
        if (coordinatesFilter != null) {
            RangeQueryBuilder latitudeQuery = new RangeQueryBuilder("address.lat");
            latitudeQuery.from(coordinatesFilter.getMinYX()[0], true);
            latitudeQuery.to(coordinatesFilter.getMaxYX()[0], true);
            boolQueryBuilder.must(latitudeQuery);

            RangeQueryBuilder longitudeQuery = new RangeQueryBuilder("address.lng");
            longitudeQuery.from(coordinatesFilter.getMinYX()[1], true);
            longitudeQuery.to(coordinatesFilter.getMaxYX()[1], true);
            boolQueryBuilder.must(longitudeQuery);

        }
        AddressFilter addressFilter = offerFilter.getAddress();
        if (addressFilter != null) {
            if (addressFilter.getCountries() != null) {
                boolQueryBuilder.must(new TermsQueryBuilder("address.country.code", addressFilter.getCountries().split(",")));
            }
            if (addressFilter.getRegions() != null) {
                boolQueryBuilder.must(new TermsQueryBuilder("address.region.code", addressFilter.getRegions().split(",")));
            }
            if (addressFilter.getDistricts() != null) {
                boolQueryBuilder.must(new TermsQueryBuilder("address.district.code", addressFilter.getDistricts().split(",")));
            }
            if (addressFilter.getCities() != null) {
                boolQueryBuilder.must(new TermsQueryBuilder("address.city.code", addressFilter.getCities().split(",")));
            }

        }

        MoneyFilter price = offerFilter.getPrice();
        if (offerFilter.getPrice() != null) {
            RangeQueryBuilder moneyRangeQuery = new RangeQueryBuilder("price.baseAmount");
            if (price.getFrom() != null) {
                moneyRangeQuery.from(price.getFrom(), true);
            }
            if (price.getTo() != null) {
                moneyRangeQuery.to(price.getTo(), true);
            }
            boolQueryBuilder.must(moneyRangeQuery);

        }
        if (offerFilter.getAttrs() != null) {
            for (AttributeFilter attrFilter : offerFilter.getAttrs()) {
                boolQueryBuilder.must(new TermsQueryBuilder("attrs." + attrFilter.getKey() + ".selected.key", attrFilter.getVals().split(",")));
            }
        }
        if (offerFilter.getMultiAttrs() != null) {
            for (AttributeFilter attrFilter : offerFilter.getMultiAttrs()) {
//                query.addCriteria(Criteria.where("multiAttrs." + attrFilter.getKey() + ".selected").elemMatch(Criteria.where("key").in(attrFilter.getVals().split(","))));
            }
        }
        if (offerFilter.getNumAttrs() != null) {
            for (NumericAttributeFilter filter : offerFilter.getNumAttrs()) {
                RangeQueryBuilder numAttrRangeQuery = new RangeQueryBuilder("numAttrs." + filter.getKey() + ".selectedDouble");
                if (filter.getFrom() != null) {
                    numAttrRangeQuery.from(filter.getFrom());
                }
                if (filter.getTo() != null) {
                    numAttrRangeQuery.to(filter.getTo());
                }
                boolQueryBuilder.must(numAttrRangeQuery);
            }
        }
        if (offerFilter.getBoolAttrs() != null) {
            for (BooleanAttributeFilter filter : offerFilter.getBoolAttrs()) {
                boolQueryBuilder.must(new TermQueryBuilder("boolAttrs." + filter.getKey() + ".selected", filter.getVal()));
            }
        }

        LocalDate rentStart = offerFilter.getDtRentStart();
        LocalDate rentEnd = offerFilter.getDtRentEnd();


        if (rentStart != null
                && rentEnd != null && (rentStart.isBefore(rentEnd))) {


            long daysDiff = ChronoUnit.DAYS.between(rentStart, rentEnd);

            RangeQueryBuilder maxRentDaysRangeBuilder = new RangeQueryBuilder("settings.maxRentDays");
            maxRentDaysRangeBuilder.gte(daysDiff);
            boolQueryBuilder.must(maxRentDaysRangeBuilder);


            RangeQueryBuilder minRentDaysRangeBuilder = new RangeQueryBuilder("settings.minRentDays");
            minRentDaysRangeBuilder.lte(daysDiff);
            boolQueryBuilder.must(minRentDaysRangeBuilder);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            BoolQueryBuilder childQueryBuilder = new BoolQueryBuilder();
            RangeQueryBuilder rentStartDateBuilder = new RangeQueryBuilder("rentStartDate");
            rentStartDateBuilder.lte(rentStart.format(formatter));
            childQueryBuilder.must(rentStartDateBuilder);

            RangeQueryBuilder rentEndDateBuilder = new RangeQueryBuilder("rentEndDate");
            rentEndDateBuilder.gte(rentEnd.format(formatter));
            childQueryBuilder.must(rentEndDateBuilder);

            HasChildQueryBuilder hasChildQueryBuilder = new HasChildQueryBuilder("calendar", childQueryBuilder, ScoreMode.None);
            while (rentStart.isBefore(rentEnd)) {
                childQueryBuilder.must(new TermQueryBuilder("daysMap." + formatter.format(rentStart) + ".dayStatus", "free"));
                rentStart = rentStart.plusDays(1);
            }
            boolQueryBuilder.must(hasChildQueryBuilder);

        }

        return boolQueryBuilder;
    }


    @Override
    public Page findIdsByFilter(RentOfferFilter offerFilter, Pageable pageable) throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(RENT_INDEX);
        searchRequest.types(OFFER_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource("rentOfferId", "");
        searchSourceBuilder.size(pageable.getPageSize());
        searchSourceBuilder.from(pageable.getOffset());


        Sort sort = pageable.getSort();
        if (sort != null) {

            sort.forEach(s -> {
                FieldSortBuilder sortBuilder = new FieldSortBuilder(s.getProperty());
                sortBuilder.order(SortOrder.fromString(s.getDirection().name()));
                searchSourceBuilder.sort(sortBuilder);
            });

        }


        QueryBuilder queryBuilder = boolQueryBuilder(offerFilter);
        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = esClient.search(searchRequest);

        List<String> ids = new ArrayList<>();
        for (SearchHit searchHitFields : response.getHits().getHits()) {
            ids.add(searchHitFields.getId());
        }

        long total = response.getHits().getTotalHits();
        Page page = new PageImpl(ids, pageable, total);
        return page;

    }

    @Override
    public List<ESCategoriesOffersStatistic> countOffersInCategoriesByStatus(String offerStatus) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(RENT_INDEX);
        searchRequest.types(OFFER_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(0);
        searchSourceBuilder.fetchSource(false);

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(new TermQueryBuilder("status", offerStatus));

        searchSourceBuilder.query(boolQueryBuilder);

        TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_category").field("categories").size(Integer.MAX_VALUE);
        searchSourceBuilder.aggregation(aggregation);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = esClient.search(searchRequest);
        Aggregations aggregations = searchResponse.getAggregations();


        if (aggregations != null) {
            Terms byCategoryAgg = aggregations.get("by_category");
            List<ESCategoriesOffersStatistic> categoriesOffers = new ArrayList<>(byCategoryAgg.getBuckets().size());
            byCategoryAgg.getBuckets().forEach(b -> categoriesOffers.add(new ESCategoriesOffersStatistic(b.getKeyAsNumber().longValue(), b.getDocCount())));
            return categoriesOffers;
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void indexRentOffer(Map<String, Object> map) throws IOException {
        Map<String, Object> document = new HashMap<>();
        String id = (String) map.get("id");
        if (!StringUtils.isEmpty(id)) {
            for (String s : map.keySet()) {
                if (rentOfferIndexFields.contains(s)) {

                    document.put(s, map.get(s));
                }
            }
            IndexRequest indexRequest = new IndexRequest(RENT_INDEX, OFFER_TYPE, id).source(document);
            IndexResponse indexResponse = esClient.index(indexRequest);
            switch (indexResponse.getResult()) {
                case CREATED:
                case UPDATED:
                    break;
            }
        }
    }

    @Override
    public void indexRentOfferCalendars(String rentOfferId, Map<String, Object> rentOfferCalendarMap) throws IOException {

        Map<String, Object> document = new HashMap<>();
        String id = (String) rentOfferCalendarMap.get("id");
        for (String s : rentOfferCalendarMap.keySet()) {
            document.put(s, rentOfferCalendarMap.get(s));
        }
        IndexRequest indexRequest = new IndexRequest(RENT_INDEX, "calendar", id).parent(rentOfferId).source(document);
        IndexResponse indexResponse = esClient.index(indexRequest);
        switch (indexResponse.getResult()) {
            case CREATED:
            case UPDATED:
                break;
        }


    }

    @Override
    public void clearRentOfferIndex() throws IOException {

        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            HttpPost deletePost = new HttpPost(e.getRequiredProperty("elasticsearch.scheme").concat("://")
                    .concat(e.getRequiredProperty("elasticsearch.host"))
                    .concat(":" + e.getRequiredProperty("elasticsearch.port")).concat("/heroku_lktlmxlq_rent/_delete_by_query"));
            System.out.println(deletePost.getURI().toString());
            deletePost.setHeader(new BasicHeader("Content-Type", "application/json"));
            deletePost.setEntity(new StringEntity("{\"query\": {\"match_all\": {}}}"));

            System.out.println("Executing request " + deletePost.getRequestLine());

            // Create a custom response handler
            ResponseHandler<String> responseHandler = response -> {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };

            String responseBody = httpclient.execute(deletePost, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        }
    }

    @Override
    public Set<String> suggestByOffersTitlesAndDescriptions(String query) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(RENT_INDEX);
        searchRequest.types(OFFER_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(0);

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.setGlobalText(query);

        suggestBuilder.addSuggestion("ua-suggest", descriptionUaSuggestion);
        suggestBuilder.addSuggestion("ru-suggest", descriptionRuSuggestion);

        searchSourceBuilder.suggest(suggestBuilder);
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = esClient.search(searchRequest);
        Suggest suggest = searchResponse.getSuggest();
        if (suggest != null) {

            Set<String> suggests = new HashSet<>();
            Suggest.Suggestion<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> suggestionUa = suggest.getSuggestion("ua-suggest");
            Suggest.Suggestion<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> suggestionRu = suggest.getSuggestion("ru-suggest");

            List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> uaEntries = suggestionUa.getEntries();
            List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> ruEntries = suggestionRu.getEntries();

            for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> uaEntry : uaEntries) {
                for (Suggest.Suggestion.Entry.Option option : uaEntry.getOptions()) {
                    suggests.add(option.getText().toString());
                }
            }

            for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> ruEntry : ruEntries) {
                for (Suggest.Suggestion.Entry.Option option : ruEntry.getOptions()) {
                    suggests.add(option.getText().toString());
                }
            }
            return suggests;
        }
        return Collections.emptySet();
    }
}

