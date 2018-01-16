package ua.com.gup.dto.offer.view;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.common.dto.CommonAddressDTO;
import ua.com.gup.common.model.attribute.OfferCategoryBoolAttributeValue;
import ua.com.gup.common.model.attribute.OfferCategoryMultiAttributeValue;
import ua.com.gup.common.model.attribute.OfferCategoryNumericAttributeValue;
import ua.com.gup.common.model.attribute.OfferCategorySingleAttributeValue;
import ua.com.gup.common.model.enumeration.CommonStatus;
import ua.com.gup.dto.offer.OfferContactInfoDTO;
import ua.com.gup.dto.offer.OfferLandsDTO;
import ua.com.gup.dto.offer.statistic.OfferStatisticDTO;

import java.util.LinkedHashMap;

public class OfferViewDetailsDTO extends OfferViewBaseDTO {

    @ApiModelProperty(position = 6)
    private CommonStatus status;

    @ApiModelProperty(position = 60)
    private CommonAddressDTO address;

    @ApiModelProperty(position = 100, example = "oaWMAKukXGE")
    private String youtubeVideoId;

    @ApiModelProperty(position = 105)
    private OfferContactInfoDTO contactInfo;

    @ApiModelProperty(position = 150)
    private OfferLandsDTO lands;

    @ApiModelProperty(position = 160)
    @JsonProperty(value = "statistic")
    private OfferStatisticDTO offerStatistic;

    @ApiModelProperty(position = 110)
    private LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 115)
    private LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 120)
    private LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 130)
    private LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public CommonAddressDTO getAddress() {
        return address;
    }

    public void setAddress(CommonAddressDTO address) {
        this.address = address;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public OfferContactInfoDTO getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(OfferContactInfoDTO contactInfo) {
        this.contactInfo = contactInfo;
    }


    public OfferLandsDTO getLands() {
        return lands;
    }

    public void setLands(OfferLandsDTO lands) {
        this.lands = lands;
    }

    public OfferStatisticDTO getOfferStatistic() {
        return offerStatistic;
    }

    public void setOfferStatistic(OfferStatisticDTO offerStatistic) {
        this.offerStatistic = offerStatistic;
    }

    public LinkedHashMap<String, OfferCategorySingleAttributeValue> getAttrs() {
        return attrs;
    }

    public void setAttrs(LinkedHashMap<String, OfferCategorySingleAttributeValue> attrs) {
        this.attrs = attrs;
    }

    public LinkedHashMap<String, OfferCategoryMultiAttributeValue> getMultiAttrs() {
        return multiAttrs;
    }

    public void setMultiAttrs(LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs) {
        this.multiAttrs = multiAttrs;
    }

    public LinkedHashMap<String, OfferCategoryNumericAttributeValue> getNumAttrs() {
        return numAttrs;
    }

    public void setNumAttrs(LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs) {
        this.numAttrs = numAttrs;
    }

    public LinkedHashMap<String, OfferCategoryBoolAttributeValue> getBoolAttrs() {
        return boolAttrs;
    }

    public void setBoolAttrs(LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs) {
        this.boolAttrs = boolAttrs;
    }
}
