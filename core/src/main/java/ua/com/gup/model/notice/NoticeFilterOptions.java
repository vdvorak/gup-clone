package ua.com.gup.model.notice;

public class NoticeFilterOptions extends Notice {
    private int skip;
    private int limit;

    public NoticeFilterOptions() {
        this.limit = 10;
        this.skip = 0;
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
