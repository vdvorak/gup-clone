package ua.com.gup.dto.offer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.common.model.mongo.offer.Polygons;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class OfferLandsDTO implements Serializable {

    @ApiModelProperty(position = 0)
    private String cadnums;

    @ApiModelProperty(position = 10)
    private List<Polygons> polygons;

    public OfferLandsDTO() {}

    public OfferLandsDTO(String cadnums, List<Polygons> polygons) {
        this.cadnums = cadnums;
        this.polygons = polygons;
    }

    public String getCadnums() {
        return cadnums;
    }

    public void setCadnums(String cadnums) {
        this.cadnums = cadnums;
    }

    public List<Polygons> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<Polygons> polygons) {
        this.polygons = polygons;
    }
}
