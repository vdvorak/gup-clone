package ua.com.gup.rent.model.rent.category.attribute;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import ua.com.gup.common.model.enumeration.CommonCategoryAttributeType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//@EqualsAndHashCode(of={"code,type"})
public class RentOfferCategoryAttributeBaseValue implements Serializable {
  //  @Setter @Getter
    private int code;
  //  @Setter @Getter
    private Map<String, String> title = new HashMap<>();
  //  @Setter @Getter
    private Map<String, String> unit = new HashMap<>();
  //  @Setter @Getter
    private CommonCategoryAttributeType type;
  //  @Setter @Getter
    private boolean privateAttr;

    public RentOfferCategoryAttributeBaseValue(){
        privateAttr = false;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Map<String, String> getTitle() {
        return title;
    }

    public void setTitle(Map<String, String> title) {
        this.title = title;
    }

    public Map<String, String> getUnit() {
        return unit;
    }

    public void setUnit(Map<String, String> unit) {
        this.unit = unit;
    }

    public CommonCategoryAttributeType getType() {
        return type;
    }

    public void setType(CommonCategoryAttributeType type) {
        this.type = type;
    }

    public boolean isPrivateAttr() {
        return privateAttr;
    }

    public void setPrivateAttr(boolean privateAttr) {
        this.privateAttr = privateAttr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentOfferCategoryAttributeBaseValue)) return false;
        RentOfferCategoryAttributeBaseValue that = (RentOfferCategoryAttributeBaseValue) o;
        return getCode() == that.getCode();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
    }
}
