package ua.com.gup.search.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.gup.search.model.search.ESCategory;
import ua.com.gup.search.repository.ESCategoryRepository;
import ua.com.gup.search.util.Locale;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Repository
public class ESCategoryRepositoryImpl implements ESCategoryRepository {

    private static final String ES_INDEX = "heroku_lktlmxlq_category";
    private static final String ES_TYPE = "category";


    @Autowired
    private RestHighLevelClient esClient;


    ObjectMapper objectMapper = new ObjectMapper();

    public void createOffer() throws IOException {

//        String id = UUID.randomUUID().toString();
//
//        IndexRequest indexRequest = new IndexRequest("heroku_lktlmxlq_offer","offer");
//
//
//
//        for (int j = 0; j < 10; j++) {
//            List<Integer> randoms = new ArrayList<>(3);
//            for (int i = 0; i < 3; i++) {
//                randoms.add(new Random().nextInt(999));
//            }
//
//            ESOffer esOffer = new ESOffer(id, "demo_title", "demo_desc", randoms);
//            indexRequest.source(objectMapper.writeValueAsString(esOffer));
//        }
//
//        IndexResponse response = esClient.indexRentOffer(indexRequest);


    }

    @Override
    public ESCategory findOneByCode(Long code, Locale locale) throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(ES_INDEX);
        searchRequest.types(ES_TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("code", code));
        searchSourceBuilder.fetchSource(new String[]{"code", "title." + locale},
                new String[]{});
        searchSourceBuilder.size(1);
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = esClient.search(searchRequest);
        Iterator<SearchHit> searchHitIterator = response.getHits().iterator();
        while (searchHitIterator.hasNext()) {
            SearchHit searchHit = searchHitIterator.next();
            Map<String, Object> source = searchHit.getSource();
            Map map = (Map) source.get("title");
            return new ESCategory(source.get("code"), map.get(locale.toString()));
        }
        return new ESCategory();
    }
}
