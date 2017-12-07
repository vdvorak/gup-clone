package ua.com.gup.rent.model.rent;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RentOfferPolygons {

    @ApiModelProperty(position = 0)
    private RentOfferMapData rentOfferMapData;

    @ApiModelProperty(position = 10)
    private RentOfferGeometry rentOfferGeometry;

    public RentOfferPolygons() {}

    public RentOfferPolygons(RentOfferMapData rentOfferMapData, RentOfferGeometry rentOfferGeometry) {
        this.rentOfferMapData = rentOfferMapData;
        this.rentOfferGeometry = rentOfferGeometry;
    }

    public RentOfferMapData getRentOfferMapData()
    {
        return rentOfferMapData;
    }

    public void setRentOfferMapData(RentOfferMapData rentOfferMapData)
    {
        this.rentOfferMapData = rentOfferMapData;
    }

    public RentOfferGeometry getRentOfferGeometry() {
        return rentOfferGeometry;
    }

    public void setRentOfferGeometry(RentOfferGeometry rentOfferGeometry) {
        this.rentOfferGeometry = rentOfferGeometry;
    }

    @Override
    public String toString()
    {
        return "RentOfferPolygons [rentOfferMapData = "+ rentOfferMapData +", rentOfferGeometry = "+ rentOfferGeometry +"]";
    }
}
