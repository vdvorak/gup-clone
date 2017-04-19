package ua.com.gup.service.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "Offer filter model")
public class OfferFilter implements Serializable {

    @ApiModelProperty("String for full text search")
    private String query;

    @ApiModelProperty("Categories with ',' delimiter")
    private String categories;

    @ApiModelProperty("Filter for creation date [from; to] optional")
    private Range<LocalDate> date;

    @ApiModelProperty("Filter for address")
    private AddressFilter address;

    @ApiModelProperty("Filter for price")
    private MoneyFilter price;

    @ApiModelProperty("Filters for attributes")
    private List<AttributeFilter> attrs = new ArrayList<>();

    @ApiModelProperty("Filters for numeric attributes")
    private List<NumericAttributeFilter> numAttrs = new ArrayList<>();

    @ApiModelProperty("Filters for boolean attributes")
    private List<BooleanAttributeFilter> boolAttrs = new ArrayList<>();

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Range<LocalDate> getDate() {
        return date;
    }

    public void setDate(Range<LocalDate> date) {
        this.date = date;
    }

    public AddressFilter getAddress() {
        return address;
    }

    public void setAddress(AddressFilter address) {
        this.address = address;
    }

    public MoneyFilter getPrice() {
        return price;
    }

    public void setPrice(MoneyFilter price) {
        this.price = price;
    }

    public List<AttributeFilter> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<AttributeFilter> attrs) {
        this.attrs = attrs;
    }

    public List<NumericAttributeFilter> getNumAttrs() {
        return numAttrs;
    }

    public void setNumAttrs(List<NumericAttributeFilter> numAttrs) {
        this.numAttrs = numAttrs;
    }

    public List<BooleanAttributeFilter> getBoolAttrs() {
        return boolAttrs;
    }

    public void setBoolAttrs(List<BooleanAttributeFilter> boolAttrs) {
        this.boolAttrs = boolAttrs;
    }
}
