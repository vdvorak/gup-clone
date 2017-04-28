package ua.com.gup.domain.category;

import java.util.HashSet;
import java.util.Set;

public class CategoryAttributeValidator {

    private boolean required;

    private Set<Integer> except = new HashSet<>();

    private Integer min;

    private  Integer max;

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

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
