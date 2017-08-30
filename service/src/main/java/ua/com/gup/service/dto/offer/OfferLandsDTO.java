package ua.com.gup.service.dto.offer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.geo.GeoJsonMultiPolygon;
import ua.com.gup.domain.offer.land.MapData;

@ApiModel
public class OfferLandsDTO {
    @ApiModelProperty(position = 0)
    private MapData mapData;

    @ApiModelProperty(position = 10)
    private GeoJsonMultiPolygon geometry;

    public MapData getMapData() {
        return mapData;
    }

    public void setMapData(MapData mapData) {
        this.mapData = mapData;
    }

    public GeoJsonMultiPolygon getGeometry() {
        return geometry;
    }

    public void setGeometry(GeoJsonMultiPolygon geometry) {
        this.geometry = geometry;
    }
}
