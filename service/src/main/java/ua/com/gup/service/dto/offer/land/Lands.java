package ua.com.gup.service.dto.offer.land;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Lands
{
    @ApiModelProperty(position = 0)
    private MapData mapData;

    @ApiModelProperty(position = 10)
    private Geometry geometry;

    public MapData getMapData ()
    {
        return mapData;
    }

    public void setMapData (MapData mapData)
    {
        this.mapData = mapData;
    }

    public Geometry getGeometry ()
    {
        return geometry;
    }

    public void setGeometry (Geometry geometry)
    {
        this.geometry = geometry;
    }

    @Override
    public String toString()
    {
        return "Lands [mapData = "+mapData+", geometry = "+geometry+"]";
    }
}
