package ua.com.gup.mongo.model.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.common.model.filter.CommonAddressFilter;
import ua.com.gup.common.model.filter.CommonAttributeFilter;
import ua.com.gup.common.model.filter.CommonAuthorFilter;
import ua.com.gup.common.model.filter.CommonCoordinatesFilter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ApiModel(description = "Offer filter model")
public class OfferFilter implements Serializable {

    private static final long serialVersionUID = 1232825578694716871L;

    @ApiModelProperty(value = "String for full text search", position = 0)
    private String query;

    @ApiModelProperty(value = "Field form object Profile", position = 5)
    private CommonAuthorFilter authorFilter;

    @ApiModelProperty(value = "Categories with ',' delimiter", position = 10)
    private Integer[] categories;

    @ApiModelProperty(value = "Filter for creation banDate [from; to] optional", position = 20)
    private DateFilter date;

    @ApiModelProperty(value = "Filter for address", position = 30)
    private CommonAddressFilter address;

    @ApiModelProperty(value = "Coordinates for address", position = 30)
    private CommonCoordinatesFilter coordinates;

    @ApiModelProperty(value = "Filter for price", position = 40)
    private MoneyFilter price;

    @ApiModelProperty(value = "Filters for attributes", position = 50)
    private List<CommonAttributeFilter> attrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for attributes", position = 55)
    private List<CommonAttributeFilter> multiAttrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for numeric attributes", position = 60)
    private List<NumericAttributeFilter> numAttrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for boolean attributes", position = 70)
    private List<BooleanAttributeFilter> boolAttrs = new ArrayList<>();

    @ApiModelProperty(value = "Seo url's with ',' delimiter", position = 80)
    private String[] seoUrls;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer[] getCategories() {
        return categories;
    }

    public void setCategories(Integer[] categories) {
        this.categories = categories;
    }

    public DateFilter getDate() {
        return date;
    }

    public void setDate(DateFilter date) {
        this.date = date;
    }

    public CommonAddressFilter getAddress() {
        return address;
    }

    public void setAddress(CommonAddressFilter address) {
        this.address = address;
    }

    public MoneyFilter getPrice() {
        return price;
    }

    public void setPrice(MoneyFilter price) {
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

    public CommonAuthorFilter getAuthorFilter() {
        return authorFilter;
    }

    public void setAuthorFilter(CommonAuthorFilter authorFilter) {
        this.authorFilter = authorFilter;
    }


    public String[] getSeoUrls() {
        return seoUrls;
    }

    public void setSeoUrls(String[] seoUrls) {
        this.seoUrls = seoUrls;
    }

    public CommonCoordinatesFilter getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CommonCoordinatesFilter coordinates) {
        this.coordinates = coordinates;

    }

    @Override
    public String toString() {
        return "OfferFilter{" +
                "query='" + query + '\'' +
                ", authorFilter=" + authorFilter +
                ", categories=" + Arrays.toString(categories) +
                ", banDate=" + date +
                ", address=" + address +
                ", price=" + price +
                ", attrs=" + attrs +
                ", multiAttrs=" + multiAttrs +
                ", numAttrs=" + numAttrs +
                ", boolAttrs=" + boolAttrs +
                '}';
    }
}
