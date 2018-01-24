package ua.com.gup.rent.service.dto.rent.offer.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @ApiModelProperty(position = 10, required = true, example = "15")
    @JsonProperty(required = true)
    private BigDecimal businessDayCost;

    @ApiModelProperty(position = 20, required = true, example = "10.1")
    @JsonProperty(required = true)
    private BigDecimal weekendDayCost;

    @ApiModelProperty(position = 30, required = true, example = "0.12")
    @JsonProperty(required = true)
    private BigDecimal holidayDayCost;

    @ApiModelProperty(position = 40, example = "0.3")
    private BigDecimal prepaymentPercent;
}
