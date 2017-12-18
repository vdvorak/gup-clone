package ua.com.gup.rent.service.dto.category.attribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCategoryAttributeUpdateDTO extends RentOfferCategoryAttributeCreateDTO {
    private String id;
    private int code;
}
