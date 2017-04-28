package ua.com.gup.domain.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = Category.COLLECTION_NAME)
public class CategoryAttribute {

    public static final String COLLECTION_NAME = "category_attribute";

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private int code;

    private boolean active;

    private String key;

    private Map<String, String> label = new HashMap<>();

    private CategoryAttributeType type;

    private Map<String, Map<String, String>> values = new HashMap<>();

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

    public Map<String, String> getLabel() {
        return label;
    }

    public void setLabel(Map<String, String> label) {
        this.label = label;
    }

    public CategoryAttributeType getType() {
        return type;
    }

    public void setType(CategoryAttributeType type) {
        this.type = type;
    }

    public Map<String, Map<String, String>> getValues() {
        return values;
    }

    public void setValues(Map<String, Map<String, String>> values) {
        this.values = values;
    }
}
