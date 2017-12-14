package ua.com.gup.rent.model.rent.category.attribute.validator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCategoryAttributeValidator {
    private boolean required;
    private Set<Integer> except = new HashSet<>();
    private BigDecimal min;
    private BigDecimal max;
}
