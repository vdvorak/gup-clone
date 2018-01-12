package ua.com.gup.rent.model.mongo.rent.calendar;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = ObjectType.RENT_OFFER_CALENDAR)
@Setter
@Getter
@ToString
public class RentOfferCalendar implements Serializable {

    @Id
    private String Id;
    @Indexed
    private String offerId;
    private RentOfferCalendarDay[] days;
    private String startDate;
    private String endDate;
}
