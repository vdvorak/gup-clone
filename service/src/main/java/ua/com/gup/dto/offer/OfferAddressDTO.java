package ua.com.gup.dto.offer;


import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.mongo.model.offer.AddressArea;

import java.io.Serializable;
import java.math.BigDecimal;

public class OfferAddressDTO implements Serializable{

    @ApiModelProperty(position = 10, example = "50")
    private BigDecimal lat;

    @ApiModelProperty(position = 20, example = "30")
    private BigDecimal lng;

    @ApiModelProperty(position = 30, example = "30")
    private Integer level;

    @ApiModelProperty(position = 40, value = "'code':'1','name':{'en': 'Ukraine'}")
    private AddressArea country;

    @ApiModelProperty(position = 50, value ="'code':'2','name':{'en': 'Kyiv region'}" )
    private AddressArea region;

    @ApiModelProperty(position = 60, value = "'code':'2','name':{'en': 'Kyiv district'}")
    private AddressArea district;

    @ApiModelProperty(position = 70 ,value = "'code':'2','name':{'en': 'Kyiv council'}")
    private AddressArea council;

    @ApiModelProperty(position = 80, value = "'code':'3','name':{'en': 'Kyiv city'}")
    private AddressArea city;

    @ApiModelProperty(position = 90,value = "street PeryAveny 23")
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
        return "OfferAddressDTO{" +
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
