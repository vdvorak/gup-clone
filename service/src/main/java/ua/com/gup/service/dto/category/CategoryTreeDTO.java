package ua.com.gup.service.dto.category;


import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class CategoryTreeDTO implements Serializable {
    private int code;
    private boolean active;
    private String key;
    private Map<String, String> title = new HashMap<>();
    private LinkedHashSet<CategoryAttributeDTO> attrs = new LinkedHashSet<>();
    private LinkedHashSet<CategoryTreeDTO> children = new LinkedHashSet<>();

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

    public LinkedHashSet<CategoryAttributeDTO> getAttrs() {
        return attrs;
    }

    public void setAttrs(LinkedHashSet<CategoryAttributeDTO> attrs) {
        this.attrs = attrs;
    }

    public LinkedHashSet<CategoryTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(LinkedHashSet<CategoryTreeDTO> children) {
        this.children = children;
    }

}
