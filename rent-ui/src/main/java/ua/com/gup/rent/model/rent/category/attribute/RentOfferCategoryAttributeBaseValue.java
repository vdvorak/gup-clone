package ua.com.gup.rent.model.rent.category.attribute;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ua.com.gup.common.model.enumeration.CommonCategoryAttributeType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(of={"code,type"})
public class RentOfferCategoryAttributeBaseValue implements Serializable {
    @Setter @Getter
    private int code;
    @Setter @Getter
    private Map<String, String> title = new HashMap<>();
    @Setter @Getter
    private Map<String, String> unit = new HashMap<>();
    @Setter @Getter
    private CommonCategoryAttributeType type;
    @Setter @Getter
    private boolean privateAttr;

    public RentOfferCategoryAttributeBaseValue(){
        privateAttr = false;
    }
}
