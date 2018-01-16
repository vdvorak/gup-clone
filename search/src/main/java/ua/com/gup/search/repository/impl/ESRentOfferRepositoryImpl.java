package ua.com.gup.search.repository.impl;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class ESRentOfferRepositoryImpl implements ESRentOfferRepository {

    private static final String ES_INDEX = "heroku_lktlmxlq_rent";
    private static final String ES_TYPE = "offer";


    @Autowired
    private RestHighLevelClient esClient;

    @Override
    public List<ESCategoriesStatistic> countOffersInCategoriesByStatusAndProfileId(String offerStatus, String profileId) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ES_INDEX);
        searchRequest.types(ES_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(false);

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

        if (offerFilter.getAuthor() != null && !StringUtils.isEmpty(offerFilter.getAuthor().getAuthorId())) {
            boolQueryBuilder.must(new TermQueryBuilder("authorId", offerFilter.getAuthor().getAuthorId()));
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
                boolQueryBuilder.must(new TermsQueryBuilder("address.region.code", addressFilter.getDistricts().split(",")));
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

        return boolQueryBuilder;
    }


    @Override
    public Page findIdsByFilter(RentOfferFilter offerFilter, Pageable pageable) throws IOException {


        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ES_INDEX);
        searchRequest.types(ES_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource("_id", "");
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
        for (SearchHit fields : response.getHits()) {
            ids.add(fields.getId());
        }

        Page page = new PageImpl(ids, pageable, response.getHits().getTotalHits());
        return page;

    }

    @Override
    public List<ESCategoriesOffersStatistic> countOffersInCategoriesByStatus(String offerStatus) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ES_INDEX);
        searchRequest.types(ES_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
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
}
