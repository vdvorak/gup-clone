package ua.com.gup.rent.service.dto.category.attribute;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.com.gup.common.model.enumeration.CommonCategoryAttributeType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(of={"code"})
public class RentOfferCategoryAttributeDTO implements Serializable {
    private int code;
    private boolean active;
    private String key;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> unit = new HashMap<>();
    private Integer category_sort;
    private CommonCategoryAttributeType type;
    private RentOfferCategoryAttributeValidatorDTO validator;
    private Set<RentOfferCategoryAttributeValueDTO> values;
    private boolean privateAttr;
}
