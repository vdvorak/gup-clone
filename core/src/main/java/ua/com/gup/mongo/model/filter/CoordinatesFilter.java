package ua.com.gup.mongo.model.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Coordinates filter model")
public class CoordinatesFilter {

    @ApiModelProperty("Min coordinates with ',' delimiter")
    private BigDecimal[] minYX;

    @ApiModelProperty("Max coordinates with ',' delimiter")
    private BigDecimal[] maxYX;

    public CoordinatesFilter() {
    }

    public BigDecimal[] getMinYX() {
        return minYX;
    }

    public void setMinYX(BigDecimal[] minYX) {
        this.minYX = minYX;
    }

    public BigDecimal[] getMaxYX() {
        return maxYX;
    }

    public void setMaxYX(BigDecimal[] maxYX) {
        this.maxYX = maxYX;
    }
}
