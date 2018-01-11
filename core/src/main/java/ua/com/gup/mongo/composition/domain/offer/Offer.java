package ua.com.gup.mongo.composition.domain.offer;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.address.Address;
import ua.com.gup.mongo.model.category.attribute.OfferCategoryBoolAttributeValue;
import ua.com.gup.mongo.model.category.attribute.OfferCategoryMultiAttributeValue;
import ua.com.gup.mongo.model.category.attribute.OfferCategoryNumericAttributeValue;
import ua.com.gup.mongo.model.category.attribute.OfferCategorySingleAttributeValue;
import ua.com.gup.mongo.model.offer.*;
import ua.com.gup.mongo.model.statistic.OfferStatistic;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;

import ua.com.gup.common.model.mongo.CommonRentOffer;

/**
 * Database table entity Offer.
 */

@Document(collection = Offer.COLLECTION_NAME, language = "russian")
@CompoundIndexes({
        @CompoundIndex(name = "status_categoriesRegExp", def = "{'status': 1, 'categoriesRegExp': 1}")
})
public class Offer extends CommonRentOffer {

    public static final String COLLECTION_NAME = "offer";
    private static final long serialVersionUID = 1L;
   
    private ZonedDateTime createdDate = ZonedDateTime.now();

    private String lastModifiedBy;

    @Indexed
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    private CommonStatus status;

    private List<Integer> categories;

    @NotNull
    @Size(min = 2, max = 70, message = "The length of field 'title' should be in range 2-70")
    private String title;

    @Size(max = 5000, message = "The length of field 'description' should be less then 5000")
    private String description;

    private String authorId;

    private Address address;

    private Price price;

    private Lands lands;

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

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }


    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
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

    public Lands getLands() {
        return lands;
    }

    public void setLands(Lands lands) {
        this.lands = lands;
    }

    public OfferStatistic getStatistic() {
        if (statistic == null) {
            statistic = new OfferStatistic();
        }
        return statistic;
    }

    public void setStatistic(OfferStatistic statistic) {
        this.statistic = statistic;
    }

    public void incrementView(boolean incrementOfferViews, boolean incrementOfferPhoneViews) {
        if (incrementOfferViews)
            getStatistic().incrementTodayViewStatistic(getCreatedDate().toLocalDate());
        if (incrementOfferPhoneViews)
            getStatistic().incrementTodayViewPhoneStatistic(getCreatedDate().toLocalDate());
    }
}
