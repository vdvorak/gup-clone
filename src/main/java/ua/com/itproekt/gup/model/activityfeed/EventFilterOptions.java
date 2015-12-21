package ua.com.itproekt.gup.model.activityfeed;


public final class EventFilterOptions extends Event {
    private int skip;
    private int limit;
    private boolean hideViewed;
    private Long afterDate;

    public EventFilterOptions() {
        this.skip = 0;
        this.limit = 30;
    }

    public boolean isHideViewed() {
        return hideViewed;
    }

    public void setHideViewed(boolean hideViewed) {
        this.hideViewed = hideViewed;
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

    public Long getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(Long afterDate) {
        this.afterDate = afterDate;
    }
}
