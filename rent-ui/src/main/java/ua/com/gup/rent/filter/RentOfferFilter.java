package ua.com.gup.rent.filter;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.filter.CommonOfferFilter;

@Data
@ApiModel(description = "Rent Offer filter model")
public class RentOfferFilter extends CommonOfferFilter {

    @ApiModelProperty(value = "Only one category (level not important)", position = 10)
    private Integer category;

    private String dtRentStart;

    private String dtRentEnd;

    @ApiModelProperty(value = "rent objects count")
    private Integer count;
}
