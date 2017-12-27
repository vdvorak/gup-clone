package ua.com.gup.rent.service.dto.rent.offer;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCategoryCountDTO {

    @ApiModelProperty(position = 10, example = "1")
    private int code;

    @ApiModelProperty(position = 20, example = "price")
    private String key;

    @ApiModelProperty(position = 30, example = "{'price':'price'}")
    private Map<String, String> title = new HashMap<>();

    @ApiModelProperty(position = 40, example = "1")
    private int count;


}
