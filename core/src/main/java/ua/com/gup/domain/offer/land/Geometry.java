package ua.com.gup.domain.offer.land;

import java.io.Serializable;

public class Geometry implements Serializable {
    private String type;

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
    public String toString() {
        return "ClassPojo [type = " + type + ", coordinates = " + coordinates + "]";
    }
}
