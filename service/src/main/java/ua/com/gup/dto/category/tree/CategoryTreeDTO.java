package ua.com.gup.dto.category.tree;


import java.io.Serializable;
import java.util.*;

public class CategoryTreeDTO implements Serializable {
    private int code;
    private boolean active;
    private String key;
    private String color;
    private Integer order;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> description = new HashMap<>();
    private SortedSet<CategoryAttributeDTO> attrs;
    private SortedSet<CategoryTreeDTO> children;
    private boolean privateAttr;

    public CategoryTreeDTO(String lang) {
        attrs = new TreeSet<CategoryAttributeDTO>(getCategoryAttributeDTOComparator(lang));
        children = new TreeSet<CategoryTreeDTO>(getCategoryTreeDTOComparator(lang));
    }

    public static Comparator<CategoryTreeDTO> getCategoryTreeDTOComparator(String lang) {
        //return Comparator.comparing(c -> c.getTitle() == null ? "" : c.getTitle().getOrDefault(lang, ""));
        return Comparator.comparing(c -> c.getOrder() == null ? -1 : c.getOrder());
    }

    private Comparator<CategoryAttributeDTO> getCategoryAttributeDTOComparator(String lang) {
        //return Comparator.comparing(c -> c.getTitle() == null ? "" : c.getTitle().getOrDefault(lang, ""));
        return Comparator.comparing(c -> c.getCategory_sort() == null ? -1  : c.getCategory_sort());
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

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(Map<String, String> description) {
        this.description = description;
    }

    public SortedSet<CategoryTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(SortedSet<CategoryTreeDTO> children) {
        this.children = children;
    }

    public boolean isPrivateAttr() {
        return privateAttr;
    }

    public void setPrivateAttr(boolean privateAttr) {
        this.privateAttr = privateAttr;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public SortedSet<CategoryAttributeDTO> getAttrs() {
        return attrs;
    }

    public void setAttrs(SortedSet<CategoryAttributeDTO> attrs) {
        this.attrs = attrs;
    }
}
