package ua.com.gup.mongo.model.category.attribute;

import java.io.Serializable;
import java.util.Map;

public class OfferCategoryAttributeValue implements Serializable {

    private String key;

    private Map<String, String> title;

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
}
