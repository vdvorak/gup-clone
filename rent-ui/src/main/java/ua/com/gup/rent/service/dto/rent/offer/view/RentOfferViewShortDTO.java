package ua.com.gup.rent.service.dto.rent.offer.view;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.dto.CommonAddressDTO;
import ua.com.gup.common.model.attribute.OfferCategoryBoolAttributeValue;
import ua.com.gup.common.model.attribute.OfferCategoryMultiAttributeValue;
import ua.com.gup.common.model.attribute.OfferCategoryNumericAttributeValue;
import ua.com.gup.common.model.attribute.OfferCategorySingleAttributeValue;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferContactInfoDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferLandsDTO;
import ua.com.gup.rent.service.dto.rent.offer.statistic.RentOfferStatisticDTO;

import java.util.LinkedHashMap;

@ApiModel
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferViewShortDTO extends RentOfferViewBaseDTO {

    @ApiModelProperty(position = 200)
    private CommonStatus status;

    @ApiModelProperty(position = 210)
    private CommonAddressDTO address;

    @ApiModelProperty(position = 220, example = "oaWMAKukXGE")
    private String youtubeVideoId;

    @ApiModelProperty(position = 230)
    private RentOfferContactInfoDTO contactInfo;

    @ApiModelProperty(position = 240)
    private RentOfferLandsDTO lands;

    @ApiModelProperty(position = 250)
    @JsonProperty(value = "statistic")
    private RentOfferStatisticDTO offerStatistic;

    @ApiModelProperty(position = 260)
    private LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 270)
    private LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 280)
    private LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 290)
    private LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();
}
