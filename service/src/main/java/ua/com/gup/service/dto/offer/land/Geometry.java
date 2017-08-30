package ua.com.gup.service.dto.offer.land;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Geometry
{
    @ApiModelProperty(position = 0)
    private String type;

    @ApiModelProperty(position = 10)
    private String[][][][] coordinates;

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String[][][][] getCoordinates ()
    {
        return coordinates;
    }

    public void setCoordinates (String[][][][] coordinates)
    {
        this.coordinates = coordinates;
    }

    @Override
    public String toString()
    {
        return "Geometry [type = "+type+", coordinates = "+coordinates+"]";
    }
}
