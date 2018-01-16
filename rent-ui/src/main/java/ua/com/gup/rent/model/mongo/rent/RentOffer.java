package ua.com.gup.rent.model.mongo.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.GupLoggedUser;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;
import ua.com.gup.rent.model.rent.RentOfferContactInfo;
import ua.com.gup.rent.model.rent.RentOfferLands;
import ua.com.gup.rent.model.rent.RentOfferModerationReport;
import ua.com.gup.rent.model.rent.RentOfferSettings;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryBoolAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryMultiAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryNumericAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategorySingleAttributeValue;
import ua.com.gup.rent.model.rent.price.RentOfferPrice;
import ua.com.gup.rent.model.rent.statistic.RentOfferStatistic;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Document(collection = ObjectType.RENT_OFFER, language = "russian")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(of = {"id"})
public class RentOffer extends CommonRentOffer {

    public static final String CLASS_NAME = "ua.com.gup.rent.model.mongo.rent.RentOffer";

    @CreatedBy
    private GupLoggedUser user;

    @CreatedDate
    private ZonedDateTime createdDate;

    private GupLoggedUser lastModifiedUser;

    @LastModifiedDate
    private ZonedDateTime lastModifiedDate;

    private RentOfferLands lands;

    private String youtubeVideoId;

    private RentOfferContactInfo contactInfo;

    private RentOfferPrice price;

    @Indexed(unique = true)
    private String seoUrl;

    private String deposit;

    private LinkedHashMap<String, RentOfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

    private LinkedHashMap<String, RentOfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    private LinkedHashMap<String, RentOfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    private LinkedHashMap<String, RentOfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();

    private RentOfferStatistic statistic;

    private RentOfferModerationReport lastOfferModerationReport;

    private RentOfferSettings settings;

    private List<RentOfferCalendar> rentOfferCalendars;

    public RentOfferStatistic getStatistic() {
        if (statistic == null) {
            statistic = new RentOfferStatistic();
        }
        return statistic;
    }


    public List<RentOfferCalendar> getRentOfferCalendars() {
        if (rentOfferCalendars == null) {
            rentOfferCalendars = new ArrayList<>();
        }
        return rentOfferCalendars;
    }

    public void incrementView(boolean incrementOfferViews, boolean incrementOfferPhoneViews) {
        if (incrementOfferViews)
            getStatistic().incrementTodayViewStatistic(LocalDate.from(createdDate));
        if (incrementOfferPhoneViews)
            getStatistic().incrementTodayViewPhoneStatistic(LocalDate.from(createdDate));
    }
}
