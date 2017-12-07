package ua.com.gup.rent.model.rent.category.attribute;


import java.util.LinkedHashSet;

public class RentOfferCategoryMultiAttributeValue extends RentOfferCategoryAttributeBaseValue {
    private LinkedHashSet<RentOfferCategoryAttributeValue> selected = new LinkedHashSet<>();

    public LinkedHashSet<RentOfferCategoryAttributeValue> getSelected() {
        return selected;
    }

    public void setSelected(LinkedHashSet<RentOfferCategoryAttributeValue> selected) {
        this.selected = selected;
    }
}
