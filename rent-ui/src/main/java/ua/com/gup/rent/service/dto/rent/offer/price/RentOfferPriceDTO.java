package ua.com.gup.rent.service.dto.rent.offer.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author viktor dvorak
 **/
@ApiModel(description = "price")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferPriceDTO implements Serializable {

    @ApiModelProperty(position = 10, example = "15")
    private BigDecimal businessDayCost;

    @ApiModelProperty(position = 20, example = "10.1")
    private BigDecimal weekendDayCost;

    @ApiModelProperty(position = 30, example = "0.12")
    private BigDecimal holidayDayCost;
    
    @ApiModelProperty(position = 40, example = "0.3")
    private BigDecimal prepaymentPercent;
}
