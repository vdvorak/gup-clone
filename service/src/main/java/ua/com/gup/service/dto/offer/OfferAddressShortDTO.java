package ua.com.gup.service.dto.offer;


import io.swagger.annotations.ApiModelProperty;

public class OfferAddressShortDTO {

    @ApiModelProperty(position = 0, example = "50")
    private Double lat;

    @ApiModelProperty(position = 10, example = "30")
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
