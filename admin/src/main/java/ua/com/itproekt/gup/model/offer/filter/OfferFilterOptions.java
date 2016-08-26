package ua.com.itproekt.gup.model.offer.filter;


import ua.com.itproekt.gup.model.offer.Offer;

import java.util.List;

public final class OfferFilterOptions extends Offer {
    private int skip;
    private int limit;
    private Integer fromPrice;
    private Integer toPrice;
    private Boolean showReserved;
    private List<PropertyWithValuesInterval> propertiesInterval;
    private String searchField;
    private String priceSortDirection;
    private String createdDateSortDirection;

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

    public Integer getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(Integer fromPrice) {
        this.fromPrice = fromPrice;
    }

    public Integer getToPrice() {
        return toPrice;
    }

    public void setToPrice(Integer toPrice) {
        this.toPrice = toPrice;
    }

    public Boolean getShowReserved() {
        return showReserved;
    }

    public void setShowReserved(Boolean showReserved) {
        this.showReserved = showReserved;
    }

    public List<PropertyWithValuesInterval> getPropertiesInterval() {
        return propertiesInterval;
    }

    public void setPropertiesInterval(List<PropertyWithValuesInterval> propertiesInterval) {
        this.propertiesInterval = propertiesInterval;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getPriceSortDirection() {
        return priceSortDirection;
    }

    public void setPriceSortDirection(String priceSortDirection) {
        this.priceSortDirection = priceSortDirection;
    }

    public String getCreatedDateSortDirection() {
        return createdDateSortDirection;
    }

    public void setCreatedDateSortDirection(String createdDateSortDirection) {
        this.createdDateSortDirection = createdDateSortDirection;
    }
}
