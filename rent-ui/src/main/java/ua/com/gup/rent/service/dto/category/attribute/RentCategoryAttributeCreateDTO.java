package ua.com.gup.rent.service.dto.category.attribute;


import ua.com.gup.rent.model.enumeration.RentOfferCategoryAttributeType;
import ua.com.gup.rent.model.rent.category.RentOfferCategoriesSort;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.validator.RentOfferCategoryAttributeValidator;

import java.util.*;

public class RentCategoryAttributeCreateDTO {

    private boolean active;

    private String key;

    private Map<String, String> title = new HashMap<>();

    private Map<String, String> unit = new HashMap<>();

    private Set<Integer> categories = new HashSet<>();

    private LinkedHashSet<RentOfferCategoriesSort> rentOfferCategoriesSort = new LinkedHashSet<>();

    private RentOfferCategoryAttributeType type;

    private RentOfferCategoryAttributeValidator validator;

    private LinkedHashSet<RentOfferCategoryAttributeValue> values = new LinkedHashSet<>();

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

    public LinkedHashSet<RentOfferCategoriesSort> getRentOfferCategoriesSort() {
        return rentOfferCategoriesSort;
    }

    public void setRentOfferCategoriesSort(LinkedHashSet<RentOfferCategoriesSort> rentOfferCategoriesSort) {
        this.rentOfferCategoriesSort = rentOfferCategoriesSort;
    }

    public RentOfferCategoryAttributeType getType() {
        return type;
    }

    public void setType(RentOfferCategoryAttributeType type) {
        this.type = type;
    }

    public RentOfferCategoryAttributeValidator getValidator() {
        return validator;
    }

    public void setValidator(RentOfferCategoryAttributeValidator validator) {
        this.validator = validator;
    }

    public LinkedHashSet<RentOfferCategoryAttributeValue> getValues() {
        return values;
    }

    public void setValues(LinkedHashSet<RentOfferCategoryAttributeValue> values) {
        this.values = values;
    }

    public boolean isPrivateAttr() {
        return privateAttr;
    }

    public void setPrivateAttr(boolean privateAttr) {
        this.privateAttr = privateAttr;
    }
}