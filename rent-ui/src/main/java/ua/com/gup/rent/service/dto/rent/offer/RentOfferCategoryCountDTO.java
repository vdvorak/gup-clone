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
    @ApiModelProperty(example = "category_code")
    private int code;
    private String key;
    @ApiModelProperty(example = "category_title")
    private Map<String, String> title = new HashMap<>();
    @ApiModelProperty(example = "count")
    private int count;


}
