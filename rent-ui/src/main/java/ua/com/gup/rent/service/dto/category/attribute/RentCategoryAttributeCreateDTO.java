package ua.com.gup.rent.service.dto.category.attribute;


import ua.com.gup.rent.model.rent.category.RentCategoriesSort;
import ua.com.gup.rent.model.rent.category.attribute.RentCategoryAttributeType;
import ua.com.gup.rent.model.rent.category.attribute.RentCategoryAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.validator.RentCategoryAttributeValidator;

import java.util.*;

public class RentCategoryAttributeCreateDTO {

    private boolean active;

    private String key;

    private Map<String, String> title = new HashMap<>();

    private Map<String, String> unit = new HashMap<>();

    private Set<Integer> categories = new HashSet<>();

    private LinkedHashSet<RentCategoriesSort> rentCategoriesSort = new LinkedHashSet<>();

    private RentCategoryAttributeType type;

    private RentCategoryAttributeValidator validator;

    private LinkedHashSet<RentCategoryAttributeValue> values = new LinkedHashSet<>();

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

    public LinkedHashSet<RentCategoriesSort> getRentCategoriesSort() {
        return rentCategoriesSort;
    }

    public void setRentCategoriesSort(LinkedHashSet<RentCategoriesSort> rentCategoriesSort) {
        this.rentCategoriesSort = rentCategoriesSort;
    }

    public RentCategoryAttributeType getType() {
        return type;
    }

    public void setType(RentCategoryAttributeType type) {
        this.type = type;
    }

    public RentCategoryAttributeValidator getValidator() {
        return validator;
    }

    public void setValidator(RentCategoryAttributeValidator validator) {
        this.validator = validator;
    }

    public LinkedHashSet<RentCategoryAttributeValue> getValues() {
        return values;
    }

    public void setValues(LinkedHashSet<RentCategoryAttributeValue> values) {
        this.values = values;
    }

    public boolean isPrivateAttr() {
        return privateAttr;
    }

    public void setPrivateAttr(boolean privateAttr) {
        this.privateAttr = privateAttr;
    }
}