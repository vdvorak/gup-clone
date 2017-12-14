package ua.com.gup.rent.model.rent.category.attribute;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.LinkedHashSet;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCategoryMultiAttributeValue extends RentOfferCategoryAttributeBaseValue {
    private LinkedHashSet<RentOfferCategoryAttributeValue> selected = new LinkedHashSet<>();
}
