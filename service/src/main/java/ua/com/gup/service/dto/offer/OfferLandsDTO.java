package ua.com.gup.service.dto.offer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.offer.land.Geometry;
import ua.com.gup.domain.offer.land.MapData;

import java.io.Serializable;

@ApiModel
public class OfferLandsDTO implements Serializable {
    @ApiModelProperty(position = 0)
    private MapData mapData;

    @ApiModelProperty(position = 10)
    private Geometry geometry;

    public OfferLandsDTO(MapData mapData, Geometry geometry) {
        this.mapData = mapData;
        this.geometry = geometry;
    }

    public OfferLandsDTO() {

    }

    public MapData getMapData() {
        return mapData;
    }

    public void setMapData(MapData mapData) {
        this.mapData = mapData;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
