package ua.com.gup.common.model.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "Offer filter model")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class CommonOfferFilter implements Serializable {

    private static final long serialVersionUID = 1232825578694716871L;

    @ApiModelProperty(value = "String for full text search", position = 0)
    private String query;

    @ApiModelProperty(value = "Field form object Profile", position = 5)
    private CommonAuthorFilter authorFilter;

    @ApiModelProperty(value = "Filter for address", position = 30)
    private CommonAddressFilter address;

    @ApiModelProperty(value = "Coordinates for address", position = 30)
    private CommonCoordinatesFilter coordinates;

    @ApiModelProperty(value = "Filter for price", position = 40)
    private MoneyFilter price;

    @ApiModelProperty(value = "Filters for attributes", position = 50)
    private List<CommonAttributeFilter> attrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for attributes", position = 55)
    private List<CommonAttributeFilter> multiAttrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for numeric attributes", position = 60)
    private List<NumericAttributeFilter> numAttrs = new ArrayList<>();

    @ApiModelProperty(value = "Filters for boolean attributes", position = 70)
    private List<BooleanAttributeFilter> boolAttrs = new ArrayList<>();

    @ApiModelProperty(value = "Seo url's with ',' delimiter", position = 80)
    private String[] seoUrls;

    @ApiModelProperty("Date from inclusive")
    private String createdDate;
}