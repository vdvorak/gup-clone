package ua.com.gup.rent.model.mongo.category.attribute;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.common.model.enumeration.CommonCategoryAttributeType;
import ua.com.gup.rent.model.rent.category.RentOfferCategoriesSort;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.validator.RentOfferCategoryAttributeValidator;

import java.io.Serializable;
import java.util.*;

@Document(collection = RentOfferCategoryAttribute.COLLECTION_NAME)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentOfferCategoryAttribute implements Serializable {
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
    private LinkedHashSet<RentOfferCategoriesSort> categories_sort = new LinkedHashSet<>();
    private CommonCategoryAttributeType type;
    private RentOfferCategoryAttributeValidator validator;
    private LinkedHashSet<RentOfferCategoryAttributeValue> values = new LinkedHashSet<>();
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

    public LinkedHashSet<RentOfferCategoriesSort> getCategories_sort() {
        return categories_sort;
    }

    public void setCategories_sort(LinkedHashSet<RentOfferCategoriesSort> categories_sort) {
        this.categories_sort = categories_sort;
    }

    public CommonCategoryAttributeType getType() {
        return type;
    }

    public void setType(CommonCategoryAttributeType type) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentOfferCategoryAttribute)) return false;
        RentOfferCategoryAttribute that = (RentOfferCategoryAttribute) o;
        return getCode() == that.getCode() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getKey(), that.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getKey());
    }
}