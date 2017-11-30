package ua.com.gup.rent.dto.category;


import java.util.*;

public class RentCategoryAttributeCreateDTO {

    private boolean active;

    private String key;

    private Map<String, String> title = new HashMap<>();

    private Map<String, String> unit = new HashMap<>();

    private Set<Integer> categories = new HashSet<>();

    private LinkedHashSet<ua.com.gup.rent.dto.category.attribute.RentCategoriesSort> rentCategoriesSort = new LinkedHashSet<>();

    private ua.com.gup.rent.dto.category.tree.RentCategoryAttributeType type;

    private ua.com.gup.rent.dto.category.attribute.validator.RentCategoryAttributeValidator validator;

    private LinkedHashSet<ua.com.gup.rent.dto.category.attribute.RentCategoryAttributeValue> values = new LinkedHashSet<>();

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

    public LinkedHashSet<ua.com.gup.rent.dto.category.attribute.RentCategoriesSort> getRentCategoriesSort() {
        return rentCategoriesSort;
    }

    public void setRentCategoriesSort(LinkedHashSet<ua.com.gup.rent.dto.category.attribute.RentCategoriesSort> rentCategoriesSort) {
        this.rentCategoriesSort = rentCategoriesSort;
    }

    public ua.com.gup.rent.dto.category.tree.RentCategoryAttributeType getType() {
        return type;
    }

    public void setType(ua.com.gup.rent.dto.category.tree.RentCategoryAttributeType type) {
        this.type = type;
    }

    public ua.com.gup.rent.dto.category.attribute.validator.RentCategoryAttributeValidator getValidator() {
        return validator;
    }

    public void setValidator(ua.com.gup.rent.dto.category.attribute.validator.RentCategoryAttributeValidator validator) {
        this.validator = validator;
    }

    public LinkedHashSet<ua.com.gup.rent.dto.category.attribute.RentCategoryAttributeValue> getValues() {
        return values;
    }

    public void setValues(LinkedHashSet<ua.com.gup.rent.dto.category.attribute.RentCategoryAttributeValue> values) {
        this.values = values;
    }

    public boolean isPrivateAttr() {
        return privateAttr;
    }

    public void setPrivateAttr(boolean privateAttr) {
        this.privateAttr = privateAttr;
    }
}
