package ua.com.gup.rent.service.dto.category.attribute;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;
import ua.com.gup.common.model.category.attribute.CategoriesSort;
import ua.com.gup.common.model.category.attribute.OfferCategoryAttributeValue;
import ua.com.gup.common.model.category.attribute.validator.CategoryAttributeValidator;
import ua.com.gup.common.model.enumeration.CommonCategoryAttributeType;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
//@Data
@ToString
public class RentOfferCategoryAttributeCreateDTO {
    private boolean active;
    private String key;
    private Map<String, String> title = new HashMap<>();
    private Map<String, String> unit = new HashMap<>();
    private Set<Integer> categories = new HashSet<>();
    private LinkedHashSet<CategoriesSort> categoriesSort = new LinkedHashSet<>();
    private CommonCategoryAttributeType type;
    private CategoryAttributeValidator validator;
    private LinkedHashSet<OfferCategoryAttributeValue> values = new LinkedHashSet<>();
    private boolean privateAttr;


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

    public LinkedHashSet<CategoriesSort> getCategoriesSort() {
        return categoriesSort;
    }

    public void setCategoriesSort(LinkedHashSet<CategoriesSort> categoriesSort) {
        this.categoriesSort = categoriesSort;
    }

    public CommonCategoryAttributeType getType() {
        return type;
    }

    public void setType(CommonCategoryAttributeType type) {
        this.type = type;
    }

    public CategoryAttributeValidator getValidator() {
        return validator;
    }

    public void setValidator(CategoryAttributeValidator validator) {
        this.validator = validator;
    }

    public LinkedHashSet<OfferCategoryAttributeValue> getValues() {
        return values;
    }

    public void setValues(LinkedHashSet<OfferCategoryAttributeValue> values) {
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
        if (!(o instanceof RentOfferCategoryAttributeCreateDTO)) return false;
        RentOfferCategoryAttributeCreateDTO that = (RentOfferCategoryAttributeCreateDTO) o;
        return Objects.equals(getKey(), that.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey());
    }
}