package ua.com.gup.rent.service.dto.rent.offer.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferToOfferCategoryAttributeValue;

import java.util.Map;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferPriceShortDTO extends RentOfferPriceDTO {
    private Map<String, String> title;
    private RentOfferToOfferCategoryAttributeValue selected;
}
