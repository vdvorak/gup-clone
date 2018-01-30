package ua.com.gup.search.util;

import org.elasticsearch.action.search.SearchRequest;

public class ElasticSearchRequestFactory {

    private ElasticSearchRequestFactory() {
    }

    public static SearchRequest getSearchRequest(String[] indicies, String[] types) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indicies);
        searchRequest.types(types);
        return searchRequest;
    }

    public static SearchRequest getSearchRequest(String index, String type) {
        return getSearchRequest(new String[]{index}, new String[]{type});
    }

}
