package ua.com.gup.service.dto.offer;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * DTO for creation the Offer entity.
 */
@ApiModel(description = "DTO for Offer")
public class OfferCreateDTO implements Serializable {

    @ApiModelProperty(position = 30)
    private Integer category;

    @ApiModelProperty(position = 40, example = "title")
    private String title;

    @ApiModelProperty(position = 50, example = "description")
    private String description;

    @ApiModelProperty(position = 60)
    private OfferAddressDTO address;

    @ApiModelProperty(position = 70)
    private OfferPriceDTO price;

    @ApiModelProperty(position = 80)
    private LinkedHashSet<OfferImageDTO> images;

    @ApiModelProperty(position = 100, example = "oaWMAKukXGE")
    private String youtubeVideoId;

    @ApiModelProperty(position = 105)
    private OfferContactInfoDTO contactInfo;

    @ApiModelProperty(position = 110, value = "{\"color\": \"Black\", \"size\":\"XL\"}")
    private Map<String, String> attrs = new HashMap<>();

    @ApiModelProperty(position = 115, value = "{\"deliveryType\": \"NOVAPOSHTA,INTIME\"}")
    private Map<String, String> multiAttrs = new HashMap<>();

    @ApiModelProperty(position = 120, value = "{\"year\": 2011, \"volume_sm3\":1500}")
    private Map<String, BigDecimal> numAttrs = new HashMap<>();

    @ApiModelProperty(position = 130, value = "{\"used\": true, \"mediator\":false}")
    private Map<String, Boolean> boolAttrs = new HashMap<>();

    @ApiModelProperty(position = 140)
    private OfferLandsDTO lands;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OfferAddressDTO getAddress() {
        return address;
    }

    public void setAddress(OfferAddressDTO address) {
        this.address = address;
    }

    public OfferPriceDTO getPrice() {
        return price;
    }

    public void setPrice(OfferPriceDTO price) {
        this.price = price;
    }

    public LinkedHashSet<OfferImageDTO> getImages() {
        return images;
    }

    public void setImages(LinkedHashSet<OfferImageDTO> images) {
        this.images = images;
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

    public Map<String, String> getAttrs() {
        return attrs;
    }

    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }

    public Map<String, String> getMultiAttrs() {
        return multiAttrs;
    }

    public void setMultiAttrs(Map<String, String> multiAttrs) {
        this.multiAttrs = multiAttrs;
    }

    public Map<String, BigDecimal> getNumAttrs() {
        return numAttrs;
    }

    public void setNumAttrs(Map<String, BigDecimal> numAttrs) {
        this.numAttrs = numAttrs;
    }

    public Map<String, Boolean> getBoolAttrs() {
        return boolAttrs;
    }

    public void setBoolAttrs(Map<String, Boolean> boolAttrs) {
        this.boolAttrs = boolAttrs;
    }

    public OfferLandsDTO getLands() {
        return lands;
    }

    public void setLands(OfferLandsDTO lands) {
        this.lands = lands;
    }
}
