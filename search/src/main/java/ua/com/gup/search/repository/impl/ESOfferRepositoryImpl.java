package ua.com.gup.search.repository.impl;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.gup.search.model.ESCategoriesStatistic;
import ua.com.gup.search.model.ESOffer;
import ua.com.gup.search.repository.ESOfferRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ESOfferRepositoryImpl implements ESOfferRepository {

    private static final String ES_INDEX = "heroku_lktlmxlq_offer";
    private static final String ES_TYPE = "offer";


    @Autowired
    private RestHighLevelClient esClient;


    @Override
    public List<ESCategoriesStatistic> countAggregatedOffersCategories(String query) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ES_INDEX);
        searchRequest.types(ES_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(query, "description_*", "title_*"));
        searchSourceBuilder.fetchSource(false);

        TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_category")
                .field("categories").size(3);
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

    @Override
    public Iterable<ESOffer> findByQueryAndCategoriesIds(String query, Integer[] categoriesIds) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ES_INDEX);
        searchRequest.types(ES_TYPE);

        return null;
//        AggregationBuilder aggregationBuilder = new AggregationBuilder() {
//        }
//
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.match);
//        searchRequest.source(searchSourceBuilder);


    }

    @Override
    public List<String> suggestByOffersTitlesAndDescriptions(String query) {
        return null;
    }

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

}
