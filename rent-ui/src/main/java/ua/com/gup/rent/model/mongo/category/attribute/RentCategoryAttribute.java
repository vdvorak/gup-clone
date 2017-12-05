package ua.com.gup.rent.model.mongo.category.attribute;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.rent.model.rent.category.RentCategoriesSort;
import ua.com.gup.rent.model.rent.category.attribute.RentCategoryAttributeType;
import ua.com.gup.rent.model.rent.category.attribute.RentCategoryAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.validator.RentCategoryAttributeValidator;

import java.io.Serializable;
import java.util.*;

@Document(collection = RentCategoryAttribute.COLLECTION_NAME)
public class RentCategoryAttribute implements Serializable {

    public static final String COLLECTION_NAME = "rent.category_attribute";

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private int code;

    private boolean active;

    private String key;

    private Map<String, String> title = new HashMap<>();

    private Map<String, String> unit = new HashMap<>();

    private Set<Integer> categories = new HashSet<>();

    private LinkedHashSet<RentCategoriesSort> categories_sort = new LinkedHashSet<>();

    private RentCategoryAttributeType type;

    private RentCategoryAttributeValidator validator;

    private LinkedHashSet<RentCategoryAttributeValue> values = new LinkedHashSet<>();

    private boolean privateAttr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Set<Integer> getCategories() {
        return categories;
    }

    public void setCategories(Set<Integer> categories) {
        this.categories = categories;
    }

    public LinkedHashSet<RentCategoriesSort> getCategoriesSort() {
        return categories_sort;
    }

    public void setCategoriesSort(LinkedHashSet<RentCategoriesSort> rentCategoriesSort) {
        this.categories_sort = rentCategoriesSort;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentCategoryAttribute that = (RentCategoryAttribute) o;

        if (code != that.code) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + code;
        return result;
    }
}