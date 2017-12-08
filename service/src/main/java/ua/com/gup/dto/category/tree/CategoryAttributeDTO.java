package ua.com.gup.dto.category.tree;


import ua.com.gup.common.model.enumeration.CommonCategoryAttributeType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CategoryAttributeDTO implements Serializable {
    private int code;
    private boolean active;
    private String key;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> unit = new HashMap<>();
    private Integer category_sort;
    private CommonCategoryAttributeType type;
    private CategoryAttributeValidatorDTO validator;
    private Set<CategoryAttributeValueDTO> values;
    private boolean privateAttr;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public Integer getCategory_sort() {
        return category_sort;
    }

    public void setCategory_sort(Integer category_sort) {
        this.category_sort = category_sort;
    }

    public CommonCategoryAttributeType getType() {
        return type;
    }

    public void setType(CommonCategoryAttributeType type) {
        this.type = type;
    }

    public CategoryAttributeValidatorDTO getValidator() {
        return validator;
    }

    public void setValidator(CategoryAttributeValidatorDTO validator) {
        this.validator = validator;
    }

    public Set<CategoryAttributeValueDTO> getValues() {
        return values;
    }

    public void setValues(Set<CategoryAttributeValueDTO> values) {
        this.values = values;
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
        if (o == null || getClass() != o.getClass()) return false;

        CategoryAttributeDTO that = (CategoryAttributeDTO) o;

        return code == that.code;
    }

    @Override
    public int hashCode() {
        return code;
    }
}
