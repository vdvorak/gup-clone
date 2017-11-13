package ua.com.gup.mongo.model.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Coordinates filter model")
public class CoordinatesFilter {

    @ApiModelProperty("Min coordinates with ',' delimiter")
    private BigDecimal[] minXY;

    @ApiModelProperty("Max coordinates with ',' delimiter")
    private BigDecimal[] maxXY;

    public CoordinatesFilter() {
    }

    public BigDecimal[] getMinXY() {
        return minXY;
    }

    public void setMinXY(BigDecimal[] minXY) {
        this.minXY = minXY;
    }

    public BigDecimal[] getMaxXY() {
        return maxXY;
    }

    public void setMaxXY(BigDecimal[] maxXY) {
        this.maxXY = maxXY;
    }
}
