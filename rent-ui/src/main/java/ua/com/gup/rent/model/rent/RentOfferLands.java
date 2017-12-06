package ua.com.gup.rent.model.rent;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class RentOfferLands {

   @ApiModelProperty(position = 0)
   private String cadnums;

   @ApiModelProperty(position = 10)
   private List<RentOfferPolygons> polygons;

    public RentOfferLands(String cadnums, List<RentOfferPolygons> polygons) {
        this.cadnums = cadnums;
        this.polygons = polygons;
    }

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
