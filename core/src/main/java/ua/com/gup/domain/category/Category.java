package ua.com.gup.domain.category;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

@Document(collection = Category.COLLECTION_NAME)
public class Category {

    public static final String COLLECTION_NAME = "category";

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private int parent;

    private int code;

    private boolean active;

    private String key;

    private Map<String, String> label = new HashMap<>();

    private LinkedHashSet<String> attributes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
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

    public LinkedHashSet<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(LinkedHashSet<String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return code == category.code;
    }

    @Override
    public int hashCode() {
        return code;
    }
}
