package ua.com.itproekt.gup.model.news;


public final class BlogFilterOptions extends Blog {
    private int skip;
    private int limit;
    private String searchField;

    public BlogFilterOptions() {
        this.skip = 0;
        this.limit = 20;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
