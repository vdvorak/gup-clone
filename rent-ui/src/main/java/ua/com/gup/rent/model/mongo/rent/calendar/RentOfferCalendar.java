package ua.com.gup.rent.model.mongo.rent.calendar;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = ObjectType.RENT_OFFER_CALENDAR)
public class RentOfferCalendar extends RentOfferCalendarInterval {

    @Id
    private String Id;
    @Indexed
    private String offerId;
    private RentOfferCalendarDay[] days;

    public RentOfferCalendar() {
        super();
    }

    public RentOfferCalendar(RentOfferCalendarInterval calendarInterval) {
        super(calendarInterval);
    }


}
