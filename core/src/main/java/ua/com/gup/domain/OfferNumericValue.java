package ua.com.gup.domain;

import java.math.BigDecimal;

public class OfferNumericValue {

    private BigDecimal viewValue;
    private double value;

    public BigDecimal getViewValue() {
        return viewValue;
    }

    public void setViewValue(BigDecimal viewValue) {
        this.viewValue = viewValue;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
