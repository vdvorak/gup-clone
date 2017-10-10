package ua.com.gup.model.offer;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@ApiModel(description = "Address Object")
@Component
public class Address {

    @ApiModelProperty(position = 10)
    private BigDecimal lat;

    @ApiModelProperty(position = 15)
    private BigDecimal lng;

    @ApiModelProperty(position = 20)
    private Integer level;

    @ApiModelProperty(position = 30)
    private AddressArea country;

    @ApiModelProperty(position = 40)
    private AddressArea region;

    @ApiModelProperty(position = 50)
    private AddressArea district;

    @ApiModelProperty(position = 60)
    private AddressArea council;

    @ApiModelProperty(position = 70)
    private AddressArea city;

    @ApiModelProperty(position = 80)
    private String fullAddress;

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public AddressArea getCountry() {
        return country;
    }

    public void setCountry(AddressArea country) {
        this.country = country;
    }

    public AddressArea getRegion() {
        return region;
    }

    public void setRegion(AddressArea region) {
        this.region = region;
    }

    public AddressArea getDistrict() {
        return district;
    }

    public void setDistrict(AddressArea district) {
        this.district = district;
    }

    public AddressArea getCouncil() {
        return council;
    }

    public void setCouncil(AddressArea council) {
        this.council = council;
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

    @Override
    public String toString() {
        return "Address{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", level=" + level +
                ", country=" + country +
                ", region=" + region +
                ", district=" + district +
                ", council=" + council +
                ", city=" + city +
                ", fullAddress='" + fullAddress + '\'' +
                '}';
    }
}
