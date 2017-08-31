package ua.com.gup.domain.offer.land;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class Lands implements Serializable
{
    @ApiModelProperty(position = 0)
    private MapData mapData;

    @ApiModelProperty(position = 10)
    private Geometry geometry;


    public Lands(MapData mapData, Geometry geometry) {
        this.mapData = mapData;
        this.geometry = geometry;
    }
    public Lands() {}

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
        return "Lands [mapData = "+mapData+", geometry = "+geometry+"]";
    }
}
