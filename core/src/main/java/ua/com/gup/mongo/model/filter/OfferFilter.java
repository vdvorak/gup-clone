package ua.com.gup.mongo.model.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.filter.CommonOfferFilter;

@ApiModel(description = "Offer filter model")
@Data
public class OfferFilter extends CommonOfferFilter {

    @ApiModelProperty(value = "Categories with ',' delimiter", position = 10)
    private Integer[] categories;
}
