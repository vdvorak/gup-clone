package ua.com.gup.rent.service.dto.search;

public class SearchResponseDTO {

    private String[] suggests;
    private CategoryStatistic[] aggregations;

    public SearchResponseDTO() {
    }

    public CategoryStatistic[] getAggregations() {
        return aggregations;
    }

    public void setAggregations(CategoryStatistic[] aggregations) {
        this.aggregations = aggregations;
    }

    public String[] getSuggests() {
        return suggests;
    }

    public void setSuggests(String[] suggests) {
        this.suggests = suggests;
    }
}
