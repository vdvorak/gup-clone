package ua.com.gup.rent.service.dto.rent.offer.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferViewCoordinatesDTO {
    private String offerSeoUrl;
    private BigDecimal[] coordinates;

}
