package ua.com.gup.service.dto;


import java.math.BigDecimal;

public class CategoryAttributeValidatorDTO {
    private boolean required;
    private BigDecimal min;
    private BigDecimal max;

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }
}
