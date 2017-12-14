package ua.com.gup.rent.model.rent.category.attribute;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCategoryNumericAttributeValue extends RentOfferCategoryAttributeBaseValue {
    private BigDecimal selected;
    private double selectedDouble;
}
