package ua.com.gup.model.profiles;

/**
 * The type Profile rating filter options, use for pagination.
 */
public final class ProfileRatingFilterOptions extends ProfileRating {
    private int skip;
    private int limit;

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
