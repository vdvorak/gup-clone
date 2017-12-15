package ua.com.gup.rent.model.rent.category.attribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCategorySingleAttributeValue extends RentOfferCategoryAttributeBaseValue {
    private RentOfferToOfferCategoryAttributeValue selected;
}
