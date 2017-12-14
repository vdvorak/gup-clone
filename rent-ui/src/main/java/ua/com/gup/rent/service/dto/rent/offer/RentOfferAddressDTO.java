package ua.com.gup.rent.service.dto.rent.offer;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.rent.model.rent.RentOfferAddressArea;

import java.io.Serializable;
import java.math.BigDecimal;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
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

}
