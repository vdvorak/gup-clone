package ua.com.gup.rent.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.common.model.filter.CommonAddressFilter;
import ua.com.gup.common.model.filter.CommonAttributeFilter;
import ua.com.gup.common.model.filter.CommonAuthorFilter;
import ua.com.gup.common.model.filter.CommonCoordinatesFilter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "Rent Offer filter model")
public class RentOfferFilter implements Serializable {

    private static final long serialVersionUID = 1232825578694716871L;

    @ApiModelProperty(value = "String for full text search", position = 0)
    private String query;

    @ApiModelProperty(value = "Field form object Profile", position = 5)
    private CommonAuthorFilter rentOfferAuthorFilter;

    @ApiModelProperty(value = "Categories with ',' delimiter", position = 10)
    private Integer[] categories;

    @ApiModelProperty(value = "Filter for creation date [from; to] optional", position = 20)
    private RentOfferDateFilter date;

    @ApiModelProperty(value = "Filter for address", position = 30)
    private CommonAddressFilter address;

    @ApiModelProperty(value = "Coordinates for address", position = 30)
    private CommonCoordinatesFilter coordinates;

    @ApiModelProperty(value = "Filter for price", position = 40)
    private RentOfferMoneyFilter price;

    @ApiModelProperty(value = "Filters for attributes", position = 50)
    private List<CommonAttributeFilter> attrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for attributes", position = 55)
    private List<CommonAttributeFilter> multiAttrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for numeric attributes", position = 60)
    private List<RentOfferNumericAttributeFilter> numAttrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for boolean attributes", position = 70)
    private List<RentOfferBooleanAttributeFilter> boolAttrs = new ArrayList<>();

    @ApiModelProperty(value = "Seo url's with ',' delimiter", position = 80)
    private String[] seoUrls;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public CommonAuthorFilter getRentOfferAuthorFilter() {
        return rentOfferAuthorFilter;
    }

    public void setRentOfferAuthorFilter(CommonAuthorFilter rentOfferAuthorFilter) {
        this.rentOfferAuthorFilter = rentOfferAuthorFilter;
    }

    public Integer[] getCategories() {
        return categories;
    }

    public void setCategories(Integer[] categories) {
        this.categories = categories;
    }

    public RentOfferDateFilter getDate() {
        return date;
    }

    public void setDate(RentOfferDateFilter date) {
        this.date = date;
    }

    public CommonAddressFilter getAddress() {
        return address;
    }

    public void setAddress(CommonAddressFilter address) {
        this.address = address;
    }

    public CommonCoordinatesFilter getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CommonCoordinatesFilter coordinates) {
        this.coordinates = coordinates;
    }

    public RentOfferMoneyFilter getPrice() {
        return price;
    }

    public void setPrice(RentOfferMoneyFilter price) {
        this.price = price;
    }

    public List<CommonAttributeFilter> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<CommonAttributeFilter> attrs) {
        this.attrs = attrs;
    }

    public List<CommonAttributeFilter> getMultiAttrs() {
        return multiAttrs;
    }

    public void setMultiAttrs(List<CommonAttributeFilter> multiAttrs) {
        this.multiAttrs = multiAttrs;
    }

    public List<RentOfferNumericAttributeFilter> getNumAttrs() {
        return numAttrs;
    }

    public void setNumAttrs(List<RentOfferNumericAttributeFilter> numAttrs) {
        this.numAttrs = numAttrs;
    }

    public List<RentOfferBooleanAttributeFilter> getBoolAttrs() {
        return boolAttrs;
    }

    public void setBoolAttrs(List<RentOfferBooleanAttributeFilter> boolAttrs) {
        this.boolAttrs = boolAttrs;
    }

    public String[] getSeoUrls() {
        return seoUrls;
    }

    public void setSeoUrls(String[] seoUrls) {
        this.seoUrls = seoUrls;
    }
}
