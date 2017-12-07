package ua.com.gup.rent.model.rent;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel
public class RentOfferGeometry {

    @ApiModelProperty(position = 0)
    private String type;
    @ApiModelProperty(position = 10)
    private List<List<List<List<BigDecimal>>>> coordinates;

    public RentOfferGeometry() {}

    public RentOfferGeometry(String type, List<List<List<List<BigDecimal>>>> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<List<List<List<BigDecimal>>>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<List<List<List<BigDecimal>>>> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "ClassPojo [type = " + type + ", coordinates = " + coordinates + "]";
    }
}