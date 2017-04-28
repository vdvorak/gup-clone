package ua.com.gup.domain.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Numeric attribute filter model")
public class NumericAttributeFilter {

    @ApiModelProperty("Numeric attribute key (code)")
    private String key;

    @ApiModelProperty("Numeric value from inclusive")
    private Long from;
    
    @ApiModelProperty("Numeric value to inclusive")
    private Long to;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
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
