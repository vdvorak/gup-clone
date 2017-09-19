package ua.com.gup.model.profiles;

/**
 * The type Profile filter options, use for pagination.
 */
public final class ProfileFilterOptions extends Profile {
    private String searchField;
    private int skip;
    private int limit;

    public ProfileFilterOptions () {
        this.skip = 0;
        this.limit = 20;
    }

    public ProfileFilterOptions (int skip, int limit) {
        this.skip = skip;
        this.limit = limit;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    /**
     * Gets skip.
     *
     * @return the skip
     */
    public int getSkip() {
        return skip;
    }

    /**
     * Sets skip.
     *
     * @param skip the skip
     */
    public void setSkip(int skip) {
        this.skip = skip;
    }

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets limit.
     *
     * @param limit the limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }
}
