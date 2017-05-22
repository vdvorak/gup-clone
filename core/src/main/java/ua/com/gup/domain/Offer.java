package ua.com.gup.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Language;
import ua.com.gup.domain.enumeration.OfferStatus;
import ua.com.gup.domain.offer.*;
import ua.com.gup.domain.offer.attribute.value.OfferCategoryBoolAttributeValue;
import ua.com.gup.domain.offer.attribute.value.OfferCategoryMultiAttributeValue;
import ua.com.gup.domain.offer.attribute.value.OfferCategoryNumericAttributeValue;
import ua.com.gup.domain.offer.attribute.value.OfferCategorySingleAttributeValue;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * A Offer.
 */

@Document(collection = Offer.COLLECTION_NAME, language = "russian")
public class Offer implements Serializable {

    public static final String COLLECTION_NAME = "offer2";
    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    @Language
    private String createdBy;

    private ZonedDateTime createdDate = ZonedDateTime.now();

    private String lastModifiedBy;

    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    @Indexed
    private OfferStatus status;

    @Indexed
    private String categoriesRegExp;

    private LinkedList<OfferCategory> categories;

    @NotNull
    @Size(min = 2, max = 70, message = "The length of field 'title' should be in range 2-70")
    private String title;

    @Size(max = 5000, message = "The length of field 'description' should be less then 5000")
    private String description;

    private LinkedHashSet<String> imageIds;

    private String authorId;

    private Address address;

    private Price price;

    @Indexed(unique = true)
    private String seoUrl;

    private String youtubeVideoId;

    private OfferContactInfo contactInfo;

    private LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

    private LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    private LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    private LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();

    private OfferStatistic statistic;

    private OfferModerationReport lastOfferModerationReport;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public OfferModerationReport getLastOfferModerationReport() {
        return lastOfferModerationReport;
    }

    public void setLastOfferModerationReport(OfferModerationReport lastOfferModerationReport) {
        this.lastOfferModerationReport = lastOfferModerationReport;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public String getCategoriesRegExp() {
        return categoriesRegExp;
    }

    public void setCategoriesRegExp(String categoriesRegExp) {
        this.categoriesRegExp = categoriesRegExp;
    }

    public LinkedList<OfferCategory> getCategories() {
        return categories;
    }

    public void setCategories(LinkedList<OfferCategory> categories) {
        this.categories = categories;
        this.categoriesRegExp = categories.stream().map(c -> "" + c.getCode()).collect(Collectors.joining("/")) + "/";
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

    public LinkedHashSet<String> getImageIds() {
        return imageIds;
    }

    public void setImageIds(LinkedHashSet<String> imageIds) {
        this.imageIds = imageIds;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public OfferContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(OfferContactInfo contactInfo) {
        this.contactInfo = contactInfo;
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

    public OfferModerationReport getLastModerationReport() {
        return lastOfferModerationReport;
    }

    public void setLastModerationReport(OfferModerationReport lastOfferModerationReport) {
        this.lastOfferModerationReport = lastOfferModerationReport;
    }

}
