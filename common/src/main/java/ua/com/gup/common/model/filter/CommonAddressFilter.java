package ua.com.gup.common.model.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Address filter model")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommonAddressFilter {

    @ApiModelProperty("Country codes with ',' delimiter")
    private String countries;

    @ApiModelProperty("Regions codes with ',' delimiter")
    private String regions;

    @ApiModelProperty("Area codes with ',' delimiter")
    private String districts;

    @ApiModelProperty("City codes with ',' delimiter")
    private String cities;


}
