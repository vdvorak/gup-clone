package ua.com.gup.rent.service.dto.rent.offer.statistic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferStatisticByDateDTO extends RentOfferStatisticDTO {

    private String date;

    public RentOfferStatisticByDateDTO() {
        super();
    }
}
