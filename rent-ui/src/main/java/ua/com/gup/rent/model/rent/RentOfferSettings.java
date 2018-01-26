package ua.com.gup.rent.model.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author victor dvorak
 **/

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class RentOfferSettings {

    private Integer minRentDays;
    private Integer maxRentDays;
    private Boolean firstDayPayable;
    private String startDay;
    private String endDay;

    public RentOfferSettings() {
    }
}
