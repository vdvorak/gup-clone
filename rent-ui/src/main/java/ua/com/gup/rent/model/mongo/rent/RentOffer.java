package ua.com.gup.rent.model.mongo.rent;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.rent.model.image.RentOfferImageInfo;
import ua.com.gup.rent.model.rent.RentOfferAddress;
import ua.com.gup.rent.model.rent.RentOfferContactInfo;
import ua.com.gup.rent.model.rent.RentOfferLands;
import ua.com.gup.rent.model.rent.RentOfferModerationReport;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryBoolAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryMultiAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryNumericAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategorySingleAttributeValue;
import ua.com.gup.rent.model.rent.price.RentOfferPrice;
import ua.com.gup.rent.model.rent.statistic.RentOfferStatistic;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import ua.com.gup.common.model.image.ImageStorage;

import static ua.com.gup.rent.model.mongo.rent.RentOffer.COLLECTION_NAME;

@Document(collection = COLLECTION_NAME, language = "russian")
@CompoundIndexes({
        @CompoundIndex(name = "status_categoriesRegExp", def = "{'status': 1, 'categoriesRegExp': 1}")
})
public class RentOffer {

    public static final String COLLECTION_NAME = "rent.offer";
    public static final String CLASS_NAME = "ua.com.gup.rent.model.mongo.rent.RentOffer";

    @Id
    private String id;

    private String title;

    private String description;

    private LocalDateTime createdDate = LocalDateTime.now();

    private String lastModifiedBy;

    @Indexed
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    private String authorId;

     private RentOfferAddress address;

    private RentOfferLands lands;

    private String youtubeVideoId;

    private RentOfferContactInfo contactInfo;

    private List<Integer> categories;

    private CommonStatus status;

    private RentOfferPrice price;

    private List<ImageStorage> images;

    @Indexed(unique = true)
    private String seoUrl;

    private LinkedHashMap<String, RentOfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

    private LinkedHashMap<String, RentOfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    private LinkedHashMap<String, RentOfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    private LinkedHashMap<String, RentOfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();

    private RentOfferStatistic statistic;

    private RentOfferModerationReport lastOfferModerationReport;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public RentOfferAddress getAddress() {
        return address;
    }

    public void setAddress(RentOfferAddress address) {
        this.address = address;
    }

    public RentOfferLands getLands() {
        return lands;
    }

    public void setLands(RentOfferLands lands) {
        this.lands = lands;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public RentOfferContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(RentOfferContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public RentOfferPrice getPrice() {
        return price;
    }

    public void setPrice(RentOfferPrice price) {
        this.price = price;
    }

    public List<ImageStorage> getImages() {
        if (images == null) {
            images = new LinkedList();
        }
        return images;
    }

    public void setImages(List<ImageStorage> images) {
        this.images = images;
    }  

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public LinkedHashMap<String, RentOfferCategorySingleAttributeValue> getAttrs() {
        return attrs;
    }

    public void setAttrs(LinkedHashMap<String, RentOfferCategorySingleAttributeValue> attrs) {
        this.attrs = attrs;
    }

    public LinkedHashMap<String, RentOfferCategoryMultiAttributeValue> getMultiAttrs() {
        return multiAttrs;
    }

    public void setMultiAttrs(LinkedHashMap<String, RentOfferCategoryMultiAttributeValue> multiAttrs) {
        this.multiAttrs = multiAttrs;
    }

    public LinkedHashMap<String, RentOfferCategoryNumericAttributeValue> getNumAttrs() {
        return numAttrs;
    }

    public void setNumAttrs(LinkedHashMap<String, RentOfferCategoryNumericAttributeValue> numAttrs) {
        this.numAttrs = numAttrs;
    }

    public LinkedHashMap<String, RentOfferCategoryBoolAttributeValue> getBoolAttrs() {
        return boolAttrs;
    }

    public void setBoolAttrs(LinkedHashMap<String, RentOfferCategoryBoolAttributeValue> boolAttrs) {
        this.boolAttrs = boolAttrs;
    }


    public void setStatistic(RentOfferStatistic statistic) {
        this.statistic = statistic;
    }

    public RentOfferStatistic getStatistic() {
        if (statistic == null) {
            statistic = new RentOfferStatistic();
        }
        return statistic;
    }

    public RentOfferModerationReport getLastOfferModerationReport() {
        return lastOfferModerationReport;
    }

    public void setLastOfferModerationReport(RentOfferModerationReport lastOfferModerationReport) {
        this.lastOfferModerationReport = lastOfferModerationReport;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }


    public void incrementView(boolean incrementOfferViews, boolean incrementOfferPhoneViews) {
        if (incrementOfferViews)
            getStatistic().incrementTodayViewStatistic(getCreatedDate().toLocalDate());
        if (incrementOfferPhoneViews)
            getStatistic().incrementTodayViewPhoneStatistic(getCreatedDate().toLocalDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentOffer)) return false;
        RentOffer rentOffer = (RentOffer) o;
        return Objects.equals(getId(), rentOffer.getId()) &&
                Objects.equals(getAuthorId(), rentOffer.getAuthorId()) &&
                Objects.equals(getSeoUrl(), rentOffer.getSeoUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthorId(), getSeoUrl());
    }
}
