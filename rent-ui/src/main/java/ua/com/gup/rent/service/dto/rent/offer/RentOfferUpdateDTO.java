package ua.com.gup.rent.service.dto.rent.offer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * A DTO for the Offer entity.
 */
@ApiModel(description = "Rent Offer update DTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferUpdateDTO extends RentOfferCreateDTO {

}
