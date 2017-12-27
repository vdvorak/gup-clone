package ua.com.gup.common.model.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Numeric attribute filter model")
public class NumericAttributeFilter {

    @ApiModelProperty("Numeric attribute key (code)")
    private String key;

    @ApiModelProperty("Numeric value from inclusive")
    private Double from;
    
    @ApiModelProperty("Numeric value to inclusive")
    private Double to;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getFrom() {
        return from;
    }

    public void setFrom(Double from) {
        this.from = from;
    }

    public Double getTo() {
        return to;
    }

    public void setTo(Double to) {
        this.to = to;
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
