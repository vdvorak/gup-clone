package ua.com.gup.rent.service.dto.rent.offer;


import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.rent.model.rent.RentOfferAddressArea;

import java.io.Serializable;
import java.math.BigDecimal;

public class RentOfferAddressDTO implements Serializable{

    @ApiModelProperty(position = 10, example = "50")
    private BigDecimal lat;

    @ApiModelProperty(position = 20, example = "30")
    private BigDecimal lng;

    @ApiModelProperty(position = 30, example = "30")
    private Integer level;

    @ApiModelProperty(position = 40, value = "'code':'1','name':{'en': 'Ukraine'}")
    private RentOfferAddressArea country;

    @ApiModelProperty(position = 50, value ="'code':'2','name':{'en': 'Kyiv region'}" )
    private RentOfferAddressArea region;

    @ApiModelProperty(position = 60, value = "'code':'2','name':{'en': 'Kyiv district'}")
    private RentOfferAddressArea district;

    @ApiModelProperty(position = 70 ,value = "'code':'2','name':{'en': 'Kyiv council'}")
    private RentOfferAddressArea council;

    @ApiModelProperty(position = 80, value = "'code':'3','name':{'en': 'Kyiv city'}")
    private RentOfferAddressArea city;

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

    public RentOfferAddressArea getCountry() {
        return country;
    }

    public void setCountry(RentOfferAddressArea country) {
        this.country = country;
    }

    public RentOfferAddressArea getRegion() {
        return region;
    }

    public void setRegion(RentOfferAddressArea region) {
        this.region = region;
    }

    public RentOfferAddressArea getDistrict() {
        return district;
    }

    public void setDistrict(RentOfferAddressArea district) {
        this.district = district;
    }

    public RentOfferAddressArea getCouncil() {
        return council;
    }

    public void setCouncil(RentOfferAddressArea council) {
        this.council = council;
    }

    public RentOfferAddressArea getCity() {
        return city;
    }

    public void setCity(RentOfferAddressArea city) {
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
        return "RentOfferAddressDTO{" +
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
