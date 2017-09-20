package ua.com.gup.dto.category;


import ua.com.gup.domain.category.attribute.CategoryAttributeType;
import ua.com.gup.domain.category.attribute.CategoryAttributeValidator;
import ua.com.gup.domain.category.attribute.CategoryAttributeValue;

import java.util.*;

public class CategoryAttributeCreateDTO {

    private boolean active;

    private String key;

    private Map<String, String> title = new HashMap<>();

    private Map<String, String> unit = new HashMap<>();

    private Set<Integer> categories = new HashSet<>();

    private CategoryAttributeType type;

    private CategoryAttributeValidator validator;

    private LinkedHashSet<CategoryAttributeValue> values = new LinkedHashSet<>();

    private boolean privateAttr;

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

    public Set<Integer> getCategories() {
        return categories;
    }

    public void setCategories(Set<Integer> categories) {
        this.categories = categories;
    }

    public CategoryAttributeType getType() {
        return type;
    }

    public void setType(CategoryAttributeType type) {
        this.type = type;
    }

    public CategoryAttributeValidator getValidator() {
        return validator;
    }

    public void setValidator(CategoryAttributeValidator validator) {
        this.validator = validator;
    }

    public LinkedHashSet<CategoryAttributeValue> getValues() {
        return values;
    }

    public void setValues(LinkedHashSet<CategoryAttributeValue> values) {
        this.values = values;
    }

    public boolean isPrivateAttr() {
        return privateAttr;
    }

    public void setPrivateAttr(boolean privateAttr) {
        this.privateAttr = privateAttr;
    }
}
