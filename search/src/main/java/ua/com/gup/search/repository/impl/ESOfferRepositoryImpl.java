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
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.gup.search.model.search.ESCategoriesOffersStatistic;
import ua.com.gup.search.model.search.ESCategoriesStatistic;
import ua.com.gup.search.model.search.ESOffer;
import ua.com.gup.search.model.filter.OfferFilter;
import ua.com.gup.search.repository.ESOfferRepository;

import java.io.IOException;
import java.util.*;

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
    public Set<String> suggestByOffersTitlesAndDescriptions(String query) throws IOException {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ES_INDEX);
        searchRequest.types(ES_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.fetchSource(false);

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.setGlobalText(query);
        suggestBuilder.addSuggestion("ua-suggest", new TermSuggestionBuilder("description_ua").size(10));
        suggestBuilder.addSuggestion("ru-suggest", new TermSuggestionBuilder("description_ru").size(10));

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

    @Override
    public Set<String> findOffersIdsByFilter(OfferFilter filter) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ES_INDEX);
        searchRequest.types(ES_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.fetchSource(false);

        searchRequest.source(searchSourceBuilder);


        SearchResponse searchResponse = esClient.search(searchRequest);


        return null;
    }
}