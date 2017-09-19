package ua.com.gup.domain.offer.attribute;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.com.gup.domain.category.attribute.CategoryAttributeType;
import ua.com.gup.domain.category.attribute.CategoryAttributeValidator;
import ua.com.gup.domain.category.attribute.CategoryAttributeValue;

import java.io.Serializable;
import java.util.*;

@Document(collection = CategoryAttribute.COLLECTION_NAME)
public class CategoryAttribute implements Serializable {

    public static final String COLLECTION_NAME = "category_attribute";

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private int code;

    private boolean active;

    private String key;

    private Map<String, String> title = new HashMap<>();

    private Map<String, String> unit = new HashMap<>();

    private Set<Integer> categories = new HashSet<>();

    private CategoryAttributeType type;

    private CategoryAttributeValidator validator;

    private LinkedHashSet<CategoryAttributeValue> values = new LinkedHashSet<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryAttribute that = (CategoryAttribute) o;

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
