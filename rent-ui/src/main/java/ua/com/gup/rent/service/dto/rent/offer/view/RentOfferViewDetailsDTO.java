package ua.com.gup.rent.service.dto.rent.offer.view;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryBoolAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryMultiAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategoryNumericAttributeValue;
import ua.com.gup.rent.model.rent.category.attribute.RentOfferCategorySingleAttributeValue;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferAddressDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferContactInfoDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferLandsDTO;
import ua.com.gup.rent.service.dto.rent.offer.statistic.RentOfferStatisticDTO;

import java.util.LinkedHashMap;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferViewDetailsDTO extends RentOfferViewBaseDTO {

    @ApiModelProperty(position = 6)
    private CommonStatus status;

    @ApiModelProperty(position = 60)
    private RentOfferAddressDTO address;

    @ApiModelProperty(position = 100, example = "oaWMAKukXGE")
    private String youtubeVideoId;

    @ApiModelProperty(position = 105)
    private RentOfferContactInfoDTO contactInfo;

    @ApiModelProperty(position = 150)
    private RentOfferLandsDTO lands;

    @ApiModelProperty(position = 160)
    @JsonProperty(value = "statistic")
    private RentOfferStatisticDTO offerStatistic;

    @ApiModelProperty(position = 110)
    private LinkedHashMap<String, RentOfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 115)
    private LinkedHashMap<String, RentOfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 120)
    private LinkedHashMap<String, RentOfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 130)
    private LinkedHashMap<String, RentOfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();
}
