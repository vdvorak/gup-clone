package ua.com.gup.rent.service.dto.category.tree;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ua.com.gup.rent.service.dto.category.attribute.RentOfferCategoryAttributeDTO;

import java.io.Serializable;
import java.util.*;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@Slf4j
public class RentOfferCategoryTreeDTO implements Serializable {
    private int code;
    private boolean active;
    private String key;
    private String color;
    private Integer order;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> seoTitle = new HashMap<>();
    private Map<String, String> description = new HashMap<>();
    private Set<RentOfferCategoryAttributeDTO> attrs;
    private SortedSet<RentOfferCategoryTreeDTO> children;
    private boolean privateAttr;

    public RentOfferCategoryTreeDTO(String lang) {
        attrs = new  TreeSet<>(getCategoryAttributeDTOComparator(lang));
        children = new TreeSet<>(getCategoryTreeDTOComparator(lang));
    }

    public static Comparator<RentOfferCategoryTreeDTO> getCategoryTreeDTOComparator(String lang) {
        return Comparator.comparing(RentOfferCategoryTreeDTO::getOrder);
    }

    private Comparator<RentOfferCategoryAttributeDTO> getCategoryAttributeDTOComparator(String lang) {
        return Comparator.comparing(RentOfferCategoryAttributeDTO::getCategory_sort);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentOfferCategoryTreeDTO)) return false;
        RentOfferCategoryTreeDTO that = (RentOfferCategoryTreeDTO) o;
        return getCode() == that.getCode() &&
                Objects.equals(getKey(), that.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getKey());
    }

    /*public int getCode() {

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

    public Set<RentOfferCategoryAttributeDTO> getAttrs() {
        return attrs;
    }

    public void setAttrs(Set<RentOfferCategoryAttributeDTO> attrs) {

        this.attrs = attrs;
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
    }*/
}
