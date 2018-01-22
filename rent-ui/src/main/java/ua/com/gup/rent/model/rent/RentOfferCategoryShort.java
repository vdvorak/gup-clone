package ua.com.gup.rent.model.rent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.rent.model.mongo.category.RentOfferCategory;

import java.util.HashMap;
import java.util.Map;
@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCategoryShort {

    @ApiModelProperty(example = "code")
    private int code;
    @ApiModelProperty(example = "key")
    private String key;
    @ApiModelProperty(example = "title")
    private Map<String, String> title = new HashMap<>();


    public RentOfferCategoryShort() {
    }

    public RentOfferCategoryShort(RentOfferCategory category) {
        this.code = category.getCode();
        this.key = category.getKey();
        this.title = category.getTitle();

    }

}
