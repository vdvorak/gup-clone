package ua.com.gup.domain.offer.attribute.value;


import java.math.BigDecimal;

public class OfferCategoryNumericAttributeValue extends OfferCategoryAttributeBaseValue {
    private BigDecimal selected;
    private double selectedDouble;
    private double value;

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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
