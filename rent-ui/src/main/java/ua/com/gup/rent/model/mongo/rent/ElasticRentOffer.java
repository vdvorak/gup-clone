package ua.com.gup.rent.model.mongo.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendarInterval;
import ua.com.gup.rent.model.rent.price.RentOfferPrice;

@Data
@Document(collection = ObjectType.RENT_OFFER_ELASTIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of = {"id"})
public class ElasticRentOffer extends CommonRentOffer {

    private String rentOfferId;
    private RentOfferCalendarInterval rentOfferCalendarInterval;
    private RentOfferPrice price;

    public ElasticRentOffer() {
    }

    public ElasticRentOffer(RentOffer cro) {
        super(cro);
        this.rentOfferId = cro.getId();
        this.price = cro.getPrice();
        this.youtubeVideoId = null;
        this.images = null;
        this.lands = null;
        this.contactInfo = null;
        this.lastOfferModerationReport = null;
    }
}
