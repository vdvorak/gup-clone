package ua.com.gup.mongo.model.category.attribute;


public class OfferCategorySingleAttributeValue extends OfferCategoryAttributeBaseValue {

    private OfferCategoryAttributeValue selected;

    public OfferCategoryAttributeValue getSelected() {
        return selected;
    }

    public void setSelected(OfferCategoryAttributeValue selected) {
        this.selected = selected;
    }
}
