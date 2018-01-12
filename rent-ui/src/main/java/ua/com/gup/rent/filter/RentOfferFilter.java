package ua.com.gup.rent.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import ua.com.gup.common.model.filter.CommonOfferFilter;

@ApiModel(description = "Rent Offer filter model")
@Getter
@Setter
public class RentOfferFilter extends CommonOfferFilter {

    @ApiModelProperty(value = "Only one category (level not important)", position = 10)
    private Integer category;

}
