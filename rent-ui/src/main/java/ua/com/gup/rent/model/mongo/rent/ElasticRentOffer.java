package ua.com.gup.rent.model.mongo.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.mongo.CommonRentOffer;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.model.mongo.rent.calendar.RentOfferCalendar;

@Data
@Document(collection = ObjectType.RENT_OFFER_ELASTIC)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of = {"id"})
public class ElasticRentOffer extends CommonRentOffer {

    private String rentOfferId;
    private RentOfferCalendar rentOfferCalendar;

    public ElasticRentOffer() {
    }

    public ElasticRentOffer(RentOffer cro) {
        super(cro);
        this.rentOfferId = cro.getId();
        this.images = null;
    }
}
