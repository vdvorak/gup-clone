package ua.com.gup.rent.service.dto.category.attribute;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonCategoryAttributeType;
import ua.com.gup.rent.model.rent.category.RentOfferCategoriesSort;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.validator.RentOfferCategoryAttributeValidator;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCategoryAttributeCreateDTO {
    private boolean active;
    private String key;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> unit = new HashMap<>();
    private Set<Integer> categories = new HashSet<>();
    private LinkedHashSet<RentOfferCategoriesSort> categoriesSort = new LinkedHashSet<>();
    private CommonCategoryAttributeType type;
    private RentOfferCategoryAttributeValidator validator;
    private LinkedHashSet<RentOfferCategoryAttributeValue> values = new LinkedHashSet<>();
    private boolean privateAttr;
}