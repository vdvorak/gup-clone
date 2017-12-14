package ua.com.gup.rent.model.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author victor dvorak
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferSettings {
    private Integer minRentDays;
    private Integer maxRentDays;
    private String startDay;
    private String endDay;
}
