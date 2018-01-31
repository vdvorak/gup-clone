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
import ua.com.gup.rent.model.rent.RentOfferSettings;
import ua.com.gup.rent.model.rent.price.RentOfferPrice;
import ua.com.gup.rent.model.rent.statistic.RentOfferStatistic;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Document(collection = ObjectType.RENT_OFFER, language = "russian")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(of = {"id"})
public class RentOffer extends CommonRentOffer {

    public static final String CLASS_NAME = "ua.com.gup.rent.model.mongo.rent.RentOffer";

    @CreatedBy
    private GupLoggedUser user;
    private GupLoggedUser lastModifiedUser;
    @Indexed(unique = true)
    private String seoUrl;
    private RentOfferPrice price;
    private String deposit;
    private RentOfferStatistic statistic;
    private RentOfferSettings settings;
    private RentOfferCalendar rentOfferCalendar;
    private Integer rentObjectsCount;

    public RentOffer() {
        rentObjectsCount = 1;
    }

    @Override
    @CreatedDate
    public ZonedDateTime getCreatedDate() {
        return super.getCreatedDate();
    }

    @Override
    @LastModifiedDate
    public ZonedDateTime getLastModifiedDate() {
        return super.getLastModifiedDate();
    }

    public RentOfferStatistic getStatistic() {
        if (statistic == null) {
            statistic = new RentOfferStatistic();
        }
        return statistic;
    }

    public void incrementView(boolean incrementOfferViews, boolean incrementOfferPhoneViews) {
        if (incrementOfferViews)
            getStatistic().incrementTodayViewStatistic(LocalDate.from(createdDate));
        if (incrementOfferPhoneViews)
            getStatistic().incrementTodayViewPhoneStatistic(LocalDate.from(createdDate));
    }
}
