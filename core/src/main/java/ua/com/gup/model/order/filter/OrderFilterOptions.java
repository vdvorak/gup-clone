package ua.com.itproekt.gup.model.order.filter;


import ua.com.itproekt.gup.model.order.Order;

public class OrderFilterOptions extends Order {
    private int skip;
    private int limit;
    private String createdDateSortDirection;


    public OrderFilterOptions() {
        this.skip = 0;
        this.limit = 10;
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

    public String getCreatedDateSortDirection() {
        return createdDateSortDirection;
    }

    public void setCreatedDateSortDirection(String createdDateSortDirection) {
        this.createdDateSortDirection = createdDateSortDirection;
    }

    @Override
    public String toString() {
        return "OrderFilterOptions{" +
                "skip=" + skip +
                ", limit=" + limit +
                ", createdDateSortDirection='" + createdDateSortDirection + '\'' +
                '}';
    }
}



