package ua.com.gup.common.model.mongo.address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(description = "Address Object")
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
