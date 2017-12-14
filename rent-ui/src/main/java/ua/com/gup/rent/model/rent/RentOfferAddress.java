package ua.com.gup.rent.model.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel(description = "RentOfferAddress Object")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferAddress {

    @ApiModelProperty(position = 10)
    private BigDecimal lat;

    @ApiModelProperty(position = 15)
    private BigDecimal lng;

    @ApiModelProperty(position = 20)
    private Integer level;

    @ApiModelProperty(position = 30)
    private RentOfferAddressArea country;

    @ApiModelProperty(position = 40)
    private RentOfferAddressArea region;

    @ApiModelProperty(position = 50)
    private RentOfferAddressArea district;

    @ApiModelProperty(position = 60)
    private RentOfferAddressArea council;

    @ApiModelProperty(position = 70)
    private RentOfferAddressArea city;

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
        return "RentOfferAddress{" +
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
