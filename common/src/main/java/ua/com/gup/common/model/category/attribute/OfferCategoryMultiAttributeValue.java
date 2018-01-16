package ua.com.gup.common.model.category.attribute;


import java.util.LinkedHashSet;

public class OfferCategoryMultiAttributeValue extends OfferCategoryAttributeBaseValue {
    private LinkedHashSet<OfferCategoryAttributeValue> selected = new LinkedHashSet<>();

    public LinkedHashSet<OfferCategoryAttributeValue> getSelected() {
        return selected;
    }

    public void setSelected(LinkedHashSet<OfferCategoryAttributeValue> selected) {
        this.selected = selected;
    }
}
