package ua.com.gup.repository.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "Offer filter model")
public class OfferFilter implements Serializable {

    private static final long serialVersionUID = 1232825578694716871L;

    @ApiModelProperty(value = "String for full text search", position = 0)
    private String query;

    @ApiModelProperty(value = "Categories with ',' delimiter", position = 10)
    private String categories;

    @ApiModelProperty(value = "Filter for creation date [from; to] optional", position = 20)
    private DateFilter date;

    @ApiModelProperty(value = "Filter for address", position = 30)
    private AddressFilter address;

    @ApiModelProperty(value = "Filter for price", position = 40)
    private MoneyFilter price;

    @ApiModelProperty(value = "Filters for attributes", position = 50)
    private List<AttributeFilter> attrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for numeric attributes", position = 60)
    private List<NumericAttributeFilter> numAttrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for boolean attributes", position = 70)
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

    public DateFilter getDate() {
        return date;
    }

    public void setDate(DateFilter date) {
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
