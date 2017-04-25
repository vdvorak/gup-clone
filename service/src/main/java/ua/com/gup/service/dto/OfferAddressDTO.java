package ua.com.gup.service.dto;


import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.AddressArea;

public class OfferAddressDTO {

    @ApiModelProperty(position = 0, example = "50")
    private double lat;

    @ApiModelProperty(position = 10, example = "30")
    private double lng;

    @ApiModelProperty(position = 20, value = "\"code\":\"1\",\"name\":{\"en\": \"Ukraine\"}")
    private AddressArea country;

    @ApiModelProperty(position = 30, value = "\"code\":\"2\",\"name\":{\"en\": \"Kyiv district\"}")
    private AddressArea district;

    @ApiModelProperty(position = 40, value = "\"code\":\"3\",\"name\":{\"en\": \"Kyiv\"}")
    private AddressArea city;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
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
}
