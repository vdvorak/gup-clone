package ua.com.gup.rent.service.dto.rent.offer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.rent.model.rent.RentOfferPolygons;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class RentOfferLandsDTO implements Serializable {

    @ApiModelProperty(position = 0)
    private String cadnums;

    @ApiModelProperty(position = 10)
    private List<RentOfferPolygons> polygons;

    public RentOfferLandsDTO() {}

    public String getCadnums() {
        return cadnums;
    }

    public void setCadnums(String cadnums) {
        this.cadnums = cadnums;
    }

    public List<RentOfferPolygons> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<RentOfferPolygons> polygons) {
        this.polygons = polygons;
    }
}
