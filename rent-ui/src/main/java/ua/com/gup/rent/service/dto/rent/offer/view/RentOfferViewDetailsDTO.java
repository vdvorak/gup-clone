package ua.com.gup.rent.service.dto.rent.offer.view;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.dto.CommonAddressDTO;
import ua.com.gup.common.model.category.attribute.OfferCategoryBoolAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategoryMultiAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategoryNumericAttributeValue;
import ua.com.gup.common.model.category.attribute.OfferCategorySingleAttributeValue;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferContactInfoDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferLandsDTO;
import ua.com.gup.rent.service.dto.rent.offer.calendar.RentOfferCalendarDTO;
import ua.com.gup.rent.service.dto.rent.offer.statistic.RentOfferStatisticDTO;

import java.util.LinkedHashMap;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferViewDetailsDTO extends RentOfferViewBaseDTO {

    @ApiModelProperty(position = 6)
    private CommonStatus status;

    @ApiModelProperty(position = 60)
    private CommonAddressDTO address;

    @ApiModelProperty(position = 100, example = "oaWMAKukXGE")
    private String youtubeVideoId;

    @ApiModelProperty(position = 105)
    private RentOfferContactInfoDTO contactInfo;

    @ApiModelProperty(position = 150)
    private RentOfferLandsDTO lands;

    @ApiModelProperty(position = 160)
    @JsonProperty(value = "statistic")
    private RentOfferStatisticDTO offerStatistic;

    @ApiModelProperty(position = 170, notes = "rent offers count")
    private Integer count;

    @ApiModelProperty(notes = "rent calendar")
    @JsonProperty(value = "calendar")
    private RentOfferCalendarDTO rentOfferCalendarDTO;

    @ApiModelProperty(position = 110)
    private LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 115)
    private LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 120)
    private LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 130)
    private LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();
}
