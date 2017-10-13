package ua.com.gup.mongo.model.category.attribute.validator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class CategoryAttributeValidator {

    private boolean required;

    private Set<Integer> except = new HashSet<>();

    private BigDecimal min;

    private BigDecimal max;

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Set<Integer> getExcept() {
        return except;
    }

    public void setExcept(Set<Integer> except) {
        this.except = except;
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
