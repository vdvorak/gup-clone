package ua.com.gup.service.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Numeric attribute filter model")
public class NumericAttributeFilter {

    @ApiModelProperty("Numeric attribute key (code)")
    private String key;

    @ApiModelProperty("Numeric attribute value range")
    private Range<BigDecimal> val;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Range<BigDecimal> getVal() {
        return val;
    }

    public void setVal(Range<BigDecimal> val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NumericAttributeFilter that = (NumericAttributeFilter) o;

        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
