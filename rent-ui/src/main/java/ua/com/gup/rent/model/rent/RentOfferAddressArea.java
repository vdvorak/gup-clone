package ua.com.gup.rent.model.rent;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@ApiModel(description = "RentOfferAddress attribute ")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferAddressArea {

    @ApiModelProperty(position = 10, example = "code")
    private String code;
    @ApiModelProperty(position = 20,example = "4")
    private Integer level;
    @ApiModelProperty(position = 30, value = "{'en': 'Ukraine'}")
    private Map<String, String> name;
    @ApiModelProperty(position = 40, value = "{'en': 'Country'}")
    private Map<String, String> type;

}
