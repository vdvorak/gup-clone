package ua.com.gup.rent.service.dto.rent.offer.period;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public enum RentOfferPeriodDayTypeDTO {

    BUSINESS(0),
    WEEKEND(1),
    HOLIDAY(2),
    CUSTOM(3);

    @Getter @Setter
    private Integer type;


    RentOfferPeriodDayTypeDTO(Integer type) {
      this.type = type;
    }
}
