package ua.com.gup.mongo.composition.domain.offer;

import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.mongo.model.category.attribute.OfferCategoryBoolAttributeValue;
import ua.com.gup.mongo.model.category.attribute.OfferCategoryMultiAttributeValue;
import ua.com.gup.mongo.model.category.attribute.OfferCategoryNumericAttributeValue;
import ua.com.gup.mongo.model.category.attribute.OfferCategorySingleAttributeValue;
import ua.com.gup.mongo.model.offer.Lands;
import ua.com.gup.mongo.model.offer.OfferContactInfo;
import ua.com.gup.mongo.model.offer.OfferModerationReport;
import ua.com.gup.mongo.model.offer.Price;
import ua.com.gup.mongo.model.statistic.OfferStatistic;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;

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

    private Price price;

    private Lands lands;



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

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
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
