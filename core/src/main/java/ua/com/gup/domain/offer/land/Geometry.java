package ua.com.gup.domain.offer.land;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel
public class Geometry implements Serializable {

    @ApiModelProperty(position = 0)
    private String type;
    @ApiModelProperty(position = 10)
    private BigDecimal[][][][] coordinates;

    public Geometry(String type, BigDecimal[][][][] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public Geometry() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal[][][][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(BigDecimal[][][][] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "ClassPojo [type = " + type + ", coordinates = " + coordinates + "]";
    }
}
