package ua.com.itproekt.gup.model.subscription.filter;


import ua.com.itproekt.gup.model.subscription.Subscription;

public final class SubscriptionFilterOptions extends Subscription {
    private int skip;
    private int limit;


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

    @Override
    public String toString() {
        return "SubscriptionFilterOptions{" +
                "skip=" + skip +
                ", limit=" + limit +
                '}';
    }
}
