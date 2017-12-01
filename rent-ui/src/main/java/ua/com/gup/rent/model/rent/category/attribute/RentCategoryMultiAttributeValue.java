package ua.com.gup.rent.model.rent.category.attribute;


import java.util.LinkedHashSet;

public class RentCategoryMultiAttributeValue extends RentCategoryAttributeBaseValue {
    private LinkedHashSet<RentCategoryAttributeValue> selected = new LinkedHashSet<>();

    public LinkedHashSet<RentCategoryAttributeValue> getSelected() {
        return selected;
    }

    public void setSelected(LinkedHashSet<RentCategoryAttributeValue> selected) {
        this.selected = selected;
    }
}
