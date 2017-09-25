package ua.com.gup.model.offer;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.stereotype.Component;

@ApiModel(description = "Address Object")
@Component
public class Address {

    @ApiModelProperty(position = 0)
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;
    @ApiModelProperty(position = 10)
    private Integer level;
    @ApiModelProperty(position = 20)
    private AddressArea country;
    @ApiModelProperty(position = 30)
    private AddressArea district;
    @ApiModelProperty(position = 40)
    private AddressArea city;
    @ApiModelProperty(position = 50)
    private String fullAddress;

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public AddressArea getCountry() {
        return country;
    }

    public void setCountry(AddressArea country) {
        this.country = country;
    }

    public AddressArea getDistrict() {
        return district;
    }

    public void setDistrict(AddressArea district) {
        this.district = district;
    }

    public AddressArea getCity() {
        return city;
    }

    public void setCity(AddressArea city) {
        this.city = city;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
