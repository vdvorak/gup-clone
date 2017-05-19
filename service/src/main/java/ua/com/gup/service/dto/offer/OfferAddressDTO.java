package ua.com.gup.service.dto.offer;


import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.offer.AddressArea;

import java.io.Serializable;

public class OfferAddressDTO implements Serializable{

    @ApiModelProperty(position = 0, example = "50")
    private Double lat;

    @ApiModelProperty(position = 10, example = "30")
    private Double lng;

    @ApiModelProperty(position = 20, value = "\"code\":\"1\",\"name\":{\"en\": \"Ukraine\"}")
    private AddressArea country;

    @ApiModelProperty(position = 30, value = "\"code\":\"2\",\"name\":{\"en\": \"Kyiv district\"}")
    private AddressArea district;

    @ApiModelProperty(position = 40, value = "\"code\":\"3\",\"name\":{\"en\": \"Kyiv\"}")
    private AddressArea city;

    @ApiModelProperty(position = 50)
    private String fullAddress;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
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

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
