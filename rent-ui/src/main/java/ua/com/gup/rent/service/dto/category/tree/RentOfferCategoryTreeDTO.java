package ua.com.gup.rent.service.dto.category.tree;


import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeDTO;

import java.io.Serializable;
import java.util.*;

public class RentOfferCategoryTreeDTO implements Serializable {
    private int code;
    private boolean active;
    private String key;
    private String color;
    private Integer order;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> description = new HashMap<>();
    private SortedSet<RentOfferCategoryAttributeDTO> attrs;
    private SortedSet<RentOfferCategoryTreeDTO> children;
    private boolean privateAttr;

    public RentOfferCategoryTreeDTO(String lang) {
        attrs = new TreeSet<RentOfferCategoryAttributeDTO>(getCategoryAttributeDTOComparator(lang));
        children = new TreeSet<RentOfferCategoryTreeDTO>(getCategoryTreeDTOComparator(lang));
    }

    public static Comparator<RentOfferCategoryTreeDTO> getCategoryTreeDTOComparator(String lang) {
        //return Comparator.comparing(c -> c.getTitle() == null ? "" : c.getTitle().getOrDefault(lang, ""));
        return Comparator.comparing(RentOfferCategoryTreeDTO::getOrder);
    }

    private Comparator<RentOfferCategoryAttributeDTO> getCategoryAttributeDTOComparator(String lang) {
        //return Comparator.comparing(c -> c.getTitle() == null ? "" : c.getTitle().getOrDefault(lang, ""));
        return Comparator.comparing(RentOfferCategoryAttributeDTO::getCategory_sort);
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

    public SortedSet<RentOfferCategoryTreeDTO> getChildren() {
        return children;
    }

    public void setChildren(SortedSet<RentOfferCategoryTreeDTO> children) {
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

    public SortedSet<RentOfferCategoryAttributeDTO> getAttrs() {
        return attrs;
    }

    public void setAttrs(SortedSet<RentOfferCategoryAttributeDTO> attrs) {
        this.attrs = attrs;
    }
}
