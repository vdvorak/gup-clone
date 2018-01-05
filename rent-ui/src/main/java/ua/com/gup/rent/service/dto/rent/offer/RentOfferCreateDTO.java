package ua.com.gup.rent.service.dto.rent.offer;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import ua.com.gup.common.dto.CommonAddressDTO;
import ua.com.gup.rent.service.dto.rent.offer.calendar.RentOfferCalendarDTO;
import ua.com.gup.rent.service.dto.rent.offer.price.RentOfferPriceDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * DTO for creation the RentOffer Offer entity.
 */
@ApiModel(description = "DTO for RentOffer Offer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferCreateDTO extends RentOfferDTO {

    @ApiModelProperty(position = 30)
    private Integer category;

    @ApiModelProperty(position = 40)
    private CommonAddressDTO address;

    @ApiModelProperty(position = 60, example = "ret price")
    private RentOfferPriceDTO price;

    @ApiModelProperty(position = 80, example = "rent calendar")
    private RentOfferCalendarDTO calendar;

    @ApiModelProperty(position = 100, example = "oaWMAKukXGE")
    private String youtubeVideoId;

    @ApiModelProperty(position = 105)
    private RentOfferContactInfoDTO contactInfo;

    @ApiModelProperty(position = 110, value = "{'color': 'Black', 'size':'XL'}")
    private Map<String, String> attrs = new HashMap<>();

    @ApiModelProperty(position = 115, value = "{'deliveryType': 'NOVAPOSHTA,INTIME'}")
    private Map<String, String> multiAttrs = new HashMap<>();

    @ApiModelProperty(position = 120, value = "{'year': 2011, 'volume_sm3':1500}")
    private Map<String, BigDecimal> numAttrs = new HashMap<>();

    @ApiModelProperty(position = 130, value = "{'used': true, 'mediator':false}")
    private Map<String, Boolean> boolAttrs = new HashMap<>();

    @ApiModelProperty(position = 140)
    private RentOfferLandsDTO lands;

    @ApiModelProperty(position = 150)
    private RentOfferSettingsDTO settings;

    @ApiModelProperty(position = 170, example = "1")
    private Integer count;
}