package ua.com.gup.common.model.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

@ApiModel(description = "Attribute filter model")
public class CommonAttributeFilter {
    @ApiModelProperty("Attribute key (code)")
    private String key;

    @ApiModelProperty("Attribute values (codes)")
    private String vals;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getVals() {
        return vals;
    }

    public void setVals(String vals) {
        this.vals = vals;
    }

    public List<String> getValuesAsList() {
        return Arrays.asList(vals.split(","));
    }
}
