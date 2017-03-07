package ua.com.itproekt.gup.model.offer.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.itproekt.gup.model.offer.Address;
import ua.com.itproekt.gup.model.offer.Offer;

import java.util.List;
import java.util.Set;


/**
 * Class describe options for searching offers.
 *
 * @author Kobylyatskyy Alexander
 */
@Component
public final class OfferFilterOptions extends Offer {
    Set<String> cityList;
    @Autowired private Set<Address> addresses;
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
    private List<String> favouriteCategories;
    private boolean isOfferModifiedAfterModeratorCheck; // is offer was modified after moderator has checked it


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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public List<String> getFavouriteCategories() {
        return favouriteCategories;
    }

    public void setFavouriteCategories(List<String> favouriteCategories) {
        this.favouriteCategories = favouriteCategories;
    }

    public boolean isOfferModifiedAfterModeratorCheck() {
        return isOfferModifiedAfterModeratorCheck;
    }

    public void setIsOfferModifiedAfterModeratorCheck(boolean isOfferModifiedAfterModeratorCheck) {
        this.isOfferModifiedAfterModeratorCheck = isOfferModifiedAfterModeratorCheck;
    }

    @Override
    public String toString() {
        return "OfferFilterOptions{" +
                "cityList=" + cityList +
                ", address='" + getAddress() + '\'' +
                ", addresses='" + addresses + '\'' +
                ", skip=" + skip +
                ", limit=" + limit +
                ", fromPrice=" + fromPrice +
                ", toPrice=" + toPrice +
                ", currency=" + getCurrency() +
                ", showReserved=" + showReserved +
                ", propertiesInterval=" + propertiesInterval +
                ", searchField='" + searchField + '\'' +
                ", priceSortDirection='" + priceSortDirection + '\'' +
                ", createdDateSortDirection='" + createdDateSortDirection + '\'' +
                ", isMain=" + isMain +
                ", favouriteCategories=" + favouriteCategories +
                ", isOfferModifiedAfterModeratorCheck=" + isOfferModifiedAfterModeratorCheck +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OfferFilterOptions that = (OfferFilterOptions) o;

        if (getSkip() != that.getSkip()) return false;
        if (getLimit() != that.getLimit()) return false;
        if (isMain() != that.isMain()) return false;
        if (getCityList() != null ? !getCityList().equals(that.getCityList()) : that.getCityList() != null)
            return false;
        if (getAddresses() != null ? !getAddresses().equals(that.getAddresses()) : that.getAddresses() != null)
            return false;
        if (getFromPrice() != null ? !getFromPrice().equals(that.getFromPrice()) : that.getFromPrice() != null)
            return false;
        if (getToPrice() != null ? !getToPrice().equals(that.getToPrice()) : that.getToPrice() != null) return false;
        if (getShowReserved() != null ? !getShowReserved().equals(that.getShowReserved()) : that.getShowReserved() != null)
            return false;
        if (getPropertiesInterval() != null ? !getPropertiesInterval().equals(that.getPropertiesInterval()) : that.getPropertiesInterval() != null)
            return false;
        if (getSearchField() != null ? !getSearchField().equals(that.getSearchField()) : that.getSearchField() != null)
            return false;
        if (getPriceSortDirection() != null ? !getPriceSortDirection().equals(that.getPriceSortDirection()) : that.getPriceSortDirection() != null)
            return false;
        return !(getCreatedDateSortDirection() != null ? !getCreatedDateSortDirection().equals(that.getCreatedDateSortDirection()) : that.getCreatedDateSortDirection() != null) && !(getFavouriteCategories() != null ? !getFavouriteCategories().equals(that.getFavouriteCategories()) : that.getFavouriteCategories() != null);

    }

    @Override
    public int hashCode() {
        int result = 31 * (getCityList() != null ? getCityList().hashCode() : 0);
        result = 31 * (getAddresses() != null ? getAddresses().hashCode() : 0);
        result = 31 * result + getSkip();
        result = 31 * result + getLimit();
        result = 31 * result + (getFromPrice() != null ? getFromPrice().hashCode() : 0);
        result = 31 * result + (getToPrice() != null ? getToPrice().hashCode() : 0);
        result = 31 * result + (getShowReserved() != null ? getShowReserved().hashCode() : 0);
        result = 31 * result + (getPropertiesInterval() != null ? getPropertiesInterval().hashCode() : 0);
        result = 31 * result + (getSearchField() != null ? getSearchField().hashCode() : 0);
        result = 31 * result + (getPriceSortDirection() != null ? getPriceSortDirection().hashCode() : 0);
        result = 31 * result + (getCreatedDateSortDirection() != null ? getCreatedDateSortDirection().hashCode() : 0);
        result = 31 * result + (getCategories() != null ? getCategories().hashCode() : 0);
        return result;
    }
}
