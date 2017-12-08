package ua.com.gup.rent.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "Rent Offer filter model")
public class RentOfferFilter implements Serializable {

    private static final long serialVersionUID = 1232825578694716871L;

    @ApiModelProperty(value = "String for full text search", position = 0)
    private String query;

    @ApiModelProperty(value = "Field form object Profile", position = 5)
    private RentOfferAuthorFilter rentOfferAuthorFilter;

    @ApiModelProperty(value = "Categories with ',' delimiter", position = 10)
    private Integer[] categories;

    @ApiModelProperty(value = "Filter for creation date [from; to] optional", position = 20)
    private RentOfferDateFilter date;

    @ApiModelProperty(value = "Filter for address", position = 30)
    private RentOfferAddressFilter address;

    @ApiModelProperty(value = "Coordinates for address", position = 30)
    private RentOfferCoordinatesFilter coordinates;

    @ApiModelProperty(value = "Filter for price", position = 40)
    private RentOfferMoneyFilter price;

    @ApiModelProperty(value = "Filters for attributes", position = 50)
    private List<RentOfferAttributeFilter> attrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for attributes", position = 55)
    private List<RentOfferAttributeFilter> multiAttrs = new ArrayList<>();

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

    public RentOfferAuthorFilter getRentOfferAuthorFilter() {
        return rentOfferAuthorFilter;
    }

    public void setRentOfferAuthorFilter(RentOfferAuthorFilter rentOfferAuthorFilter) {
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

    public RentOfferAddressFilter getAddress() {
        return address;
    }

    public void setAddress(RentOfferAddressFilter address) {
        this.address = address;
    }

    public RentOfferCoordinatesFilter getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(RentOfferCoordinatesFilter coordinates) {
        this.coordinates = coordinates;
    }

    public RentOfferMoneyFilter getPrice() {
        return price;
    }

    public void setPrice(RentOfferMoneyFilter price) {
        this.price = price;
    }

    public List<RentOfferAttributeFilter> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<RentOfferAttributeFilter> attrs) {
        this.attrs = attrs;
    }

    public List<RentOfferAttributeFilter> getMultiAttrs() {
        return multiAttrs;
    }

    public void setMultiAttrs(List<RentOfferAttributeFilter> multiAttrs) {
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
