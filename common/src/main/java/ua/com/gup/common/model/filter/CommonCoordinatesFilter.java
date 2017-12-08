package ua.com.gup.common.model.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "Coordinates filter model")
public class CommonCoordinatesFilter {
    @ApiModelProperty("Min coordinates with ',' delimiter")
    private BigDecimal[] minYX;

    @ApiModelProperty("Max coordinates with ',' delimiter")
    private BigDecimal[] maxYX;

    public CommonCoordinatesFilter() {
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
