package ua.com.gup.common.model.mongo.offer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Polygons{

    @ApiModelProperty(position = 0)
    private MapData mapData;

    @ApiModelProperty(position = 10)
    private Geometry geometry;

    public Polygons() {}

    public Polygons(MapData mapData, Geometry geometry) {
        this.mapData = mapData;
        this.geometry = geometry;
    }

    public MapData getMapData ()
    {
        return mapData;
    }

    public void setMapData (MapData mapData)
    {
        this.mapData = mapData;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString()
    {
        return "Polygons [mapData = "+mapData+", geometry = "+geometry+"]";
    }
}
