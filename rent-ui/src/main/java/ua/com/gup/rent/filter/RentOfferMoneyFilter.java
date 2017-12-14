package ua.com.gup.rent.filter;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonCurrency;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Money filter model")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferMoneyFilter {

    @ApiModelProperty("Currency")
    @NotNull
    private CommonCurrency currency;

    @ApiModelProperty("From")
    private Double from;

    @ApiModelProperty("To")
    private Double to;

}
