package ua.com.gup.rent.model.mongo.rent.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.object.ObjectType;
import ua.com.gup.rent.model.rent.calendar.RentOfferCalendarDay;

import java.io.Serializable;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = ObjectType.RENT_OFFER_CALENDAR_CHILD)
public class RentOfferCalendarChild implements Serializable {
    private String rentOfferId;
    private String rentStartDate;
    private String rentEndDate;
    private Map<String, RentOfferCalendarDay> daysMap;

}
