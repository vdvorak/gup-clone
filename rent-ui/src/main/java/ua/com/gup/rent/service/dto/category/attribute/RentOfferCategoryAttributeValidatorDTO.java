package ua.com.gup.rent.service.dto.category.attribute;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
///@Data
@ToString
public class RentOfferCategoryAttributeValidatorDTO {
    private boolean required;
    private BigDecimal min;
    private BigDecimal max;

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RentOfferCategoryAttributeValidatorDTO)) return false;
        RentOfferCategoryAttributeValidatorDTO that = (RentOfferCategoryAttributeValidatorDTO) o;
        return required == that.required &&
                Objects.equals(getMin(), that.getMin()) &&
                Objects.equals(getMax(), that.getMax());
    }

    @Override
    public int hashCode() {
        return Objects.hash(required, getMin(), getMax());
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
