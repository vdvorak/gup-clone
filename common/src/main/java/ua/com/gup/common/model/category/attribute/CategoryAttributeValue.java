package ua.com.gup.common.model.category.attribute;

import java.util.Map;
import java.util.Set;

public class CategoryAttributeValue {

    private String key;

    private Map<String, String> title;

    private Set<Integer> exceptCategory;

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

    public Set<Integer> getExceptCategory() {
        return exceptCategory;
    }

    public void setExceptCategory(Set<Integer> exceptCategory) {
        this.exceptCategory = exceptCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryAttributeValue that = (CategoryAttributeValue) o;

        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
