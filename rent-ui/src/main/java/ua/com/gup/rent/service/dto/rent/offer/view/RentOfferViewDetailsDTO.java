package ua.com.gup.rent.service.dto.rent.offer.view;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public RentOfferAddressDTO getAddress() {
        return address;
    }

    public void setAddress(RentOfferAddressDTO address) {
        this.address = address;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public RentOfferContactInfoDTO getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(RentOfferContactInfoDTO contactInfo) {
        this.contactInfo = contactInfo;
    }

    public RentOfferLandsDTO getLands() {
        return lands;
    }

    public void setLands(RentOfferLandsDTO lands) {
        this.lands = lands;
    }

    public RentOfferStatisticDTO getOfferStatistic() {
        return offerStatistic;
    }

    public void setOfferStatistic(RentOfferStatisticDTO offerStatistic) {
        this.offerStatistic = offerStatistic;
    }

    public LinkedHashMap<String, RentOfferCategorySingleAttributeValue> getAttrs() {
        return attrs;
    }

    public void setAttrs(LinkedHashMap<String, RentOfferCategorySingleAttributeValue> attrs) {
        this.attrs = attrs;
    }

    public LinkedHashMap<String, RentOfferCategoryMultiAttributeValue> getMultiAttrs() {
        return multiAttrs;
    }

    public void setMultiAttrs(LinkedHashMap<String, RentOfferCategoryMultiAttributeValue> multiAttrs) {
        this.multiAttrs = multiAttrs;
    }

    public LinkedHashMap<String, RentOfferCategoryNumericAttributeValue> getNumAttrs() {
        return numAttrs;
    }

    public void setNumAttrs(LinkedHashMap<String, RentOfferCategoryNumericAttributeValue> numAttrs) {
        this.numAttrs = numAttrs;
    }

    public LinkedHashMap<String, RentOfferCategoryBoolAttributeValue> getBoolAttrs() {
        return boolAttrs;
    }

    public void setBoolAttrs(LinkedHashMap<String, RentOfferCategoryBoolAttributeValue> boolAttrs) {
        this.boolAttrs = boolAttrs;
    }
}
