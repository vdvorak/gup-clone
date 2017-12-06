package ua.com.gup.rent.model.rent.category.attribute;


import java.math.BigDecimal;

public class RentOfferCategoryNumericAttributeValue extends RentOfferCategoryAttributeBaseValue {

    private BigDecimal selected;

    private double selectedDouble;

    public BigDecimal getSelected() {
        return selected;
    }

    public void setSelected(BigDecimal selected) {
        this.selected = selected;
    }

    public double getSelectedDouble() {
        return selectedDouble;
    }

    public void setSelectedDouble(double selectedDouble) {
        this.selectedDouble = selectedDouble;
    }

}
