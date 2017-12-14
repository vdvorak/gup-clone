package ua.com.gup.rent.model.mongo.rent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.GupLoggedUser;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.common.model.mongo.CommonRentOffer;
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

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

import static ua.com.gup.rent.model.mongo.rent.RentOffer.COLLECTION_NAME;

@Document(collection = COLLECTION_NAME, language = "russian")
@Data
@EqualsAndHashCode(callSuper = false)
public class RentOffer  extends  CommonRentOffer{

    public static final String COLLECTION_NAME = "rent.offer";
    public static final String CLASS_NAME = "ua.com.gup.rent.model.mongo.rent.RentOffer";

    private String title;

    private String description;

    @CreatedBy
    private GupLoggedUser user;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedBy
    private GupLoggedUser lastModifiedUser;

    @LastModifiedDate
    private Instant lastModifiedDate;

    private String authorId;

     private RentOfferAddress address;

    private RentOfferLands lands;

    private String youtubeVideoId;

    private RentOfferContactInfo contactInfo;

    private List<Integer> categories;

    private CommonStatus status;

    private RentOfferPrice price;

    @Indexed(unique = true)
    private String seoUrl;

    private LinkedHashMap<String, RentOfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

    private LinkedHashMap<String, RentOfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    private LinkedHashMap<String, RentOfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    private LinkedHashMap<String, RentOfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();

    private RentOfferStatistic statistic;

    private RentOfferModerationReport lastOfferModerationReport;






    public void setStatistic(RentOfferStatistic statistic) {
        this.statistic = statistic;
    }

    public RentOfferStatistic getStatistic() {
        if (statistic == null) {
            statistic = new RentOfferStatistic();
        }
        return statistic;
    }

    public void incrementView(boolean incrementOfferViews, boolean incrementOfferPhoneViews) {
        if (incrementOfferViews)
            getStatistic().incrementTodayViewStatistic(LocalDate.from(getCreatedDate()));
        if (incrementOfferPhoneViews)
            getStatistic().incrementTodayViewPhoneStatistic(LocalDate.from(getCreatedDate()));
    }

   /* @Override
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
    }*/
}
