package ua.com.gup.domain.offer.attribute.value;


import ua.com.gup.domain.category.attribute.CategoryAttributeType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OfferCategoryAttributeBaseValue implements Serializable {

    private int code;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> unit = new HashMap<>();
    private CategoryAttributeType type;

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

    public CategoryAttributeType getType() {
        return type;
    }

    public void setType(CategoryAttributeType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OfferCategoryAttributeBaseValue that = (OfferCategoryAttributeBaseValue) o;

        return code == that.code;
    }

    @Override
    public int hashCode() {
        return code;
    }
}
