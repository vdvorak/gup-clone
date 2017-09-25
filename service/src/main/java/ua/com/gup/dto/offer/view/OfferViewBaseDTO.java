package ua.com.gup.dto.offer.view;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.model.offer.OfferCategory;
import ua.com.gup.model.offer.OfferStatistic;
import ua.com.gup.domain.category.attribute.value.OfferCategoryBoolAttributeValue;
import ua.com.gup.domain.category.attribute.value.OfferCategoryMultiAttributeValue;
import ua.com.gup.domain.category.attribute.value.OfferCategoryNumericAttributeValue;
import ua.com.gup.domain.category.attribute.value.OfferCategorySingleAttributeValue;
import ua.com.gup.dto.offer.OfferPriceDTO;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.*;

public class OfferViewBaseDTO implements Serializable {

    @ApiModelProperty(position = 0, example = "58ff0d6c821847a4bc8c5bff")
    private String id;

    @ApiModelProperty(position = 5)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime lastModifiedDate;

    @ApiModelProperty(position = 20, example = "58edf17a4c8e83648c2f1aa3")
    private String authorId;

    @ApiModelProperty(position = 30)
    private LinkedList<OfferCategory> categories;

    @ApiModelProperty(position = 40, example = "title")
    private String title;

    @ApiModelProperty(position = 50, example = "description")
    private String description;

    @ApiModelProperty(position = 70)
    private OfferPriceDTO price;

    @ApiModelProperty(position = 80)
    private List<String> imageIds;

    @ApiModelProperty(position = 90, example = "prodam-toyota-rav-4-2016hod-ls")
    private String seoUrl;

    @ApiModelProperty(position = 110)
    private LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 115)
    private LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 120)
    private LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 130)
    private LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 140)
    private OfferStatistic statistic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public LinkedList<OfferCategory> getCategories() {
        return categories;
    }

    public void setCategories(LinkedList<OfferCategory> categories) {
        this.categories = categories;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OfferPriceDTO getPrice() {
        return price;
    }

    public void setPrice(OfferPriceDTO price) {
        this.price = price;
    }

    public List<String> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<String> imageIds) {
        this.imageIds = imageIds;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public LinkedHashMap<String, OfferCategorySingleAttributeValue> getAttrs() {
        return attrs;
    }

    public void setAttrs(LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs) {
        this.attrs = attrs;
    }

    public LinkedHashMap<String, OfferCategoryMultiAttributeValue> getMultiAttrs() {
        return multiAttrs;
    }

    public void setMultiAttrs(LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs) {
        this.multiAttrs = multiAttrs;
    }

    public LinkedHashMap<String, OfferCategoryNumericAttributeValue> getNumAttrs() {
        return numAttrs;
    }

    public void setNumAttrs(LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs) {
        this.numAttrs = numAttrs;
    }

    public LinkedHashMap<String, OfferCategoryBoolAttributeValue> getBoolAttrs() {
        return boolAttrs;
    }

    public void setBoolAttrs(LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs) {
        this.boolAttrs = boolAttrs;
    }

    public OfferStatistic getStatistic() {
        return statistic;
    }

    public void setStatistic(OfferStatistic statistic) {
        this.statistic = statistic;
    }
}
