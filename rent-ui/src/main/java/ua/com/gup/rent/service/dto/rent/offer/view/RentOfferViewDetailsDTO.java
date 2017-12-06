package ua.com.gup.rent.service.dto.rent.offer.view;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.dto.offer.OfferAddressDTO;
import ua.com.gup.dto.offer.OfferContactInfoDTO;
import ua.com.gup.dto.offer.OfferLandsDTO;
import ua.com.gup.dto.offer.statistic.OfferStatisticDTO;
import ua.com.gup.mongo.model.category.attribute.OfferCategoryBoolAttributeValue;
import ua.com.gup.mongo.model.category.attribute.OfferCategoryMultiAttributeValue;
import ua.com.gup.mongo.model.category.attribute.OfferCategoryNumericAttributeValue;
import ua.com.gup.mongo.model.category.attribute.OfferCategorySingleAttributeValue;
import ua.com.gup.mongo.model.enumeration.OfferStatus;
import ua.com.gup.rent.model.enumeration.RentOfferStatus;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferAddressDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferContactInfoDTO;
import ua.com.gup.rent.service.dto.rent.offer.RentOfferLandsDTO;
import ua.com.gup.rent.service.dto.rent.offer.statistic.RentOfferStatisticDTO;

import java.util.LinkedHashMap;

public class RentOfferViewDetailsDTO extends RentOfferViewBaseDTO {

    @ApiModelProperty(position = 6)
    private RentOfferStatus status;

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
    private LinkedHashMap<String, OfferCategoryMultiAttributeValue> multiAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 120)
    private LinkedHashMap<String, OfferCategoryNumericAttributeValue> numAttrs = new LinkedHashMap<>();

    @ApiModelProperty(position = 130)
    private LinkedHashMap<String, OfferCategoryBoolAttributeValue> boolAttrs = new LinkedHashMap<>();

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public OfferAddressDTO getAddress() {
        return address;
    }

    public void setAddress(OfferAddressDTO address) {
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
