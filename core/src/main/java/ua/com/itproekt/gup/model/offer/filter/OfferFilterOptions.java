package ua.com.itproekt.gup.model.offer.filter;


import ua.com.itproekt.gup.model.offer.Offer;

import java.util.List;
import java.util.Set;

public final class OfferFilterOptions extends Offer {
    Set<String> cityList;
    private int skip;
    private int limit;
    private Long fromPrice;
    private Long toPrice;
    private Boolean showReserved; // allow to get reserved offers (by default it is not allowed)
    private List<PropertyWithValuesInterval> propertiesInterval;
    private String searchField;
    private String priceSortDirection;
    private String createdDateSortDirection;
    private boolean isMain;

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

    public Long getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(Long fromPrice) {
        this.fromPrice = fromPrice;
    }

    public Long getToPrice() {
        return toPrice;
    }

    public void setToPrice(Long toPrice) {
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

    public boolean isMain() {
        return isMain;
    }

    public void setIsMain(boolean isMain) {
        this.isMain = isMain;
    }

    public Set<String> getCityList() {
        return cityList;
    }

    public void setCityList(Set<String> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "OfferFilterOptions{" +
                "skip=" + skip +
                ", limit=" + limit +
                ", fromPrice=" + fromPrice +
                ", toPrice=" + toPrice +
                ", showReserved=" + showReserved +
                ", propertiesInterval=" + propertiesInterval +
                ", searchField='" + searchField + '\'' +
                ", priceSortDirection='" + priceSortDirection + '\'' +
                ", createdDateSortDirection='" + createdDateSortDirection + '\'' +
                ", isMain=" + isMain +
                ", cityList=" + cityList +
                '}';
    }
}
