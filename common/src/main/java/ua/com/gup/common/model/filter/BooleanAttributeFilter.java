package ua.com.gup.common.model.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Boolean attribute filter model")
public class BooleanAttributeFilter {

    @ApiModelProperty("Boolean attribute key (code)")
    private String key;

    @ApiModelProperty("Boolean attribute value (true/false)")
    private Boolean val;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getVal() {
        return val;
    }

    public void setVal(Boolean val) {
        this.val = val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooleanAttributeFilter that = (BooleanAttributeFilter) o;

        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
